package iu.LCAC.Member.action.Concretes.DEResultActions;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.RCAI_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.One_DEResultPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.*;

public class LoadPaneOrderAction extends AbstActionMember {

    String deresultpane_order_setting_file_path_str = "";

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

        /* REFERENCE COHORT AND IMAGING */
        AbstCHolderMember member = this.cholderMediator.getInstanceOfAMember("tab_of_reference_cohort_and_imaging_holder");
        RCAI_SubTabsHolder RCAISubTabsHolder = (RCAI_SubTabsHolder) member;
        String sectionName = RCAISubTabsHolder.getSectionName();
        ArrayList<ManagerOfSubTabBasePane> arrayList_of_managerOfSubTabBasePane = RCAISubTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();

        deresultpane_order_setting_file_path_str = "./settings/" + sectionName + ".prop";
        propManager = createPropertyManager(deresultpane_order_setting_file_path_str);

        //System.out.println("Properties file '" + deresultpane_order_setting_file_path_str + "' was loaded.");
        //propManager.listUpProperty();

        Set<String> property_names = propManager.stringPropertyNames();

        String loaded_order = "";
        for (String property_name : property_names) {
            loaded_order = (String) propManager.getValueOrCreateNew(property_name);
        }

        Component[] components = null;
        propManager = createPropertyManager("./settings/" + sectionName + ".prop");
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : arrayList_of_managerOfSubTabBasePane) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();

            JPanel subSectionPanel = managerOfSubTabBasePane.getBasePanel();
            components = subSectionPanel.getComponents();

            // リストに変換して任意の順序付け
            ArrayList<Component> currentComponentArray = new ArrayList();
            Collections.addAll(currentComponentArray, components);

            for (String property_name : property_names) {
                loaded_order = (String) propManager.getValueOrCreateNew(property_name);
                //System.out.println(property_name + " -> " + loaded_order);
                if (property_name.equals(subSectionName)) break;
            }
            System.out.println("Loaded Order of " + subSectionName + ": " + loaded_order);
            ArrayList<String> newlyOrderedJsonNameArray = splitToArrayList(loaded_order);

            //並び替え
            ArrayList<Component> newlyOrderedComponents = new ArrayList<>();
            for (int i = 0; i < newlyOrderedJsonNameArray.size(); i++) {
                for (Component comp : currentComponentArray) {
                    One_DEResultPane one_deResultPane1 = (One_DEResultPane) comp;
                    String jsonName_of_checking_comp = one_deResultPane1.getJsonName();
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

            // 再描画
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
