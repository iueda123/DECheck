package iu.LCAC.Member.componentholder.Concretes.ResultPanels.AResultPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AResultPane extends JPanel {

    final String jsonName;
    JPanel panel = new JPanel();

    JButton sampleButton = new JButton("Button");

    JTextField tField_jsonName = new JTextField("jsonName");
    JTextArea tArea_Answer = new JTextArea("Answer");
    JTextField tFiled_ConfidenceRating = new JTextField("Confidence Rating");
    JTextField tField_NegativeAnswerCategory = new JTextField("Negative Answer Category");

    JTextArea tArea_Reason = new JTextArea("Reason");
    JTextArea tArea_SupportingText = new JTextArea("Supporting Text");
    JTextArea tArea_PageLine = new JTextArea("Page Line");

    public AResultPane(String jsonName) {
        this.jsonName = jsonName;

        tArea_Reason.setLineWrap(true);
        tArea_Reason.setWrapStyleWord(true);

        tArea_SupportingText.setLineWrap(true);
        tArea_SupportingText.setWrapStyleWord(true);

        tArea_PageLine.setLineWrap(true);
        tArea_PageLine.setWrapStyleWord(true);

        sampleButton.addActionListener(
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        //if (actionMediator != null) {
                        //    actionMediator.getInstanceOfAMember("change_color_of_center").perform(actionEvent);
                        //} else {
                        //    System.err.println("Action Starter is null! @ " + this.getClass().toString());
                        //}
                    }
                });

        JPanel basePane = new JPanel();
        basePane.setLayout(new BoxLayout(basePane, BoxLayout.Y_AXIS));

        Box northBox = Box.createHorizontalBox();
        //northBox.add(tField_jsonName);
        northBox.add(tArea_Answer);
        northBox.add(tFiled_ConfidenceRating);
        northBox.add(tField_NegativeAnswerCategory);

        // JTextAreaをJScrollPaneでラップしてスクロールバーを追加
        JScrollPane scrollPane_Reason = new JScrollPane(tArea_Reason);
        scrollPane_Reason.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Reason.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollPane_SupportingText = new JScrollPane(tArea_SupportingText);
        scrollPane_SupportingText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_SupportingText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollPane_PageLine = new JScrollPane(tArea_PageLine);
        scrollPane_PageLine.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_PageLine.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        Box centerBox = Box.createHorizontalBox();
        centerBox.add(scrollPane_Reason);
        centerBox.add(scrollPane_SupportingText);
        centerBox.add(scrollPane_PageLine);

        setLayout(new BorderLayout());
        add(northBox, BorderLayout.NORTH);
        add(centerBox, BorderLayout.CENTER);

        setBorder(BorderFactory.createTitledBorder("A Result Panel"));

        setPreferredSize(new Dimension(600, 300));

    }

    public void setTextToTheButton(String text) {
        this.sampleButton.setText(text);
    }
    public void setValTo_JsonName(String value) {
        tField_jsonName.setText(value);
        setBorder(BorderFactory.createTitledBorder(value));
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

    public String getJsonName() {
        return jsonName;
    }
}
