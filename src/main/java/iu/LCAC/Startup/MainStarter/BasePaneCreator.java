package iu.LCAC.Startup.MainStarter;

import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Concretes.MainWindow.MainWindowHolder;
import iu.LCAC.Utils.CollapsiblePanel;
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

    tabbedPane.add("2. SC", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_SC")).getBaseComponent());
    tabbedPane.setToolTipTextAt(1, "Study Characteristics");

    tabbedPane.add("3. RCAI", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_RCAI")).getBaseComponent());
    tabbedPane.setToolTipTextAt(2, "Reference Cohort and Imaging");

    tabbedPane.add("4. NM", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_NM")).getBaseComponent());
    tabbedPane.setToolTipTextAt(3, "Normative Modeling");

    tabbedPane.add("5. CAAA", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_CAAA")).getBaseComponent());
    tabbedPane.setToolTipTextAt(4, "Clinical Application and Analysis");

    tabbedPane.add("6. GN", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_GN")).getBaseComponent());
    tabbedPane.setToolTipTextAt(5, "General Notes");

    //tabbedPane.add("7. QA1", (cHolderMediator.getInstanceOfAMember("tab_of_reference_cohort_and_imaging_holder")).getBaseComponent());
    //tabbedPane.setToolTipTextAt(6, "Quality Check 1");
    //tabbedPane.add("8. QA2", (cHolderMediator.getInstanceOfAMember("tab_of_reference_cohort_and_imaging_holder")).getBaseComponent());
    //tabbedPane.setToolTipTextAt(7, "Quality Check 2");
    JComponent explanationPanelHolder = (cHolderMediator.getInstanceOfAMember("explanation_panel_holder").getBaseComponent());

    CollapsiblePanel collapsiblePanel = new CollapsiblePanel(tabbedPane, explanationPanelHolder, new JLabel("West"), new JLabel("South"), new JLabel("North"));

    collapsiblePanel.setPreferredSize(new Dimension(900, 900));

    return collapsiblePanel;
    //return new CollapsiblePanel(tabbedPane, null, new JLabel("West"), new JLabel("South"), new JLabel("North"));

    //basePane.add(tabbedPane, BorderLayout.CENTER);
    //return basePane;

  }
}
