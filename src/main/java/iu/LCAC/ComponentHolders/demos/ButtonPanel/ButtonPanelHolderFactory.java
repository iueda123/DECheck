package iu.LCAC.ComponentHolders.demos.ButtonPanel;


import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMemberFactory;

public class ButtonPanelHolderFactory extends AbstCHolderMemberFactory {

    private ButtonPanelHolder buttonPanelHolder;


    @Override
    protected AbstCHolderMember createInstace() {
        if (this.buttonPanelHolder == null) {
            this.buttonPanelHolder = new ButtonPanelHolder();
        }
        return buttonPanelHolder;
    }



}