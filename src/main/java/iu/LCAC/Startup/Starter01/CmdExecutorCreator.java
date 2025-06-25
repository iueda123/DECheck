package iu.LCAC.Startup.Starter01;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Concretes.MainWindow.MainWindowHolder;

import javax.swing.*;
import java.awt.*;

public class CmdExecutorCreator {
    private final CHolderMediator cholderMediator;
    private final ActionMediator actionMediator;



    public CmdExecutorCreator(ActionMediator actionMediator, CHolderMediator cholderMediator) {
        this.actionMediator = actionMediator;
        this.cholderMediator = cholderMediator;
    }

    public void addCmdExecutorToMainFrame() {
        ((MainWindowHolder) cholderMediator.getInstanceOfAMember("main_window_holder")).addPanelToSouth(createCmdExecutor());
    }

    private JPanel createCmdExecutor(){
        JPanel basePane = new JPanel();
        basePane.setBackground(Color.ORANGE);
        return basePane;
    }
}
