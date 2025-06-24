package iu.LCAC.ComponentHolders.demos.TextField;


import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMemberFactory;

public class TextFieldPanelHolderFactory extends AbstCHolderMemberFactory {

    private TextFieldPanelHolder textFieldPanelHolder;

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
        if (this.textFieldPanelHolder == null) {
            this.textFieldPanelHolder = new TextFieldPanelHolder();
        }
        return textFieldPanelHolder;
    }



}
