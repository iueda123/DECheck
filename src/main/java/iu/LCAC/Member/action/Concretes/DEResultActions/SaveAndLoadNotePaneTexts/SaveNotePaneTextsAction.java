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

public class SaveNotePaneTextsAction extends AbstActionMember {


    public SaveNotePaneTextsAction(String action_name, String short_name) {
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

        // 全セクションに拡張可能
        saveNotePaneState("SI", "./settings/NotePane/" + "study_identification" + ".prop");
        saveNotePaneState("SC", "./settings/NotePane/" + "study_characteristics" + ".prop");
        saveNotePaneState("RCAI", "./settings/NotePane/" + "reference_cohort_and_imaging" + ".prop");
        saveNotePaneState("NM", "./settings/NotePane/" + "normative_modeling" + ".prop");
        saveNotePaneState("CAAA", "./settings/NotePane/" + "clinical_application_and_analysis" + ".prop");
        saveNotePaneState("GN", "./settings/NotePane/" + "general_notes" + ".prop");

        saveNotePaneState("QASI", "./settings/NotePane/" + "study_identification_of_qa" + ".prop");
        saveNotePaneState("QA1_v6", "./settings/NotePane/" + "quality_assessment_1_v6" + ".prop");
        saveNotePaneState("QA2_v6", "./settings/NotePane/" + "quality_assessment_2_v6" + ".prop");
        saveNotePaneState("QAAC", "./settings/NotePane/" + "additional_comments" + ".prop");
    }

    private void saveNotePaneState(String member_name_key_word, String prop_file_path_str) {
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
        System.out.println("----- Save texts on NotePanes of '" + sectionName + "' section -----");

        // 全タブ（SubSectionに相当）配置されているコンポーネントの順番を把握し、propertyへ書き込む
        propManager = createPropertyManager(prop_file_path_str);
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane()) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();
            //System.out.println("Now registering NotePane of  '" + subSectionName + "'");
            NotePane notePane = managerOfSubTabBasePane.getNotePane();
            propManager.setProperty(subSectionName + ".status", notePane.getStatusText());
            propManager.setProperty(subSectionName + ".note", notePane.getNoteText());
            notePane.resetBackgroundColors();
            notePane.updateDefaultValues();
            notePane.updateTabTitle();
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

}
