package iu.LCAC.Startup;


import iu.LCAC.Startup.BasePane.BasePaneCreator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Startup.MenuBar.MenuBarCreator;
import iu.LCAC.Member.componentholder.Concretes.MainWindow.MainWindowHolder;
import iu.LCAC.Mediator.componentholder.CHolderMediatorFactory;
import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.action.ActionMediatorFactory;

public class Starter {
    /**
     * ショートカット制御機構を備えたメニューバー付きSwingFrameWork
     * <p>
     * TODO:
     * - adjustable border area
     * - status area
     * - terminal
     * - logging
     *
     * @param args
     */
    public static void main(String[] args) {

        /* **** 新しい ActionMediator を作る **** */
        ActionMediator actionMediator = ActionMediatorFactory.create();
        // Actionの生成と登録はここで完了している。

        /* **** CHolderMediator を作る **** */
        CHolderMediator cholderMediator = CHolderMediatorFactory.create();
        // Componentの生成と登録はここで完了している。


        /* **** Component-holders と Actions を連携させる **** */
        actionMediator.registerCHolderMediatorToEachMember(cholderMediator);
        cholderMediator.registerActionMediatorToEachMember(actionMediator);


        /* **** Widowを作る **** */

        /* **** 各種コンポーネントをMainWindowに埋め込む **** */
        BasePaneCreator basePaneCreator = new BasePaneCreator(cholderMediator);
        basePaneCreator.addBasePaneToMainFrame();

        /* **** メニューバー を作り、MainWindowにはめ込む **** */
        MenuBarCreator menuBarCreator = new MenuBarCreator(actionMediator, cholderMediator);
        menuBarCreator.addMenuBarToMainFrame();

        /* **** 表示 **** */
        ((MainWindowHolder) cholderMediator.getInstanceOfAMember("main_window_holder")).displayAndInitialize();

        /* **** 表示後の初期化 **** */
        cholderMediator.postInitializeEachMember();

    }
}
