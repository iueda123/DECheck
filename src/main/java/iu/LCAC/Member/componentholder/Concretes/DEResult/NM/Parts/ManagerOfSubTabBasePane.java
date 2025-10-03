package iu.LCAC.Member.componentholder.Concretes.DEResult.NM.Parts;

import javax.swing.*;
import java.util.ArrayList;

public class ManagerOfSubTabBasePane {

    final String sectionName = "reference_cohort_and_imaging";
    final String tabName;

    final String subSectionName;

    ArrayList<One_DEResultPane> dePaneArray = new ArrayList<>();

    JPanel basePanel = new JPanel();

    public ManagerOfSubTabBasePane(String tabName, String subSectionName) {
        this.tabName = tabName;
        this.subSectionName = subSectionName;
    }


    public void addToTheDePaneArray(One_DEResultPane oneDEResultPane) {
        this.dePaneArray.add(oneDEResultPane);
    }

    public JScrollPane constructBasePaneOfSubTab() {
        for (int i = 0; i < dePaneArray.size(); i++) {
            //basePanel.add(new JLabel(" ")); // Separator
            One_DEResultPane pane = dePaneArray.get(i);
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            basePanel.add(pane);
        }

        // パネル全体のPreferredSizeを明示的に計算
        int totalHeight = dePaneArray.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePanel.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane = new JScrollPane(basePanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        return (scrollPane);
    }

    public ArrayList<One_DEResultPane> getDePaneArray() {
        return dePaneArray;
    }

    public String getTabName() {
        return this.tabName;
    }

    public JPanel getBasePanel() {
        return basePanel;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getSubSectionName() {
        return subSectionName;
    }
}
