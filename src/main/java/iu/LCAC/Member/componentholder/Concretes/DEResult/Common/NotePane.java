package iu.LCAC.Member.componentholder.Concretes.DEResult.Common;

import iu.LCAC.Utils.CollapsiblePanel;
import iu.LCAC.Utils.ColorChangeableTextArea;
import iu.LCAC.Utils.ColorChangeableTextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NotePane extends JPanel {

    final String sectionName;
    final String subSectionName;

    JButton updateTabTitleButton = new JButton("update tab title"); //For debug

    JTabbedPane parentTabbedPanel;

    ColorChangeableTextField tFiled_Status = new ColorChangeableTextField("");
    final String tooltipForStatusFiled = "先頭文字がタブに表示される。タブ視認性向上のために使う。";
    ColorChangeableTextArea tArea_Note = new ColorChangeableTextArea("");
    final String tooltipForNoteArea = "自由記載ノート。";

    public NotePane(String sectionName, String subSectionName, JTabbedPane parentTabbedPanel) {

        // Initialization
        this.sectionName = sectionName;
        this.subSectionName = subSectionName;
        this.parentTabbedPanel = parentTabbedPanel;
        this.setLayout(new BorderLayout());

        /* **** NORTH AREA **** */
        JPanel baseOfNorth = new JPanel(new BorderLayout());

        // Status TextField
        tFiled_Status.setToolTipText(tooltipForStatusFiled);
        baseOfNorth.setMaximumSize(new Dimension(9000, 30));
        baseOfNorth.setMinimumSize(new Dimension(9000, 30));
        baseOfNorth.add(tFiled_Status, BorderLayout.NORTH);
        this.add(baseOfNorth, BorderLayout.NORTH);

        // Note Text Area
        JPanel baseOfCenter = new JPanel(new BorderLayout());
        tArea_Note.setToolTipText(tooltipForNoteArea);
        JScrollPane scrollPane = new JScrollPane(tArea_Note);
        baseOfCenter.setPreferredSize(new Dimension(800, 100));
        baseOfCenter.add(scrollPane, BorderLayout.CENTER);

        this.add(baseOfCenter, BorderLayout.CENTER);

        //For debug
        /*
        this.updateTabTitleButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTabTitle();
            }
        });
        add(updateTabTitleButton);
        */
    }

    private void saveStatusField() {

    }

    private void saveNoteArea() {
    }


    public void updateTabTitle() {
        String text = tFiled_Status.getText();
        // この JTextField が含まれているタブインデックスを調べる
        Component tabComponent = NotePane.this;
        while (tabComponent != null && tabComponent.getParent() != parentTabbedPanel) {
            tabComponent = tabComponent.getParent();
        }
        if (tabComponent != null) {
            int idx = parentTabbedPanel.indexOfComponent(tabComponent);
            if (idx != -1) {
                //String baseTitle = "タブ" + (idx + 1);
                String baseTitle = subSectionName;
                if (!text.isEmpty()) {
                    baseTitle += " - " + text.charAt(0);
                }
                System.out.println(idx + " - " + baseTitle);
                parentTabbedPanel.setTitleAt(idx, baseTitle);
            }
        }
    }

    public String getStatusText() {
        return tFiled_Status.getText();
    }

    public String getNoteText() {
        return tArea_Note.getText();
    }

    public void setStatusText(String text) {
        tFiled_Status.setText(text);
    }

    public void setNoteText(String text) {
        tArea_Note.setText(text);
    }

    public void resetBackgroundColors() {
        tFiled_Status.resetBackgroundColor();
        tArea_Note.resetBackgroundColor();
    }

    public void updateDefaultValues() {
        tFiled_Status.updateDefaultValue();
        tArea_Note.updateDefaultValue();
    }

    private class CustomDocumentListener implements DocumentListener {
        final JTabbedPane tabbedPane;

        CustomDocumentListener(JTabbedPane tabbedPane) {
            this.tabbedPane = tabbedPane;
        }

        void updateTabTitle(JTextField tf) {
            // 共通で使えるメソッド
            String text = tf.getText();
            // この JTextField が含まれているタブインデックスを調べる
            Container parent = tf.getParent();
            while (parent != null && !(parent instanceof JPanel)) {
                parent = parent.getParent();
            }
            if (parent != null) {
                int idx = tabbedPane.indexOfComponent(parent);
                if (idx != -1) {
                    //String baseTitle = "タブ" + (idx + 1);
                    String baseTitle = subSectionName;
                    if (!text.isEmpty()) {
                        baseTitle += " - " + text.charAt(0);
                    }
                    System.out.println(idx + " - " + baseTitle);
                    tabbedPane.setTitleAt(idx, baseTitle);
                }
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateTabTitle((JTextField) e.getDocument().getProperty("owner"));
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateTabTitle((JTextField) e.getDocument().getProperty("owner"));
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateTabTitle((JTextField) e.getDocument().getProperty("owner"));

        }
    }
}

