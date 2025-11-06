package iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Utils.ColorChangeableTextField;
import iu.LCAC.Utils.JsonManagerWithConflictSafe.JsonManagerCallback;
import iu.LCAC.Utils.JsonManagerWithConflictSafe.JsonManagerWithConflictSafe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class One_DEResult_Pane_Abs extends JPanel implements JsonManagerCallback {

    final String jsonFolderPathStr;
    String jsonName;
    //JsonManager jsonManager;
    JsonManagerWithConflictSafe jsonManager;

    final String sectionName;
    final String subSectionName;

    final CHolderMediator cHolderMediator;
    final ActionMediator actionMediator;

    ColorChangeableTextField tField_jsonName = new ColorChangeableTextField("JSON File Name");
    String tooltipForJsonName = "JSON File Name";

    JButton loadButton = new JButton("load");
    JButton saveButton = new JButton("save");
    JButton jsonFileNameEditButton = new JButton("edit json name");
    JButton convertJson2MarkdownButton = new JButton("2MD");
    JButton convertJson2TsvButton = new JButton("2TSV");


    public One_DEResult_Pane_Abs(
            String jsonFolderPathStr,
            String jsonName, String sectionName, String subSectionName) {
        this(jsonFolderPathStr, jsonName, sectionName, subSectionName, null, null);
    }

    public One_DEResult_Pane_Abs(
            String jsonFolderPathStr, String jsonName, String sectionName, String subSectionName,
            CHolderMediator cHolderMediator, ActionMediator actionMediator) {

        // BashScriptを呼び出すActionMemberを利用すべく
        // CHolderMediator や ActionMediator を このクラス内に保持したいと
        // このコンストラクタを作った。しかし、どうやって CHolderMediatorやActionMediatorを
        // nullじゃない状態で持ってくるかの良い方法を思いつけず。
        //　ひとまずこのコンストラクタを残しておく。

        this.jsonFolderPathStr = jsonFolderPathStr;
        this.jsonName = jsonName;

        //ToDo: JsonManagerWithConflictSafe() を使いたい。
        // 問題は、reloadが選択されたときにどのようにコンポーネントに反映させるか
        // JsonManagerWithConfilictSafe() という JsonManagerの一種と
        // JComponent系へのリロード処理をどう結びつける？
        //JsonManagerWithConflictSafe()のコンストラクタに引数としてreload()メソッドをもつインターフェースを渡し、
        //そのインターフェースのreload()を呼び出すか？
        //this.jsonManager = new JsonManager(this.jsonFolderPathStr + "/" + jsonName);
        this.jsonManager = new JsonManagerWithConflictSafe(this.jsonFolderPathStr + "/" + jsonName, this);

        this.sectionName = sectionName;
        this.subSectionName = subSectionName;

        this.cHolderMediator = cHolderMediator;
        this.actionMediator = actionMediator;

        saveButton.addActionListener(
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        saveJson();
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

        convertJson2MarkdownButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertJson2Markdown();
            }
        });

    }

    private void convertJson2Markdown() {

        if (cHolderMediator != null) {
            System.err.println("The cHolderMediator is not null.");
        } else {
            System.err.println("The cHolderMediator is null.");
        }

        if (this.actionMediator != null) {
            System.err.println("The actionMediator is not null.");

            // 何かしらの文字列を引数として渡すには以下のようにしてActionEventを作成
            AbstActionMember abstActionMember = actionMediator.getInstanceOfAMember("run_a_bash_script");
            ActionEvent customEvent_with_ActionNameAndArgs = new ActionEvent(
                    this,
                    ActionEvent.ACTION_PERFORMED,
                    "DummyActionName RunBashPanelHolderから渡した引数1 " + "RunBashPanelHolderから渡した引数2 "

                    // AbstrActionMember#getActionCommandAndArgs()の自分が決めた仕様で、
                    // ActionEventオブジェクトに格納された文字列の１つ目の要素はアクション名、
                    // ２つ目以降の要素は引数扱い。
                    // なので ここでは１つ目を DummyActionName としている。

            );
            abstActionMember.perform(customEvent_with_ActionNameAndArgs);

        } else {
            System.err.println("The actionMediator is null @ One_DEResult_Pane_Abs.java.");
        }
    }

    public abstract void saveJson();

    public abstract void loadJson();

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

    public void updateRegisteredJsonName(String value) {
        tField_jsonName.setText(value);
        setBorder(BorderFactory.createTitledBorder(value));
    }


    protected abstract void resetBackgroundColorOfTAreasTFields();

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
            Container parent = One_DEResult_Pane_Abs.this.getParent();
            if (parent == null) {
                return;
            }

            Component[] components = parent.getComponents();
            int paneIndex = -1;
            for (int i = 0; i < components.length; i++) {
                if (components[i] == One_DEResult_Pane_Abs.this) {
                    paneIndex = i;
                    break;
                }
            }

            if (paneIndex == -1) {
                return;
            }

            Component previousPane = null;
            for (int i = paneIndex - 1; i >= 0; i--) {
                if (components[i] instanceof One_DEResult_Pane_Abs) {
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
            parent.remove(One_DEResult_Pane_Abs.this);

            int insertionIndex = parent.getComponentZOrder(previousPane);
            if (spacerBefore != null) {
                parent.add(spacerBefore, insertionIndex);
                insertionIndex++;
            }
            parent.add(One_DEResult_Pane_Abs.this, insertionIndex);

            parent.revalidate();
            parent.repaint();
        }

        private void movePaneDown() {
            Container parent = One_DEResult_Pane_Abs.this.getParent();
            if (parent == null) {
                return;
            }

            Component[] components = parent.getComponents();
            int paneIndex = -1;
            for (int i = 0; i < components.length; i++) {
                if (components[i] == One_DEResult_Pane_Abs.this) {
                    paneIndex = i;
                    break;
                }
            }

            if (paneIndex == -1) {
                return;
            }

            Component nextPane = null;
            for (int i = paneIndex + 1; i < components.length; i++) {
                if (components[i] instanceof One_DEResult_Pane_Abs) {
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
            parent.remove(One_DEResult_Pane_Abs.this);

            int insertionIndex = parent.getComponentZOrder(nextPane) + 1;
            if (spacerBefore != null) {
                parent.add(spacerBefore, insertionIndex);
                insertionIndex++;
            }
            parent.add(One_DEResult_Pane_Abs.this, insertionIndex);

            parent.revalidate();
            parent.repaint();
        }

    }

    protected void changeJsonFileName() {
        System.out.println("Start editing JSON file name");

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
            jsonName = newJsonName;
            tField_jsonName.setText(jsonName);
            updateRegisteredJsonName(jsonName);
            tField_jsonName.updateDefaultValue();
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

}
