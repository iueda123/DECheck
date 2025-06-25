package iu.LCAC.Member.action.Concretes.CoreActions.SetAcceleratorOnNextClick;


import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;
import iu.LCAC.Member.action.Abstract.AbstActionMember;

public class SetAcceleratorOnNextClickActionFactory extends AbstActionMemberFactory {

    private SetAcceleratorOnNextClickAction action;

    @Override
    protected AbstActionMember createInstance(String action_name, String short_name) {
        if (action == null) {
            action = new SetAcceleratorOnNextClickAction(action_name, short_name);
        }
        return action;
    }
}
