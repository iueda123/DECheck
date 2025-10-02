package iu.LCAC.Startup.Starter02;

import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.DEResultPanes;
import iu.LCAC.Member.componentholder.Concretes.MainWindow.MainWindowHolder;

import javax.swing.*;
import java.awt.*;
import java.io.File;

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

    // JTabbedPaneを二重にネストして、スクロール動作を確認

    // 複数のDEResultPaneを縦に並べたパネルを作成
    JPanel containerPanel = new JPanel();
    containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

    // ./json下のすべてのJSONファイルを取得
    File jsonDir = new File("./json");
    File[] jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));

    if (jsonFiles != null) {
      for (File jsonFile : jsonFiles) {
        String jsonFileName = jsonFile.getName();
        DEResultPanes pane = new DEResultPanes(jsonFileName, "reference_cohort_and_imaging", "dataset_name");
        pane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        pane.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerPanel.add(pane);
      }
    }

    // パネル全体のPreferredSizeを設定
    int totalHeight = jsonFiles != null ? jsonFiles.length * 400 : 400;
    containerPanel.setPreferredSize(new Dimension(600, totalHeight));

    // JScrollPaneでラップ
    JScrollPane scrollPane = new JScrollPane(containerPanel,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);

    // 内側のJTabbedPaneを作成
    JTabbedPane innerTabbedPane = new JTabbedPane();
    innerTabbedPane.addTab("Dataset Name", scrollPane);
    innerTabbedPane.addTab("HC N", new JLabel("HC N Tab"));

    // 外側のJTabbedPaneを作成して、内側のTabbedPaneを入れる
    JTabbedPane outerTabbedPane = new JTabbedPane();
    outerTabbedPane.addTab("Reference Cohort and Imaging", innerTabbedPane);

    basePane.add(outerTabbedPane, BorderLayout.CENTER);

    return basePane;
  }
}
