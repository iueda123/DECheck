package iu.LCAC.Member.action.Concretes.DEResultActions.SaveAndLoadPaneOrder;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.NM_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.OneDEResultPane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.RCAI_SubTabsHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SavePaneOrderAction extends AbstActionMember {


    public SavePaneOrderAction(String action_name, String short_name) {
        super(action_name, short_name);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(
                        KeyStroke.getKeyStroke(
                                KeyEvent.VK_S,
                                InputEvent.CTRL_DOWN_MASK+ InputEvent.SHIFT_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        // TODO: 全セクションに拡張
        savePaneOrderOf_RCAI_Tab();
        savePaneOrderOf_NM_Tab();


    }

    private void savePaneOrderOf_RCAI_Tab() {
        AbstCHolderMember member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_RCAI");
        RCAI_SubTabsHolder subTabsHolder = (RCAI_SubTabsHolder) member;
        ArrayList<ManagerOfSubTabBasePane> arrayList_of_managerOfSubTabBasePane = subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();

        String sectionName = subTabsHolder.getSectionName();
        String prop_file_path_str = "./settings/" + sectionName + ".prop";
        System.out.println("----- Save pane order of '" + sectionName + "' section -----");

        // 全タブ（SubSectionに相当）配置されているコンポーネントの順番を把握し、propertyへ書き込む
        Component[] components = null;
        OneDEResultPane onteDEResultPane = null;
        ArrayList<String> arrayList_PanelOrder = new ArrayList<>();
        propManager = createPropertyManager(prop_file_path_str);
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : arrayList_of_managerOfSubTabBasePane) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();
            //System.out.println("subSectionName: " + subSectionName);
            JPanel subSectionPanel = managerOfSubTabBasePane.getBasePaneForDEResultPanes();
            components = subSectionPanel.getComponents();
            for (int i = 0; i < components.length; i++) {
                Object component = components[i];
                if (component instanceof OneDEResultPane) {
                    onteDEResultPane = ((OneDEResultPane) components[i]);
                    String jsonName = onteDEResultPane.getJsonName();
                    //System.out.println("  DEResultPane No. " + i + ": " + jsonName);
                    arrayList_PanelOrder.add(jsonName);
                }
            }
            propManager.setProperty(subSectionName, joinWithSemicolon(arrayList_PanelOrder));
            arrayList_PanelOrder.clear();
        }
        propManager.writeoutProperties();
        propManager = null;
    }

    private void savePaneOrderOf_NM_Tab() {
        AbstCHolderMember member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_NM");
        NM_SubTabsHolder subTabsHolder = (NM_SubTabsHolder) member;
        ArrayList<ManagerOfSubTabBasePane> arrayList_of_managerOfSubTabBasePaneNMRCAI = subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();

        String sectionName = subTabsHolder.getSectionName();
        String prop_file_path_str = "./settings/" + sectionName + ".prop";
        System.out.println("----- Save pane order of '" + sectionName + "' section -----");

        // 全タブ（SubSectionに相当）配置されているコンポーネントの順番を把握し、propertyへ書き込む
        Component[] components = null;
        OneDEResultPane oneDEResultPane = null;
        ArrayList<String> arrayList_PanelOrder = new ArrayList<>();
        propManager = createPropertyManager(prop_file_path_str);
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : arrayList_of_managerOfSubTabBasePaneNMRCAI) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();
            System.out.println("subSectionName: " + subSectionName);
            JPanel subSectionPanel = managerOfSubTabBasePane.getBasePaneForDEResultPanes();
            components = subSectionPanel.getComponents();
            for (int i = 0; i < components.length; i++) {
                Object component = components[i];
                if (component instanceof OneDEResultPane) {
                    oneDEResultPane = ((OneDEResultPane) components[i]);
                    String jsonName = oneDEResultPane.getJsonName();
                    System.out.println("  DEResultPane No. " + i + ": " + jsonName);
                    arrayList_PanelOrder.add(jsonName);
                }
            }
            propManager.setProperty(subSectionName, joinWithSemicolon(arrayList_PanelOrder));
            arrayList_PanelOrder.clear();
        }
        propManager.writeoutProperties();
        propManager = null;
    }


    public void setCHolderMediator(CHolderMediator cHolderMediator) {
        this.cholderMediator = cHolderMediator;
    }

    @Override
    public void setActionMediator(ActionMediator actionMediator) {
        this.actionMediator = actionMediator;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void doWorkAsMember() {
    }


    public static String joinWithSemicolon(ArrayList<String> list) {
        return String.join(";", list);
    }

}
