package iu.LCAC.Member.action.Concretes.DEResultActions.Init_NM_Section;

import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;

public class Initialize_NM_TabPanes_ActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name) {
    if (action == null) {
      action = new Initialize_NM_TabPanes_Action(action_name, short_name);
    }
    return action;
  }
}
