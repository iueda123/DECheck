package iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Concretes.StatusPanel.StatusPanelHolder;
import iu.LCAC.Utils.JsonManagerWithConflictSafe.JsonManagerCallback;
import iu.LCAC.Utils.JsonManagerWithConflictSafe.JsonManagerWithConflictSafe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public abstract class One_DEQAResult_Pane_Abs extends JPanel implements JsonManagerCallback {

    final String jsonFolderPathStr;
    String jsonName;
    //JsonManager jsonManager;
    JsonManagerWithConflictSafe jsonManager;

    final String sectionName;
    final String subSectionName;

    JLabel jsonNameLabel = new JLabel("JSON File Name");
    //ColorChangeableTextField tField_jsonName = new ColorChangeableTextField("JSON File Name");
    String tooltipForJsonName = "JSON File Name";

    JButton saveButton = new JButton("save");
    JButton openButton = new JButton("open");
    JButton loadButton = new JButton("load");
    JButton jsonFileNameEditButton = new JButton("edit json name");
    JButton convertJson2TsvButton = new JButton("2TSV");
    private ManagerOfSubTabBasePane managerOfSubTabBasePane;


    public One_DEQAResult_Pane_Abs(
            String jsonFolderPathStr,
            String jsonName, String sectionName, String subSectionName) {

        this.jsonFolderPathStr = jsonFolderPathStr;
        this.jsonName = jsonName;

        //ToDo: JsonManagerWithConflictSafe() を使いたい。
        // 問題は、reloadが選択されたときにどのようにコンポーネントに反映させるか
        // JsonManagerWithConfilictSafe() という JsonManagerの一種と
        // JComponent系へのリロード処理をどう結びつける？
        //JsonManagerWithConflictSafe()のコンストラクタに引数としてreload()メソッドをもつインターフェースを渡し、
        //そのインターフェースのreload()を呼び出すか？
        //this.jsonManager = new JsonManager(this.jsonFolderPathStr + "/" + jsonName);

        // JsonManagerの初期化は子クラスのフィールド初期化後に行う必要があるため、ここでは行わない
        // 子クラスのコンストラクタの最後でinitializeJsonManager()を呼び出すこと

        this.sectionName = sectionName;
        this.subSectionName = subSectionName;

        saveButton.addActionListener(
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        saveJson();
                    }
                });

        openButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openJsonFile();
            }
        });

        loadButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJson();
            }
        });

        jsonFileNameEditButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeJsonFileName();
            }
        });


    }


    public abstract void saveJson();

    public void openJsonFile() {

        Path jsonFilePath = Paths.get( this.getJsonFolderPathStr() + "/" + this.getJsonName() ) ;

        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (jsonFilePath.toFile().exists()) {
                    desktop.open(jsonFilePath.toFile());
                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        "JSONファイルが見つかりません: " + jsonFilePath.toAbsolutePath(),
                        "エラー",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "このシステムではファイルを開く機能がサポートされていません。",
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "ファイルを開く際にエラーが発生しました: " + e.getMessage(),
                "エラー",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }

    }

    public abstract void loadJson();

    /**
     * JsonManagerを初期化する。
     * 子クラスのコンストラクタの最後で呼び出すこと。
     * 子クラスのフィールドが初期化された後に呼び出す必要がある。
     */
    protected void initializeJsonManager() {
        this.jsonManager = new JsonManagerWithConflictSafe(this.jsonFolderPathStr + "/" + jsonName, this);
    }

    public String getJsonFolderPathStr() {
        return jsonFolderPathStr;
    }

    public String getJsonName() {
        return jsonName;
    }

    public String getSubSectionName() {
        return subSectionName;
    }

    public String getSectionName() {
        return sectionName;
    }


    protected abstract void resetBackgroundColorOfTAreasTFields();

    public void registerManagerOfSubTabBasePane(ManagerOfSubTabBasePane managerOfSubTabBasePane) {
        this.managerOfSubTabBasePane = managerOfSubTabBasePane;
    }

    ;

    protected class PanelMoverPane extends JPanel {

        JButton moveUpButton = new JButton("↑");
        JButton moveDownButton = new JButton("↓");

        PanelMoverPane() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            add(moveUpButton);
            add(moveDownButton);
            moveDownButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    movePaneDown();
                }
            });

            moveUpButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    movePaneUp();
                }
            });
        }


        private void movePaneUp() {
            Container parent = One_DEQAResult_Pane_Abs.this.getParent();
            if (parent == null) {
                return;
            }

            Component[] components = parent.getComponents();
            int paneIndex = -1;
            for (int i = 0; i < components.length; i++) {
                if (components[i] == One_DEQAResult_Pane_Abs.this) {
                    paneIndex = i;
                    break;
                }
            }

            if (paneIndex == -1) {
                return;
            }

            Component previousPane = null;
            for (int i = paneIndex - 1; i >= 0; i--) {
                if (components[i] instanceof One_DEQAResult_Pane_Abs) {
                    previousPane = components[i];
                    break;
                }
            }

            if (previousPane == null) {
                return;
            }

            JLabel spacerBefore = null;
            if (paneIndex > 0 && components[paneIndex - 1] instanceof JLabel) {
                spacerBefore = (JLabel) components[paneIndex - 1];
            }

            if (spacerBefore != null) {
                parent.remove(spacerBefore);
            }
            parent.remove(One_DEQAResult_Pane_Abs.this);

            int insertionIndex = parent.getComponentZOrder(previousPane);
            if (spacerBefore != null) {
                parent.add(spacerBefore, insertionIndex);
                insertionIndex++;
            }
            parent.add(One_DEQAResult_Pane_Abs.this, insertionIndex);

            parent.revalidate();
            parent.repaint();
        }

        private void movePaneDown() {
            Container parent = One_DEQAResult_Pane_Abs.this.getParent();
            if (parent == null) {
                return;
            }

            Component[] components = parent.getComponents();
            int paneIndex = -1;
            for (int i = 0; i < components.length; i++) {
                if (components[i] == One_DEQAResult_Pane_Abs.this) {
                    paneIndex = i;
                    break;
                }
            }

            if (paneIndex == -1) {
                return;
            }

            Component nextPane = null;
            for (int i = paneIndex + 1; i < components.length; i++) {
                if (components[i] instanceof One_DEQAResult_Pane_Abs) {
                    nextPane = components[i];
                    break;
                }
            }

            if (nextPane == null) {
                return;
            }

            JLabel spacerBefore = null;
            if (paneIndex > 0 && components[paneIndex - 1] instanceof JLabel) {
                spacerBefore = (JLabel) components[paneIndex - 1];
            }

            if (spacerBefore != null) {
                parent.remove(spacerBefore);
            }
            parent.remove(One_DEQAResult_Pane_Abs.this);

            int insertionIndex = parent.getComponentZOrder(nextPane) + 1;
            if (spacerBefore != null) {
                parent.add(spacerBefore, insertionIndex);
                insertionIndex++;
            }
            parent.add(One_DEQAResult_Pane_Abs.this, insertionIndex);

            parent.revalidate();
            parent.repaint();
        }

    }

    protected void changeJsonFileName() {
        System.out.println("Start editing JSON file name");

        String oldJsonName = jsonName;

        String newJsonName = (String) JOptionPane.showInputDialog(
                this,
                "JSONファイル名を入力してください:",
                "JSONファイル名変更",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                jsonName
        );

        if (newJsonName == null || newJsonName.trim().isEmpty() || newJsonName.equals(jsonName)) {
            return;
        }

        Path oldFilePath = Paths.get(jsonFolderPathStr, jsonName);
        Path newFilePath = Paths.get(jsonFolderPathStr, newJsonName);

        if (!oldFilePath.toFile().exists()) {
            JOptionPane.showMessageDialog(
                    this,
                    "元のJSONファイルが見つかりません: " + oldFilePath.toAbsolutePath(),
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (newFilePath.toFile().exists()) {
            JOptionPane.showMessageDialog(
                    this,
                    "同名のファイルが既に存在します: " + newJsonName,
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            java.nio.file.Files.move(oldFilePath, newFilePath);

            updateJsonName(newJsonName);//このDEパネルにおける登録Jsonの更新

            //すべての同胞パネルにおける登録Jsonを更新
            updateRegisteredJsonNamesOfAllOtherPane(oldJsonName, newJsonName);

            JOptionPane.showMessageDialog(
                    this,
                    "JSONファイル名を変更しました: " + newJsonName,
                    "成功",
                    JOptionPane.INFORMATION_MESSAGE
            );

            System.out.println("Successfully renamed to " + newFilePath.toAbsolutePath());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "ファイル名の変更に失敗しました: " + e.getMessage(),
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    public void updateJsonName(String newJsonName) {
        jsonName = newJsonName;
        jsonNameLabel.setText(newJsonName);
        initializeJsonManager();
    }

    private void updateRegisteredJsonNamesOfAllOtherPane(String oldJsonName, String newJsonName) {

        //System.out.println("★");

        ArrayList<ManagerOfSubTabBasePane> managerOfSubTabBasePanes = managerOfSubTabBasePane.getSubTabsHolder().getArrayList_of_ManagerOfSubTabBasePane();
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : managerOfSubTabBasePanes) {

            ArrayList<One_DEQAResult_Pane_Abs> oneDeqaResultPaneAbsArray = managerOfSubTabBasePane.getDePaneArray();
            for (One_DEQAResult_Pane_Abs oneDeResultPane : oneDeqaResultPaneAbsArray) {

                //他のサブサブパネル下にあるすべてのDEパネルを参照してもし旧JSON名を保持するものがあれば更新操作を施す。
                if (oneDeResultPane.getJsonName().equals(oldJsonName)) {
                    oneDeResultPane.updateJsonName(newJsonName);
                }

            }
        }
    }

}
