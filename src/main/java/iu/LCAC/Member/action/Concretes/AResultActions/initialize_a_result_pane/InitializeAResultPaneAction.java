package iu.LCAC.Member.action.Concretes.AResultActions.initialize_a_result_pane;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.AResultPane.AResultPaneHolder;
import iu.LCAC.Member.componentholder.Concretes.Sample.TextField.TextFieldPanelHolder;
import iu.LCAC.Tools.PropertyManager_v5;

import java.awt.event.ActionEvent;
import java.io.File;

public class InitializeAResultPaneAction extends AbstActionMember {

  static final String SettingPropertyFilePath =
      "./json/Example9997.json";

  public InitializeAResultPaneAction(String action_name, String short_name) {
    super(action_name, short_name);
  }

  @Override
  protected void setAcceleratorKeyStroke() {}

  @Override
  public void perform(ActionEvent action_event) {
    System.out.println("perform() in " + this.getClass().toString() + " was called.");

    // Load Properties
    //PropertyManager_v5 prop_manager = createPropertyManager(SettingPropertyFilePath);
    JsonManager jsonManager = createPropertyManager(SettingPropertyFilePath);

    String answer = jsonManager.getValue("reference_cohort_and_imaging/dataset_name/Answer");
    String confidenceRating = jsonManager.getValue("reference_cohort_and_imaging/dataset_name/Confidence\\ Rating");
    String negativeAnswerCategory = jsonManager.getValue("reference_cohort_and_imaging/Negative\\ Answer\\ Category");
    String reason = jsonManager.getValue("reference_cohort_and_imaging/dataset_name/Reason");
    String supportingText = jsonManager.getValue("reference_cohort_and_imaging/dataset_name/Supporting\\ Text");
    String pageLine = jsonManager.getValue("reference_cohort_and_imaging/dataset_name/Page\\/Line");

    // Preparation of Component
    AResultPaneHolder aResultPaneHolder =
            (AResultPaneHolder) this.cholderMediator.getInstanceOfAMember("a_result_pane_holder");

    // Initialization Core
    if (aResultPaneHolder != null) {

      aResultPaneHolder.setValTo_JsonName(new File(SettingPropertyFilePath).getName());
      aResultPaneHolder.setValTo_Answer(answer);
      aResultPaneHolder.setValTo_ConfidenceRating(confidenceRating);
      aResultPaneHolder.setValTo_NegativeAnswerCategory(negativeAnswerCategory);
      aResultPaneHolder.setValTo_Reason(reason);
      aResultPaneHolder.setValTo_SupportingText(supportingText);
      aResultPaneHolder.setValTo_PageLine(pageLine);

    } else {
      System.err.println("textfield_panel is null.");
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
  public void initialize() {}

  @Override
  public void doWorkAsMember() {}
}
