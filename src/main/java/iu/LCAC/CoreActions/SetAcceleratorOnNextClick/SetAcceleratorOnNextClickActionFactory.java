package iu.LCAC.CoreActions.SetAcceleratorOnNextClick;


import iu.LCAC.Framework.action.member.AbstActionMemberFactory;
import iu.LCAC.Framework.action.member.AbstActionMember;

public class SetAcceleratorOnNextClickActionFactory extends AbstActionMemberFactory {

    private SetAcceleratorOnNextClickAction action;

    @Override
    public AbstActionMember createAction(String action_name, String short_name) {
        return createSingleton(action_name, short_name);
    }

    @Override
    protected AbstActionMember createSingleton(String action_name, String short_name) {
        if (action == null) {
            action = new SetAcceleratorOnNextClickAction(action_name, short_name);
        }
        return action;
    }
}
