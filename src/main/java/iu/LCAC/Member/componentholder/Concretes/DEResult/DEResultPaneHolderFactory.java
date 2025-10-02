package iu.LCAC.Member.componentholder.Concretes.DEResult;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class DEResultPaneHolderFactory extends AbstCHolderMemberFactory {

  private DEResultPaneHolder DEResultPaneHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name) {
    if (this.DEResultPaneHolder == null) {
      this.DEResultPaneHolder = new DEResultPaneHolder(cholder_name, short_name);
    }
    return DEResultPaneHolder;
  }
}
