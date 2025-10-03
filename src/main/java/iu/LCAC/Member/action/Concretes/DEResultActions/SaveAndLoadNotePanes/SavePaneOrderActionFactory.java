package iu.LCAC.Member.action.Concretes.DEResultActions.SaveAndLoadNotePanes;

import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;

public class SavePaneOrderActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name) {
    if (action == null) {
      action = new SaveNotePaneAction(action_name, short_name);
    }
    return action;
  }
}
