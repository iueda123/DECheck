package iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.CAAA;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class CAAA_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private CAAA_SubTabsHolder caaa_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.caaa_SubTabsHolder == null) {
      this.caaa_SubTabsHolder = new CAAA_SubTabsHolder(cholder_name, short_name, args[0]);
    }
    return caaa_SubTabsHolder;
  }
}
