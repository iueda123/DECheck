package iu.LCAC.Member.action.Concretes.DEResultActions.ConvertJson;

import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;

public class ConvertJson2TsvActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name) {
    if (action == null) {
      action = new ConvertJson2TsvAction(action_name, short_name);
    }
    return action;
  }
}
