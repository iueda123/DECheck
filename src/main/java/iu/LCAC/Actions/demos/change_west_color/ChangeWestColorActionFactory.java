package iu.LCAC.Actions.demos.change_west_color;


import iu.LCAC.Framework.Member.action.AbstActionMemberFactory;
import iu.LCAC.Framework.Member.action.AbstActionMember;

public class ChangeWestColorActionFactory extends AbstActionMemberFactory {

    private ChangeWestColorAction action;

    @Override
    protected AbstActionMember createInstance(String action_name, String short_name) {
        if (action == null) {
            action = new ChangeWestColorAction(action_name, short_name);
        }
        return action;
    }
}
