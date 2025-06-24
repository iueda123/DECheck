package iu.LCAC.ComponentHolders.core.mainwindow;


import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMemberFactory;

public class MainWindowHolderFactory extends AbstCHolderMemberFactory {
    static AbstCHolderMember mainWindow;

    @Override
    public AbstCHolderMember createCHolder(String cholder_name, String short_name) {
        //return new MindMapPanelHolder();

        AbstCHolderMember component_holder = createSingleton();
        //registerComponent(component_holder);
        component_holder.setName(cholder_name);
        component_holder.postInitialize();
        return component_holder;

    }

    @Override
    protected AbstCHolderMember createSingleton( ) {
        if (mainWindow == null) {
            mainWindow = new MainWindowHolder();
        }
        return mainWindow;
    }



}
