package iu.LCAC.Member.action.Concretes.initialize_textfield_panel;


import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.TextField.TextFieldPanelHolder;
import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Tools.PropertyManager_v5;

import java.awt.event.ActionEvent;

public class InitializeTextFieldPanelAction extends AbstActionMember {


    final static String SettingPropertyFilePath = "./settings/ActionControlledComponentFramework/settings.prop";

    public InitializeTextFieldPanelAction(String action_name, String short_name) {
        super(action_name, short_name);
    }



    @Override
    protected void setAcceleratorKeyStroke() {

    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        // Load Properties
        PropertyManager_v5 prop_manager = createPropertyManager(SettingPropertyFilePath);
        String default_text = prop_manager.getValueOrCreateNew("default_text");

        // Preparation of Component
        TextFieldPanelHolder textfield_panel = (TextFieldPanelHolder) this.cholderMediator.getInstanceOfAMember("text_field_panel_holder");

        // Initialization Core
        if (textfield_panel != null) {
            textfield_panel.setText(default_text);
        } else {
            System.err.println("textfield_panel is null.");
        }

        System.out.println("");
    }

    @Override
    public void setCHolderMediator(CHolderMediator cHolderMediator) {
        this.cholderMediator =  cHolderMediator;
    }

    @Override
    public void setActionMediator(ActionMediator actionMediator) {
        this.actionMediator =  actionMediator;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void doWorkAsMember() {

    }

}
