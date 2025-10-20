package iu.LCAC.Utils.JsonManagerWithConflictSafe;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

public class TestComponent extends JPanel implements Intrfc_CompWithReloadFunc {

    private final JFrame frame = new JFrame("Over View of Json");

    private final JTextField textField_1 = new JTextField();
    private final JTextField textField_2 = new JTextField();
    private final JButton openBtn = new JButton("Open");
    private final JButton saveBtn = new JButton("Save");
    private final JButton saveAsBtn = new JButton("Save As");
    private final JLabel statusLbl = new JLabel("No file");

    private JsonManagerWithConflictSafe jsonManagerWithConflictSafe;

    public TestComponent() {

        // 基本的な使い方
        Path json_file_path = Paths.get("/home/iu/Downloads/test.json");
        this.jsonManagerWithConflictSafe = new JsonManagerWithConflictSafe(json_file_path.toFile(), this);

        // Center Area
        Box centerBase = Box.createVerticalBox();
        Box centerSub1 = Box.createHorizontalBox();
        centerSub1.add(new JLabel("Key1"));
        centerSub1.add(textField_1);
        centerBase.add(centerSub1);
        Box centerSub2 = Box.createHorizontalBox();
        centerSub2.add(new JLabel("Key2"));
        centerSub2.add(textField_2);
        centerBase.add(centerSub2);
        frame.add(centerBase, BorderLayout.CENTER);

        // North Area
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(openBtn);
        top.add(saveBtn);
        top.add(saveAsBtn);
        top.add(new JSeparator(SwingConstants.VERTICAL));
        top.add(statusLbl);
        frame.add(top, BorderLayout.NORTH);

        // Actions
        openBtn.addActionListener(e -> doOpen());
        saveBtn.addActionListener(e -> doSave(false));
        //saveBtn.setEnabled(false);
        saveAsBtn.addActionListener(e -> doSaveAs());
        //saveAsBtn.setEnabled(false);

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        this.actionAfterOpeningJson(this.jsonManagerWithConflictSafe);
    }

    private void doSave(boolean forceOverWrite) {
        boolean rslt_1 = this.jsonManagerWithConflictSafe.setValue("/test/key1", textField_1.getText());
        //if(rslt_1) System.out.println("The value of textField1 was successfully set.");
        boolean rslt_2 = this.jsonManagerWithConflictSafe.setValue("/test/key2", textField_2.getText());
        //if(rslt_2) System.out.println("The value of textField2 was successfully set.");
        this.jsonManagerWithConflictSafe.doSave(forceOverWrite);
    }

    @Override
    public void actionAfterSavingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        long loadedMtime = jsonManagerWithConflictSafe.getLoadedMtime();
        setStatus("The values were saved \n" +
                " at " + Instant.ofEpochMilli(loadedMtime) + "\n" +
                " to " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    private void doSaveAs() {
        boolean rslt_1 = this.jsonManagerWithConflictSafe.setValue("/test/key1", textField_1.getText());
        //if(rslt_1) System.out.println("The value of textField1 was successfully set.");
        boolean rslt_2 = this.jsonManagerWithConflictSafe.setValue("/test/key2", textField_2.getText());
        //if(rslt_2) System.out.println("The value of textField2 was successfully set.");
        this.jsonManagerWithConflictSafe.doSaveAs();
    }

    public void doOpen() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Text files", "txt", "md", "log", "csv", "java", "json"));
        if (fc.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
            this.jsonManagerWithConflictSafe.openJson(fc.getSelectedFile());
        }
    }

    private void setStatus(String s) {
        statusLbl.setText(s);
    }


    @Override
    public void actionAfterReloading() {
        if (this.jsonManagerWithConflictSafe != null) {
            String value1 = this.jsonManagerWithConflictSafe.getValue("/test/key1");
            //System.out.println("value1: " + value1);
            textField_1.setText(value1);
            String value2 = this.jsonManagerWithConflictSafe.getValue("/test/key2");
            //System.out.println("value2: " + value2);

            textField_2.setText(value2);
            long loadedMtime = this.jsonManagerWithConflictSafe.getLoadedMtime();
            setStatus("Reloaded from disk at " + Instant.ofEpochMilli(loadedMtime));
        } else {
            System.err.println("this.jsonManagerWithConflictSafe is null.");
        }
    }


    @Override
    public void actionAfterOpeningJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        if (this.jsonManagerWithConflictSafe != null) {
            String value1 = this.jsonManagerWithConflictSafe.getValue("/test/key1");
            System.out.println("value1: " + value1);
            textField_1.setText(value1);
            String value2 = this.jsonManagerWithConflictSafe.getValue("/test/key2");
            System.out.println("value2: " + value2);
            textField_2.setText(value2);

            long loadedMtime = this.jsonManagerWithConflictSafe.getLoadedMtime();

            setStatus("Opened: " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath() + " (mtime=" + Instant.ofEpochMilli(loadedMtime) + ")");
        } else {
            System.err.println("this.jsonManagerWithConflictSafe2 is null.");
        }
    }

    @Override
    public Component getFrame() {
        return frame;
        //return this;
    }


    public static void main(String[] args) {

        TestComponent compWithReloadFunc = new TestComponent();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                compWithReloadFunc.getFrame().setVisible(true);
            }
        });


        /*
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
        */

    }
}
