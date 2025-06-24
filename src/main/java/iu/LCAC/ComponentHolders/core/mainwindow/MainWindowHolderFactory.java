package iu.LCAC.ComponentHolders.core.mainwindow;


import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMemberFactory;

public class MainWindowHolderFactory extends AbstCHolderMemberFactory {
    static AbstCHolderMember mainWindow;


    @Override
    protected AbstCHolderMember createSingleton( ) {
        if (mainWindow == null) {
            mainWindow = new MainWindowHolder();
        }
        return mainWindow;
    }



}
