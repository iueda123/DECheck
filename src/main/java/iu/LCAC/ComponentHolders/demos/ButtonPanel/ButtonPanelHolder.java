package iu.LCAC.ComponentHolders.demos.ButtonPanel;



import iu.LCAC.Framework.componentholder.mediator.CHolderMediator;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.componentholder.mediator.CHolderMediatorIntrfc;
import iu.LCAC.Framework.action.mediator.ActionMediatorIntrfc;
import iu.LCAC.Framework.action.mediator.ActionMediator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonPanelHolder extends AbstCHolderMember {

    JPanel panel = new JPanel();

    JButton sampleButton = new JButton("Button");

    public ButtonPanelHolder(String cholder_name, String short_name)
    {
        super(cholder_name, short_name);

        sampleButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionMediator != null) {
                    actionMediator.getInstanceOfAMember("change_color_of_center").perform(actionEvent);
                } else {
                    System.err.println("Action Starter is null! @ " + this.getClass().toString());
                }
            }
        });

        panel.add(sampleButton);
    }



    @Override
    public void postInitialize() {

    }

    @Override
    public JComponent getBaseComponent(){
        return this.panel;
    }

    public void setTextToTheButton(String text) {
        this.sampleButton.setText(text);
    }


    @Override
    public void setCHMediator(CHolderMediatorIntrfc CHolderMediatorIntrfc) {
        this.cholderMediator = (CHolderMediator) CHolderMediatorIntrfc;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void doWorkAsCHMember() {

    }


    @Override
    public void setActnMediator(ActionMediatorIntrfc actionMediatorIntrfc) {
        this.actionMediator = (ActionMediator) actionMediatorIntrfc;
    }

    @Override
    public void doWorkAsActnMember() {

    }
}
