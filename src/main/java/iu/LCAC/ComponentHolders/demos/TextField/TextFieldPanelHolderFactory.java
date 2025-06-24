package iu.LCAC.ComponentHolders.demos.TextField;


import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMemberFactory;

public class TextFieldPanelHolderFactory extends AbstCHolderMemberFactory {

    private TextFieldPanelHolder textFieldPanelHolder;


    @Override
    protected AbstCHolderMember createSingleton() {
        if (this.textFieldPanelHolder == null) {
            this.textFieldPanelHolder = new TextFieldPanelHolder();
        }
        return textFieldPanelHolder;
    }



}
