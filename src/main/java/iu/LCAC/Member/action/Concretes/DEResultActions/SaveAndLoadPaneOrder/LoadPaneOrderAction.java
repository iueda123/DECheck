package iu.LCAC.Member.action.Concretes.DEResultActions.SaveAndLoadPaneOrder;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.CAAA.CAAA_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.One_DEResult_Pane_Abs;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.SubTabsHolderItrfc;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.GN.GN_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.NM.NM_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.RCAI.RCAI_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.SC.SC_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.SI.SI_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QA1.QA1_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QA2.QA2_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QAAC.QAAC_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QASI.QASI_SubTabsHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.*;

public class LoadPaneOrderAction extends AbstActionMember {

    String authorYear = "Someone20XX";

    public LoadPaneOrderAction(String action_name, String short_name, String authorYear) {
        super(action_name, short_name);
        this.authorYear = authorYear;
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(
                        KeyStroke.getKeyStroke(
                                KeyEvent.VK_L,
                                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("");
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        loadPaneOrder("SI", "./settings/" + authorYear + "/PaneOrder/" + "study_identification" + ".prop");
        loadPaneOrder("SC", "./settings/" + authorYear + "/PaneOrder/" + "study_characteristics" + ".prop");
        loadPaneOrder("RCAI", "./settings/" + authorYear + "/PaneOrder/" + "reference_cohort_and_imaging" + ".prop");
        loadPaneOrder("NM", "./settings/" + authorYear + "/PaneOrder/" + "normative_modeling" + ".prop");
        loadPaneOrder("CAAA", "./settings/" + authorYear + "/PaneOrder/" + "clinical_application_and_analysis" + ".prop");
        loadPaneOrder("GN", "./settings/" + authorYear + "/PaneOrder/" + "general_notes" + ".prop");

        loadPaneOrder("QASI", "./settings/" + authorYear + "/PaneOrder/" + "study_identification_of_qa" + ".prop");
        loadPaneOrder("QA1_v6", "./settings/" + authorYear + "/PaneOrder/" + "quality_assessment_1_v6" + ".prop");
        loadPaneOrder("QA2_v6", "./settings/" + authorYear + "/PaneOrder/" + "quality_assessment_2_v6" + ".prop");
        loadPaneOrder("QAAC", "./settings/" + authorYear + "/PaneOrder/" + "additional_comments" + ".prop");

    }

    /**
     * Loads pane order for a specific section keyed by member name.
     */
    private void loadPaneOrder(String member_name_key_word, String prop_file_path_str) {
        AbstCHolderMember member;
        SubTabsHolderItrfc subTabsHolder;
        switch (member_name_key_word) {
            case "SI":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_SI");
                subTabsHolder = (SI_SubTabsHolder) member;
                break;
            case "SC":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_SC");
                subTabsHolder = (SC_SubTabsHolder) member;
                break;
            case "RCAI":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_RCAI");
                subTabsHolder = (RCAI_SubTabsHolder) member;
                break;
            case "NM":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_NM");
                subTabsHolder = (NM_SubTabsHolder) member;
                break;
            case "CAAA":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_CAAA");
                subTabsHolder = (CAAA_SubTabsHolder) member;
                break;
            case "GN":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_GN");
                subTabsHolder = (GN_SubTabsHolder) member;
                break;
            case "QASI":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QASI");
                subTabsHolder = (QASI_SubTabsHolder) member;
                break;
            case "QA1_v6":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QA1_v6");
                subTabsHolder = (QA1_SubTabsHolder) member;
                break;
            case "QA2_v6":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QA2_v6");
                subTabsHolder = (QA2_SubTabsHolder) member;
                break;
            case "QAAC":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QAAC");
                subTabsHolder = (QAAC_SubTabsHolder) member;
                break;
            default:
                System.err.println("未知のSection指定です" + "@" + this.getClass());
                return;
        }

        String sectionName = subTabsHolder.getSectionName();
        System.out.println("----- Load pane order of '" + sectionName + "' section -----");

        propManager = createPropertyManager(prop_file_path_str);
        //System.out.println("Properties file '" + deresultpane_order_setting_file_path_str + "' was loaded.");
        //propManager.listUpProperty();

        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane()) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();

            JPanel subSectionPanel = managerOfSubTabBasePane.getBasePaneForDEResultPanes();
            Component[] components = subSectionPanel.getComponents();

            // リストに変換して任意の順序付け
            ArrayList<Component> currentComponentArray = new ArrayList<>();
            Collections.addAll(currentComponentArray, components);

            String loaded_order = "";
            for (String property_name : propManager.stringPropertyNames()) {
                loaded_order = (String) propManager.getValueOrCreateNew(property_name);
                //System.out.println(property_name + " -> " + loaded_order);
                if (property_name.equals(subSectionName)) break;
            }
            //System.out.println("Loaded Order of " + subSectionName + ": " + loaded_order);
            ArrayList<String> newlyOrderedJsonNameArray = splitToArrayList(loaded_order);

            //並び替え
            ArrayList<Component> newlyOrderedComponents = new ArrayList<>();
            for (String orderedJsonName : newlyOrderedJsonNameArray) {
                for (Component comp : currentComponentArray) {
                    One_DEResult_Pane_Abs oneDEResultPane = (One_DEResult_Pane_Abs) comp;
                    String jsonName_of_checking_comp = oneDEResultPane.getJsonName();
                    //System.out.println("  Now checking '" + jsonName_of_checking_comp + "'");
                    if (orderedJsonName.equals(jsonName_of_checking_comp)) {
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
