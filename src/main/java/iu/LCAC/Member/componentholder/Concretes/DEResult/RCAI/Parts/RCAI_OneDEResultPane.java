package iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts;

import iu.LCAC.Utils.ColorChangeableTextArea;
import iu.LCAC.Utils.ColorChangeableTextField;
import iu.LCAC.Utils.JsonManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RCAI_OneDEResultPane extends JPanel {

    final String jsonName;
    final String sectionName;
    final String subSectionName;

    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");

    ColorChangeableTextField tField_jsonName = new ColorChangeableTextField("jsonName");
    ColorChangeableTextArea tArea_Answer = new ColorChangeableTextArea("Answer");
    ColorChangeableTextField tFiled_ConfidenceRating = new ColorChangeableTextField("Confidence Rating");
    ColorChangeableTextField tField_NegativeAnswerCategory = new ColorChangeableTextField("Negative Answer Category");

    ColorChangeableTextArea tArea_Reason = new ColorChangeableTextArea("Reason");
    ColorChangeableTextArea tArea_SupportingText = new ColorChangeableTextArea("Supporting Text");
    ColorChangeableTextArea tArea_PageLine = new ColorChangeableTextArea("Page Line");

    public RCAI_OneDEResultPane(String jsonName, String sectionName, String subSectionName) {
        this.jsonName = jsonName;
        this.sectionName = sectionName;
        this.subSectionName = subSectionName;

        tArea_Answer.setLineWrap(true);
        tArea_Answer.setWrapStyleWord(true);

        tArea_Reason.setLineWrap(true);
        tArea_Reason.setWrapStyleWord(true);

        tArea_SupportingText.setLineWrap(true);
        tArea_SupportingText.setWrapStyleWord(true);

        tArea_PageLine.setLineWrap(true);
        tArea_PageLine.setWrapStyleWord(true);

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

        setLayout(new BorderLayout());

        /* North Area */
        Box northBox = Box.createVerticalBox();


        JPanel northSubPane1 = new JPanel(new BorderLayout());
        northSubPane1.setPreferredSize(new Dimension(600, 75));

        JScrollPane scrollPane_Answer = new JScrollPane(tArea_Answer);
        scrollPane_Answer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Answer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        northSubPane1.add(scrollPane_Answer, BorderLayout.CENTER);
        northSubPane1.add(new panelMoverPane(), BorderLayout.EAST);
        northBox.add(northSubPane1);

        Box northSubBox2 = Box.createHorizontalBox();
        northSubBox2.add(tFiled_ConfidenceRating);
        northSubBox2.add(tField_NegativeAnswerCategory);
        northSubBox2.add(loadButton);
        northSubBox2.add(saveButton);
        northBox.add(northSubBox2);
        add(northBox, BorderLayout.NORTH);

        /* Center Area */
        JScrollPane scrollPane_Reason = new JScrollPane(tArea_Reason);
        scrollPane_Reason.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Reason.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollPane_SupportingText = new JScrollPane(tArea_SupportingText);
        scrollPane_SupportingText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_SupportingText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollPane_PageLine = new JScrollPane(tArea_PageLine);
        scrollPane_PageLine.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_PageLine.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        Box centerBox = Box.createHorizontalBox();
        centerBox.add(scrollPane_Reason);
        centerBox.add(scrollPane_SupportingText);
        centerBox.add(scrollPane_PageLine);

        add(centerBox, BorderLayout.CENTER);

        /* South Area */
        Box southBox = Box.createHorizontalBox();
        southBox.add(new JLabel(" "));

        add(southBox, BorderLayout.SOUTH);

        /* Finalization */
        setBorder(BorderFactory.createTitledBorder("A Result Panel"));
        // BoxLayoutで適切にスクロールするために、固定の高さを設定
        setPreferredSize(new Dimension(600, 400));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
    }


    public void setTextToTheButton(String text) {
        this.saveButton.setText(text);
    }

    public void setValTo_JsonName(String value) {
        tField_jsonName.setText(value);
        setBorder(BorderFactory.createTitledBorder(value));
    }

    public void setValTo_Answer(String value) {
        tArea_Answer.setText(value);
    }

    public void setValTo_ConfidenceRating(String value) {
        tFiled_ConfidenceRating.setText(value);
    }

    public void setValTo_NegativeAnswerCategory(String value) {
        tField_NegativeAnswerCategory.setText(value);
    }

    public void setValTo_Reason(String value) {
        tArea_Reason.setText(value);
    }

    public void setValTo_SupportingText(String value) {
        tArea_SupportingText.setText(value);
    }

    public void setValTo_PageLine(String value) {
        tArea_PageLine.setText(value);
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

    private void loadJson() {
        Path jsonFilePath = Paths.get("./json/" + jsonName);

        if (!jsonFilePath.toFile().exists()) {
            System.err.println("JSON file not found: " + jsonFilePath.toFile().getAbsolutePath());
            return;
        }

        // JSONから読み込み
        JsonManager jsonManager = new JsonManager(jsonFilePath.toFile());
        String answer = jsonManager.getValue(sectionName + "/" + subSectionName + "/Answer");
        String confidenceRating = jsonManager.getValue(sectionName + "/" + subSectionName + "/Confidence\\ Rating");
        String negativeAnswerCategory = jsonManager.getValue(sectionName + "/" + subSectionName + "/Negative\\ Answer\\ Category");
        String reason = jsonManager.getValue(sectionName + "/" + subSectionName + "/Reason");
        String supportingText = jsonManager.getValue(sectionName + "/" + subSectionName + "/Supporting\\ Text");
        String pageLine = jsonManager.getValue(sectionName + "/" + subSectionName + "/Page\\/Line");

        // 各フィールドに値を設定
        if (answer != null) tArea_Answer.setText(answer);
        if (confidenceRating != null) tFiled_ConfidenceRating.setText(confidenceRating);
        if (negativeAnswerCategory != null) tField_NegativeAnswerCategory.setText(negativeAnswerCategory);
        if (reason != null) tArea_Reason.setText(reason);
        if (supportingText != null) tArea_SupportingText.setText(supportingText);
        if (pageLine != null) tArea_PageLine.setText(pageLine);

        resetBackgroundColorOfTAreasTFields();

        System.out.println("Successfully loaded from " + jsonFilePath.toFile().getAbsolutePath());
    }

    private void saveJson() {
        Path jsonFilePath = Paths.get("./json/" + jsonName);
        String answerText = tArea_Answer.getText();
        String confidenceRatingText = tFiled_ConfidenceRating.getText();
        String negativeAnswerCategoryText = tField_NegativeAnswerCategory.getText();
        String reasonText = tArea_Reason.getText();
        String supportingText = tArea_SupportingText.getText();
        String pageLineText = tArea_PageLine.getText();

        // JSONへ書き込み
        JsonManager jsonManager = new JsonManager(jsonFilePath.toFile());
        jsonManager.setValue(sectionName + "/" + subSectionName + "/Answer", answerText);
        jsonManager.setValue(sectionName + "/" + subSectionName + "/Confidence\\ Rating", confidenceRatingText);
        jsonManager.setValue(sectionName + "/" + subSectionName + "/Negative\\ Answer\\ Category", negativeAnswerCategoryText);
        jsonManager.setValue(sectionName + "/" + subSectionName + "/Reason", reasonText);
        jsonManager.setValue(sectionName + "/" + subSectionName + "/Supporting\\ Text", supportingText);
        jsonManager.setValue(sectionName + "/" + subSectionName + "/Page\\/Line", pageLineText);

        boolean success = jsonManager.writeoutJson();
        if (success) {
            System.out.println("Successfully saved to " + jsonFilePath.toFile().getAbsolutePath());
            resetBackgroundColorOfTAreasTFields();
        } else {
            System.err.println("Failed to save to " + jsonFilePath.toFile().getAbsolutePath());
        }
    }

    public void resetBackgroundColorOfTAreasTFields() {
        tField_jsonName.resetBackgroundColor();
        tField_jsonName.updateDefaultValue();
        tField_NegativeAnswerCategory.resetBackgroundColor();
        tField_NegativeAnswerCategory.updateDefaultValue();
        tFiled_ConfidenceRating.resetBackgroundColor();
        tFiled_ConfidenceRating.updateDefaultValue();
        tArea_Answer.resetBackgroundColor();
        tArea_Answer.updateDefaultValue();
        tArea_Reason.resetBackgroundColor();
        tArea_Reason.updateDefaultValue();
        tArea_SupportingText.resetBackgroundColor();
        tArea_SupportingText.updateDefaultValue();
        tArea_PageLine.resetBackgroundColor();
        tArea_PageLine.updateDefaultValue();
    }

    private class panelMoverPane extends JPanel {

        JButton moveUpButton = new JButton("↑");
        JButton moveDownButton = new JButton("↓");

        panelMoverPane() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
            Container parent = RCAI_OneDEResultPane.this.getParent();
            if (parent == null) {
                return;
            }

            Component[] components = parent.getComponents();
            int paneIndex = -1;
            for (int i = 0; i < components.length; i++) {
                if (components[i] == RCAI_OneDEResultPane.this) {
                    paneIndex = i;
                    break;
                }
            }

            if (paneIndex == -1) {
                return;
            }

            Component previousPane = null;
            for (int i = paneIndex - 1; i >= 0; i--) {
                if (components[i] instanceof RCAI_OneDEResultPane) {
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
            parent.remove(RCAI_OneDEResultPane.this);

            int insertionIndex = parent.getComponentZOrder(previousPane);
            if (spacerBefore != null) {
                parent.add(spacerBefore, insertionIndex);
                insertionIndex++;
            }
            parent.add(RCAI_OneDEResultPane.this, insertionIndex);

            parent.revalidate();
            parent.repaint();
        }

        private void movePaneDown() {
            Container parent = RCAI_OneDEResultPane.this.getParent();
            if (parent == null) {
                return;
            }

            Component[] components = parent.getComponents();
            int paneIndex = -1;
            for (int i = 0; i < components.length; i++) {
                if (components[i] == RCAI_OneDEResultPane.this) {
                    paneIndex = i;
                    break;
                }
            }

            if (paneIndex == -1) {
                return;
            }

            Component nextPane = null;
            for (int i = paneIndex + 1; i < components.length; i++) {
                if (components[i] instanceof RCAI_OneDEResultPane) {
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
            parent.remove(RCAI_OneDEResultPane.this);

            int insertionIndex = parent.getComponentZOrder(nextPane) + 1;
            if (spacerBefore != null) {
                parent.add(spacerBefore, insertionIndex);
                insertionIndex++;
            }
            parent.add(RCAI_OneDEResultPane.this, insertionIndex);

            parent.revalidate();
            parent.repaint();
        }

    }
}
