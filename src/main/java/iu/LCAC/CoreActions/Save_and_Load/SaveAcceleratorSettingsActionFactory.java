package iu.LCAC.CoreActions.Save_and_Load;


import iu.LCAC.Framework.action.member.AbstActionMemberFactory;
import iu.LCAC.Framework.action.member.AbstActionMember;

public class SaveAcceleratorSettingsActionFactory extends AbstActionMemberFactory {

    @Override
    public AbstActionMember createAction(String action_name, String short_name) {
        return new SaveAcceleratorSettingsAction(action_name, short_name);
    }
}
