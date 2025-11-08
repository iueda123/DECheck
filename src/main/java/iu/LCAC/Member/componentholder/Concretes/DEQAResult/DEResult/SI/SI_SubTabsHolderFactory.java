package iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.SI;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class SI_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private SI_SubTabsHolder SI_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.SI_SubTabsHolder == null) {
      this.SI_SubTabsHolder = new SI_SubTabsHolder(cholder_name, short_name, args[0]);
    }
    return SI_SubTabsHolder;
  }
}
