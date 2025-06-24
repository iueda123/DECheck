package iu.LCAC.Framework.componentholder.mediator;


import iu.LCAC.Framework.action.mediator.ActionMediatorIntrfc;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMember;
import iu.LCAC.Framework.action.member.ActionMemberIntrfc;
import iu.LCAC.Framework.componentholder.member.AbstCHolderMemberFactory;
import iu.LCAC.Framework.componentholder.member.CHolderMemberIntrfc;

import java.util.HashMap;
import java.util.Iterator;

/**
 * メインフレームに対する各種操作を一手に引き受けるクラスのためのアブストラクトクラス。
 * <p>
 * もう一つの役割は、
 * AbstActionMember Class を継承したクラスが呼び出されたときに、一旦Eventを引き受けて、
 * もしショートカット再設定モードに入っているならば、
 * 呼び出されたAction_A Classのサブクラスのperform()を抑制するという機能。
 */
public class CHolderMediator implements CHolderMediatorIntrfc {

    // これは文字列からComponentのインスタンスを取り出すために必要
    private final HashMap<String, CHolderMemberIntrfc> memberMap = new HashMap<>();


    public CHolderMediator() {
        createCHMembers();
    }

    @Override
    public void createCHMembers() {

        AbstCHolderMemberFactory chMemberFactory;
        //AbstCHolderMember mainWindowHolder = new MainWindowHolderFactory().create("main_window_holder");
        chMemberFactory = AbstCHolderMemberFactory.getCHolderMemberFactory("iu.LCAC.ComponentHolders.core.mainwindow.MainWindowHolderFactory");
        AbstCHolderMember mainWindowHolder = chMemberFactory.createCHolder("main_window_holder", "main window");
        mainWindowHolder.setCHMediator(this);
        mainWindowHolder.initialize();
        registerMemberToMap(mainWindowHolder);

        chMemberFactory = AbstCHolderMemberFactory.getCHolderMemberFactory("iu.LCAC.ComponentHolders.demos.ButtonPanel.ButtonPanelHolderFactory");
        AbstCHolderMember buttonPanelHolder = chMemberFactory.createCHolder("button_panel_holder", "Button Penel Holder");
        buttonPanelHolder.setCHMediator(this);
        buttonPanelHolder.initialize();
        registerMemberToMap(buttonPanelHolder);

        chMemberFactory = AbstCHolderMemberFactory.getCHolderMemberFactory("iu.LCAC.ComponentHolders.demos.CheckboxPanel.CheckboxPanelHolderFactory");
        AbstCHolderMember checkboxPanelHolder = chMemberFactory.createCHolder("checkbox_panel_holder", "Checkbox Penel Holder");
        checkboxPanelHolder.setCHMediator(this);
        checkboxPanelHolder.initialize();
        registerMemberToMap(checkboxPanelHolder);

        chMemberFactory = AbstCHolderMemberFactory.getCHolderMemberFactory("iu.LCAC.ComponentHolders.demos.TextField.TextFieldPanelHolderFactory");
        AbstCHolderMember textFieldPanelHolder = chMemberFactory.createCHolder("text_field_panel_holder", "Checkbox Penel Holder");
        textFieldPanelHolder.setCHMediator(this);
        textFieldPanelHolder.initialize();
        registerMemberToMap(textFieldPanelHolder);

        //AbstCHolderMember checkboxPanelHolder = new CheckboxPanelHolderFactory().create("checkbox_panel_holder");
        //checkboxPanelHolder.setCHMediator(this);
        //checkboxPanelHolder.initialize();
        //registerMember(checkboxPanelHolder);

        //AbstCHolderMember textFieldPanelHolder = new TextFieldPanelHolderFactory().create("text_field_panel_holder");
        //textFieldPanelHolder.setCHMediator(this);
        //textFieldPanelHolder.initialize();
        //registerMember(textFieldPanelHolder);

    }

    private void registerMemberToMap(AbstCHolderMember member) {
        memberMap.put(member.getName(), member);
    }

    @Override
    public void requestFromACHMember() {

    }

    public HashMap<String, CHolderMemberIntrfc> getMemberMap() {
        return memberMap;
    }

    public AbstCHolderMember getInstanceOfAMember(String member_name) {
        return (AbstCHolderMember) memberMap.get(member_name);
    }


    public void registerActionMediatorToEachMember(ActionMediatorIntrfc actionMediatorIntrfc) {
        Iterator<String> it = memberMap.keySet().iterator();
        String key = null;
        while (it.hasNext()) {
            key = it.next();
            ((ActionMemberIntrfc) (memberMap.get(key))).setActnMediator(actionMediatorIntrfc);
        }
    }

    public void finalizeEachMember(){
        Iterator<String> it = memberMap.keySet().iterator();
        String key = null;
        while (it.hasNext()) {
            key = it.next();
            ((AbstCHolderMember) memberMap.get(key)).finalize();
        }
    }



}
