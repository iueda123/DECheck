package iu.LCAC.Actions.demos.change_east_color;


import iu.LCAC.Framework.action.member.AbstActionMember;
import iu.LCAC.Framework.action.member.AbstActionMemberFactory;

public class ChangeEastColorActionFactory extends AbstActionMemberFactory {

    private ChangeEastColorAction action;

    @Override
    protected AbstActionMember createInstance(String action_name, String short_name) {
        if (action == null) {
            action = new ChangeEastColorAction(action_name, short_name);
        }
        return action;
    }
}
