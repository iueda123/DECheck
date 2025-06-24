package iu.LCAC.Actions.demos.initialize_textfield_panel;


import iu.LCAC.Framework.componentholder.mediator.CHolderMediator;
import iu.LCAC.Framework.action.member.AbstActionMember;
import iu.LCAC.ComponentHolders.demos.TextField.TextFieldPanelHolder;
import iu.LCAC.Framework.action.mediator.ActionMediator;
import iu.LCAC.Framework.componentholder.mediator.CHolderMediatorIntrfc;
import iu.LCAC.Tools.PropertyManager_v5;
import iu.LCAC.Framework.action.mediator.ActionMediatorIntrfc;

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
    public void setActnMediator(ActionMediatorIntrfc actionMediatorIntrfc) {
        this.actionMediator = (ActionMediator) actionMediatorIntrfc;
    }

    @Override
    public void doWorkAsActnMember() {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void setCHMediator(CHolderMediatorIntrfc CHolderMediatorIntrfc) {
        this.cholderMediator = (CHolderMediator) CHolderMediatorIntrfc;
    }

    @Override
    public void doWorkAsCHMember() {

    }


}
