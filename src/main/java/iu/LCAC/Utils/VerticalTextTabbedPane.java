package iu.LCAC.Utils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class VerticalTextTabbedPane extends JTabbedPane {

    public VerticalTextTabbedPane(int tabPlacement) {
        super(tabPlacement);
        setUI(new VerticalTextTabbedPaneUI());
    }

    private static class VerticalTextTabbedPaneUI extends BasicTabbedPaneUI {

        @Override
        protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics,
                                 int tabIndex, String title, Rectangle textRect, boolean isSelected) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (tabPlacement == LEFT) {
                // テキストを90度回転（反時計回り）
                // タブの幅の中央にテキストを配置
                int centerX = textRect.x + textRect.width / 2;
                AffineTransform at = new AffineTransform();
                at.translate(centerX, textRect.y + textRect.height - 5);
                at.rotate(-Math.PI / 2);
                g2d.setTransform(at);
                g2d.setFont(font);
                g2d.setColor(isSelected ? Color.BLACK : Color.DARK_GRAY);
                g2d.drawString(title, -metrics.stringWidth(title) / 2, metrics.getAscent() / 2);

                // 元に戻す
                g2d.setTransform(new AffineTransform());
            } else if (tabPlacement == RIGHT) {
                // テキストを90度回転（時計回り）
                // タブの幅の中央にテキストを配置
                int centerX = textRect.x + textRect.width / 2;
                AffineTransform at = new AffineTransform();
                at.translate(centerX, textRect.y + 5);
                at.rotate(Math.PI / 2);
                g2d.setTransform(at);
                g2d.setFont(font);
                g2d.setColor(isSelected ? Color.BLACK : Color.DARK_GRAY);
                g2d.drawString(title, -metrics.stringWidth(title) / 2, metrics.getAscent() / 2);

                // 元に戻す
                g2d.setTransform(new AffineTransform());
            } else {
                super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
            }
        }

        @Override
        protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
            if (tabPlacement == LEFT || tabPlacement == RIGHT) {
                // 回転時はテキストの高さ分を幅として使用
                return super.calculateTabHeight(tabPlacement, tabIndex, metrics.getHeight());
            }
            return super.calculateTabWidth(tabPlacement, tabIndex, metrics);
        }

        @Override
        protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
            if (tabPlacement == LEFT || tabPlacement == RIGHT) {
                // 回転時はテキストの幅分を高さとして使用
                String title = tabPane.getTitleAt(tabIndex);
                FontMetrics metrics = tabPane.getFontMetrics(tabPane.getFont());
                return metrics.stringWidth(title) + 20;
            }
            return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight);
        }
    }
}
