package iu.LCAC.Member.componentholder.Concretes.DEResult.Common;

import iu.LCAC.Utils.ColorChangeableTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class One_DEResult_Pane_Abs extends JPanel {

    final String jsonFolderPathStr;
    String jsonName;
    final String sectionName;
    final String subSectionName;

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
        this.jsonFolderPathStr = jsonFolderPathStr;
        this.jsonName = jsonName;
        this.sectionName = sectionName;
        this.subSectionName = subSectionName;


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

    }

    protected abstract void saveJson();

    protected abstract void loadJson();

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

    protected void changeJsonFileName(){
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
