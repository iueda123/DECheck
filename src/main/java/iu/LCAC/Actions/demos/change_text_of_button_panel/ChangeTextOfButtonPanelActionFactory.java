package iu.LCAC.Actions.demos.change_text_of_button_panel;


import iu.LCAC.Framework.action.member.AbstActionMemberFactory;
import iu.LCAC.Framework.action.member.AbstActionMember;

public class ChangeTextOfButtonPanelActionFactory extends AbstActionMemberFactory {

    @Override
    public AbstActionMember createAction(String action_name, String short_name) {
        return new ChangeTextOfButtonPanelAction(action_name, short_name);
    }
}
