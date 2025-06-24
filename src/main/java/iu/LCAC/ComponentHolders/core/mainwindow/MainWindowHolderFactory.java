package iu.LCAC.ComponentHolders.core.mainwindow;


import iu.LCAC.Framework.Member.componentholder.AbstCHolderMember;
import iu.LCAC.Framework.Member.componentholder.AbstCHolderMemberFactory;

public class MainWindowHolderFactory extends AbstCHolderMemberFactory {
    static AbstCHolderMember mainWindow;


    @Override
    protected AbstCHolderMember createInstace(String cholder_name, String short_name) {
        if (mainWindow == null) {
            mainWindow = new MainWindowHolder(cholder_name, short_name);
        }
        return mainWindow;
    }



}
