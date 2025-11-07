package iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.Explanation;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExplanationPanelHolder extends AbstCHolderMember {

  private Path guideFilePath_of_DE = Paths.get("./settings/Guides/DE_Guide_v9_jp.md");
  private Path guideFilePath_of_QA = Paths.get("./settings/Guides/QA_Guide_v6_jp.md");

  JPanel basePanel = new JPanel(new BorderLayout());
  JTabbedPane tabbedPane = new JTabbedPane();
  JTextArea explanationTextArea_for_DE = new JTextArea("DE EXPLANATION");
  JTextArea explanationTextArea_for_QA = new JTextArea("QA EXPLANATION");

  public ExplanationPanelHolder(String cholder_name, String short_name) {
    super(cholder_name, short_name);

    // DE
    JScrollPane scrollPane_for_DE = new JScrollPane(explanationTextArea_for_DE);
    explanationTextArea_for_DE.setEditable(false);
    scrollPane_for_DE.setPreferredSize(new Dimension(500, 9000));
    scrollPane_for_DE.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane_for_DE.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    tabbedPane.add(guideFilePath_of_DE.toFile().getName(), scrollPane_for_DE);

    // QA
    JScrollPane scrollPane_for_QA = new JScrollPane(explanationTextArea_for_QA);
    explanationTextArea_for_QA.setEditable(false);
    scrollPane_for_QA.setPreferredSize(new Dimension(500, 9000));
    scrollPane_for_QA.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane_for_QA.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    tabbedPane.add(guideFilePath_of_QA.toFile().getName(), scrollPane_for_QA);

    // Finalize
    basePanel.add(tabbedPane, BorderLayout.EAST);
  }




  @Override
  public JComponent getBaseComponent() {
    return this.basePanel;
  }

  @Override
  public void postInitialize() {

    /* ** 説明文を書き込む ** */
    // DE Guide
    try {
      StringBuilder content = new StringBuilder();
      try (java.io.BufferedReader reader = new java.io.BufferedReader(
          new java.io.InputStreamReader(
              new java.io.FileInputStream(guideFilePath_of_DE.toFile()),
              java.nio.charset.StandardCharsets.UTF_8))) {
        String line;
        while ((line = reader.readLine()) != null) {
          content.append(line).append("\n");
        }
      }
      explanationTextArea_for_DE.setText(content.toString());
      explanationTextArea_for_DE.setCaretPosition(0);
    } catch (java.io.IOException e) {
      explanationTextArea_for_DE.setText("Error loading guide file: " + e.getMessage());
      e.printStackTrace();
    }

    // QA Guide
    try {
      StringBuilder content = new StringBuilder();
      try (java.io.BufferedReader reader = new java.io.BufferedReader(
              new java.io.InputStreamReader(
                      new java.io.FileInputStream(guideFilePath_of_QA.toFile()),
                      java.nio.charset.StandardCharsets.UTF_8))) {
        String line;
        while ((line = reader.readLine()) != null) {
          content.append(line).append("\n");
        }
      }
      explanationTextArea_for_QA.setText(content.toString());
      explanationTextArea_for_QA.setCaretPosition(0);
    } catch (java.io.IOException e) {
      explanationTextArea_for_QA.setText("Error loading guide file: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void setText(String text) {
    explanationTextArea_for_DE.setText(text);
  }

  @Override
  public void setCHolderMediator(CHolderMediator cHolderMediator) {
    this.cholderMediator = cHolderMediator;
  }

  @Override
  public void setActionMediator(ActionMediator actionMediator) {
    this.actionMediator = actionMediator;
  }

  @Override
  public void initialize() {}

  @Override
  public void doWorkAsMember() {}

  /**
   * コンポーネントのプロパティが変更された時に呼び出される。 コンポーネントのプロパティが変更されるときとは、 フォント、前景色、背景色が変更されたとき、 コンポーネント生成時などである。
   *
   * <p>起動時に行わせたい処理を書き込むと良いのだと思う。 TODO:起動時に2回呼び出されるバグ？あり。大きな問題はないので一旦放置。なんか気持ち悪い。
   */
  private class SamplePropertyChangeListener implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
      System.out.println("Property of SampleTextField has changed.");

      if (actionMediator != null) {
        System.out.println("Action Starter is not null! @" + this.getClass().toString());
        actionMediator
            .getInstanceOfAMember("initialize_sample_text_field")
            .perform(new ActionEvent(this, 0, "Property of SampleTextField has changed."));
      } else {
        System.err.println("Action Starter is null!" + "@" + this.getClass().toString());
      }
    }
  }
}
