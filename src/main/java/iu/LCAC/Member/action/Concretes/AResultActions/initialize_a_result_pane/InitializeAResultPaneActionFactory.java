package iu.LCAC.Member.action.Concretes.AResultActions.initialize_a_result_pane;

import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;

public class InitializeAResultPaneActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name) {
    if (action == null) {
      action = new InitializeTextFieldPanelAction(action_name, short_name);
    }
    return action;
  }
}
