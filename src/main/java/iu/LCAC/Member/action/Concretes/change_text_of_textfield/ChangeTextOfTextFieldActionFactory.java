package iu.LCAC.Member.action.Concretes.change_text_of_textfield;


import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;
import iu.LCAC.Member.action.Concretes.change_text_of_button_panel.ChangeTextOfButtonPanelAction;

public class ChangeTextOfTextFieldActionFactory extends AbstActionMemberFactory {

    private AbstActionMember action;

    @Override
    protected AbstActionMember createInstance(String action_name, String short_name) {
        if (action == null) {
            action = new ChangeTextOfTextFieldAction(action_name, short_name);
        }
        return action;
    }
}
