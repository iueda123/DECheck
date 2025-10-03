package iu.LCAC.Utils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ColorChangeableTextField extends JTextField {

    private String defaultValue = "";

    public ColorChangeableTextField(int columns) {
        super(columns);
        setupTextField();
    }

    public ColorChangeableTextField(String text) {
        super(text);
        setupTextField();
    }

    private void setupTextField(){

        defaultValue = this.getText();

        // テキストフィールドのドキュメントにリスナーを追加
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeColor();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeColor();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // 属性変更時 (プレーンテキストではあまり使われない)
                changeColor();
            }

            private void changeColor() {

                if (ColorChangeableTextField.this.getText().equals(ColorChangeableTextField.this.defaultValue)) {
                    ColorChangeableTextField.this.resetBackgroundColor();
                } else {
                    ColorChangeableTextField.this.setBackground(Color.PINK);
                }
            }


        });
    }

    public void updateDefaultValue() {
        ColorChangeableTextField.this.defaultValue = ColorChangeableTextField.this.getText();
    }

    public void resetBackgroundColor(){
        // UIManager から L&F 標準の背景色を取得
        Color defaultBg = UIManager.getColor("TextField.background");
        this.setBackground(defaultBg);
    }

}