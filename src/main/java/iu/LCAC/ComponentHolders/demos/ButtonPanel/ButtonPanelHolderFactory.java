package iu.LCAC.ComponentHolders.demos.ButtonPanel;


import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMemberFactory;

public class ButtonPanelHolderFactory extends AbstCHolderMemberFactory {

    private ButtonPanelHolder buttonPanelHolder;

    @Override
    public AbstCHolderMember createCHolder(String cholder_name, String short_name) {

        AbstCHolderMember component_holder = createSingleton();
        component_holder.setName(cholder_name);
        component_holder.postInitialize();
        return component_holder;

    }

    @Override
    protected AbstCHolderMember createSingleton() {
        if (this.buttonPanelHolder == null) {
            this.buttonPanelHolder = new ButtonPanelHolder();
        }
        return buttonPanelHolder;
    }



}