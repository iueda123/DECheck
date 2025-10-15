package iu.LCAC.Member.action.Concretes.DEResultActions.Init_All_Section;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.One_ARSL_Style_Pane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.One_DEResult_Pane_Abs;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.NM_SubTabsHolder;
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

        // Preparation of Component
        NM_SubTabsHolder subTabsHolder = (NM_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_NM");
        // Initialization Core
        if (subTabsHolder != null) {
            subTabsHolder.postInitialize();
        } else {
            System.err.println("sub_tabs_holder_NM is null.");
        }

        System.out.println("");
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
