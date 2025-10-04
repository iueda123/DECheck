package iu.LCAC.Member.componentholder.Concretes.DEResult.Common;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManagerOfSubTabBasePane {
    JPanel basePaneForDEResultPanes = new JPanel();
    final String tabName;

    final String sectionName;
    final String subSectionName;

    ArrayList<OneDEResultPane> dePaneArray = new ArrayList<>();

    final JTabbedPane tabbedPane;
    final NotePane notePane;

    public ManagerOfSubTabBasePane(String tabName, String sectionName, String subSectionName, JTabbedPane tabbedPane) {
        this.basePaneForDEResultPanes.setLayout(new BoxLayout(this.basePaneForDEResultPanes, BoxLayout.Y_AXIS));
        this.tabName = tabName;
        this.sectionName = sectionName;
        this.subSectionName = subSectionName;
        this.tabbedPane = tabbedPane;
        notePane = new NotePane(sectionName, subSectionName, this.tabbedPane);
    }


    public void addToTheDePaneArray(OneDEResultPane oneDEResultPane) {
        this.dePaneArray.add(oneDEResultPane);
    }

    public JComponent constructBasePaneOfSubTab() {

        JPanel basePane = new JPanel();
        basePane.setLayout(new BoxLayout(basePane, BoxLayout.Y_AXIS));

        // NotePane を配置
        notePane.setPreferredSize(new Dimension(600, 50));
        basePane.add(notePane);

        // OneDEResultPane たちを配置
        for (int i = 0; i < dePaneArray.size(); i++) {
            //basePanel.add(new JLabel(" ")); // Separator
            OneDEResultPane pane = dePaneArray.get(i);
            pane.setMaximumSize(new Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(Component.LEFT_ALIGNMENT);
            basePaneForDEResultPanes.add(pane);
        }
        // パネル全体のPreferredSizeを明示的に計算
        int totalHeight = dePaneArray.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePaneForDEResultPanes.setPreferredSize(new Dimension(600, totalHeight));
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

    public ArrayList<OneDEResultPane> getDePaneArray() {
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
