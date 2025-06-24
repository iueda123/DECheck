package iu.LCAC.ComponentHolders.demos.CheckboxPanel;


import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMemberFactory;

public class CheckboxPanelHolderFactory extends AbstCHolderMemberFactory {


    private CheckboxPanelHolder checkboxPanelHolder;

    @Override
    public AbstCHolderMember createCHolder(String cholder_name, String short_name) {
        AbstCHolderMember component_holder = createSingleton();
        //registerComponent(component_holder);
        component_holder.setName(cholder_name);
        component_holder.finalize();
        return component_holder;
    }

    @Override
    protected AbstCHolderMember createSingleton() {
        if (this.checkboxPanelHolder == null) {
            this.checkboxPanelHolder = new CheckboxPanelHolder();
        }
        return checkboxPanelHolder;
    }



}
