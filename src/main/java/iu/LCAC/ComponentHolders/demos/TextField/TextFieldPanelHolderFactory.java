package iu.LCAC.ComponentHolders.demos.TextField;


import iu.LCAC.Framework.Member.componentholder.AbstCHolderMember;
import iu.LCAC.Framework.Member.componentholder.AbstCHolderMemberFactory;

public class TextFieldPanelHolderFactory extends AbstCHolderMemberFactory {

    private TextFieldPanelHolder textFieldPanelHolder;


    @Override
    protected AbstCHolderMember createInstace(String cholder_name, String short_name) {
        if (this.textFieldPanelHolder == null) {
            this.textFieldPanelHolder = new TextFieldPanelHolder(cholder_name, short_name);
        }
        return textFieldPanelHolder;
    }



}
