package iu.LCAC.Framework.action.mediator;

import iu.LCAC.Framework.componentholder.mediator.CHolderMediatorIntrfc;
import iu.LCAC.Framework.action.member.AbstActionMember;
import iu.LCAC.Framework.action.member.AbstActionMemberFactory;
import iu.LCAC.Framework.factory.FactoryLoader;
import iu.LCAC.Framework.action.member.ActionMemberIntrfc;
import iu.LCAC.Framework.componentholder.member.CHolderMemberIntrfc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

public class ActionMediator implements ActionListener, ActionMediatorIntrfc {

    public HashMap<String, ActionMemberIntrfc> memberMap = new HashMap<>();

    public ActionMediator() {
        this.createActnMembers();
    }

    /* *** Variables and Methods for AcceleratorSetupMode *** */

    static boolean SetupAcceleratorMode = false;

    public void enableAcceleratorSetupMode() {
        SetupAcceleratorMode = true;
    }

    public void disableAcceleratorSetupMode() {
        SetupAcceleratorMode = false;
    }


    @Override
    public void actionPerformed(ActionEvent action_event) {
        String action_command = action_event.getActionCommand();
        System.out.println("==== ActionMediator has called an action_command: " + action_command + " ====");

        if (SetupAcceleratorMode) {
            System.out.println("AcceleratorSerupMode has turned ON.");
            //System.out.print("ただいま呼び出されたActionのショートカットを書き換えます。");
            //System.out.print("ただいま呼び出されたActionの名前は '" + action_command + "' です。");
            ((AbstActionMember) memberMap.get("set_acceleration_on_next_click")).perform(action_event);
        } else {
            AbstActionMember called_action = (AbstActionMember) memberMap.get(action_command);
            called_action.perform(action_event);
        }
    }


    @Override
    public void createActnMembers() {
        /* *** children of main_menu_1 *** */
        AbstActionMemberFactory actionMemberFactory;

        actionMemberFactory = FactoryLoader.loadFactory(
                "iu.LCAC.Actions.demos.change_west_color.ChangeWestColorActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember changeWestColorAction = actionMemberFactory.createAction("change_color_of_west", "blue west");
        //changeWestColorAction.setActionListenerToMenuItem((ActionListener) this);
        changeWestColorAction.setActnMediator(this);
        changeWestColorAction.initialize();
        registerMemberToMap(changeWestColorAction);


        actionMemberFactory = FactoryLoader.loadFactory(
                "iu.LCAC.Actions.demos.change_center_color.ChangeCenterColorActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember changeCenterColorAction = actionMemberFactory.createAction("change_color_of_center", "blue center");
        changeCenterColorAction.setActnMediator(this);
        changeCenterColorAction.initialize();
        registerMemberToMap(changeCenterColorAction);

        actionMemberFactory = FactoryLoader.loadFactory(
                "iu.LCAC.Actions.demos.change_east_color.ChangeEastColorActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember changeEastColorAction = actionMemberFactory.createAction("change_color_of_east", "blue east");
        changeEastColorAction.setActnMediator(this);
        changeEastColorAction.initialize();
        registerMemberToMap(changeEastColorAction);

        actionMemberFactory = FactoryLoader.loadFactory(
                "iu.LCAC.Actions.demos.change_text_of_button_panel.ChangeTextOfButtonPanelActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember changeTextOfButtonPanelAction = actionMemberFactory.createAction("change_text", "change text");
        changeTextOfButtonPanelAction.setActnMediator(this);
        changeTextOfButtonPanelAction.initialize();
        registerMemberToMap(changeTextOfButtonPanelAction);

        actionMemberFactory = FactoryLoader.loadFactory(
                "iu.LCAC.Actions.demos.initialize_textfield_panel.InitializeTextFieldPanelActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember initializeTextFieldPanelAction = actionMemberFactory.createAction("initialize_sample_text_field", "initialize textfield");
        initializeTextFieldPanelAction.setActnMediator(this);
        initializeTextFieldPanelAction.initialize();
        registerMemberToMap(initializeTextFieldPanelAction);

        actionMemberFactory = FactoryLoader.loadFactory(
                "iu.LCAC.CoreActions.Save_and_Load.LoadAcceleratorSettingsActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember loadAcceleratorSettingsAction = actionMemberFactory.createAction("load_accelerator_settings", "Load Accelerator");
        loadAcceleratorSettingsAction.setActnMediator(this);
        loadAcceleratorSettingsAction.initialize();
        registerMemberToMap(loadAcceleratorSettingsAction);

        actionMemberFactory = FactoryLoader.loadFactory(
                "iu.LCAC.CoreActions.Save_and_Load.SaveAcceleratorSettingsActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember saveAcceleratorSettingsAction = actionMemberFactory.createAction("save_accelerator_settings", "Save Accelerator");
        saveAcceleratorSettingsAction.setActnMediator(this);
        saveAcceleratorSettingsAction.initialize();
        registerMemberToMap(saveAcceleratorSettingsAction);

        actionMemberFactory = FactoryLoader.loadFactory(
                "iu.LCAC.CoreActions.SetAcceleratorOnNextClick.SetAcceleratorOnNextClickActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember setAcceleratorOnNextClickAction = actionMemberFactory.createAction("set_acceleration_on_next_click", "Setup Accelerator");
        setAcceleratorOnNextClickAction.setActnMediator(this);
        setAcceleratorOnNextClickAction.initialize();
        registerMemberToMap(setAcceleratorOnNextClickAction);

    }

    private void registerMemberToMap(AbstActionMember action) {
        memberMap.put(action.getActionName(), action);
    }

    @Override
    public void requestFromAnAcMember() {

    }

    public HashMap<String, ActionMemberIntrfc> getMemberMap() {
        return memberMap;
    }

    public AbstActionMember getInstanceOfAMember(String member_name) {
        return (AbstActionMember) memberMap.get(member_name);
    }

    public void registerCHolderMediatorToEachMember(CHolderMediatorIntrfc CHolderMediatorIntrfc) {
        Iterator<String> it = memberMap.keySet().iterator();
        String key = null;
        while (it.hasNext()) {
            key = it.next();
            ((CHolderMemberIntrfc) memberMap.get(key)).setCHMediator(CHolderMediatorIntrfc);
        }
    }

}
