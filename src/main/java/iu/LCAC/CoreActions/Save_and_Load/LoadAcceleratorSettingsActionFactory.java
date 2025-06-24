package iu.LCAC.CoreActions.Save_and_Load;

import iu.LCAC.Framework.action.member.AbstActionMemberFactory;
import iu.LCAC.Framework.action.member.AbstActionMember;

public class LoadAcceleratorSettingsActionFactory extends AbstActionMemberFactory {

    private LoadAcceleratorSettingsAction action;

    @Override
    protected AbstActionMember createInstance(String action_name, String short_name) {
        if (action == null) {
            action = new LoadAcceleratorSettingsAction(action_name, short_name);
        }
        return action;
    }
}
