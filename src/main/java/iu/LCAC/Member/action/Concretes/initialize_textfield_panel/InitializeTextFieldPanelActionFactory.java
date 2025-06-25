package iu.LCAC.Member.action.Concretes.initialize_textfield_panel;

import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;
import iu.LCAC.Member.action.Abstract.AbstActionMember;

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
