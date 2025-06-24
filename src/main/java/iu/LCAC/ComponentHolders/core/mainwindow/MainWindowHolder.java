package iu.LCAC.ComponentHolders.core.mainwindow;



import iu.LCAC.Framework.componentholder.mediator.CHolderMediator;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.componentholder.mediator.CHolderMediatorIntrfc;
import iu.LCAC.Framework.action.mediator.ActionMediatorIntrfc;
import iu.LCAC.Framework.action.mediator.ActionMediator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindowHolder extends AbstCHolderMember {

    private JFrame mainWindow = new JFrame();

    public MainWindowHolder() throws HeadlessException {
        mainWindow.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("The main window is Closing...");
                //mainWindow.finalize();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("The main window Closed.");
                System.exit(0);
            }
        });
    }

    @Override
    public JComponent getBaseComponent() {
        return null;
    }

    @Override
    public void finalize() {

    }

    public JFrame getMainWindow() {
        return this.mainWindow;
    }


    public void addPanelToCenter(JComponent panel) {
        mainWindow.getContentPane().add(panel, BorderLayout.CENTER);
    }

    public void addPanelToSouth(JComponent panel) {
        mainWindow.getContentPane().add(panel, BorderLayout.SOUTH);
    }

    public void addPanelToNorth(JComponent panel) {
        mainWindow.getContentPane().add(panel, BorderLayout.NORTH);
    }

    public void addPanelToWest(JComponent panel) {
        mainWindow.getContentPane().add(panel, BorderLayout.WEST);
    }

    public void addPanelToEast(JComponent panel) {
        mainWindow.getContentPane().add(panel, BorderLayout.EAST);
    }

    public void displayAndInitialize() {

        //System.out.println("displayAndInitialize()");

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                try {
                    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mainWindow.pack();
                mainWindow.setVisible(true);

                mainWindow.setLocationRelativeTo(null);

            }

        });

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

}


