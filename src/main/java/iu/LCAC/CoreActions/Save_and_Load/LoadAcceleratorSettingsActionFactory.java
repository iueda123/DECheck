package iu.LCAC.CoreActions.Save_and_Load;


import iu.LCAC.Framework.action.member.AbstActionMemberFactory;
import iu.LCAC.Framework.action.member.AbstActionMember;

public class LoadAcceleratorSettingsActionFactory extends AbstActionMemberFactory {


    @Override
    public AbstActionMember createAction(String action_name, String short_name) {
        return new LoadAcceleratorSettingsAction(action_name, short_name);
    }
}
