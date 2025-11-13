package iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common;

import iu.LCAC.Utils.ColorChangeableTextArea;
import iu.LCAC.Utils.ColorChangeableTextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class NotePane extends JPanel {

    final String sectionName;
    final String subSectionName;

    JTabbedPane parentTabbedPanel;

    ColorChangeableTextField tFiled_Status = new ColorChangeableTextField("");
    final String tooltipForStatusFiled = "先頭文字がタブに表示される。タブ視認性向上のために使う。";
    ColorChangeableTextArea tArea_Note = new ColorChangeableTextArea("");
    final String tooltipForNoteArea = "自由記載ノート。";

    public NotePane(String sectionName, String subSectionName, JTabbedPane parentTabbedPanel) {

        this.setBackground(Color.YELLOW);  //For debug

        // Initialization
        this.sectionName = sectionName;
        this.subSectionName = subSectionName;
        this.parentTabbedPanel = parentTabbedPanel;
        this.setLayout(new BorderLayout());

        /* **** NORTH AREA **** */
        JPanel baseOfNorth = new JPanel();
        //baseOfNorth.setBackground(Color.YELLOW);  //For debug
        baseOfNorth.setLayout(new BoxLayout(baseOfNorth, BoxLayout.Y_AXIS));

        // Status TextField
        tFiled_Status.setToolTipText(tooltipForStatusFiled);
        //tFiled_Status.setPreferredSize(new Dimension(80, 30));
        tFiled_Status.setMaximumSize(new Dimension(800, 30));
        baseOfNorth.add(tFiled_Status);

        // Note Text Area
        tArea_Note.setToolTipText(tooltipForNoteArea);
        JScrollPane scrollPane = new JScrollPane(tArea_Note);
        //scrollPane.setMaximumSize(new Dimension(400, 100));
        //scrollPane.setMinimumSize(new Dimension(400, 100));
        scrollPane.setPreferredSize(new Dimension(800, 100));
        baseOfNorth.add(scrollPane);

        this.setLayout(new BorderLayout());
        //this.add(baseOfNorth, BorderLayout.NORTH);
        this.add(baseOfNorth, BorderLayout.CENTER);
        //this.setPreferredSize(new Dimension(80, 130));
        this.setMaximumSize(new Dimension(800, 130));

       //this.setBackground(Color.YELLOW);

        /* *** SOUTH AREA *** */

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
                //System.out.println(idx + " - " + baseTitle);
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

