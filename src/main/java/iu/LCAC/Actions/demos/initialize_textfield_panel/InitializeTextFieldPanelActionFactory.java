package iu.LCAC.Actions.demos.initialize_textfield_panel;


import iu.LCAC.Framework.action.member.AbstActionMemberFactory;
import iu.LCAC.Framework.action.member.AbstActionMember;

public class InitializeTextFieldPanelActionFactory extends AbstActionMemberFactory {

    @Override
    public AbstActionMember createAction(String action_name, String short_name) {
        return new InitializeTextFieldPanelAction(action_name, short_name);
    }
}
