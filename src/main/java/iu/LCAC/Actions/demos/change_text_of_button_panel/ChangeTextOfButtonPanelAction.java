package iu.LCAC.Actions.demos.change_text_of_button_panel;


import iu.LCAC.Framework.componentholder.mediator.CHolderMediator;
import iu.LCAC.Framework.action.member.AbstActionMember;
import iu.LCAC.ComponentHolders.demos.ButtonPanel.ButtonPanelHolder;
import iu.LCAC.Framework.action.mediator.ActionMediator;
import iu.LCAC.Framework.componentholder.mediator.CHolderMediatorIntrfc;
import iu.LCAC.Tools.PropertyManager_v5;
import iu.LCAC.Framework.action.mediator.ActionMediatorIntrfc;

import java.awt.event.ActionEvent;

public class ChangeTextOfButtonPanelAction extends AbstActionMember {


    final static String SettingPropertyFilePath = "./settings/ActionControlledComponentFramework/settings.prop";

    public ChangeTextOfButtonPanelAction(String action_name, String short_name) {
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

        // Preparation of Components
        ButtonPanelHolder button_panel_holder = (ButtonPanelHolder) this.cholderMediator.getInstanceOfAMember("button_panel_holder");

        // Initialization Core
        if (button_panel_holder != null) {
            button_panel_holder.setTextToTheButton(prop_manager.getValueOrCreateNew("default_text"));
        } else {
            System.err.println("button_panel is null.");
        }

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

    @Override
    public String getName() {
        return null;
    }

}
