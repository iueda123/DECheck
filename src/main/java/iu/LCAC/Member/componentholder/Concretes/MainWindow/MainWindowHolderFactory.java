package iu.LCAC.Member.componentholder.Concretes.MainWindow;


import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class MainWindowHolderFactory extends AbstCHolderMemberFactory {
    static AbstCHolderMember mainWindow;


    @Override
    protected AbstCHolderMember createInstance(String cholder_name, String short_name) {
        if (mainWindow == null) {
            mainWindow = new MainWindowHolder(cholder_name, short_name);
        }
        return mainWindow;
    }



}
