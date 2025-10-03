package iu.LCAC.Member.componentholder.Concretes.DEResult.NM;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class NM_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private NM_SubTabsHolder NM_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name) {
    if (this.NM_SubTabsHolder == null) {
      this.NM_SubTabsHolder = new NM_SubTabsHolder(cholder_name, short_name);
    }
    return NM_SubTabsHolder;
  }
}
