package iu.LCAC.Member.action.Concretes.DEResultActions.Init_SC_Section;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.One_A_Style_Pane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.SC.SC_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.SI.SI_SubTabsHolder;
import iu.LCAC.Utils.JsonManager;

import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Initialize_SC_TabPanes_Action extends AbstActionMember {

    static final String jsonFolderPathString = "./json/DE";

    public Initialize_SC_TabPanes_Action(String action_name, String short_name) {
        super(action_name, short_name);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        String jsonFileName =  jsonFolderPathString + "/" + "empty.json";
        String sectionName = "";
        String subSectionName = "";
        String[] cmd_and_args = getActionCommandAndArgs(action_event, false);
        if (cmd_and_args.length > 3) {
            // 引数からjsonNameを取得
            jsonFileName = cmd_and_args[1];
            Path jsonFilePath = Paths.get(jsonFolderPathString + "/" + jsonFileName);
            if (!jsonFilePath.toFile().exists()) {
                System.err.println(jsonFilePath.toFile().getAbsolutePath() + " does not exist.");
                return;
            }
            sectionName = cmd_and_args[2];
            subSectionName = cmd_and_args[3];
            System.out.println("Loading JSON file: " + jsonFilePath.toFile().getAbsolutePath());
            System.out.println("     Section Name: " + sectionName);
            System.out.println("  Subsection Name: " + subSectionName);

            // Load Properties
            JsonManager jsonManager = new JsonManager(jsonFilePath.toFile());

            String answer = jsonManager.getValue(sectionName + "/" + subSectionName );
            //System.out.println(answer);

            // Preparation of Component
            SC_SubTabsHolder subTabsHolder =
                    (SC_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_SC");

            // Initialization Core
            if (subTabsHolder != null) {

                One_A_Style_Pane trtgDEResultPane = (One_A_Style_Pane) subTabsHolder.getResultPane(jsonFileName, sectionName, subSectionName);
                trtgDEResultPane.updateRegisteredJsonName(jsonFileName);
                trtgDEResultPane.setValTo_Answer(answer);

                trtgDEResultPane.resetBackgroundColorOfTAreasTFields();

            } else {
                System.err.println("sub_tabs_holder_SC is null.");
            }
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
