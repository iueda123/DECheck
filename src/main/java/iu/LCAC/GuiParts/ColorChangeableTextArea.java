package iu.LCAC.GuiParts;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ColorChangeableTextArea extends JTextArea {

    private String defaultValue = "";

    public ColorChangeableTextArea(int rows, int columns) {
        super(rows, columns);
        setupTextArea();
    }

    public ColorChangeableTextArea(String text) {
        super(text);
        setupTextArea();
    }

    private void setupTextArea(){

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

                if (ColorChangeableTextArea.this.getText().equals(ColorChangeableTextArea.this.defaultValue)) {
                    ColorChangeableTextArea.this.resetBackgroundColor();
                } else {
                    ColorChangeableTextArea.this.setBackground(Color.PINK);
                }
            }


        });
    }

    public void updateDefaultValue() {
        ColorChangeableTextArea.this.defaultValue = ColorChangeableTextArea.this.getText();
    }

    public void resetBackgroundColor(){
        // UIManager から L&F 標準の背景色を取得
        Color defaultBg = UIManager.getColor("TextArea.background");
        this.setBackground(defaultBg);
    }

}
