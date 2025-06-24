package iu.LCAC.Starters;


import iu.LCAC.Framework.componentholder.mediator.CHolderMediator;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.MenuBar.MenuBarCreator;
import iu.LCAC.ComponentHolders.core.mainwindow.MainWindowHolder;
import iu.LCAC.Framework.componentholder.mediator.CHolderMediatorFactory;
import iu.LCAC.Framework.action.mediator.ActionMediator;
import iu.LCAC.Framework.action.mediator.ActionMediatorFactory;

import javax.swing.*;
import java.awt.*;

public class MainStarter {
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

                /* **** Panels を作る **** */
                // Factory を介して各パネル（Panel_Aのサブクラス）を生成し、配置。
                JPanel base_panel = new JPanel();
                base_panel.setLayout(new BorderLayout());

                /* **** Component を配置する **** */
                base_panel.add(( cholderMediator.getInstanceOfAMember("button_panel_holder")).getBaseComponent(), BorderLayout.WEST);
                base_panel.add(( cholderMediator.getInstanceOfAMember("checkbox_panel_holder")).getBaseComponent(), BorderLayout.CENTER);
                base_panel.add(( cholderMediator.getInstanceOfAMember("text_field_panel_holder")).getBaseComponent(), BorderLayout.EAST);

                /* **** メニューバー を作り、はめ込む **** */
                MenuBarCreator menuBarCreator = new MenuBarCreator(actionMediator);
                ((MainWindowHolder) cholderMediator.getInstanceOfAMember("main_window_holder")).getMainWindow().setJMenuBar(menuBarCreator.createMenuBar());

                /* **** 土台パネルをメインパネルに埋め込む **** */
                // このとき各サブパネルに登録されているPropertyChangeListenerにシグナルが送られる。
                ((MainWindowHolder) cholderMediator.getInstanceOfAMember("main_window_holder")).addPanelToCenter(base_panel);

                /* **** 表示 **** */
                ((MainWindowHolder) cholderMediator.getInstanceOfAMember("main_window_holder")).displayAndInitialize();

                /* **** 表示後の初期化 **** */
                cholderMediator.postInitializeEachMember();

            }
}
