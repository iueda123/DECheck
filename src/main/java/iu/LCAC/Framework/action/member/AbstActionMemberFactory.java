package iu.LCAC.Framework.action.member;

// Factoryではabstractクラスしか使ってないところがポイント。フィールドもない。

public abstract class AbstActionMemberFactory {


    /**
     * e.g. this.ActionName = "change_color_of_center";
     */
     //protected abstract void setActionName(String action_name);

    /**
     * e.g. this.LinkedMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
     * e.g. this.LinkedMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
     * e.g. this.LinkedMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
     */
    //protected abstract void setAcceleratorKeyStroke();


    //public abstract AbstActionMember createAction(String action_name, String short_name, ActionMediator actionMediator, CHolderMediator CHolderMediator);

    /**
     * Create or return an action instance. Subclasses normally only need to
     * implement {@link #createSingleton(String, String)} which performs the
     * actual instantiation and caching.
     */
    public AbstActionMember createAction(String action_name, String short_name) {
        return createSingleton(action_name, short_name);
    }

    /**
     * Create or return a singleton instance of the action.
     * <p>
     * Actions normally receive their name and a short description when
     * instantiated.  In order to support a singleton style similar to
     * {@link iu.LCAC.Framework.componentholder.member.AbstCHolderMemberFactory},
     * subclasses can cache the created instance and return it on subsequent
     * calls.
     *
     * @param action_name the identifier of the action
     * @param short_name  the label shown in the menu
     * @return singleton instance of the action
     */
    protected abstract AbstActionMember createSingleton(String action_name, String short_name);

}



