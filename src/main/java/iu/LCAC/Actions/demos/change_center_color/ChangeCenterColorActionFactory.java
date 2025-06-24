package iu.LCAC.Actions.demos.change_center_color;


import iu.LCAC.Framework.action.member.AbstActionMember;
import iu.LCAC.Framework.action.member.AbstActionMemberFactory;

public class ChangeCenterColorActionFactory extends AbstActionMemberFactory {

    private ChangeCenterColorAction action;

    @Override
    public AbstActionMember createAction(String action_name, String short_name ) {
        return createSingleton(action_name, short_name );
    }

    @Override
    protected AbstActionMember createSingleton(String action_name, String short_name) {
        if (action == null) {
            action = new ChangeCenterColorAction(action_name, short_name);
        }
        return action;
    }

}
