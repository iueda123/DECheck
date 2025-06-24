package iu.LCAC.Actions.demos.initialize_textfield_panel;

import iu.LCAC.Framework.Member.action.AbstActionMemberFactory;
import iu.LCAC.Framework.Member.action.AbstActionMember;

public class InitializeTextFieldPanelActionFactory extends AbstActionMemberFactory {

    private InitializeTextFieldPanelAction action;

    @Override
    protected AbstActionMember createInstance(String action_name, String short_name) {
        if (action == null) {
            action = new InitializeTextFieldPanelAction(action_name, short_name);
        }
        return action;
    }
}
