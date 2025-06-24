package iu.LCAC.Actions.demos.change_west_color;


import iu.LCAC.Framework.action.member.AbstActionMemberFactory;
import iu.LCAC.Framework.action.member.AbstActionMember;

public class ChangeWestColorActionFactory extends AbstActionMemberFactory {


    @Override
    public AbstActionMember createAction(String action_name, String short_name) {
        return new ChangeWestColorAction(action_name, short_name);
    }
}
