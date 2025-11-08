package iu.LCAC.Member.action.Concretes.DEResultActions.SaveAndLoadNotePaneTexts;

import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;

public class SaveNotePaneTextsActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
    if (action == null) {
      action = new SaveNotePaneTextsAction(action_name, short_name, args[0]);
    }
    return action;
  }
}
