package iu.LCAC.Utils.JsonManagerWithConflictSafe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

/**
 * JsonManagerWithConflictSafe
 * ----------------------
 *
 * 目的: 保存時に、読み取り時（ロード時）とファイルの更新タイムスタンプが異なる場合、
 * ユーザに「キャンセル / 上書き / リロード / 別名保存」を選択させる。
 * この版では **ファイルロック機構は使用しない**（要求により削除）。
 * <p>
 * 動作:
 * - オープン時に内容を読み込み、ロード時の mtime とハッシュを記録。
 * - 保存時にディスク上の mtime を再確認し、異なれば競合ダイアログを表示。
 * - キャンセル: 何もしない
 * - 上書き: ディスクの変更を無視して現在のテキストを保存
 * - リロード: ディスク上の内容でエディタを置き換え（ローカル編集は破棄）
 * - 別名保存: 現在のテキストを別ファイルとして保存し、以降はそのファイルを編集中として扱う
 * - showLoadedContent() でロードされているファイルの内容を確認できる。
 * <p>
 *
 * 2025.10.14 ConflictSafeDitorDemo.javaとJsonManager.javaを融合させて作成。
 * https://chatgpt.com/c/68e796fa-5aa8-8322-b3dc-2a26e5861832 を参考に作成。
 *
 */
public class JsonManagerWithConflictSafe extends JsonManager {

    private final JFrame frame = new JFrame("Over View of Json");

    //GUI
    private final Intrfc_CompWithReloadFunc compWithReloadFunc;
    private final JTextArea textArea = new JTextArea();
    private final JButton openBtn = new JButton("Open");
    private final JButton saveBtn = new JButton("Save");
    private final JButton saveAsBtn = new JButton("Save As");
    private final JLabel statusLbl = new JLabel("No file");

    private  Path currentPath = null;
    private volatile long loadedMtime = -1L;
    private volatile String loadedHash = null;


    public JsonManagerWithConflictSafe(String json_file_path, Intrfc_CompWithReloadFunc compWithReloadFunc) {
        this(new File(json_file_path), compWithReloadFunc);
    }

    public JsonManagerWithConflictSafe(File jsonFile, Intrfc_CompWithReloadFunc compWithReloadFunc) {
        super(jsonFile);
        this.compWithReloadFunc = compWithReloadFunc;
        openPath(jsonFile.toPath());

        /*
        currentPath = jsonFile.toPath();
        String content = null;
        try {
            content = Files.readString(currentPath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            content = "Error";
            throw new RuntimeException(e);
        }
        textArea.setText(content);
        */

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(openBtn);
        top.add(saveBtn);
        top.add(saveAsBtn);
        top.add(new JSeparator(SwingConstants.VERTICAL));
        top.add(statusLbl);

        frame.add(top, BorderLayout.NORTH);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        saveBtn.setEnabled(false);
        saveAsBtn.setEnabled(false);
        textArea.setEditable(false);

        // Actions
        openBtn.addActionListener(e -> doOpen());
        saveBtn.addActionListener(e -> doSave(false));
        saveAsBtn.addActionListener(e -> doSaveAs());


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                //System.exit(0);
            }
        });


        // Open Json
        openPath(jsonFile.toPath());

    }

    // ---- File operations ----
    private void doOpen() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Text files", "txt", "md", "log", "csv", "java", "json"));
        if (fc.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
            Path p = fc.getSelectedFile().toPath();
            openPath(p);
        }
    }

    private void openPath(Path p) {
        try {
            if (!Files.exists(p)) {
                // 新規作成も許容
                Files.createFile(p);
            }
            String content = Files.readString(p, StandardCharsets.UTF_8);
            textArea.setText(content);
            loadedMtime = Files.getLastModifiedTime(p).toMillis();
            System.out.println("loadeMtime: " + loadedMtime);
            loadedHash = Hashes.sha256(content);

            currentPath = p;
            textArea.setEditable(true);
            saveBtn.setEnabled(true);
            saveAsBtn.setEnabled(true);
            setStatus("Opened: " + p.toAbsolutePath() + " (mtime=" + Instant.ofEpochMilli(loadedMtime) + ")");
        } catch (IOException ex) {
            showError("Open failed: " + ex.getMessage());
        }
    }


    @Override
    public boolean writeoutJson(){
        //super.writeoutJson();
       return doSave(false);
    }


    /**
     * Save current text. If forceOverwrite==false and disk mtime differs from loadedMtime,
     * show conflict dialog (Cancel/Overwrite/Reload/Save As).
     */
    private boolean doSave(boolean forceOverwrite) {
        //System.out.println("doSave");
        if (currentPath == null){
            System.err.println("currentPath is null.");
            return false;
        }
        try {
            long diskMtime = Files.getLastModifiedTime(currentPath).toMillis();
            //System.out.println("diskMtime: " + diskMtime);

            //String currentText = textArea.getText();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String currentText = gson.toJson(getJsonObject());
            textArea.setText(currentText);

            if (!forceOverwrite && loadedMtime != -1 && diskMtime != loadedMtime) {
                // Conflict
                int choice = showConflictDialog(currentPath, diskMtime, loadedMtime);
                switch (choice) {
                    case 0: // Cancel
                        //System.out.println("Cancel");
                        return false;
                    case 1: // Overwrite
                        //System.out.println("Overwrite");
                        writeText(currentPath, currentText);
                        afterSaveUpdate(currentPath, currentText);
                        return true;
                    case 2: // Reload
                        //System.out.println("Reload");
                        String reloadedContent = reloadFromDisk(); //textArea の内容が更新される
                        this.compWithReloadFunc.reload();
                        return false;
                    case 3: // Save As
                        //System.out.println("Save As");
                        return doSaveAs();

                    default:
                        //System.out.println("default");
                        return false;
                }
            }

            //System.out.println("No conflict or force overwrite");
            writeText(currentPath, currentText);
            afterSaveUpdate(currentPath, currentText);
            return true;
        } catch (IOException ex) {
            showError("Save failed: " + ex.getMessage());
            return false;
        }
    }

    private boolean doSaveAs() {
        if (currentPath == null && textArea.getText().isEmpty()) return false;
        JFileChooser fc = new JFileChooser();
        if (currentPath != null) fc.setSelectedFile(currentPath.toFile());
        if (fc.showSaveDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
            Path dest = fc.getSelectedFile().toPath();
            if (Files.exists(dest)) {
                int ans = JOptionPane.showConfirmDialog(this.frame,
                        "既に存在します。上書きしますか？" + dest.toAbsolutePath(),
                        "別名保存の確認", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (ans != JOptionPane.YES_OPTION) return false;
            }
            try {
                String text = textArea.getText();
                writeText(dest, text);
                currentPath = dest;
                afterSaveUpdate(dest, text);
                return true;
            } catch (IOException ex) {
                showError("Save As failed: " + ex.getMessage());
                return false;
            }
        }
        return false;
    }

    private String reloadFromDisk() {
        if (currentPath == null) return "";
        try {
            String disk = Files.readString(currentPath, StandardCharsets.UTF_8);
            textArea.setText(disk);
            loadedMtime = Files.getLastModifiedTime(currentPath).toMillis();
            loadedHash = Hashes.sha256(disk);
            setStatus("Reloaded from disk at " + Instant.ofEpochMilli(loadedMtime));
        } catch (IOException ex) {
            showError("Reload failed: " + ex.getMessage());
        }

        return textArea.getText();
    }

    private void writeText(Path p, String text) throws IOException {
        // 親ディレクトリ作成（必要なら）
        /*
        Path parent = p.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
        Files.writeString(p, text, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        */

      super.writeoutJson();


    }

    private void afterSaveUpdate(Path p, String text) throws IOException {
        loadedMtime = Files.getLastModifiedTime(p).toMillis();
        loadedHash = Hashes.sha256(text);
        setStatus("Saved at " + Instant.ofEpochMilli(loadedMtime) + " — " + p.toAbsolutePath());
    }

    private int showConflictDialog(Path p, long diskMtime, long myLoadedMtime) {
        String msg = "保存しようとしているファイルは、読み込み後に外部で更新されています。\n" +
                "  ファイル: " + p.toAbsolutePath() + "\n" +
                "  読み込み時刻: " + Instant.ofEpochMilli(myLoadedMtime) + "\n" +
                "  ディスク上更新時刻: " + Instant.ofEpochMilli(diskMtime) + "\n" +
                "  どうしますか？";
        String[] options = {"キャンセル", "上書き", "リロード", "別名保存"};
        return JOptionPane.showOptionDialog(this.frame, msg, "競合の検出",
        //return JOptionPane.showOptionDialog(null, msg, "競合の検出",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
    }

    private void setStatus(String s) {
        statusLbl.setText(s);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this.frame, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // ---- small hashing helper ----
    static class Hashes {
        static String sha256(String s) {
            try {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
                byte[] dig = md.digest(s.getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder(dig.length * 2);
                for (byte b : dig) sb.append(String.format("%02x", b));
                return sb.toString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private JFrame getFrame() {
        return this.frame;
    }

    public void showLoadedContent() {
        SwingUtilities.invokeLater(() -> this.getFrame().setVisible(true));
    }

    public static void main(String[] args) {

        Intrfc_CompWithReloadFunc compWithReloadFunc = new TestComponent();

        // 基本的な使い方
        JsonManagerWithConflictSafe jm = new JsonManagerWithConflictSafe(
                                                new File("/home/iu/Downloads/test.json"),
                                                compWithReloadFunc);

        System.out.println("Json読み込み");
        System.out.println("key1 = " + jm.getValue("test/key1"));
        System.out.println("key2 = " + jm.getValue("test/key2"));

        // 視覚的に確認用
        jm.showLoadedContent();

        System.out.println("15秒待機");
        System.out.println("（この時間中に外部プログラムから内容を書き換えてみてください）");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("書き込みテスト");
        jm.setValue("test/key1", "value1");
        jm.setValue("test/key2", "value2");
        jm.writeoutJson();




    }
}
