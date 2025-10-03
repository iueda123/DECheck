package iu.LCAC.Member.action.Concretes.DEResultActions.SaveAndLoadNotePanes;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.NM_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.Parts.ManagerOfSubTabBasePane_NM;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.Parts.NM_OneDEResultPane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.ManagerOfSubTabBasePane_RCAI;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.RCAI_OneDEResultPane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.RCAI_SubTabsHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SaveNotePaneAction extends AbstActionMember {


    public SaveNotePaneAction(String action_name, String short_name) {
        super(action_name, short_name);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(
                        KeyStroke.getKeyStroke(
                                KeyEvent.VK_S,
                                InputEvent.CTRL_DOWN_MASK));
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
        RCAI_SubTabsHolder RCAISubTabsHolder = (RCAI_SubTabsHolder) member;
        ArrayList<ManagerOfSubTabBasePane_RCAI> arrayList_of_managerOfSubTabBasePaneRCAI = RCAISubTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();

        String sectionName = RCAISubTabsHolder.getSectionName();
        String prop_file_path_str = "./settings/" + sectionName + ".prop";
        System.out.println("----- Save pane order of '" + sectionName + "' section -----");

        // 全タブ（SubSectionに相当）配置されているコンポーネントの順番を把握し、propertyへ書き込む
        Component[] components = null;
        RCAI_OneDEResultPane RCAIOne_deResultPane = null;
        ArrayList<String> arrayList_PanelOrder = new ArrayList<>();
        propManager = createPropertyManager(prop_file_path_str);
        for (ManagerOfSubTabBasePane_RCAI managerOfSubTabBasePaneRCAI : arrayList_of_managerOfSubTabBasePaneRCAI) {
            String subSectionName = managerOfSubTabBasePaneRCAI.getSubSectionName();
            //System.out.println("subSectionName: " + subSectionName);
            JPanel subSectionPanel = managerOfSubTabBasePaneRCAI.getBasePaneForDEResultPanes();
            components = subSectionPanel.getComponents();
            for (int i = 0; i < components.length; i++) {
                Object component = components[i];
                if (component instanceof RCAI_OneDEResultPane) {
                    RCAIOne_deResultPane = ((RCAI_OneDEResultPane) components[i]);
                    String jsonName = RCAIOne_deResultPane.getJsonName();
                    //System.out.println("  DEResultPane No. " + i + ": " + jsonName);
                    arrayList_PanelOrder.add(jsonName);
                }
            }
            propManager.setProperty(subSectionName, Joiner.joinWithSemicolon(arrayList_PanelOrder));
            arrayList_PanelOrder.clear();
        }
        propManager.writeoutProperties();
        propManager=null;
    }

    private void savePaneOrderOf_NM_Tab() {
        AbstCHolderMember member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_NM");
        NM_SubTabsHolder subTabsHolder = (NM_SubTabsHolder) member;
        ArrayList<ManagerOfSubTabBasePane_NM> arrayList_of_managerOfSubTabBasePaneNMRCAI = subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();

        String sectionName = subTabsHolder.getSectionName();
        String prop_file_path_str = "./settings/" + sectionName + ".prop";
        System.out.println("----- Save pane order of '" + sectionName + "' section -----");

        // 全タブ（SubSectionに相当）配置されているコンポーネントの順番を把握し、propertyへ書き込む
        Component[] components = null;
        NM_OneDEResultPane oneDEResultPane = null;
        ArrayList<String> arrayList_PanelOrder = new ArrayList<>();
        propManager = createPropertyManager(prop_file_path_str);
        for (ManagerOfSubTabBasePane_NM managerOfSubTabBasePaneNM : arrayList_of_managerOfSubTabBasePaneNMRCAI) {
            String subSectionName = managerOfSubTabBasePaneNM.getSubSectionName();
            System.out.println("subSectionName: " + subSectionName);
            JPanel subSectionPanel = managerOfSubTabBasePaneNM.getBasePaneForDEResultPanes();
            components = subSectionPanel.getComponents();
            for (int i = 0; i < components.length; i++) {
                Object component = components[i];
                if (component instanceof NM_OneDEResultPane) {
                    oneDEResultPane = ((NM_OneDEResultPane) components[i]);
                    String jsonName = oneDEResultPane.getJsonName();
                    System.out.println("  DEResultPane No. " + i + ": " + jsonName);
                    arrayList_PanelOrder.add(jsonName);
                }
            }
            propManager.setProperty(subSectionName, Joiner.joinWithSemicolon(arrayList_PanelOrder));
            arrayList_PanelOrder.clear();
        }
        propManager.writeoutProperties();
        propManager=null;
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


    class Joiner {
        public static String joinWithSemicolon(ArrayList<String> list) {
            return String.join(";", list);
        }

        public static void main(String[] args) {
            ArrayList<String> items = new ArrayList<>();
            items.add("apple");
            items.add("banana");
            items.add("cherry");

            String result = joinWithSemicolon(items);
            System.out.println(result);  // apple;banana;cherry
        }
    }


}
