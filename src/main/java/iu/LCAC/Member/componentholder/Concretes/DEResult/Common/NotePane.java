package iu.LCAC.Member.componentholder.Concretes.DEResult.Common;

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
    JButton updateTabTitleButton = new JButton("update tab title");

    JTabbedPane parentTabbedPanel;

    ColorChangeableTextField tFiled_Status = new ColorChangeableTextField("");
    ColorChangeableTextArea tArea_Note = new ColorChangeableTextArea("");

    public NotePane(String sectionName, String subSectionName, JTabbedPane parentTabbedPanel) {
        this.sectionName = sectionName;
        this.subSectionName = subSectionName;
        this.parentTabbedPanel = parentTabbedPanel;

        this.updateTabTitleButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTabTitle();
            }
        });

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(tFiled_Status);
        add(tArea_Note);
        add(updateTabTitleButton);
    }

    private void saveNoteArea() {
    }

    private void saveMarkField() {

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

