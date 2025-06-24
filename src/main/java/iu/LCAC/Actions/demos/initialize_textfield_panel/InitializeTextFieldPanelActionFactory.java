package iu.LCAC.Actions.demos.initialize_textfield_panel;

import iu.LCAC.Framework.action.member.AbstActionMemberFactory;
import iu.LCAC.Framework.action.member.AbstActionMember;

public class InitializeTextFieldPanelActionFactory extends AbstActionMemberFactory {

    private InitializeTextFieldPanelAction action;

    @Override
    public AbstActionMember createAction(String action_name, String short_name) {
        return createSingleton(action_name, short_name);
    }

    @Override
    protected AbstActionMember createSingleton(String action_name, String short_name) {
        if (action == null) {
            action = new InitializeTextFieldPanelAction(action_name, short_name);
        }
        return action;
    }
}
