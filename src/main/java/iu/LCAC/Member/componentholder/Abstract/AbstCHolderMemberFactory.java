package iu.LCAC.Member.componentholder.Abstract;

public abstract class AbstCHolderMemberFactory {

  /**
   * Create or return a component holder instance. Subclasses implement {@link #createInstance()} to
   * provide the actual holder instance.
   * Note: postInitialize() is called later by CHolderMediator.postInitializeEachMember()
   * after actionMediator is registered.
   */
  public AbstCHolderMember createCHolder(String cholder_name, String short_name) {
    AbstCHolderMember component_holder = createInstance(cholder_name, short_name);

    //postInitialize() is called later after actionMediator is set

    return component_holder;
  }

  protected abstract AbstCHolderMember createInstance(String cholder_name, String short_name);
}
