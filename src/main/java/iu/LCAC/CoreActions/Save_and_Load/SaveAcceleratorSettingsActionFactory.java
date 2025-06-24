package iu.LCAC.CoreActions.Save_and_Load;


import iu.LCAC.Framework.action.member.AbstActionMemberFactory;
import iu.LCAC.Framework.action.member.AbstActionMember;

public class SaveAcceleratorSettingsActionFactory extends AbstActionMemberFactory {

    private SaveAcceleratorSettingsAction action;

    @Override
    public AbstActionMember createAction(String action_name, String short_name) {
        return createSingleton(action_name, short_name);
    }

    @Override
    protected AbstActionMember createSingleton(String action_name, String short_name) {
        if (action == null) {
            action = new SaveAcceleratorSettingsAction(action_name, short_name);
        }
        return action;
    }
}
