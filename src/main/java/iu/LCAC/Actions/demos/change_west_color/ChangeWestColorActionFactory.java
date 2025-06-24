package iu.LCAC.Actions.demos.change_west_color;


import iu.LCAC.Framework.action.member.AbstActionMemberFactory;
import iu.LCAC.Framework.action.member.AbstActionMember;

public class ChangeWestColorActionFactory extends AbstActionMemberFactory {

    private ChangeWestColorAction action;

    @Override
    public AbstActionMember createAction(String action_name, String short_name) {
        return createSingleton(action_name, short_name);
    }

    @Override
    protected AbstActionMember createSingleton(String action_name, String short_name) {
        if (action == null) {
            action = new ChangeWestColorAction(action_name, short_name);
        }
        return action;
    }
}
