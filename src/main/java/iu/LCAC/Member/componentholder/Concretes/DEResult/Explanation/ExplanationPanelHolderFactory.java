package iu.LCAC.Member.componentholder.Concretes.DEResult.Explanation;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class ExplanationPanelHolderFactory extends AbstCHolderMemberFactory {

  private ExplanationPanelHolder textFieldPanelHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name) {
    if (this.textFieldPanelHolder == null) {
      this.textFieldPanelHolder = new ExplanationPanelHolder(cholder_name, short_name);
    }
    return textFieldPanelHolder;
  }
}
