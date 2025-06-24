package iu.LCAC.CoreActions.SetAcceleratorOnNextClick;



import iu.LCAC.Framework.componentholder.mediator.CHolderMediator;
import iu.LCAC.Framework.action.member.AbstActionMember;
import iu.LCAC.Framework.action.mediator.ActionMediator;
import iu.LCAC.Framework.componentholder.mediator.CHolderMediatorIntrfc;
import iu.LCAC.Framework.action.mediator.ActionMediatorIntrfc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SetAcceleratorOnNextClickAction extends AbstActionMember {


    public SetAcceleratorOnNextClickAction(String action_name, String short_name) {
        super(action_name, short_name);
    }


    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
    }

    AcceleratorSetupWindow ARSWindow = null;

    @Override
    public void perform(ActionEvent action_event) {
        this.actionMediator.enableAcceleratorSetupMode();

        ARSWindow = AcceleratorSetupWindowFactory.create(this.actionMediator);
        if(ARSWindow.isVisible() == false) {
            System.out.println("Accelerator Setup Window is invisible. Show it.");
            //accelerator_setup_window.getContentPane().add(new TextField(action_event.getActionCommand()));
            //accelerator_setup_window.setLocationRelativeTo(null);
            ARSWindow.setVisible(true);
        }else{
            System.out.println("Accelerator Setup Window is already visible.");
            String update_target_action_name = action_event.getActionCommand();
            ARSWindow.setNewMessage(update_target_action_name + "に対する新しいショートカットを入力してください。");
            ARSWindow.setUpdateTargetActionName(update_target_action_name);
            ARSWindow.requestFocus();
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
