package iu.LCAC.Startup.Starter02;

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

    tabbedPane.add("1. SI", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_SI")).getBaseComponent());
    tabbedPane.setToolTipTextAt(0, "Study Identification");

    //tabbedPane.add("2. Study Characteristics", (cHolderMediator.getInstanceOfAMember("tab_of_reference_cohort_and_imaging_holder")).getBaseComponent());
    //tabbedPane.setToolTipTextAt(2, "Clinical Application and Analysis");

    tabbedPane.add("3. RCAI", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_RCAI")).getBaseComponent());
    tabbedPane.setToolTipTextAt(1, "Reference Cohort and Imaging");

    tabbedPane.add("4. NM", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_NM")).getBaseComponent());
    tabbedPane.setToolTipTextAt(2, "Normative Modeling");

    tabbedPane.add("5. CAAA", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_CAAA")).getBaseComponent());
    tabbedPane.setToolTipTextAt(3, "Clinical Application and Analysis");

    //tabbedPane.add("6. General Notes", (cHolderMediator.getInstanceOfAMember("tab_of_reference_cohort_and_imaging_holder")).getBaseComponent());
    //tabbedPane.add("7. QA", (cHolderMediator.getInstanceOfAMember("tab_of_reference_cohort_and_imaging_holder")).getBaseComponent());

    basePane.add(tabbedPane, BorderLayout.CENTER);


    //basePane.add(
    //    (cHolderMediator.getInstanceOfAMember("text_field_panel_holder")).getBaseComponent(),
    //    BorderLayout.EAST);

    return basePane;
  }
}
