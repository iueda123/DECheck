package iu.LCAC.Member.action.Concretes.DEResultActions.Init_All_Section;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.CAAA.CAAA_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.One_ARSL_Style_Pane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.One_DEResult_Pane_Abs;
import iu.LCAC.Member.componentholder.Concretes.DEResult.GN.GN_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.NM_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.RCAI_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.SC.SC_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.SI.SI_SubTabsHolder;
import iu.LCAC.Utils.JsonManager;

import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

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
            subTabsHolder_SI.postInitialize();
        } else {
            System.err.println("sub_tabs_holder_SI is null.");
        }

        SC_SubTabsHolder subTabsHolder_SC = (SC_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_SC");
        if (subTabsHolder_SC != null) {
            subTabsHolder_SC.postInitialize();
        } else {
            System.err.println("sub_tabs_holder_SC is null.");
        }

        RCAI_SubTabsHolder subTabsHolder_RCAI = (RCAI_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_RCAI");
        if (subTabsHolder_RCAI != null) {
            subTabsHolder_RCAI.postInitialize();
        } else {
            System.err.println("sub_tabs_holder_RCAI is null.");
        }

        NM_SubTabsHolder subTabsHolder_NM = (NM_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_NM");
        if (subTabsHolder_NM != null) {
            subTabsHolder_NM.postInitialize();
        } else {
            System.err.println("sub_tabs_holder_NM is null.");
        }

        CAAA_SubTabsHolder subTabsHolder_CAAA = (CAAA_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_CAAA");
        if (subTabsHolder_CAAA != null) {
            subTabsHolder_CAAA.postInitialize();
        } else {
            System.err.println("sub_tabs_holder_CAAA is null.");
        }

        GN_SubTabsHolder subTabsHolder_GN = (GN_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_GN");
        if (subTabsHolder_GN != null) {
            subTabsHolder_GN.postInitialize();
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
