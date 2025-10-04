package iu.LCAC.Member.componentholder.Concretes.DEResult.Common;

import iu.LCAC.Utils.ColorChangeableTextArea;
import iu.LCAC.Utils.JsonManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

public class One_A_Style_Pane extends One_DEResult_Pane_Abs {

    ColorChangeableTextArea tArea_Answer = new ColorChangeableTextArea("Study ID");
    private final String tooltipForAnswer = "Study ID. e.g. AuthorYear style.";


    public One_A_Style_Pane(String jsonFolderPathStr, String jsonName, String sectionName, String subSectionName) {
        super(jsonFolderPathStr, jsonName, sectionName, subSectionName);

        tArea_Answer.setLineWrap(true);
        tArea_Answer.setWrapStyleWord(true);
        tArea_Answer.setToolTipText(tooltipForAnswer);

        setLayout(new BorderLayout());

        /* Setup North Area */
        Box northBox = Box.createVerticalBox();
        JPanel northSubPane1 = new JPanel(new BorderLayout());
        northSubPane1.setPreferredSize(new Dimension(600, 75));

        JScrollPane scrollPane_Answer = new JScrollPane(tArea_Answer);
        scrollPane_Answer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Answer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        northSubPane1.add(scrollPane_Answer, BorderLayout.CENTER);
        northBox.add(northSubPane1);

        Box northSubBox2 = Box.createHorizontalBox();
        northSubBox2.add(Box.createHorizontalGlue());
        northSubBox2.add(saveButton);
        northSubBox2.add(loadButton);
        northSubBox2.add(jsonFileNameEditButton);
        northSubBox2.add(new PanelMoverPane());
        northBox.add(northSubBox2);

        add(northBox, BorderLayout.NORTH);

        /* Setup Center Area */

        /* Finalization */
        setBorder(BorderFactory.createTitledBorder(jsonName));
        // BoxLayoutで適切にスクロールするために、固定の高さを設定
        setPreferredSize(new Dimension(600, 125));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));

    }

    @Override
    protected void saveJson() {
        Path jsonFilePath = Paths.get(jsonFolderPathStr + "/" + jsonName);
        String answerText = tArea_Answer.getText();

        // JSONへ書き込み
        JsonManager jsonManager = new JsonManager(jsonFilePath.toFile());
        jsonManager.setValue(sectionName + "/" + subSectionName, answerText);

        boolean success = jsonManager.writeoutJson();
        if (success) {
            System.out.println("Successfully saved to " + jsonFilePath.toFile().getAbsolutePath());
            resetBackgroundColorOfTAreasTFields();
        } else {
            System.err.println("Failed to save to " + jsonFilePath.toFile().getAbsolutePath());
        }
    }

    @Override
    protected void loadJson() {
        Path jsonFilePath = Paths.get(jsonFolderPathStr + "/" + jsonName);
        //System.out.println("jsonFilePath: " + jsonFilePath);

        if (!jsonFilePath.toFile().exists()) {
            System.err.println("JSON file not found: " + jsonFilePath.toFile().getAbsolutePath());
            return;
        }

        // JSONから読み込み
        JsonManager jsonManager = new JsonManager(jsonFilePath.toFile());
        //System.out.println("sectionName: " + sectionName);
        //System.out.println("subSectionName: " + subSectionName);
        //String answer = jsonManager.getValue(sectionName + "/" + subSectionName + "/study_id");
        String answer = jsonManager.getValue(sectionName + "/" + subSectionName );
        //System.out.println("answer: " + answer);

        // 各フィールドに値を設定
        if (answer != null) tArea_Answer.setText(answer);

        resetBackgroundColorOfTAreasTFields();

        System.out.println("Successfully loaded from " + jsonFilePath.toFile().getAbsolutePath());
    }

    @Override
    public void resetBackgroundColorOfTAreasTFields() {
        tArea_Answer.resetBackgroundColor();
        tArea_Answer.updateDefaultValue();
    }

    public void setValTo_Answer(String value) {
        tArea_Answer.setText(value);
    }


}
