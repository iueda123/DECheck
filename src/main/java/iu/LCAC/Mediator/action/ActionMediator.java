package iu.LCAC.Mediator.action;

import iu.LCAC.Mediator.MediatorIntrfc;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.MemberIntrfc;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;
import iu.LCAC.Mediator.MemberFactoryLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

public class ActionMediator implements ActionListener, MediatorIntrfc {

    public HashMap<String, MemberIntrfc> memberMap = new HashMap<>();

    public ActionMediator() {
        this.createMembers();
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
        System.out.println("==== ActionMediator has called an action_command: \"" + action_command + "\" ====");

        if (SetupAcceleratorMode) {
            System.out.println("AcceleratorSetupMode has turned ON.");
            //System.out.print("ただいま呼び出されたActionのショートカットを書き換えます。");
            //System.out.print("ただいま呼び出されたActionの名前は '" + action_command + "' です。");
            ((AbstActionMember) memberMap.get("set_acceleration_on_next_click")).perform(action_event);
        } else {
            AbstActionMember called_action = (AbstActionMember) memberMap.get(action_command);
            called_action.perform(action_event);
        }
    }


    @Override
    public void createMembers() {
        /* *** children of main_menu_1 *** */
        AbstActionMemberFactory actionMemberFactory;

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                "iu.LCAC.Member.action.Concretes.change_west_color.ChangeWestColorActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember changeWestColorAction = actionMemberFactory.createAction("change_color_of_west", "blue west");
        //changeWestColorAction.setActionListenerToMenuItem((ActionListener) this);
        changeWestColorAction.setActionMediator(this);
        changeWestColorAction.initialize();
        registerMemberToMap(changeWestColorAction);


        actionMemberFactory = MemberFactoryLoader.loadFactory(
                "iu.LCAC.Member.action.Concretes.change_center_color.ChangeCenterColorActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember changeCenterColorAction = actionMemberFactory.createAction("change_color_of_center", "blue center");
        changeCenterColorAction.setActionMediator(this);
        changeCenterColorAction.initialize();
        registerMemberToMap(changeCenterColorAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                "iu.LCAC.Member.action.Concretes.change_east_color.ChangeEastColorActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember changeEastColorAction = actionMemberFactory.createAction("change_color_of_east", "blue east");
        changeEastColorAction.setActionMediator(this);
        changeEastColorAction.initialize();
        registerMemberToMap(changeEastColorAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                "iu.LCAC.Member.action.Concretes.change_text_of_button_panel.ChangeTextOfButtonPanelActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember changeTextOfButtonPanelAction = actionMemberFactory.createAction("change_text", "change text");
        changeTextOfButtonPanelAction.setActionMediator(this);
        changeTextOfButtonPanelAction.initialize();
        registerMemberToMap(changeTextOfButtonPanelAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                "iu.LCAC.Member.action.Concretes.initialize_textfield_panel.InitializeTextFieldPanelActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember initializeTextFieldPanelAction = actionMemberFactory.createAction("initialize_sample_text_field", "initialize textfield");
        initializeTextFieldPanelAction.setActionMediator(this);
        initializeTextFieldPanelAction.initialize();
        registerMemberToMap(initializeTextFieldPanelAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                "iu.LCAC.Member.action.Concretes.CoreActions.Save_and_Load.LoadAcceleratorSettingsActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember loadAcceleratorSettingsAction = actionMemberFactory.createAction("load_accelerator_settings", "Load Accelerator");
        loadAcceleratorSettingsAction.setActionMediator(this);
        loadAcceleratorSettingsAction.initialize();
        registerMemberToMap(loadAcceleratorSettingsAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                "iu.LCAC.Member.action.Concretes.CoreActions.Save_and_Load.SaveAcceleratorSettingsActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember saveAcceleratorSettingsAction = actionMemberFactory.createAction("save_accelerator_settings", "Save Accelerator");
        saveAcceleratorSettingsAction.setActionMediator(this);
        saveAcceleratorSettingsAction.initialize();
        registerMemberToMap(saveAcceleratorSettingsAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                "iu.LCAC.Member.action.Concretes.CoreActions.SetAcceleratorOnNextClick.SetAcceleratorOnNextClickActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember setAcceleratorOnNextClickAction = actionMemberFactory.createAction("set_acceleration_on_next_click", "Setup Accelerator");
        setAcceleratorOnNextClickAction.setActionMediator(this);
        setAcceleratorOnNextClickAction.initialize();
        registerMemberToMap(setAcceleratorOnNextClickAction);


        actionMemberFactory = MemberFactoryLoader.loadFactory(
                "iu.LCAC.Member.action.Concretes.change_text_of_textfield.ChangeTextOfTextFieldActionFactory",
                AbstActionMemberFactory.class);
        AbstActionMember changeTextOfTextFieldAction = actionMemberFactory.createAction("change_text_of_textfield", "Change Text");
        changeTextOfTextFieldAction.setActionMediator(this);
        changeTextOfTextFieldAction.initialize();
        registerMemberToMap(changeTextOfTextFieldAction);

    }

    private void registerMemberToMap(AbstActionMember action) {
        memberMap.put(action.getMemberName(), action);
    }

    @Override
    public void requestFromMember() {

    }

    @Override
    public HashMap<String, MemberIntrfc> getMemberMap() {
        return memberMap;
    }

    public AbstActionMember getInstanceOfAMember(String member_name) {
        return (AbstActionMember) memberMap.get(member_name);
    }

    public void registerCHolderMediatorToEachMember(CHolderMediator cHolderMediator) {
        Iterator<String> it = memberMap.keySet().iterator();
        String key = null;
        while (it.hasNext()) {
            key = it.next();
            memberMap.get(key).setCHolderMediator(cHolderMediator);
        }
    }

}
