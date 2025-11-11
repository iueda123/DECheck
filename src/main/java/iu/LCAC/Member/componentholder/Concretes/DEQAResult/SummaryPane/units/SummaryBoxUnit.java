package iu.LCAC.Member.componentholder.Concretes.DEQAResult.SummaryPane.units;

import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class SummaryBoxUnit extends JPanel {
    ArrayList<ManagerOfSubTabBasePane> arrayOf_ManagerOfSubTabBasePane;
    ArrayList<String> subSectionnames = new ArrayList<>();
    TreeMap<String, JLabel> statusSquares = new TreeMap<>();

    public SummaryBoxUnit(ArrayList<ManagerOfSubTabBasePane> arrayOf_ManagerOfSubTabBasePane) {
        this.arrayOf_ManagerOfSubTabBasePane = arrayOf_ManagerOfSubTabBasePane;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        String sectionName = this.arrayOf_ManagerOfSubTabBasePane.get(0).getSectionName();
        add(new JLabel(sectionName + ": "));


        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : this.arrayOf_ManagerOfSubTabBasePane) {
            //System.out.println("★");
            subSectionnames.add(managerOfSubTabBasePane.getSectionName() + "_" + managerOfSubTabBasePane.getSubSectionName());
            statusSquares.put(managerOfSubTabBasePane.getSubSectionName(), new JLabel("□"));

        }

        //JOptionPane.showMessageDialog(null, this.arrayOf_ManagerOfSubTabBasePane.size());

        for (String key : statusSquares.keySet()) {
            JLabel label = statusSquares.get(key);
            add(label);
        }

        add(Box.createHorizontalGlue());

    }

    public void setStatus(String subSectionName, String status, String answer_value) {
        statusSquares.get(subSectionName).setText(status);
        statusSquares.get(subSectionName).setToolTipText(answer_value);
    }
}
