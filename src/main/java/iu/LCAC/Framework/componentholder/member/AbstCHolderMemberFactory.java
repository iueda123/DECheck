package iu.LCAC.Framework.componentholder.member;


public abstract class AbstCHolderMemberFactory {

    public static AbstCHolderMemberFactory getCHolderMemberFactory(String component_holder_name) {
        System.out.println("component_holder_name: " + component_holder_name);
        AbstCHolderMemberFactory cholder_factory = null;
        try {
            cholder_factory = (AbstCHolderMemberFactory) Class.forName(component_holder_name).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cholder_factory;
    }

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


    /*
    public final AbstCHolderMember create(String componentName) {
        AbstCHolderMember component_holder = createSingleton();

        //registerComponent(component_holder);
        component_holder.setName(componentName);
        component_holder.postInitialize();
        return component_holder;
    }
    */

    protected abstract AbstCHolderMember createSingleton();
    //protected abstract void registerComponent(ACComponentHolder_A component_holder);

}
