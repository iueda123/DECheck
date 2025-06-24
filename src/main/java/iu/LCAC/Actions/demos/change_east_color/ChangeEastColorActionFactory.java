package iu.LCAC.Actions.demos.change_east_color;


import iu.LCAC.Framework.action.member.AbstActionMember;
import iu.LCAC.Framework.action.member.AbstActionMemberFactory;

public class ChangeEastColorActionFactory extends AbstActionMemberFactory {

    @Override
    public AbstActionMember createAction(String action_name, String short_name ) {
        return new ChangeEastColorAction(action_name, short_name);
    }
}
