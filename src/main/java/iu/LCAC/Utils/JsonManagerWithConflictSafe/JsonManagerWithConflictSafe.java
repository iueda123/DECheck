package iu.LCAC.Utils.JsonManagerWithConflictSafe;

import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

/**
 * JsonManagerWithConflictSafe
 * ----------------------
 * <p>
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
 * <p>
 * 2025.10.14 ConflictSafeDitorDemo.javaとJsonManager.javaを融合させて作成。
 * https://chatgpt.com/c/68e796fa-5aa8-8322-b3dc-2a26e5861832 を参考に作成。
 */
public class JsonManagerWithConflictSafe extends JsonManager {


    //GUI
    private final Intrfc_CompWithReloadFunc compWithReloadFunc;

    //private Path currentPath = null;
    private String content = "";
    private volatile long loadedMtime = -1L;
    private volatile String loadedHash = null;


    public JsonManagerWithConflictSafe(String json_file_path, Intrfc_CompWithReloadFunc compWithReloadFunc) {
        this(new File(json_file_path), compWithReloadFunc);
    }

    public JsonManagerWithConflictSafe(File jsonFile, Intrfc_CompWithReloadFunc compWithReloadFunc) {
        super(jsonFile);
        //this.currentPath = jsonFile.toPath();
        this.compWithReloadFunc = compWithReloadFunc;
        //openJson(jsonFile.toPath());
        this.loadedMtime = this.getLoadedMtime();
        this.loadedHash = this.getHashString();

    }

    public void openJson(File jsonFile) {
        this.jsonFile = jsonFile;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.jsonObject = loadJsonObject(this.jsonFile);

        this.loadedMtime = this.getLoadedMtime();
        this.loadedHash = this.getHashString();

        compWithReloadFunc.actionAfterOpeningJson(this);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(compWithReloadFunc.getFrame(), msg, "Error", JOptionPane.ERROR_MESSAGE);
    }


    /**
     * Save current text. If forceOverwrite==false and disk mtime differs from loadedMtime,
     * show conflict dialog (Cancel/Overwrite/Reload/Save As).
     */
    public boolean doSave(boolean forceOverwrite) {

        String currentHash = getHashString();

        //System.out.println("doSave");
        if (jsonFile.toPath() == null) {
            System.err.println("currentPath is null.");
            return false;
        }
        try {
            long diskMtime = Files.getLastModifiedTime(jsonFile.toPath()).toMillis();
            //System.out.println("diskMtime: " + diskMtime);

            if (!forceOverwrite &&
                    loadedMtime != -1 &&
                    diskMtime != loadedMtime) {
                // Conflict
                int choice = showConflictDialog(jsonFile.toPath(), diskMtime, loadedMtime);
                switch (choice) {
                    case 0: // Cancel
                        //System.out.println("Cancel");
                        return false;
                    case 1: // Overwrite
                        //System.out.println("Overwrite");
                        writeJson();
                        updateModifiedTimeAndContentHash();
                        return true;
                    case 2: // Reload
                        //System.out.println("Reload");
                        return reloadFromDisk();
                    case 3: // Save As
                        //System.out.println("Save As");
                        return doSaveAs();
                    default:
                        //System.out.println("default");
                        return false;
                }
            }

            //System.out.println("No conflict or force overwrite");
            writeJson();
            updateModifiedTimeAndContentHash();
            compWithReloadFunc.actionAfterSavingJson(this);
            return true;
        } catch (IOException ex) {
            showError("Save failed: " + ex.getMessage());
            return false;
        }
    }

    public boolean doSaveAs() {
        if (jsonFile.toPath() == null && this.jsonObject.isEmpty()) return false;

        JFileChooser fc = new JFileChooser();
        if (jsonFile.toPath() != null) fc.setSelectedFile(jsonFile.toPath().toFile());
        if (fc.showSaveDialog(compWithReloadFunc.getFrame()) == JFileChooser.APPROVE_OPTION) {
            Path dest = fc.getSelectedFile().toPath();
            if (Files.exists(dest)) {
                int ans = JOptionPane.showConfirmDialog(compWithReloadFunc.getFrame(),
                        "既に存在します。上書きしますか？" + dest.toAbsolutePath(),
                        "別名保存の確認", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (ans != JOptionPane.YES_OPTION) return false;
            }
            this.jsonFile = dest.toFile();


            return doSave(true);
        }
        return false;
    }

    private boolean reloadFromDisk() {
        if (this.jsonFile == null) return false;

        this.jsonObject = loadJsonObject(this.jsonFile);

        System.out.println(this.getJsonAsText());

        this.loadedMtime = getLoadedMtime();
        this.loadedHash = getHashString();

        compWithReloadFunc.actionAfterReloading();

        return true;
    }

    private int showConflictDialog(Path p, long diskMtime, long myLoadedMtime) {
        String msg = "保存しようとしているファイルは、読み込み後に外部で更新されています。\n" +
                "  ファイル: " + p.toAbsolutePath() + "\n" +
                "  読み込み時刻: " + Instant.ofEpochMilli(myLoadedMtime) + "\n" +
                "  ディスク上更新時刻: " + Instant.ofEpochMilli(diskMtime) + "\n" +
                "  どうしますか？";
        String[] options = {"キャンセル", "上書き", "リロード", "別名保存"};
        return JOptionPane.showOptionDialog(compWithReloadFunc.getFrame(), msg, "競合の検出",
                //return JOptionPane.showOptionDialog(null, msg, "競合の検出",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
    }

    private void updateModifiedTimeAndContentHash() throws IOException {
        loadedMtime = getLoadedMtime();
        loadedHash = getHashString();
    }

    public long getLoadedMtime() {
        try {
            return Files.getLastModifiedTime(this.jsonFile.toPath()).toMillis();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getHashString() {
        return Hashes.sha256(gson.toJson(this.jsonObject));
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

    public void showLoadedContent() {
        SwingUtilities.invokeLater(() -> compWithReloadFunc.getFrame().setVisible(true));
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
        jm.writeJson();

    }
}
