package iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.SC;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class SC_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private SC_SubTabsHolder sc_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name) {
    if (this.sc_SubTabsHolder == null) {
      this.sc_SubTabsHolder = new SC_SubTabsHolder(cholder_name, short_name);
    }
    return sc_SubTabsHolder;
  }
}
