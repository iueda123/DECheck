package iu.LCAC.Member.action.Concretes.DEResultActions.SaveAndLoadNotePaneTexts;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.CAAA.CAAA_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.NotePane;
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
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class LoadNotePaneTextsAction extends AbstActionMember {

    String authorYear = "Someone20XX";

    public LoadNotePaneTextsAction(String action_name, String short_name, String authorYear) {
        super(action_name, short_name);
        this.authorYear = authorYear;
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

        // 全セクションに拡張可能
        loadNotePaneTexts("SI", "./settings/" + authorYear + "/NotePane/" + "study_identification" + ".prop");
        loadNotePaneTexts("SC", "./settings/" + authorYear + "/NotePane/" + "study_characteristics" + ".prop");
        loadNotePaneTexts("RCAI", "./settings/" + authorYear + "/NotePane/" + "reference_cohort_and_imaging" + ".prop");
        loadNotePaneTexts("NM", "./settings/" + authorYear + "/NotePane/" + "normative_modeling" + ".prop");
        loadNotePaneTexts("CAAA", "./settings/" + authorYear + "/NotePane/" + "clinical_application_and_analysis" + ".prop");
        loadNotePaneTexts("GN", "./settings/" + authorYear + "/NotePane/" + "general_notes" + ".prop");

        loadNotePaneTexts("QASI", "./settings/" + authorYear + "/NotePane/" + "study_identification_of_qa" + ".prop");
        loadNotePaneTexts("QA1_v6", "./settings/" + authorYear + "/NotePane/" + "quality_assessment_1_v6" + ".prop");
        loadNotePaneTexts("QA2_v6", "./settings/" + authorYear + "/NotePane/" + "quality_assessment_2_v6" + ".prop");
        loadNotePaneTexts("QAAC", "./settings/" + authorYear + "/NotePane/" + "additional_comments" + ".prop");
    }

    /**
     * REFERENCE COHORT AND IMAGING
     */
    private void loadNotePaneTexts(String member_name_key_word, String prop_file_path_str) {

        AbstCHolderMember member = null;
        SubTabsHolderItrfc subTabsHolder = null;
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
        }

        String sectionName = subTabsHolder.getSectionName();
        System.out.println("----- Load texts on NotePanes of '" + sectionName + "' section -----");

        propManager = createPropertyManager(prop_file_path_str);
        //System.out.println("Properties file '" + deresultpane_order_setting_file_path_str + "' was loaded.");
        //propManager.listUpProperty();

        String loaded_text = "";
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane()) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();
            //System.out.println("Now registering NotePane of  '" + subSectionName + "'");
            NotePane notePane = managerOfSubTabBasePane.getNotePane();

            for (String property_name : propManager.stringPropertyNames()) {
                loaded_text = propManager.getValueOrCreateNew(property_name);
                if (property_name.equals(subSectionName + "." + "status")) {
                    notePane.setStatusText(loaded_text);
                } else if (property_name.equals(subSectionName + "." + "note")) {
                    notePane.setNoteText(loaded_text);
                }
            }
            notePane.resetBackgroundColors();
            notePane.updateDefaultValues();
            notePane.updateTabTitle();
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

}
