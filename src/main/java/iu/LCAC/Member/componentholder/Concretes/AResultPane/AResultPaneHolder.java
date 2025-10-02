package iu.LCAC.Member.componentholder.Concretes.AResultPane;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AResultPaneHolder extends AbstCHolderMember {

  JPanel panel = new JPanel();

  JButton sampleButton = new JButton("Button");

  JTextField jsonName = new JTextField("jsonName");
  JTextArea tArea_Answer = new JTextArea("Answer");
  JTextField tFiled_ConfidenceRating = new JTextField("Confidence Rating");
  JTextField tField_NegativeAnswerCategory = new JTextField("Negative Answer Category");
  JTextArea tArea_Reason = new JTextArea("Reason");
  JTextArea tArea_SupportingText = new JTextArea("Supporting Text");
  JTextArea tArea_PageLine = new JTextArea("Page Line");

  public AResultPaneHolder(String cholder_name, String short_name) {
    super(cholder_name, short_name);

    sampleButton.addActionListener(
        new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent actionEvent) {
            if (actionMediator != null) {
              actionMediator.getInstanceOfAMember("change_color_of_center").perform(actionEvent);
            } else {
              System.err.println("Action Starter is null! @ " + this.getClass().toString());
            }
          }
        });

    JPanel basePane = new JPanel();
    basePane.setLayout(new BoxLayout(basePane, BoxLayout.Y_AXIS));

    Box northBox = Box.createHorizontalBox();
    northBox.add(jsonName);
    northBox.add(tArea_Answer);
    northBox.add(tFiled_ConfidenceRating);
    northBox.add(tField_NegativeAnswerCategory);

    Box centerBox = Box.createHorizontalBox();
    centerBox.add(tArea_Reason);
    centerBox.add(tArea_SupportingText);
    centerBox.add(tArea_PageLine);

    panel.setLayout(new BorderLayout());
    panel.add(northBox, BorderLayout.NORTH);
    panel.add(centerBox, BorderLayout.CENTER);
  }

  @Override
  public void postInitialize() {}

  @Override
  public JComponent getBaseComponent() {
    return this.panel;
  }

  public void setTextToTheButton(String text) {
    this.sampleButton.setText(text);
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

  public void setValTo_JsonName(String value) {
    jsonName.setText(value);
  }

  public void setValTo_Answer(String value) {
    tArea_Answer.setText(value);
  }

  public void setValTo_ConfidenceRating(String value) {
    tFiled_ConfidenceRating.setText(value);
  }

  public void setValTo_NegativeAnswerCategory(String value) {
    tField_NegativeAnswerCategory.setText(value);
  }

  public void setValTo_Reason(String value) {
    tArea_Reason.setText(value);
  }

  public void setValTo_SupportingText(String value) {
    tArea_SupportingText.setText(value);
  }

  public void setValTo_PageLine(String value) {
    tArea_PageLine.setText(value);
  }

}
