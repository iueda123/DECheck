package iu.LCAC.Framework.Mediator.componentholder;


import iu.LCAC.Framework.Mediator.MediatorIntrfc;
import iu.LCAC.Framework.Mediator.action.ActionMediator;
import iu.LCAC.Framework.Member.MemberIntrfc;
import iu.LCAC.Framework.Member.componentholder.AbstCHolderMember;
import iu.LCAC.Framework.Member.componentholder.AbstCHolderMemberFactory;
import iu.LCAC.Framework.FactoryLoader.MemberFactoryLoader;

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
public class CHolderMediator implements MediatorIntrfc {

    // これは文字列からComponentのインスタンスを取り出すために必要
    private final HashMap<String, MemberIntrfc> memberMap = new HashMap<>();


    public CHolderMediator() {
        createMembers();
    }

    @Override
    public void createMembers() {

        AbstCHolderMemberFactory chMemberFactory;
        //AbstCHolderMember mainWindowHolder = new MainWindowHolderFactory().create("main_window_holder");
        chMemberFactory = MemberFactoryLoader.loadFactory(
                "iu.LCAC.ComponentHolders.core.mainwindow.MainWindowHolderFactory",
                AbstCHolderMemberFactory.class);
        AbstCHolderMember mainWindowHolder = chMemberFactory.createCHolder("main_window_holder", "main window");
        mainWindowHolder.setCHolderMediator(this);
        mainWindowHolder.initialize();
        registerMemberToMap(mainWindowHolder);

        chMemberFactory = MemberFactoryLoader.loadFactory(
                "iu.LCAC.ComponentHolders.demos.ButtonPanel.ButtonPanelHolderFactory",
                AbstCHolderMemberFactory.class);
        AbstCHolderMember buttonPanelHolder = chMemberFactory.createCHolder("button_panel_holder", "Button Penel Holder");
        buttonPanelHolder.setCHolderMediator(this);
        buttonPanelHolder.initialize();
        registerMemberToMap(buttonPanelHolder);

        chMemberFactory = MemberFactoryLoader.loadFactory(
                "iu.LCAC.ComponentHolders.demos.CheckboxPanel.CheckboxPanelHolderFactory",
                AbstCHolderMemberFactory.class);
        AbstCHolderMember checkboxPanelHolder = chMemberFactory.createCHolder("checkbox_panel_holder", "Checkbox Penel Holder");
        checkboxPanelHolder.setCHolderMediator(this);
        checkboxPanelHolder.initialize();
        registerMemberToMap(checkboxPanelHolder);

        chMemberFactory = MemberFactoryLoader.loadFactory(
                "iu.LCAC.ComponentHolders.demos.TextField.TextFieldPanelHolderFactory",
                AbstCHolderMemberFactory.class);
        AbstCHolderMember textFieldPanelHolder = chMemberFactory.createCHolder("text_field_panel_holder", "Checkbox Penel Holder");
        textFieldPanelHolder.setCHolderMediator(this);
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
        memberMap.put(member.getMemberName(), member);
    }

    @Override
    public void requestFromMember() {

    }

    @Override
    public HashMap<String, MemberIntrfc> getMemberMap() {
        return memberMap;
    }

    public AbstCHolderMember getInstanceOfAMember(String member_name) {
        return (AbstCHolderMember) memberMap.get(member_name);
    }


    public void registerActionMediatorToEachMember(ActionMediator actionMediator) {
        Iterator<String> it = memberMap.keySet().iterator();
        String key = null;
        while (it.hasNext()) {
            key = it.next();
            memberMap.get(key).setActionMediator(actionMediator);
        }
    }

    public void postInitializeEachMember(){
        Iterator<String> it = memberMap.keySet().iterator();
        String key = null;
        while (it.hasNext()) {
            key = it.next();
            ((AbstCHolderMember) memberMap.get(key)).postInitialize();
        }
    }



}
