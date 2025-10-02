package iu.LCAC.Member.action.Concretes.AResultActions.initialize_a_result_pane;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.ResultPanels.AResultPane.AResultPane;
import iu.LCAC.Member.componentholder.Concretes.ResultPanels.AResultPane.AResultPaneHolder;
import iu.LCAC.Tools.JsonManager;

import java.awt.event.ActionEvent;
import java.io.File;

public class InitializeAResultPaneAction extends AbstActionMember {

    static final String SettingPropertyFilePath =
            "./json/Example9997.json";

    public InitializeAResultPaneAction(String action_name, String short_name) {
        super(action_name, short_name);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        String jsonFileName = SettingPropertyFilePath;
        String[] cmd_and_args = getActionCommandAndArgs(action_event, false);
        if (cmd_and_args.length > 1) {
            // 引数からjsonNameを取得
            jsonFileName = "./json/" + cmd_and_args[1];
            System.out.println("Loading JSON file: " + jsonFileName);

            // Load Properties
            JsonManager jsonManager = new JsonManager(jsonFileName);

            String answer = jsonManager.getValue("reference_cohort_and_imaging/dataset_name/Answer");
            //System.out.println(answer);
            String confidenceRating = jsonManager.getValue("reference_cohort_and_imaging/dataset_name/Confidence\\ Rating");
            //System.out.println(confidenceRating);
            String negativeAnswerCategory = jsonManager.getValue("reference_cohort_and_imaging/dataset_name/Negative\\ Answer\\ Category");
            //System.out.println(negativeAnswerCategory);
            String reason = jsonManager.getValue("reference_cohort_and_imaging/dataset_name/Reason");
            //System.out.println(reason);
            String supportingText = jsonManager.getValue("reference_cohort_and_imaging/dataset_name/Supporting\\ Text");
            //System.out.println(supportingText);
            String pageLine = jsonManager.getValue("reference_cohort_and_imaging/dataset_name/Page\\/Line");
            //System.out.println(pageLine);

            // Preparation of Component
            AResultPaneHolder aResultPaneHolder =
                    (AResultPaneHolder) this.cholderMediator.getInstanceOfAMember("a_result_pane_holder");

            // Initialization Core
            if (aResultPaneHolder != null) {
                String targetJsonName = new File(jsonFileName).getName();

                for (AResultPane resultPane : aResultPaneHolder.getResultPanes()) {
                    // このresultPaneのjsonNameと一致する場合のみ更新
                    if (resultPane.getJsonName().equals(targetJsonName)) {
                        resultPane.setValTo_JsonName(targetJsonName);
                        resultPane.setValTo_Answer(answer);
                        resultPane.setValTo_ConfidenceRating(confidenceRating);
                        resultPane.setValTo_NegativeAnswerCategory(negativeAnswerCategory);
                        resultPane.setValTo_Reason(reason);
                        resultPane.setValTo_SupportingText(supportingText);
                        resultPane.setValTo_PageLine(pageLine);
                        break; // 該当するパネルを更新したらループを抜ける
                    }
                }
            } else {
                System.err.println("aResultPaneHolder is null.");
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
