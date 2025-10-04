package iu.LCAC.Member.action.Concretes.DEResultActions.SaveAndLoadPaneOrder;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.OneDEResultPane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.SubTabsHolderItrfc;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.NM_SubTabsHolder;
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
        savePaneOrder("RCAI");
        savePaneOrder("NM");


    }

    private void savePaneOrder(String member_name_key_word) {
        AbstCHolderMember member;
        SubTabsHolderItrfc subTabsHolder;
        switch (member_name_key_word) {
            case "RCAI":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_RCAI");
                subTabsHolder = (RCAI_SubTabsHolder) member;
                break;
            case "NM":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_NM");
                subTabsHolder = (NM_SubTabsHolder) member;
                break;
            default:
                System.out.println("未知のSection指定です");
                return;
        }

        String sectionName = subTabsHolder.getSectionName();
        String prop_file_path_str = "./settings/" + sectionName + ".prop";
        System.out.println("----- Save pane order of '" + sectionName + "' section -----");

        // 全タブ（SubSectionに相当）配置されているコンポーネントの順番を把握し、propertyへ書き込む
        propManager = createPropertyManager(prop_file_path_str);
        ArrayList<String> arrayList_PanelOrder = new ArrayList<>();
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane()) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();
            //System.out.println("subSectionName: " + subSectionName);
            JPanel subSectionPanel = managerOfSubTabBasePane.getBasePaneForDEResultPanes();
            Component[] components = subSectionPanel.getComponents();
            for (int i = 0; i < components.length; i++) {
                Object component = components[i];
                if (component instanceof OneDEResultPane) {
                    OneDEResultPane oneDEResultPane = (OneDEResultPane) component;
                    String jsonName = oneDEResultPane.getJsonName();
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
