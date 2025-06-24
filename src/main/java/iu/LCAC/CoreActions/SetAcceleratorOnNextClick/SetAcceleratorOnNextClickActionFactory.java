package iu.LCAC.CoreActions.SetAcceleratorOnNextClick;


import iu.LCAC.Framework.action.member.AbstActionMemberFactory;
import iu.LCAC.Framework.action.member.AbstActionMember;

public class SetAcceleratorOnNextClickActionFactory extends AbstActionMemberFactory {

    @Override
    public AbstActionMember createAction(String action_name, String short_name) {
        return new SetAcceleratorOnNextClickAction(action_name, short_name);
    }
}
