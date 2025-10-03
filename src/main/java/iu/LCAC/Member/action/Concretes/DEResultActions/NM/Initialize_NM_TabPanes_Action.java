package iu.LCAC.Member.action.Concretes.DEResultActions.NM;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.NM_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.OneDEResultPane;
import iu.LCAC.Utils.JsonManager;

import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Initialize_NM_TabPanes_Action extends AbstActionMember {

    static final String SettingPropertyFilePath =
            "./json/Example9997.json";

    public Initialize_NM_TabPanes_Action(String action_name, String short_name) {
        super(action_name, short_name);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        String jsonFileName = SettingPropertyFilePath;
        String sectionName = "";
        String subSectionName = "";
        String[] cmd_and_args = getActionCommandAndArgs(action_event, false);
        if (cmd_and_args.length > 3) {
            // 引数からjsonNameを取得
            jsonFileName = cmd_and_args[1];
            Path jsonFilePath = Paths.get("./json/" + jsonFileName);
            if (!jsonFilePath.toFile().exists()) {
                System.err.println(jsonFilePath.toFile().getAbsolutePath() + " does not exist.");
                return;
            }
            sectionName = cmd_and_args[2];
            subSectionName = cmd_and_args[3];
            System.out.println("Loading JSON file: " + jsonFilePath.toFile().getAbsolutePath());
            System.out.println("     Section Name: " + sectionName);
            System.out.println("  Subsection Name: " + subSectionName);

            // Load Properties
            JsonManager jsonManager = new JsonManager(jsonFilePath.toFile());

            String answer = jsonManager.getValue(sectionName + "/" + subSectionName + "/Answer");
            //System.out.println(answer);
            String confidenceRating = jsonManager.getValue(sectionName + "/" + subSectionName + "/Confidence\\ Rating");
            //System.out.println(confidenceRating);
            String negativeAnswerCategory = jsonManager.getValue(sectionName + "/" + subSectionName + "/Negative\\ Answer\\ Category");
            //System.out.println(negativeAnswerCategory);
            String reason = jsonManager.getValue(sectionName + "/" + subSectionName + "/Reason");
            //System.out.println(reason);
            String supportingText = jsonManager.getValue(sectionName + "/" + subSectionName + "/Supporting\\ Text");
            //System.out.println(supportingText);
            String pageLine = jsonManager.getValue(sectionName + "/" + subSectionName + "/Page\\/Line");
            //System.out.println(pageLine);

            // Preparation of Component
            NM_SubTabsHolder subTabsHolder =
                    (NM_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_NM");

            // Initialization Core
            if (subTabsHolder != null) {

                OneDEResultPane trtgDEResultPane = subTabsHolder.getResultPane(jsonFileName, sectionName, subSectionName);
                trtgDEResultPane.setValTo_JsonName(jsonFileName);
                trtgDEResultPane.setValTo_Answer(answer);
                trtgDEResultPane.setValTo_ConfidenceRating(confidenceRating);
                trtgDEResultPane.setValTo_NegativeAnswerCategory(negativeAnswerCategory);
                trtgDEResultPane.setValTo_Reason(reason);
                trtgDEResultPane.setValTo_SupportingText(supportingText);
                trtgDEResultPane.setValTo_PageLine(pageLine);

                trtgDEResultPane.resetBackgroundColorOfTAreasTFields();

            } else {
                System.err.println("sub_tabs_holder_NM is null.");
            }
        }


        System.out.println("");
    }

    @Override
    public void setCHolderMediator(CHolderMediator cHolderMediator) {
        this.cholderMediator = cHolderMediator;
    }

    @Override
    public void setActionMediator(ActionMediator actionMediator) {
        this.actionMediator = actionMediator;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void doWorkAsMember() {
    }
}
