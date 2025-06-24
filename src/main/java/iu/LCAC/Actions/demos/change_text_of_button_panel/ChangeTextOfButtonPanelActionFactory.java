package iu.LCAC.Actions.demos.change_text_of_button_panel;


import iu.LCAC.Framework.action.member.AbstActionMemberFactory;
import iu.LCAC.Framework.action.member.AbstActionMember;

public class ChangeTextOfButtonPanelActionFactory extends AbstActionMemberFactory {

    private ChangeTextOfButtonPanelAction action;

    @Override
    protected AbstActionMember createInstance(String action_name, String short_name) {
        if (action == null) {
            action = new ChangeTextOfButtonPanelAction(action_name, short_name);
        }
        return action;
    }
}
