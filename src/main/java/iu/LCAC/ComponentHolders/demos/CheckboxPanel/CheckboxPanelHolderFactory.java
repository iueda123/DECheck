package iu.LCAC.ComponentHolders.demos.CheckboxPanel;


import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMemberFactory;

public class CheckboxPanelHolderFactory extends AbstCHolderMemberFactory {


    private CheckboxPanelHolder checkboxPanelHolder;


    @Override
    protected AbstCHolderMember createInstace(String cholder_name, String short_name) {
        if (this.checkboxPanelHolder == null) {
            this.checkboxPanelHolder = new CheckboxPanelHolder(cholder_name, short_name);
        }
        return checkboxPanelHolder;
    }



}
