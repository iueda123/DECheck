package iu.LCAC.Actions.demos.change_west_color;

import iu.LCAC.Framework.componentholder.mediator.CHolderMediator;
import iu.LCAC.Framework.action.member.AbstActionMember;
import iu.LCAC.ComponentHolders.demos.ButtonPanel.ButtonPanelHolder;
import iu.LCAC.ComponentHolders.demos.TextField.TextFieldPanelHolder;
import iu.LCAC.Framework.componentholder.mediator.CHolderMediatorIntrfc;
import iu.LCAC.ComponentHolders.demos.CheckboxPanel.CheckboxPanelHolder;
import iu.LCAC.Framework.action.mediator.ActionMediatorIntrfc;
import iu.LCAC.Framework.action.mediator.ActionMediator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ChangeWestColorAction extends AbstActionMember {

    public ChangeWestColorAction(String action_name, String short_name) {
        super(action_name, short_name);
    }


    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("Action_ChangeWestColor.paerform() has been called!");
        if (this.cholderMediator == null) {
            System.err.println("Starter is null!" +  "@" + this.getClass().toString());
        } else {

            //WEST
            ButtonPanelHolder button_panel_holder = (ButtonPanelHolder) this.cholderMediator.getInstanceOfAMember("button_panel_holder");
            if (button_panel_holder != null) {
                button_panel_holder.getBaseComponent().setBackground(Color.BLUE);
            } else {
                System.err.println("button_panel is null.");
            }

            //CENTER
            CheckboxPanelHolder checkbox_panel_holder = (CheckboxPanelHolder) this.cholderMediator.getInstanceOfAMember("checkbox_panel_holder");
            if (checkbox_panel_holder != null) {
                checkbox_panel_holder.getBaseComponent().setBackground(Color.WHITE);
            } else {
                System.err.println("checkbox_panel is null.");
            }

            //EAST
            TextFieldPanelHolder textfield_panel_holder = (TextFieldPanelHolder) this.cholderMediator.getInstanceOfAMember("text_field_panel_holder");
            if (textfield_panel_holder != null) {
                textfield_panel_holder.getBaseComponent().setBackground(Color.WHITE);
            } else {
                System.err.println("textfield_panel is null.");
            }

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


}
