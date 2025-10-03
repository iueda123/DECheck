package iu.LCAC.Member.action.Concretes.DEResultActions;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.DEResultSubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.One_DEResultPane;
import iu.LCAC.Tools.PropertyManager_v5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SavePaneOrderAction extends AbstActionMember {

    static final String deresultpane_order_setting_file_path_str =
            "settings/RCAI.prop";

    public SavePaneOrderAction(String action_name, String short_name) {
        super(action_name, short_name);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(
                        KeyStroke.getKeyStroke(
                                KeyEvent.VK_S,
                                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        // TODO: 全セクションに拡張

        AbstCHolderMember member = this.cholderMediator.getInstanceOfAMember("tab_of_reference_cohort_and_imaging_holder");
        DEResultSubTabsHolder deResultSubTabsHolder = (DEResultSubTabsHolder) member;
        String sectionName = deResultSubTabsHolder .getSectionName();
        ArrayList<ManagerOfSubTabBasePane> arrayList_of_managerOfSubTabBasePane = deResultSubTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();

        // 全タブ（SubSectionに相当）配置されているコンポーネントの順番を把握し、propertyへ書き込む
        Component[] components = null;
        One_DEResultPane one_deResultPane = null;
        ArrayList<String> arrayList_PanelOrder = new ArrayList<>();
        PropertyManager_v5 prop_manager = createPropertyManager("./settings/" + sectionName + ".prop");
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : arrayList_of_managerOfSubTabBasePane) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();
            System.out.println("subSectionName: " + subSectionName);
            JPanel subSectionPanel = managerOfSubTabBasePane.getBasePanel();
            components = subSectionPanel.getComponents();
            for (int i = 0; i < components.length; i++) {
                Object component = components[i];
                if (component instanceof One_DEResultPane) {
                    one_deResultPane = ((One_DEResultPane) components[i]);
                    String jsonName = one_deResultPane.getJsonName();
                    System.out.println("  DEResultPane No. " + i + ": " + jsonName);
                    arrayList_PanelOrder.add(jsonName);
                }
            }
            prop_manager.setProperty(subSectionName, Joiner.joinWithSemicolon(arrayList_PanelOrder));
            arrayList_PanelOrder.clear();
        }
        prop_manager.writeoutProperties();
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
