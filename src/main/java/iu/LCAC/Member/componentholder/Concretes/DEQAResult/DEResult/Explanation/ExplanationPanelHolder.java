package iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.Explanation;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ExplanationPanelHolder extends AbstCHolderMember {

  JPanel panel = new JPanel(new BorderLayout());
  JTextArea explanationTextArea = new JTextArea("EXPLANATION");

  public ExplanationPanelHolder(String cholder_name, String short_name) {
    super(cholder_name, short_name);

    JScrollPane scrollPane = new JScrollPane(explanationTextArea);

    scrollPane.setPreferredSize(new Dimension(500, 9000));
    //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    panel.add(scrollPane, BorderLayout.EAST);
    //explanationTextArea.addPropertyChangeListener(new SamplePropertyChangeListener());
    explanationTextArea.setEditable(false);
  }




  @Override
  public JComponent getBaseComponent() {
    return this.panel;
  }

  @Override
  public void postInitialize() {

    // 説明文を書き込む

    try {
      String guideFilePath = "./settings/Guides/DE_Guide_v9_jp.md";
      StringBuilder content = new StringBuilder();

      try (java.io.BufferedReader reader = new java.io.BufferedReader(
          new java.io.InputStreamReader(
              new java.io.FileInputStream(guideFilePath),
              java.nio.charset.StandardCharsets.UTF_8))) {
        String line;
        while ((line = reader.readLine()) != null) {
          content.append(line).append("\n");
        }
      }

      explanationTextArea.setText(content.toString());
      explanationTextArea.setCaretPosition(0);
    } catch (java.io.IOException e) {
      explanationTextArea.setText("Error loading guide file: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void setText(String text) {
    explanationTextArea.setText(text);
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
