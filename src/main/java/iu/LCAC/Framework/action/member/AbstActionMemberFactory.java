package iu.LCAC.Framework.action.member;

// Factoryではabstractクラスしか使ってないところがポイント。フィールドもない。

public abstract class AbstActionMemberFactory {

    public static AbstActionMemberFactory getActionFactory(String action_name) {
        System.out.println(action_name);
        AbstActionMemberFactory action_factory = null;
        try {
            action_factory = (AbstActionMemberFactory) Class.forName(action_name).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //action_factory.setActionName(action_name);

        return action_factory;
    }

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
    public abstract AbstActionMember createAction(String action_name, String short_name);

}



