package iu.LCAC.Startup.Starter01;

import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Concretes.MainWindow.MainWindowHolder;
import iu.LCAC.Utils.VerticalTextTabbedPane;

import java.awt.*;
import javax.swing.*;

public class BasePaneCreator {

  CHolderMediator cHolderMediator;

  public BasePaneCreator(CHolderMediator cHolderMediator) {
    this.cHolderMediator = cHolderMediator;
  }

  public void addBasePaneToMainFrame() {
    /* **** 土台パネルをメインパネルに埋め込む **** */
    // このとき各サブパネルに登録されているPropertyChangeListenerにシグナルが送られる。
    ((MainWindowHolder) cHolderMediator.getInstanceOfAMember("main_window_holder"))
        .addPanelToCenter(createBasePane());
  }

  private JPanel createBasePane() {
    JPanel basePane = new JPanel(new BorderLayout());

    // Factory を介して各パネル（Panel_Aのサブクラス）を生成し、配置。

    /* **** Component を配置する **** */
    //basePane.add(
    //    (cHolderMediator.getInstanceOfAMember("button_panel_holder")).getBaseComponent(),
    //    BorderLayout.WEST);

    VerticalTextTabbedPane tabbedPane = new VerticalTextTabbedPane(JTabbedPane.LEFT);
    tabbedPane.add("Reference Cohort and Imaging", (cHolderMediator.getInstanceOfAMember("tab_of_reference_cohort_and_imaging_holder")).getBaseComponent());

    basePane.add(tabbedPane, BorderLayout.CENTER);


    //basePane.add(
    //    (cHolderMediator.getInstanceOfAMember("text_field_panel_holder")).getBaseComponent(),
    //    BorderLayout.EAST);

    return basePane;
  }
}
