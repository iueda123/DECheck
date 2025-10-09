package iu.LCAC.Mediator.componentholder;

import iu.LCAC.Mediator.MediatorIntrfc;
import iu.LCAC.Mediator.MemberFactoryLoader;
import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Member.MemberIntrfc;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;
import iu.LCAC.Member.componentholder.Concretes.Sample.CheckboxPanel.CheckboxPanelHolderFactory;
import iu.LCAC.Member.componentholder.Concretes.Sample.TextField.TextFieldPanelHolderFactory;

import java.util.HashMap;
import java.util.Iterator;

/**
 * メインフレームに対する各種操作を一手に引き受けるクラスのためのアブストラクトクラス。
 *
 * <p>もう一つの役割は、 AbstActionMember Class を継承したクラスが呼び出されたときに、一旦Eventを引き受けて、 もしショートカット再設定モードに入っているならば、
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
        // AbstCHolderMember mainWindowHolder = new
        // MainWindowHolderFactory().create("main_window_holder");
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.MainWindow.MainWindowHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember mainWindowHolder =
                chMemberFactory.createCHolder("main_window_holder", "main window");
        mainWindowHolder.setCHolderMediator(this);
        mainWindowHolder.initialize();
        registerMemberToMap(mainWindowHolder);

        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.StatusPanel.StatusPanelHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember statusPanelHolder =
                chMemberFactory.createCHolder("status_panel_holder", "Status Panel Holder");
        statusPanelHolder.setCHolderMediator(this);
        statusPanelHolder.initialize();
        registerMemberToMap(statusPanelHolder);

        /* **** Samples **** */

        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.Sample.ButtonPanel.ButtonPanelHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember buttonPanelHolder =
                chMemberFactory.createCHolder("button_panel_holder", "Button Panel Holder");
        buttonPanelHolder.setCHolderMediator(this);
        buttonPanelHolder.initialize();
        registerMemberToMap(buttonPanelHolder);

        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.Sample.CheckboxPanel.CheckboxPanelHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember checkboxPanelHolder =
                chMemberFactory.createCHolder("checkbox_panel_holder", "Checkbox Panel Holder");
        checkboxPanelHolder.setCHolderMediator(this);
        checkboxPanelHolder.initialize();
        registerMemberToMap(checkboxPanelHolder);

        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.Sample.TextField.TextFieldPanelHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember textFieldPanelHolder =
                chMemberFactory.createCHolder("text_field_panel_holder", "Checkbox Panel Holder");
        textFieldPanelHolder.setCHolderMediator(this);
        textFieldPanelHolder.initialize();
        registerMemberToMap(textFieldPanelHolder);

        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.Sample.RunBashPanel.RunBashPanelHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember runBashPanelHolder =
                chMemberFactory.createCHolder("run_bash_panel_holder", "Run Bash Panel Holder");
        runBashPanelHolder.setCHolderMediator(this);
        runBashPanelHolder.initialize();
        registerMemberToMap(runBashPanelHolder);

        /* 1. SI */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEResult.SI.SI_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_SI =
                chMemberFactory.createCHolder("sub_tabs_holder_SI", "sub_tabs_holder_SI");
        subTabsHoldFactory_SI .setCHolderMediator(this);
        subTabsHoldFactory_SI .initialize();
        registerMemberToMap(subTabsHoldFactory_SI );

        /* 1. SC */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEResult.SC.SC_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_SC =
                chMemberFactory.createCHolder("sub_tabs_holder_SC", "sub_tabs_holder_SC");
        subTabsHoldFactory_SC .setCHolderMediator(this);
        subTabsHoldFactory_SC .initialize();
        registerMemberToMap(subTabsHoldFactory_SC );

        /* 3. RCAI */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.RCAI_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_RCAI =
                chMemberFactory.createCHolder("sub_tabs_holder_RCAI", "sub_tabs_holder_RCAI");
        subTabsHoldFactory_RCAI .setCHolderMediator(this);
        subTabsHoldFactory_RCAI .initialize();
        registerMemberToMap(subTabsHoldFactory_RCAI );

        /* 4. NM */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEResult.NM.NM_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_NM =
                chMemberFactory.createCHolder("sub_tabs_holder_NM", "sub_tabs_holder_NM");
        subTabsHoldFactory_NM.setCHolderMediator(this);
        subTabsHoldFactory_NM.initialize();
        registerMemberToMap(subTabsHoldFactory_NM);

        /* 5. CAAA */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEResult.CAAA.CAAA_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_CAAA =
                chMemberFactory.createCHolder("sub_tabs_holder_CAAA", "sub_tabs_holder_CAAA");
        subTabsHoldFactory_CAAA.setCHolderMediator(this);
        subTabsHoldFactory_CAAA.initialize();
        registerMemberToMap(subTabsHoldFactory_CAAA);

        /* 6. GN */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEResult.GN.GN_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_GN =
                chMemberFactory.createCHolder("sub_tabs_holder_GN", "sub_tabs_holder_GN");
        subTabsHoldFactory_GN.setCHolderMediator(this);
        subTabsHoldFactory_GN.initialize();
        registerMemberToMap(subTabsHoldFactory_GN);

        /* Explanation Text Field */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEResult.Explanation.ExplanationPanelHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember explanationPanelHolderFactory =
                chMemberFactory.createCHolder("explanation_panel_holder", "explanation panel holder");
        explanationPanelHolderFactory.setCHolderMediator(this);
        explanationPanelHolderFactory.initialize();
        registerMemberToMap(explanationPanelHolderFactory);


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

    public void postInitializeEachMember() {
        Iterator<String> it = memberMap.keySet().iterator();
        String key = null;
        while (it.hasNext()) {
            key = it.next();
            ((AbstCHolderMember) memberMap.get(key)).postInitialize();
        }
    }
}
