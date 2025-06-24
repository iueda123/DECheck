package iu.LCAC.ComponentHolders.demos.CheckboxPanel;


import iu.LCAC.Framework.componentholder.mediator.CHolderMediator;
import iu.LCAC.Framework.componentholder.mediator.CHolderMediatorIntrfc;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.action.mediator.ActionMediatorIntrfc;
import iu.LCAC.Framework.action.mediator.ActionMediator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CheckboxPanelHolder extends AbstCHolderMember {

    JPanel panel = new JPanel();

    JCheckBox SampleCheckBox = new JCheckBox("Change Color");

    public CheckboxPanelHolder(String cholder_name, String short_name)
    {
        super(cholder_name, short_name);

        SampleCheckBox.addActionListener(new SampleSelectionChangeListener());
        SampleCheckBox.addPropertyChangeListener(new SamplePropertyChangeListener());

        panel.add(SampleCheckBox);

    }

    @Override
    public JComponent getBaseComponent() {
        return this.panel;
    }


    @Override
    public void postInitialize() {

    }


    @Override
    public void setActnMediator(ActionMediatorIntrfc actionMediatorIntrfc) {
        this.actionMediator = (ActionMediator) actionMediatorIntrfc;
    }

    @Override
    public void doWorkAsActnMember() {

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



    private class SampleSelectionChangeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("Something of SampleCheckBox has changed.");

            if (actionMediator != null) {
                actionMediator.getInstanceOfAMember("change_color_of_center").perform(new ActionEvent(this, 0, "Property of SampleCheckBox has changed."));
            } else {
                System.err.println("Action Starter is null!" +  "@" + this.getClass().toString());
            }
        }
    }


    /**
     * コンポーネントのプロパティが変更された時に呼び出される。
     * コンポーネントのプロパティが変更されるときとは、
     * フォント、前景色、背景色が変更されたとき、
     * コンポーネント生成時などである。
     *
     * 起動時に行わせたい処理を書き込むと良いのだと思う。
     * TODO:起動時に2回呼び出されるバグ？あり。大きな問題はないので一旦放置。なんか気持ち悪い。
     */
    private class SamplePropertyChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
            System.out.println("Property of SampleCheckBox has changed.");

            if (actionMediator != null) {
                System.out.println("Action Starter is not null!" + "@" + this.getClass().toString() );
                actionMediator.getInstanceOfAMember("change_color_of_center").perform(
                        new ActionEvent(this, 0, "Property of SampleCheckBox has changed.")
                );
            } else {
                System.err.println("Action Starter is null!" + "@" + this.getClass().toString() );
            }
        }
    }
}
