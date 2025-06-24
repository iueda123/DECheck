package iu.LCAC.ComponentHolders.demos.TextField;



import iu.LCAC.Framework.componentholder.mediator.CHolderMediator;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.action.mediator.ActionMediator;
import iu.LCAC.Framework.componentholder.mediator.CHolderMediatorIntrfc;
import iu.LCAC.Framework.action.mediator.ActionMediatorIntrfc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TextFieldPanelHolder extends AbstCHolderMember {

    JPanel panel = new JPanel();
    JTextField SampleTextField = new JTextField("please write something.");

    public TextFieldPanelHolder() {
        panel.add(SampleTextField);
        SampleTextField.addPropertyChangeListener(new SamplePropertyChangeListener());
    }

    @Override
    public JComponent getBaseComponent() {
        return this.panel;
    }




    @Override
    public void postInitialize() {

    }

    public void setText(String text){
        SampleTextField.setText(text);
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
            System.out.println("Property of SampleTextField has changed.");

            if (actionMediator != null) {
                System.out.println("Action Starter is not null! @" + this.getClass().toString());
                actionMediator.getInstanceOfAMember("initialize_sample_text_field").perform(new ActionEvent(this, 0, "Property of SampleTextField has changed."));
            } else {
                System.err.println("Action Starter is null!" + "@" + this.getClass().toString());
            }
        }
    }
}
