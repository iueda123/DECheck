package iu.LCAC.Member.action.Concretes.DEResultActions.SaveAndLoadPaneOrder;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.CAAA.CAAA_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
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
import iu.LCAC.Member.componentholder.Concretes.StatusPanel.StatusPanelHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SavePaneOrderAction extends AbstActionMember {

    String authorYear = "Someone20XX";

    public SavePaneOrderAction(String action_name, String short_name, String authorYear) {
        super(action_name, short_name);
        this.authorYear = authorYear;
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(
                        KeyStroke.getKeyStroke(
                                KeyEvent.VK_S,
                                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        // 全セクションに拡張可能
        savePaneOrder("SI", "./settings/" + authorYear + "/PaneOrder/" + "study_identification" + ".prop");
        savePaneOrder("SC", "./settings/" + authorYear + "/PaneOrder/" + "study_characteristics" + ".prop");
        savePaneOrder("RCAI", "./settings/" + authorYear + "/PaneOrder/" + "reference_cohort_and_imaging" + ".prop");
        savePaneOrder("NM", "./settings/" + authorYear + "/PaneOrder/" + "normative_modeling" + ".prop");
        savePaneOrder("CAAA", "./settings/" + authorYear + "/PaneOrder/" + "clinical_application_and_analysis" + ".prop");
        savePaneOrder("GN", "./settings/" + authorYear + "/PaneOrder/" + "general_notes" + ".prop");

        savePaneOrder("QASI", "./settings/" + authorYear + "/PaneOrder/" + "study_identification_of_qa" + ".prop");
        savePaneOrder("QA1_v6", "./settings/" + authorYear + "/PaneOrder/" + "quality_assessment_1_v6" + ".prop");
        savePaneOrder("QA2_v6", "./settings/" + authorYear + "/PaneOrder/" + "quality_assessment_2_v6" + ".prop");
        savePaneOrder("QAAC", "./settings/" + authorYear + "/PaneOrder/" + "additional_comments" + ".prop");
    }

    private void savePaneOrder(String member_name_key_word, String prop_file_path_str) {
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
        System.out.println("----- Save pane order of '" + sectionName + "' section -----");

        // 全タブ（SubSectionに相当）配置されているコンポーネントの順番を把握し、propertyへ書き込む
        propManager = createPropertyManager(prop_file_path_str);
        ArrayList<String> arrayList_PanelOrder = new ArrayList<>();
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane()) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();
            //System.out.println("subSectionName: " + subSectionName);
            JPanel subSectionPanel = managerOfSubTabBasePane.getBasePaneForDEQAResultPanes();
            Component[] components = subSectionPanel.getComponents();
            for (int i = 0; i < components.length; i++) {
                Object component = components[i];
                if (component instanceof One_DEQAResult_Pane_Abs) {
                    One_DEQAResult_Pane_Abs oneDEResultPane = (One_DEQAResult_Pane_Abs) component;
                    String jsonName = oneDEResultPane.getJsonName();
                    //System.out.println("  DEResultPane No. " + i + ": " + jsonName);
                    arrayList_PanelOrder.add(jsonName);
                }
            }
            propManager.setProperty(subSectionName, joinWithSemicolon(arrayList_PanelOrder));
            arrayList_PanelOrder.clear();
        }
        boolean property_save_result = propManager.writeoutProperties();
        propManager = null;

        //保存が完了したことをフィードバック
        member = this.cholderMediator.getInstanceOfAMember("status_panel_holder");
        StatusPanelHolder statusPanelHolder = (StatusPanelHolder) member;
        if (property_save_result) {
            statusPanelHolder.showAMessageForWhile("The current panel order was saved.", 5000);
        } else {
            statusPanelHolder.showAMessageForWhile("★Saving the current panel order failed★", 5000);
        }
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
