package iu.LCAC.Framework.componentholder.member;


public abstract class AbstCHolderMemberFactory {


    /**
     * Create or return a component holder instance. Subclasses implement
     * {@link #createSingleton()} to provide the actual holder instance which is
     * then initialized here.
     */
    public AbstCHolderMember createCHolder(String cholder_name, String short_name) {
        AbstCHolderMember component_holder = createSingleton();
        component_holder.setName(cholder_name);
        component_holder.postInitialize();
        return component_holder;
    }

    protected abstract AbstCHolderMember createSingleton();

}
