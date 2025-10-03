package iu.LCAC.Member.action.Concretes.Sample.run_a_bash_script;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.Sample.TextField.TextFieldPanelHolder;
import iu.LCAC.Utils.PropertyManager_v5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RunABashScriptAction extends AbstActionMember {

    static final String SettingPropertyFilePath =
            "./settings/ActionControlledComponentFramework/settings.prop";

    public RunABashScriptAction(String action_name, String short_name) {
        super(action_name, short_name);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        String[] cmd_and_args = getActionCommandAndArgs(action_event, false);

        // Load Properties
        PropertyManager_v5 prop_manager = createPropertyManager(SettingPropertyFilePath);

        // Preparation of Components
        TextFieldPanelHolder text_field_panel_holder =
                (TextFieldPanelHolder) this.cholderMediator.getInstanceOfAMember("text_field_panel_holder");

        // Core
            if (cmd_and_args.length > 1) {
                onRun(cmd_and_args);
            }else{
                onRun(new String[0]);
            }
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


    private final JTextField scriptPathField = new JTextField("./show_popup.sh");
    private final JButton runButton = new JButton("実行");
    private final JButton cancelButton = new JButton("中止");
    private final JTextArea outputArea = new JTextArea(16, 60);
    private final JProgressBar progressBar = new JProgressBar();
    private ScriptWorker worker; // SwingWorker への参照

    private void onRun(String[] args) {

        if (worker != null && !worker.isDone()) return; // 二重起動防止

        outputArea.setText("");
        progressBar.setIndeterminate(true);
        progressBar.setString("実行中…");
        runButton.setEnabled(false);
        cancelButton.setEnabled(true);

        //String script = scriptPathField.getText().trim();
        String script = "src/main/java/iu/LCAC/Member/action/Concretes/Sample/run_a_bash_script/show_popup.sh";
        worker = new ScriptWorker(script,  args);
        worker.execute();
    }

    private void onCancel(ActionEvent e) {
        if (worker != null && !worker.isDone()) {
            worker.cancel(true); // キャンセル要求
            cancelButton.setEnabled(false);
        }
    }

    /** Bash スクリプトをバックグラウンドで実行し、出力を publish/process で逐次表示する */
    private class ScriptWorker extends SwingWorker<Integer, String> {
        private final String scriptPath;
        private Process process;
        private volatile boolean destroyed = false;

        String[] args;

        ScriptWorker(String scriptPath, String[] args) {
            this.scriptPath = scriptPath;
            this.args = args;
        }

        @Override
        protected Integer doInBackground() {
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", scriptPath, args[1]);
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
                            process.destroy();            // ソフト停止
                            // 応答がない場合は強制終了も検討: process.destroyForcibly();
                            break;
                        }
                        publish(line); // EDT 外 → バッファ → process() で EDT へ
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
            // ここは EDT 上。安全に UI を更新できる
            for (String s : chunks) {
                outputArea.append(s);
                outputArea.append("\n");
            }
            // 任意の進捗表示（行数で雰囲気だけ示す）
            setProgress(Math.min(99, Math.max(0, outputArea.getLineCount() % 100)));
        }

        @Override
        protected void done() {
            // ここも EDT 上
            progressBar.setIndeterminate(false);
            runButton.setEnabled(true);
            cancelButton.setEnabled(false);

            try {
                if (isCancelled()) {
                    progressBar.setValue(0);
                    progressBar.setString("中止しました");
                    outputArea.append("[INFO] 実行を中止しました。\n");
                } else {
                    Integer exit = get(); // doInBackground の戻り値
                    progressBar.setValue(100);
                    progressBar.setString("完了 (exit=" + exit + ")");
                    outputArea.append("[INFO] スクリプト終了コード: " + exit + "\n");
                }
            } catch (Exception ex) {
                progressBar.setValue(0);
                progressBar.setString("失敗");
                outputArea.append("[ERROR] " + ex.getClass().getSimpleName() + ": " + ex.getMessage() + "\n");
            }
        }
    }

}
