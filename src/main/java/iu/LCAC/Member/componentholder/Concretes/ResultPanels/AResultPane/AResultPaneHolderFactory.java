package iu.LCAC.Member.componentholder.Concretes.ResultPanels.AResultPane;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class AResultPaneHolderFactory extends AbstCHolderMemberFactory {

  private AResultPaneHolder AResultPaneHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name) {
    if (this.AResultPaneHolder == null) {
      this.AResultPaneHolder = new AResultPaneHolder(cholder_name, short_name);
    }
    return AResultPaneHolder;
  }
}
