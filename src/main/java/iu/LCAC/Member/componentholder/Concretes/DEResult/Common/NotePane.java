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

    JButton editJsonFileNameButton = new JButton("Unknown.json");
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

        // Json File Name Edit Button
        //northPane.add(editJsonFileNameButton, BorderLayout. WEST);

        /* **** NORTH AREA **** */
        Box northPane = Box.createVerticalBox();

        // Status TextField
        Box northSubPane_1 = Box.createVerticalBox();
        tFiled_Status.setMaximumSize(new Dimension(9000, 30));
        tFiled_Status.setMinimumSize(new Dimension(9000, 30));
        //tFiled_Status.setPreferredSize(new Dimension(0, 30));
        tFiled_Status.setToolTipText(tooltipForStatusFiled);
        //northSubPane_1.add(Box.createHorizontalGlue());
        northSubPane_1.add(tFiled_Status);
        //northSubPane_1.setMaximumSize(new Dimension(9000, 60));
        //northSubPane_1.setMinimumSize(new Dimension(9000, 60));
        //northSubPane_1.setPreferredSize(new Dimension(9000, 60));
        //northPane.add(northSubPane_1);
        northPane.add(tFiled_Status);

        // Status TextField
        //JTextField sample = new JTextField();
        //JPanel sample = new JPanel();
        //sample.setMaximumSize(new Dimension(9000, 180));
        //sample.setMinimumSize(new Dimension(9000, 180));
        //tFiled_Status.setPreferredSize(new Dimension(0, 30));
        //northPane.add(sample);
        //northPane.add(northSubPane_1);


        //CollapsiblePanel collapsiblePanel = new CollapsiblePanel(new JLabel("説明"), new JTextField("aiueo"), 600, 100);
        //JLabel collapsiblePanel = new JLabel("a");
        //collapsiblePanel.setBackground(Color.BLUE);
        //collapsiblePanel.setPreferredSize(new Dimension(600, 100));
        //collapsiblePanel.setMaximumSize(new Dimension(9000, 180));
        //collapsiblePanel.setMinimumSize(new Dimension(9000, 180));
        //northPane.add(collapsiblePanel);

        this.add(northPane, BorderLayout.NORTH);

        // Note Text Area
        //tArea_Note.setToolTipText(tooltipForNoteArea);
        JScrollPane scrollPane = new JScrollPane(tArea_Note);
        //northPane.add(scrollPane);
        scrollPane.setMaximumSize(new Dimension(600, 100));
        scrollPane.setMinimumSize(new Dimension(600, 100));
        //scrollPane.setPreferredSize(new Dimension(600, 80));
        this.add(scrollPane, BorderLayout.CENTER);




        //northPane.setPreferredSize(new Dimension(600, 300));

        /* **** CENTER AREA **** */
        //JPanel centerPane = new JPanel();
        //centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.Y_AXIS));
        //this.add(centerPane, BorderLayout.CENTER);

        /* **** SOUTH AREA **** */
        // Separator
        //JPanel southPane = new JPanel(new BorderLayout());
        //southPane.add(new JLabel(" "));
        //this.add(southPane, BorderLayout.SOUTH);

        // Finalization
        //centerPane.setMaximumSize(new Dimension(600, 200));
        //centerPane.setPreferredSize(new Dimension(600, 200));
        //southPane.setMaximumSize(new Dimension(600, 100));

        //For debug
        this.updateTabTitleButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTabTitle();
            }
        });
        //add(updateTabTitleButton);
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

