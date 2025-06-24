package iu.LCAC.Actions.demos.change_center_color;


import iu.LCAC.Framework.Member.action.AbstActionMember;
import iu.LCAC.Framework.Member.action.AbstActionMemberFactory;

public class ChangeCenterColorActionFactory extends AbstActionMemberFactory {

    private ChangeCenterColorAction action;

    @Override
    protected AbstActionMember createInstance(String action_name, String short_name) {
        if (action == null) {
            action = new ChangeCenterColorAction(action_name, short_name);
        }
        return action;
    }

}
