package iu.LCAC.Member.componentholder.Concretes.DEResult.Common;

import iu.LCAC.Utils.ColorChangeableTextArea;
import iu.LCAC.Utils.ColorChangeableTextField;
import iu.LCAC.Utils.JsonManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

public class One_ARSL_Style_Pane extends One_DEResult_Pane_Abs {


    ColorChangeableTextArea tArea_Answer = new ColorChangeableTextArea("Answer");
    private final String tooltipForAnswer = "Answer";

    ColorChangeableTextField tFiled_ConfidenceRating = new ColorChangeableTextField("Confidence Rating");
    private final String tooltipForConfidenceRating = "Confidence Rating";

    ColorChangeableTextField tField_NegativeAnswerCategory = new ColorChangeableTextField("Negative Answer Category");
    private final String tooltipForNegativeAnswerCategory = "Negative Answer Category";

    ColorChangeableTextArea tArea_Reason = new ColorChangeableTextArea("Reason");
    private final String tooltipForReason = "Reason";

    ColorChangeableTextArea tArea_SupportingText = new ColorChangeableTextArea("Supporting Text");
    private final String tooltipForSupportingText = "Supporting Text";

    ColorChangeableTextArea tArea_Location = new ColorChangeableTextArea("Location");
    private final String tooltip_Location = "Source file, page, or/and line of the information.";

    public One_ARSL_Style_Pane(String jsonFolderPathStr, String jsonName, String sectionName, String subSectionName) {
        super(jsonFolderPathStr, jsonName, sectionName, subSectionName);

        //this.jsonName = jsonName;
        //this.sectionName = sectionName;
        //this.subSectionName = subSectionName;

        // Answer Sub Sub Section
        tArea_Answer.setLineWrap(true);
        tArea_Answer.setWrapStyleWord(true);
        tArea_Answer.setToolTipText(tooltipForAnswer);

        // Confidence Rating Sub Sub Section
        tFiled_ConfidenceRating.setToolTipText(tooltipForConfidenceRating);

        // Negative Answer Category Sub SubSection
        tField_NegativeAnswerCategory.setToolTipText(tooltipForNegativeAnswerCategory);

        // Reason Sub Sub Section
        tArea_Reason.setLineWrap(true);
        tArea_Reason.setWrapStyleWord(true);
        tArea_Reason.setToolTipText(tooltipForReason);

        // Supporting Text Sub Sub Section
        tArea_SupportingText.setLineWrap(true);
        tArea_SupportingText.setWrapStyleWord(true);
        tArea_SupportingText.setToolTipText(tooltipForSupportingText);

        // Supporting Location Sub Sub Section
        tArea_Location.setLineWrap(true);
        tArea_Location.setWrapStyleWord(true);
        tArea_Location.setToolTipText(tooltip_Location);

        setLayout(new BorderLayout());

        /* Setup North Area */
        //JPanel baseOfNorth = new JPanel(new BorderLayout());
        Box baseOfNorth = Box.createVerticalBox();

        //NorthSubPane_0
        Box upperBaseOfNorth = Box.createHorizontalBox();
        upperBaseOfNorth.add(Box.createHorizontalGlue());
        upperBaseOfNorth.add(saveButton);
        //northSubPane_0.add(convertJson2MarkdownButton);
        //northSubPane_0.add(convertJson2TsvButton);
        upperBaseOfNorth.add(loadButton);
        upperBaseOfNorth.add(jsonFileNameEditButton);
        upperBaseOfNorth.add(new PanelMoverPane());
        baseOfNorth.add(upperBaseOfNorth);
        //baseOfNorth.add(upperBaseOfNorth, BorderLayout.NORTH);

        //NorthSubPane2
        Box lowerBaseOfNorth = Box.createHorizontalBox();
        lowerBaseOfNorth.add(tFiled_ConfidenceRating);
        lowerBaseOfNorth.add(tField_NegativeAnswerCategory);
        baseOfNorth.add(lowerBaseOfNorth);
        //northArea.add(lowerBaseOfNorth, BorderLayout.SOUTH);

        add(baseOfNorth, BorderLayout.NORTH);

        /* Center Area */

        Box baseOfCenter = Box.createVerticalBox();

        // Upper
        Box upperBaseOfCenter = Box.createHorizontalBox();
        JPanel northSubPane_1 = new JPanel(new BorderLayout());
        northSubPane_1.setPreferredSize(new Dimension(800, 150));
        JScrollPane scrollPane_Answer = new JScrollPane(tArea_Answer);
        scrollPane_Answer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Answer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        upperBaseOfCenter.add(scrollPane_Answer);
        baseOfCenter.add(upperBaseOfCenter);

        // Lower
        Box lowerBaseOfCenter = Box.createHorizontalBox();

        JScrollPane scrollPane_Reason = new JScrollPane(tArea_Reason);
        scrollPane_Reason.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Reason.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollPane_SupportingText = new JScrollPane(tArea_SupportingText);
        scrollPane_SupportingText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_SupportingText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollPane_PageLine = new JScrollPane(tArea_Location);
        scrollPane_PageLine.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_PageLine.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        lowerBaseOfCenter.add(scrollPane_Reason);
        lowerBaseOfCenter.add(scrollPane_SupportingText);
        lowerBaseOfCenter.add(scrollPane_PageLine);

        baseOfCenter.add(lowerBaseOfCenter);

        add(baseOfCenter, BorderLayout.CENTER);

        /* South Area */
        Box southBox = Box.createHorizontalBox();
        southBox.add(new JLabel(" "));

        //SouthSubPane1
        /*
        Box southSubBox1 = Box.createHorizontalBox();
        southSubBox1.add(Box.createHorizontalGlue());
        southSubBox1.add(saveButton);
        southSubBox1.add(convertJson2MarkdownButton);
        southSubBox1.add(convertJson2TsvButton);
        southSubBox1.add(loadButton);
        southSubBox1.add(jsonFileNameEditButton);
        southSubBox1.add(new PanelMoverPane(), BorderLayout.EAST);
        southBox.add(southSubBox1);
        */

        //SouthSubPane2
        Box southSubBox2 = Box.createHorizontalBox();
        southSubBox2.add(new JLabel(" "));
        southBox.add(southSubBox2);

        add(southBox, BorderLayout.SOUTH);

        /* Finalization */
        setBorder(BorderFactory.createTitledBorder("A ACNRSL Panel"));
        // BoxLayoutで適切にスクロールするために、固定の高さを設定
        setPreferredSize(new Dimension(800, 400));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
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
        tArea_Location.setText(value);
    }


    protected void loadJson() {
        Path jsonFilePath = Paths.get(jsonFolderPathStr + "/" + jsonName);
        //System.out.println("jsonFilePath: " + jsonFilePath);

        if (!jsonFilePath.toFile().exists()) {
            System.err.println("JSON file not found: " + jsonFilePath.toFile().getAbsolutePath());
            return;
        }

        // JSONから読み込み
        JsonManager jsonManager = new JsonManager(jsonFilePath.toFile());
        System.out.println("sectionName: " + sectionName);
        System.out.println("subSectionName: " + subSectionName);

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
        if (pageLine != null) tArea_Location.setText(pageLine);

        resetBackgroundColorOfTAreasTFields();

        System.out.println("Successfully loaded from " + jsonFilePath.toFile().getAbsolutePath());
    }

    protected void saveJson() {
        Path jsonFilePath = Paths.get(jsonFolderPathStr + "/" + jsonName);
        String answerText = tArea_Answer.getText();
        String confidenceRatingText = tFiled_ConfidenceRating.getText();
        String negativeAnswerCategoryText = tField_NegativeAnswerCategory.getText();
        String reasonText = tArea_Reason.getText();
        String supportingText = tArea_SupportingText.getText();
        String pageLineText = tArea_Location.getText();

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


    @Override
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
        tArea_Location.resetBackgroundColor();
        tArea_Location.updateDefaultValue();
    }


}
