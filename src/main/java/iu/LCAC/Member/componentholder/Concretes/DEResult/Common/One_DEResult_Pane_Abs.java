package iu.LCAC.Member.componentholder.Concretes.DEResult.Common;

import iu.LCAC.Utils.ColorChangeableTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class One_DEResult_Pane_Abs extends JPanel {

    final String jsonFolderPathStr;
    final String jsonName;
    final String sectionName;
    final String subSectionName;

    ColorChangeableTextField tField_jsonName = new ColorChangeableTextField("JSON File Name");
    private final String tooltipForJsonName = "JSON File Name";

    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");

    public One_DEResult_Pane_Abs(
            String jsonFolderPathStr,
            String jsonName, String sectionName, String subSectionName) {
        this.jsonFolderPathStr = jsonFolderPathStr;
        this.jsonName = jsonName;
        this.sectionName = sectionName;
        this.subSectionName = subSectionName;
    }

    protected abstract void saveJson();

    protected abstract void loadJson();

    public String getJsonFolderPathStr() {
        return jsonFolderPathStr;
    }

    public String getJsonName() {
        return jsonName;
    }

    public String getSubSectionName() {
        return subSectionName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setValTo_JsonName(String value) {
        tField_jsonName.setText(value);
        setBorder(BorderFactory.createTitledBorder(value));
    }


    protected abstract void resetBackgroundColorOfTAreasTFields();

    protected class PanelMoverPane extends JPanel {

        JButton moveUpButton = new JButton("↑");
        JButton moveDownButton = new JButton("↓");

        PanelMoverPane() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(moveUpButton);
            add(moveDownButton);
            moveDownButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    movePaneDown();
                }
            });

            moveUpButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    movePaneUp();
                }
            });
        }


        private void movePaneUp() {
            Container parent = One_DEResult_Pane_Abs.this.getParent();
            if (parent == null) {
                return;
            }

            Component[] components = parent.getComponents();
            int paneIndex = -1;
            for (int i = 0; i < components.length; i++) {
                if (components[i] == One_DEResult_Pane_Abs.this) {
                    paneIndex = i;
                    break;
                }
            }

            if (paneIndex == -1) {
                return;
            }

            Component previousPane = null;
            for (int i = paneIndex - 1; i >= 0; i--) {
                if (components[i] instanceof One_DEResult_Pane_Abs) {
                    previousPane = components[i];
                    break;
                }
            }

            if (previousPane == null) {
                return;
            }

            JLabel spacerBefore = null;
            if (paneIndex > 0 && components[paneIndex - 1] instanceof JLabel) {
                spacerBefore = (JLabel) components[paneIndex - 1];
            }

            if (spacerBefore != null) {
                parent.remove(spacerBefore);
            }
            parent.remove(One_DEResult_Pane_Abs.this);

            int insertionIndex = parent.getComponentZOrder(previousPane);
            if (spacerBefore != null) {
                parent.add(spacerBefore, insertionIndex);
                insertionIndex++;
            }
            parent.add(One_DEResult_Pane_Abs.this, insertionIndex);

            parent.revalidate();
            parent.repaint();
        }

        private void movePaneDown() {
            Container parent = One_DEResult_Pane_Abs.this.getParent();
            if (parent == null) {
                return;
            }

            Component[] components = parent.getComponents();
            int paneIndex = -1;
            for (int i = 0; i < components.length; i++) {
                if (components[i] == One_DEResult_Pane_Abs.this) {
                    paneIndex = i;
                    break;
                }
            }

            if (paneIndex == -1) {
                return;
            }

            Component nextPane = null;
            for (int i = paneIndex + 1; i < components.length; i++) {
                if (components[i] instanceof One_DEResult_Pane_Abs) {
                    nextPane = components[i];
                    break;
                }
            }

            if (nextPane == null) {
                return;
            }

            JLabel spacerBefore = null;
            if (paneIndex > 0 && components[paneIndex - 1] instanceof JLabel) {
                spacerBefore = (JLabel) components[paneIndex - 1];
            }

            if (spacerBefore != null) {
                parent.remove(spacerBefore);
            }
            parent.remove(One_DEResult_Pane_Abs.this);

            int insertionIndex = parent.getComponentZOrder(nextPane) + 1;
            if (spacerBefore != null) {
                parent.add(spacerBefore, insertionIndex);
                insertionIndex++;
            }
            parent.add(One_DEResult_Pane_Abs.this, insertionIndex);

            parent.revalidate();
            parent.repaint();
        }

    }
}
