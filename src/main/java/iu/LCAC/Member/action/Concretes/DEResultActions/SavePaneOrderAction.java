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

        AbstCHolderMember member = this.cholderMediator.getInstanceOfAMember("tab_of_reference_cohort_and_imaging_holder");
        DEResultSubTabsHolder deResultSubTabsHolder = (DEResultSubTabsHolder) member;

        ArrayList<ManagerOfSubTabBasePane> arrayList_of_managerOfSubTabBasePane = deResultSubTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();

        // 配置されているコンポーネントの順番を取得し全タブで集める
        Component[] components = null;
        One_DEResultPane one_deResultPane = null;
        String id_DEResultPane = "";
        ArrayList<String> arrayList_ID_DEResultPane = new ArrayList<>();
        for( ManagerOfSubTabBasePane managerOfSubTabBasePane : arrayList_of_managerOfSubTabBasePane) {
            components =  managerOfSubTabBasePane.getBasePanel().getComponents();
            for (int i = 0; i < components.length; i++) {
                System.out.println("Index " + i + ": ");
                one_deResultPane = ((One_DEResultPane) components[i]);
                String jsonName = one_deResultPane.getJsonName();
                String sectionName = one_deResultPane.getSectionName();
                String subSectionName = one_deResultPane.getSubSectionName();
                id_DEResultPane = managerOfSubTabBasePane.getTabName() + ":" + jsonName + "/" + sectionName + "/" + subSectionName;
                System.out.println(id_DEResultPane);
                arrayList_ID_DEResultPane.add(id_DEResultPane);
            }
        }



        //Set<String> action_commands = this.actionMediator.memberMap.keySet();

        PropertyManager_v5 prop_manager = createPropertyManager(deresultpane_order_setting_file_path_str);

        AbstActionMember action = null;
        String accelerator = "";
        for (String id : arrayList_ID_DEResultPane) {
            System.out.print(id);

            /*
            action = this.actionMediator.getInstanceOfAMember(id);


            JMenuItem menu_item = action.getMenuItem();
            // KeyStroke ket_stroke = menu_item.getAccelerator();
            if (menu_item == null) {
                System.out.println(
                        "    Can't get a JMenu item about '"
                                + id
                                + "' from the current ActionMediator instance.'");
            } else if (menu_item.getAccelerator() == null) {
                System.out.println(
                        "    Can't get a key stroke info about '"
                                + id
                                + "' from the current ActionMediator instance.'");
            } else {
                accelerator = action.getMenuItem().getAccelerator().toString();
                System.out.println("    " + accelerator);
                prop_manager.setProperty(id, accelerator);
            }
            */
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
}
