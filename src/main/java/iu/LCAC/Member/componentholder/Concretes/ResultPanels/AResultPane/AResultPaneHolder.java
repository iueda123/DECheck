package iu.LCAC.Member.componentholder.Concretes.ResultPanels.AResultPane;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AResultPaneHolder extends AbstCHolderMember {

    JPanel panel = new JPanel();
    ArrayList<AResultPane> array_AResultPane = new ArrayList<>();

    public AResultPaneHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        array_AResultPane.add( new AResultPane("Example9997.json") );
        array_AResultPane.add( new AResultPane("Example9996.json") );


        for (AResultPane aResultPane : array_AResultPane) {
            panel.add(aResultPane);
        }


    }

    @Override
    public void postInitialize() {
        if (actionMediator != null) {

            for (AResultPane aResultPane : array_AResultPane) {
                String jsonName = aResultPane.getJsonName();
                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                    this,
                    ActionEvent.ACTION_PERFORMED,
                    "initialize_a_result_pane " + jsonName
                );
                actionMediator.getInstanceOfAMember("initialize_a_result_pane").perform(customEvent);
            }

        } else {
            System.err.println("actionMediator is null in postInitialize() @ " + this.getClass().toString());
        }
    }

    @Override
    public JComponent getBaseComponent() {
        return this.panel;
    }


    @Override
    public void setCHolderMediator(CHolderMediator cHolderMediator) {
        this.cholderMediator = cHolderMediator;
    }

    @Override
    public void setActionMediator(ActionMediator actionMediator) {
        this.actionMediator = actionMediator;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void doWorkAsMember() {
    }


    public ArrayList<AResultPane> getResultPanes() {
        return array_AResultPane;
    }
}
