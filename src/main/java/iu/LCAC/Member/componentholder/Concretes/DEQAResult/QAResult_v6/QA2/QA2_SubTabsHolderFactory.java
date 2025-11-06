package iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QA2;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.NM.NM_SubTabsHolder;

public class QA2_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private QA2_SubTabsHolder qa2_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name) {
    if (this.qa2_SubTabsHolder == null) {
      this.qa2_SubTabsHolder = new QA2_SubTabsHolder(cholder_name, short_name);
    }
    return qa2_SubTabsHolder;
  }
}
