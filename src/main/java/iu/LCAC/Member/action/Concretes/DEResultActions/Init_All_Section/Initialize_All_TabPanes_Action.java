package iu.LCAC.Member.action.Concretes.DEResultActions.Init_All_Section;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.CAAA.CAAA_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.One_DEResult_Pane_Abs;
import iu.LCAC.Member.componentholder.Concretes.DEResult.GN.GN_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.NM_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.RCAI_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.SC.SC_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.SI.SI_SubTabsHolder;

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

        SI_SubTabsHolder subTabsHolder_SI = (SI_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_SI");
        if (subTabsHolder_SI != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_SI.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_SI is null.");
        }

        SC_SubTabsHolder subTabsHolder_SC = (SC_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_SC");
        if (subTabsHolder_SC != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_SC.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_SC is null.");
        }

        RCAI_SubTabsHolder subTabsHolder_RCAI = (RCAI_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_RCAI");
        if (subTabsHolder_RCAI != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_RCAI.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_RCAI is null.");
        }

        NM_SubTabsHolder subTabsHolder_NM = (NM_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_NM");
        if (subTabsHolder_NM != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_NM.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_NM is null.");
        }

        CAAA_SubTabsHolder subTabsHolder_CAAA = (CAAA_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_CAAA");
        if (subTabsHolder_CAAA != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_CAAA.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_CAAA is null.");
        }

        GN_SubTabsHolder subTabsHolder_GN = (GN_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_GN");
        if (subTabsHolder_GN != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_GN.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDePaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_GN is null.");
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
