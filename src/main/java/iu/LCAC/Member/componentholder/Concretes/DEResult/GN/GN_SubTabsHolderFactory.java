package iu.LCAC.Member.componentholder.Concretes.DEResult.GN;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class GN_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private GN_SubTabsHolder gn_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name) {
    if (this.gn_SubTabsHolder == null) {
      this.gn_SubTabsHolder = new GN_SubTabsHolder(cholder_name, short_name);
    }
    return gn_SubTabsHolder;
  }
}
