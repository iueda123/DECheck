package iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts;

import iu.LCAC.Member.componentholder.Concretes.DEResult.CommentPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManagerOfSubTabBasePane_RCAI {
    JPanel basePaneForDEResultPanes = new JPanel();

    final String sectionName = "reference_cohort_and_imaging";
    final String tabName;

    final String subSectionName;

    ArrayList<RCAI_OneDEResultPane> dePaneArray = new ArrayList<>();

    CommentPane commentPane = new CommentPane();

    public ManagerOfSubTabBasePane_RCAI(String tabName, String subSectionName) {
        this.basePaneForDEResultPanes.setLayout(new BoxLayout(this.basePaneForDEResultPanes, BoxLayout.Y_AXIS));
        this.tabName = tabName;
        this.subSectionName = subSectionName;
    }


    public void addToTheDePaneArray(RCAI_OneDEResultPane oneDEResultPane) {
        this.dePaneArray.add(oneDEResultPane);
    }

    public JComponent constructBasePaneOfSubTab() {

        JPanel basePane = new JPanel();
        basePane.setLayout(new BoxLayout(basePane, BoxLayout.Y_AXIS));

        // CommentPane を配置
        commentPane.setPreferredSize(new Dimension(600, 50));
        basePane.add(commentPane);

        // OneDEResultPane たちを配置
        for (int i = 0; i < dePaneArray.size(); i++) {
            //basePanel.add(new JLabel(" ")); // Separator
            RCAI_OneDEResultPane pane = dePaneArray.get(i);
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            basePaneForDEResultPanes.add(pane);
        }
        // パネル全体のPreferredSizeを明示的に計算
        int totalHeight = dePaneArray.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePaneForDEResultPanes.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        basePane.add(basePaneForDEResultPanes);

        // スクロールパネルを用意
        JScrollPane scrollPane = new JScrollPane(basePaneForDEResultPanes,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        //return (scrollPane);

        // 土台を返す
        basePane.add(scrollPane);
        return (basePane);

    }

    public ArrayList<RCAI_OneDEResultPane> getDePaneArray() {
        return dePaneArray;
    }

    public String getTabName() {
        return this.tabName;
    }

    public JPanel getBasePaneForDEResultPanes() {
        return basePaneForDEResultPanes;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getSubSectionName() {
        return subSectionName;
    }
}
