package iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QAAC;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.GN.GN_SubTabsHolder;

public class QAAC_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private QAAC_SubTabsHolder qaac_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name) {
    if (this.qaac_SubTabsHolder == null) {
      this.qaac_SubTabsHolder = new QAAC_SubTabsHolder(cholder_name, short_name);
    }
    return qaac_SubTabsHolder;
  }
}
