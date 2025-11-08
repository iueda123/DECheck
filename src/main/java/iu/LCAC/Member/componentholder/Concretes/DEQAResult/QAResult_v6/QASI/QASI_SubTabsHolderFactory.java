package iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QASI;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class QASI_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private QASI_SubTabsHolder qasi_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.qasi_SubTabsHolder == null) {
      this.qasi_SubTabsHolder = new QASI_SubTabsHolder(cholder_name, short_name, args[0]);
    }
    return qasi_SubTabsHolder;
  }
}
