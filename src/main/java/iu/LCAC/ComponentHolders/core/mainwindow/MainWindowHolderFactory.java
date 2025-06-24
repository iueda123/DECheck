package iu.LCAC.ComponentHolders.core.mainwindow;


import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMemberFactory;

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
