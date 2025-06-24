package iu.LCAC.ComponentHolders.demos.CheckboxPanel;


import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMemberFactory;

public class CheckboxPanelHolderFactory extends AbstCHolderMemberFactory {


    private CheckboxPanelHolder checkboxPanelHolder;


    @Override
    protected AbstCHolderMember createInstace() {
        if (this.checkboxPanelHolder == null) {
            this.checkboxPanelHolder = new CheckboxPanelHolder();
        }
        return checkboxPanelHolder;
    }



}
