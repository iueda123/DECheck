package iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.RCAI;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class RCAI_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private RCAI_SubTabsHolder RCAI_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.RCAI_SubTabsHolder == null) {
      this.RCAI_SubTabsHolder = new RCAI_SubTabsHolder(cholder_name, short_name, args[0]);
    }
    return RCAI_SubTabsHolder;
  }
}
