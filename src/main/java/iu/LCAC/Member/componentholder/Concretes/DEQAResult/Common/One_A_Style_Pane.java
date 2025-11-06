package iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Utils.ColorChangeableTextArea;
import iu.LCAC.Utils.JsonManagerWithConflictSafe.JsonManagerWithConflictSafe;

import javax.swing.*;
import java.awt.*;

public class One_A_Style_Pane extends One_DEResult_Pane_Abs  {

    ColorChangeableTextArea tArea_Answer = new ColorChangeableTextArea("Answer");
    private final String tooltipForAnswer = "This is the answer areae.";


    public One_A_Style_Pane(String jsonFolderPathStr, String jsonName, String sectionName, String subSectionName) {
        this(jsonFolderPathStr, jsonName, sectionName, subSectionName, null, null);
    }

    public One_A_Style_Pane(String jsonFolderPathStr, String jsonName, String sectionName, String subSectionName, CHolderMediator cHolderMediator, ActionMediator actionMediator) {

        super(jsonFolderPathStr, jsonName, sectionName, subSectionName, cHolderMediator, actionMediator);

        // BashScriptを呼び出すActionMemberを利用すべく
        // CHolderMediator や ActionMediator を このクラス内に保持したいと
        // このコンストラクタを作った。しかし、どうやって CHolderMediatorやActionMediatorを
        // nullじゃない状態で持ってくるかの良い方法を思いつけず。
        //　ひとまずこのコンストラクタを残しておく。

        // Answer Sub Sub Section
        tArea_Answer.setLineWrap(true);
        tArea_Answer.setWrapStyleWord(true);
        tArea_Answer.setToolTipText(tooltipForAnswer);

        setLayout(new BorderLayout());

        /* Setup North Area */
        Box northBox = Box.createVerticalBox();

        Box northSubPanel_1 = Box.createHorizontalBox();
        northSubPanel_1.add(Box.createHorizontalGlue());
        northSubPanel_1.add(saveButton);
        //northSubBox1.add(convertJson2MarkdownButton);
        //northSubBox1.add(convertJson2TsvButton);
        northSubPanel_1.add(loadButton);
        northSubPanel_1.add(jsonFileNameEditButton);
        northSubPanel_1.add(new PanelMoverPane());
        //northBox.add(northSubPanel_1);
        add(northSubPanel_1, BorderLayout.NORTH);

        /* Setup Center Area */
        JPanel northSubPanel_2 = new JPanel(new BorderLayout());
        northSubPanel_2.setPreferredSize(new Dimension(800, 150));
        JScrollPane scrollPane_Answer = new JScrollPane(tArea_Answer);
        scrollPane_Answer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Answer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        northSubPanel_2.add(scrollPane_Answer, BorderLayout.CENTER);
        northBox.add(northSubPanel_2);
        add(northSubPanel_2, BorderLayout.CENTER);

        //add(northBox, BorderLayout.NORTH);

        /* Setup Center Area */

        /* Finalization */
        setBorder(BorderFactory.createTitledBorder(jsonName));
        // BoxLayoutで適切にスクロールするために、固定の高さを設定
        setPreferredSize(new Dimension(800, 200));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
    }


    @Override
    public void saveJson() {
        String answerText = tArea_Answer.getText();
        //System.out.println("answerText: " + answerText);
        jsonManager.setValue(sectionName + "/" + subSectionName, answerText);
        jsonManager.doSave(false);
    }

    @Override
    public void loadJson() {
        jsonManager.reloadFromDisk();
    }

    @Override
    public void resetBackgroundColorOfTAreasTFields() {
        tArea_Answer.resetBackgroundColor();
        tArea_Answer.updateDefaultValue();
    }

    @Override
    public Component getFrame() {
        return null;
    }

    @Override
    public void actionAfterSuccessfullyOpeningJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.out.println("Successfully open JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterFailingToOpenJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.err.println("Failed to open JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterSuccessfullySavingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
            System.out.println("Successfully saved JSON to " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
            resetBackgroundColorOfTAreasTFields();
    }

    @Override
    public void actionAfterFailingToSaveJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.err.println("Failed to save JSON to " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterSuccessfullyReloadingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
       String answer = jsonManagerWithConflictSafe.getValue(sectionName + "/" + subSectionName );
        //System.out.println("answer: " + answer);
        if (answer != null) tArea_Answer.setText(answer);
        resetBackgroundColorOfTAreasTFields();
        System.out.println("Successfully loaded JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterFailingToReloadJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.err.println("Failed to reload JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }
}
