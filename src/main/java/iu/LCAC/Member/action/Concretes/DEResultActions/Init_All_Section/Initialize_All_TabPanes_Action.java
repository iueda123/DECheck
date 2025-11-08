package iu.LCAC.Member.action.Concretes.DEResultActions.Init_All_Section;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.CAAA.CAAA_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.One_DEQAResult_Pane_Abs;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.GN.GN_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.NM.NM_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.RCAI.RCAI_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.SC.SC_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.SI.SI_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QA1.QA1_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QA2.QA2_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QAAC.QAAC_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QASI.QASI_SubTabsHolder;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Initialize_All_TabPanes_Action extends AbstActionMember {

    public Initialize_All_TabPanes_Action(String action_name, String short_name) {
        super(action_name, short_name);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        /* 1. Study Identification of DE */
        SI_SubTabsHolder subTabsHolder_SI = (SI_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_SI");
        if (subTabsHolder_SI != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_SI.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_SI is null.");
        }

        /* 2. Study Characteristics */
        SC_SubTabsHolder subTabsHolder_SC = (SC_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_SC");
        if (subTabsHolder_SC != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_SC.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_SC is null.");
        }

        /* 3. Reference Cohort and Imaging */
        RCAI_SubTabsHolder subTabsHolder_RCAI = (RCAI_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_RCAI");
        if (subTabsHolder_RCAI != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_RCAI.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_RCAI is null.");
        }

        /* 4. Normative Modeling */
        NM_SubTabsHolder subTabsHolder_NM = (NM_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_NM");
        if (subTabsHolder_NM != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_NM.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_NM is null.");
        }

        /* 5. Clinical Application and Analysis */
        CAAA_SubTabsHolder subTabsHolder_CAAA = (CAAA_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_CAAA");
        if (subTabsHolder_CAAA != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_CAAA.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_CAAA is null.");
        }

        /* 5. Clinical Application and Analysis */
        GN_SubTabsHolder subTabsHolder_GN = (GN_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_GN");
        if (subTabsHolder_GN != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_GN.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_GN is null.");
        }

        //--------------------------

        /* 7. Study Identification of QA */
        QASI_SubTabsHolder subTabsHolder_QASI = (QASI_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QASI");
        if (subTabsHolder_QASI != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_QASI.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("subTabsHolder_QASI is null.");
        }

        /* 8. Quality Assessment Part 1 (v6) */
        QA1_SubTabsHolder subTabsHolder_QA1 = (QA1_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QA1_v6");
        if (subTabsHolder_QA1 != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_QA1.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("subTabsHolder_QA1 is null.");
        }

        /* 9. Quality Assessment Part 2 (v6) */
        QA2_SubTabsHolder subTabsHolder_QA2 = (QA2_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QA2_v6");
        if (subTabsHolder_QA2 != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_QA2.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("subTabsHolder_QA2 is null.");
        }

        /* 10. Additional Comment of QA */
        QAAC_SubTabsHolder subTabsHolder_QAAC = (QAAC_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QAAC");
        if (subTabsHolder_QAAC != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_QAAC.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("subTabsHolder_QAAC is null.");
        }
    }

    @Override
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
