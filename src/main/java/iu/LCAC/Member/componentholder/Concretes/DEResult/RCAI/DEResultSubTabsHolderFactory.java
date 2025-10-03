package iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class DEResultSubTabsHolderFactory extends AbstCHolderMemberFactory {

  private DEResultSubTabsHolder DEResultSubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name) {
    if (this.DEResultSubTabsHolder == null) {
      this.DEResultSubTabsHolder = new DEResultSubTabsHolder(cholder_name, short_name);
    }
    return DEResultSubTabsHolder;
  }
}
