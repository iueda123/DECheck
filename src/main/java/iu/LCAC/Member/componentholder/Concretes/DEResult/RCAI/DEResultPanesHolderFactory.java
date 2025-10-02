package iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class DEResultPanesHolderFactory extends AbstCHolderMemberFactory {

  private DEResultPanesHolder DEResultPanesHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name) {
    if (this.DEResultPanesHolder == null) {
      this.DEResultPanesHolder = new DEResultPanesHolder(cholder_name, short_name);
    }
    return DEResultPanesHolder;
  }
}
