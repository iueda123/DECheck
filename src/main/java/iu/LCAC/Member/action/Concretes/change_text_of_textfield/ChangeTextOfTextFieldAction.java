package iu.LCAC.Member.action.Concretes.change_text_of_textfield;


import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.ButtonPanel.ButtonPanelHolder;
import iu.LCAC.Member.componentholder.Concretes.TextField.TextFieldPanelHolder;
import iu.LCAC.Tools.PropertyManager_v5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ChangeTextOfTextFieldAction extends AbstActionMember {


    final static String SettingPropertyFilePath = "./settings/ActionControlledComponentFramework/settings.prop";

    public ChangeTextOfTextFieldAction(String action_name, String short_name) {
        super(action_name, short_name);
    }


    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        // Load Properties
        PropertyManager_v5 prop_manager = createPropertyManager(SettingPropertyFilePath);

        // Preparation of Components
        TextFieldPanelHolder text_field_panel_holder = (TextFieldPanelHolder) this.cholderMediator.getInstanceOfAMember("text_field_panel_holder");

        // Initialization Core
        if (text_field_panel_holder != null) {
            text_field_panel_holder.setText("おめでとう");
        } else {
            System.err.println("text_field_panel_holder is null.");
        }

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
