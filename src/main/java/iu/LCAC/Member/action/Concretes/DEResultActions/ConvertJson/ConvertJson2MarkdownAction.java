package iu.LCAC.Member.action.Concretes.DEResultActions.ConvertJson;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.SI.SI_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.StatusPanel.StatusPanelHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConvertJson2MarkdownAction extends AbstActionMember {

    private final String scriptFilePathStr = "settings/sh/convertJson2Markdown.sh";

    public ConvertJson2MarkdownAction(String action_name, String short_name) {
        super(action_name, short_name);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println();
        System.out.println("---- perform() in " + this.getClass() + " was called. ----");

        String[] strings_passed_from_action_event = getActionCommandAndArgs(action_event, false);

        // Prepare the components you want to integrate
        StatusPanelHolder statusPanelHolder = (StatusPanelHolder) this.cholderMediator.getInstanceOfAMember("status_panel_holder");
        SI_SubTabsHolder si_subTabsHolder = (SI_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_SI");

        String[] argsToExecute;

        // Core
        if (strings_passed_from_action_event.length > 1) {
            argsToExecute = strings_passed_from_action_event;
        } else {
            //System.out.println("引数が指定されていないので、SI Section の 1番目の JSON をターゲットとして変換をかけます。");
            String jsonName = si_subTabsHolder.getTheFirstJsonPanel().getJsonName();
            //System.out.println("jsonName: " + jsonName);

            argsToExecute = new String[]{strings_passed_from_action_event[0], "DE", jsonName};

        }
        String commandLine = buildCommandLine(argsToExecute);
        //System.out.println("cmd_and_args_in_a_line: " + commandLine);
        statusPanelHolder.showAMessageForWhile("'" + commandLine + "' was called.", 10000);

        if (!checkBashScriptExistence(true)) {
            return;
        }
        onRun(argsToExecute);
    }

    private String buildCommandLine(String[] args) {
        //StringBuilder sb = new StringBuilder(args[0]);
        StringBuilder sb = new StringBuilder(scriptFilePathStr);
        for (int i = 1; i < args.length; i++) {
            sb.append(" ").append(args[i]);
        }
        return sb.toString();
    }

    public boolean checkBashScriptExistence(boolean showPopup) {
        //scriptFilePathStr に指定されたbashファイルの存在を確認する。
        java.io.File scriptFile = new java.io.File(scriptFilePathStr);
        boolean exists = scriptFile.exists() && scriptFile.isFile();

        // showPopup が trueならポップアップを表示して 指定のbashファイルが存在しないことを警告
        if (showPopup && !exists) {
            JOptionPane.showMessageDialog(
                    null,
                    "指定されたBashスクリプトが見つかりません。\n" +
                            "パス: " + scriptFilePathStr,
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return exists;
    }

    @Override
    public void setCHolderMediator(CHolderMediator cHolderMediator) {
        this.cholderMediator = cHolderMediator;
    }

    @Override
    public void setActionMediator(ActionMediator actionMediator) {
        this.actionMediator = actionMediator;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void doWorkAsMember() {
    }


    private ScriptWorker worker; // SwingWorker への参照

    private void onRun(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(" * arg[" + i + "]: " + args[i]);
        }
        System.out.println();

        if (worker != null && !worker.isDone()) return; // 二重起動防止

        worker = new ScriptWorker(scriptFilePathStr, args);
        worker.execute();
    }

    /**
     * Bash スクリプトをバックグラウンドで実行し、出力を publish/process で逐次表示する
     */
    private static class ScriptWorker extends SwingWorker<Integer, String> {
        private final String scriptPath;
        private final String[] args;
        private Process process;
        private volatile boolean destroyed = false;

        ScriptWorker(String scriptPath, String[] args) {
            this.scriptPath = scriptPath;
            this.args = args;
        }

        @Override
        protected Integer doInBackground() {
            // コマンドリストを構築: /bin/bash, scriptPath, args[1], args[2], ...
            List<String> command = new ArrayList<>();
            command.add("/bin/bash");
            command.add(scriptPath);
            // args[0]はコマンド名なので、args[1]以降をスクリプトへの引数として追加
            for (int i = 1; i < args.length; i++) {
                command.add(args[i]);
            }

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true); // stderr を stdout に統合

            try {
                process = pb.start();

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (isCancelled()) {
                            // プロセスを安全に停止
                            destroyed = true;
                            process.destroy();
                            break;
                        }
                        publish(line);
                    }
                }

                if (!destroyed) {
                    // 終了コードを返す（waitFor は短時間で戻る想定）
                    return process.waitFor();
                }
            } catch (IOException | InterruptedException ex) {
                publish("[ERROR] " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
                // 割り込みフラグ復元（慣例）
                if (ex instanceof InterruptedException) Thread.currentThread().interrupt();
            } finally {
                if (process != null && process.isAlive()) {
                    process.destroy();
                }
            }
            return -1; // エラー or 中止時
        }

        @Override
        protected void process(List<String> chunks) {
            // 出力をコンソールに表示
            for (String s : chunks) {
                System.out.println(s);
            }
        }

        @Override
        protected void done() {
            try {
                if (isCancelled()) {
                    System.out.println("[INFO] 実行を中止しました。");
                } else {
                    Integer exit = get(); // doInBackground の戻り値
                    System.out.println("[INFO] スクリプト終了コード: " + exit);
                }
            } catch (Exception ex) {
                System.err.println("[ERROR] " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
    }

}
