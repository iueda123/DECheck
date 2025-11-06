package iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QA1;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class QA1_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private QA1_SubTabsHolder qa1_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name) {
    if (this.qa1_SubTabsHolder == null) {
      this.qa1_SubTabsHolder = new QA1_SubTabsHolder(cholder_name, short_name);
    }
    return qa1_SubTabsHolder;
  }
}
