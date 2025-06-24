package iu.LCAC.Actions.demos.change_center_color;


import iu.LCAC.Framework.action.member.AbstActionMember;
import iu.LCAC.Framework.action.member.AbstActionMemberFactory;

public class ChangeCenterColorActionFactory extends AbstActionMemberFactory {

    @Override
    public AbstActionMember createAction(String action_name, String short_name ) {
        return new ChangeCenterColorAction(action_name, short_name );
    }

}
