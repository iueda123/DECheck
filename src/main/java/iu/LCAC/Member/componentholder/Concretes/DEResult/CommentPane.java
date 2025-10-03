package iu.LCAC.Member.componentholder.Concretes.DEResult;

import iu.LCAC.Utils.ColorChangeableTextArea;
import iu.LCAC.Utils.ColorChangeableTextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CommentPane extends JPanel {

    final String sectionName;
    final String subSectionName;
    JButton saveButton = new JButton("save");
    JButton loadButton = new JButton("load");

    JTabbedPane parentTabbedPanel;

    ColorChangeableTextField tFiled_Mark = new ColorChangeableTextField("");
    ColorChangeableTextArea textArea_Comments = new ColorChangeableTextArea("");

    public CommentPane(String sectionName, String subSectionName, JTabbedPane parentTabbedPanel) {
        this.sectionName = sectionName;
        this.subSectionName = subSectionName;
        this.parentTabbedPanel = parentTabbedPanel;

        this.saveButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                updateTabTitle();
                
                saveMarkField();
                
                saveNoteArea();
                
            }
        });
        // Document に「どの JTextField のものか」を記録しておく
        //tFiled_Mark.getDocument().putProperty("owner", tFiled_Mark);
        //tFiled_Mark.getDocument().addDocumentListener(new CustomDocumentListener(this.parentTabbedPanel));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(tFiled_Mark);
        add(textArea_Comments);
        add(saveButton);
        add(loadButton);
    }

    private void saveNoteArea() {
    }

    private void saveMarkField() {
        
    }

    private void updateTabTitle() {
        String text = tFiled_Mark.getText();
                // この JTextField が含まれているタブインデックスを調べる
                Component tabComponent = CommentPane.this;
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

