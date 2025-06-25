package iu.LCAC.Member.action.Concretes.change_west_color;


import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;
import iu.LCAC.Member.action.Abstract.AbstActionMember;

public class ChangeWestColorActionFactory extends AbstActionMemberFactory {

    private AbstActionMember action;

    @Override
    protected AbstActionMember createInstance(String action_name, String short_name) {
        if (action == null) {
            action = new ChangeWestColorAction(action_name, short_name);
        }
        return action;
    }
}
