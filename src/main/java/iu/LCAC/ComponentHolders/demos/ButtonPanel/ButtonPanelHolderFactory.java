package iu.LCAC.ComponentHolders.demos.ButtonPanel;


import iu.LCAC.Framework.Member.componentholder.AbstCHolderMember;
import iu.LCAC.Framework.Member.componentholder.AbstCHolderMemberFactory;

public class ButtonPanelHolderFactory extends AbstCHolderMemberFactory {

    private ButtonPanelHolder buttonPanelHolder;


    @Override
    protected AbstCHolderMember createInstace(String cholder_name, String short_name) {
        if (this.buttonPanelHolder == null) {
            this.buttonPanelHolder = new ButtonPanelHolder(cholder_name, short_name);
        }
        return buttonPanelHolder;
    }



}