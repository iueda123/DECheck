package iu.LCAC.Member.action.Concretes.DEResultActions.SaveAndLoadPaneOrder;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.NM_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.Parts.ManagerOfSubTabBasePane_NM;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.Parts.NM_OneDEResultPane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.RCAI_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.ManagerOfSubTabBasePane_RCAI;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.RCAI_OneDEResultPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.*;

public class LoadPaneOrderAction extends AbstActionMember {

    String prop_file_path_str = "";

    public LoadPaneOrderAction(String action_name, String short_name) {
        super(action_name, short_name);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(
                        KeyStroke.getKeyStroke(
                                KeyEvent.VK_L,
                                InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("");
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        // TODO: 全セクションに拡張
        loadPaneOrderOf_RCAI_Tab();
        loadPaneOrderOf_NM_Tab();


    }

    /**
     *  REFERENCE COHORT AND IMAGING
     */
    private void loadPaneOrderOf_RCAI_Tab() {
        AbstCHolderMember member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_RCAI");
        RCAI_SubTabsHolder subTabsHolder = (RCAI_SubTabsHolder) member;
        ArrayList<ManagerOfSubTabBasePane_RCAI> arrayList_of_managerOfSubTabBasePane = subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();

        String sectionName = subTabsHolder.getSectionName();
        prop_file_path_str = "./settings/" + sectionName + ".prop";
        System.out.println("----- Load pane order of '" + sectionName + "' section -----");

        propManager = createPropertyManager(this.prop_file_path_str);
        //System.out.println("Properties file '" + deresultpane_order_setting_file_path_str + "' was loaded.");
        //propManager.listUpProperty();

        String loaded_order = "";
        Component[] components = null;
        for (ManagerOfSubTabBasePane_RCAI managerOfSubTabBasePane : arrayList_of_managerOfSubTabBasePane) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();

            JPanel subSectionPanel = managerOfSubTabBasePane.getBasePanel();
            components = subSectionPanel.getComponents();

            // リストに変換して任意の順序付け
            ArrayList<Component> currentComponentArray = new ArrayList();
            Collections.addAll(currentComponentArray, components);

            for (String property_name : propManager.stringPropertyNames()) {
                loaded_order = (String) propManager.getValueOrCreateNew(property_name);
                //System.out.println(property_name + " -> " + loaded_order);
                if (property_name.equals(subSectionName)) break;
            }
            //System.out.println("Loaded Order of " + subSectionName + ": " + loaded_order);
            ArrayList<String> newlyOrderedJsonNameArray = splitToArrayList(loaded_order);

            //並び替え
            ArrayList<Component> newlyOrderedComponents = new ArrayList<>();
            for (int i = 0; i < newlyOrderedJsonNameArray.size(); i++) {
                for (Component comp : currentComponentArray) {
                    RCAI_OneDEResultPane oneDEResultPanel = (RCAI_OneDEResultPane) comp;
                    String jsonName_of_checking_comp = oneDEResultPanel.getJsonName();
                    //System.out.println("  Now checking '" + jsonName_of_checking_comp + "'");
                    //if (jsonName_of_checking_comp.equals(newlyOrderedJsonNameArray.get(i))) {
                    if (newlyOrderedJsonNameArray.get(i).equals(jsonName_of_checking_comp)) {
                        //System.out.println("    This was added to newlyOrderedComponents!");
                        newlyOrderedComponents.add(comp);
                        break;
                    }
                }
                //System.out.println(" ");
            }

            //差分からPropertyに定義されていないComponent (One_DEResultPane) を把握
            ArrayList<Component> undefinedInPropComponents = new ArrayList<>(currentComponentArray);
            undefinedInPropComponents.removeAll(newlyOrderedComponents);

            //newlyOrderedComponents の後ろに undefinedInPropComponents を結合
            newlyOrderedComponents.addAll(undefinedInPropComponents);

            // パネルをクリアして再配置
            subSectionPanel.removeAll();
            for (Component comp : newlyOrderedComponents) {
                subSectionPanel.add(comp);
            }
            subSectionPanel.revalidate();
            subSectionPanel.repaint();
        }
        propManager = null; //Propの外部更新時のため（毎回新しいPropを呼ぶため）dispose
    }


    /**
     * Normative Modeling
     */
    private void loadPaneOrderOf_NM_Tab() {
        AbstCHolderMember member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_NM");
        NM_SubTabsHolder subTabsHolder = (NM_SubTabsHolder) member;
        ArrayList<ManagerOfSubTabBasePane_NM> arrayList_of_managerOfSubTabBasePane = subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();

        String sectionName = subTabsHolder.getSectionName();
        prop_file_path_str = "./settings/" + sectionName + ".prop";
        System.out.println("----- Load pane order of '" + sectionName + "' section -----");

        propManager = createPropertyManager(this.prop_file_path_str);
        //System.out.println("Properties file '" + deresultpane_order_setting_file_path_str + "' was loaded.");
        //propManager.listUpProperty();

        String loaded_order = "";
        Component[] components = null;
        for (ManagerOfSubTabBasePane_NM managerOfSubTabBasePane : arrayList_of_managerOfSubTabBasePane) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();

            JPanel subSectionPanel = managerOfSubTabBasePane.getBasePanel();
            components = subSectionPanel.getComponents();

            // リストに変換して任意の順序付け
            ArrayList<Component> currentComponentArray = new ArrayList();
            Collections.addAll(currentComponentArray, components);

            for (String property_name : propManager.stringPropertyNames()) {
                loaded_order = (String) propManager.getValueOrCreateNew(property_name);
                //System.out.println(property_name + " -> " + loaded_order);
                if (property_name.equals(subSectionName)) break;
            }
            //System.out.println("Loaded Order of " + subSectionName + ": " + loaded_order);
            ArrayList<String> newlyOrderedJsonNameArray = splitToArrayList(loaded_order);

            //並び替え
            ArrayList<Component> newlyOrderedComponents = new ArrayList<>();
            for (int i = 0; i < newlyOrderedJsonNameArray.size(); i++) {
                for (Component comp : currentComponentArray) {
                    NM_OneDEResultPane oneDEResultPane1 = (NM_OneDEResultPane) comp;
                    String jsonName_of_checking_comp = oneDEResultPane1.getJsonName();
                    //System.out.println("  Now checking '" + jsonName_of_checking_comp + "'");
                    //if (jsonName_of_checking_comp.equals(newlyOrderedJsonNameArray.get(i))) {
                    if (newlyOrderedJsonNameArray.get(i).equals(jsonName_of_checking_comp)) {
                        //System.out.println("    This was added to newlyOrderedComponents!");
                        newlyOrderedComponents.add(comp);
                        break;
                    }
                }
                //System.out.println(" ");
            }

            //差分からPropertyに定義されていないComponent (One_DEResultPane) を把握
            ArrayList<Component> undefinedInPropComponents = new ArrayList<>(currentComponentArray);
            undefinedInPropComponents.removeAll(newlyOrderedComponents);

            //newlyOrderedComponents の後ろに undefinedInPropComponents を結合
            newlyOrderedComponents.addAll(undefinedInPropComponents);

            // パネルをクリアして再配置
            subSectionPanel.removeAll();
            for (Component comp : newlyOrderedComponents) {
                subSectionPanel.add(comp);
            }
            subSectionPanel.revalidate();
            subSectionPanel.repaint();
        }
        propManager = null; //Propの外部更新時のため（毎回新しいPropを呼ぶため）dispose
    }

    @Override
    public void setCHolderMediator(CHolderMediator cHolderMediator) {
        this.cholderMediator = cHolderMediator;
    }

    @Override
    public void setActionMediator(ActionMediator actionMediator) {
        super.actionMediator = actionMediator;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void doWorkAsMember() {
    }

    public static ArrayList<String> splitToArrayList(String input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }
        // ";" で分割して ArrayList に変換
        return new ArrayList<>(Arrays.asList(input.split(";")));
    }
}
