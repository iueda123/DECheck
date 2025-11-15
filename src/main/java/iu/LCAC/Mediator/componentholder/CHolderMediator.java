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

    public CHolderMediator(String authorYear) {
        createMembers(authorYear);
    }

    @Override
    public void createMembers() {
        createMembers("");
    }


    @Override
    public void createMembers(String... authorYears) {

        //------------------------------------------
        /* **** Core **** */

        AbstCHolderMemberFactory chMemberFactory;
        // AbstCHolderMember mainWindowHolder = new
        // MainWindowHolderFactory().create("main_window_holder");
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.MainWindow.MainWindowHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember mainWindowHolder =
                chMemberFactory.createCHolder("main_window_holder", "main window", authorYears[0]);
        mainWindowHolder.setCHolderMediator(this);
        mainWindowHolder.initialize();
        registerMemberToMap(mainWindowHolder);

        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.StatusPanel.StatusPanelHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember statusPanelHolder =
                chMemberFactory.createCHolder("status_panel_holder", "Status Panel Holder", authorYears[0]);
        statusPanelHolder.setCHolderMediator(this);
        statusPanelHolder.initialize();
        registerMemberToMap(statusPanelHolder);

        //------------------------------------------
        /* **** Samples **** */

        /* **** ButtonPanel **** */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.Sample.ButtonPanel.ButtonPanelHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember buttonPanelHolder =
                chMemberFactory.createCHolder("button_panel_holder", "Button Panel Holder", authorYears[0]);
        buttonPanelHolder.setCHolderMediator(this);
        buttonPanelHolder.initialize();
        registerMemberToMap(buttonPanelHolder);

        /* **** CheckboxPanel **** */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.Sample.CheckboxPanel.CheckboxPanelHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember checkboxPanelHolder =
                chMemberFactory.createCHolder("checkbox_panel_holder", "Checkbox Panel Holder", authorYears[0]);
        checkboxPanelHolder.setCHolderMediator(this);
        checkboxPanelHolder.initialize();
        registerMemberToMap(checkboxPanelHolder);

        /* **** TextField **** */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.Sample.TextField.TextFieldPanelHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember textFieldPanelHolder =
                chMemberFactory.createCHolder("text_field_panel_holder", "Checkbox Panel Holder", authorYears[0]);
        textFieldPanelHolder.setCHolderMediator(this);
        textFieldPanelHolder.initialize();
        registerMemberToMap(textFieldPanelHolder);

        /* **** RunBashPanel **** */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.Sample.RunBashPanel.RunBashPanelHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember runBashPanelHolder =
                chMemberFactory.createCHolder("run_bash_panel_holder", "Run Bash Panel Holder", authorYears[0]);
        runBashPanelHolder.setCHolderMediator(this);
        runBashPanelHolder.initialize();
        registerMemberToMap(runBashPanelHolder);

        //------------------------------------------

        /* 1. Study Identification of DE */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.SI.SI_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_SI =
                chMemberFactory.createCHolder("sub_tabs_holder_SI", "sub_tabs_holder_SI", authorYears[0]);
        subTabsHoldFactory_SI.setCHolderMediator(this);
        subTabsHoldFactory_SI.initialize();
        registerMemberToMap(subTabsHoldFactory_SI);

        /* 2. Study Characteristics */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.SC.SC_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_SC =
                chMemberFactory.createCHolder("sub_tabs_holder_SC", "sub_tabs_holder_SC", authorYears[0]);
        subTabsHoldFactory_SC.setCHolderMediator(this);
        subTabsHoldFactory_SC.initialize();
        registerMemberToMap(subTabsHoldFactory_SC);

        /* 3. Reference Cohort and Imaging */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.RCAI.RCAI_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_RCAI =
                chMemberFactory.createCHolder("sub_tabs_holder_RCAI", "sub_tabs_holder_RCAI", authorYears[0]);
        subTabsHoldFactory_RCAI.setCHolderMediator(this);
        subTabsHoldFactory_RCAI.initialize();
        registerMemberToMap(subTabsHoldFactory_RCAI);

        /* 4. Normative Modeling */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.NM.NM_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_NM =
                chMemberFactory.createCHolder("sub_tabs_holder_NM", "sub_tabs_holder_NM", authorYears[0]);
        subTabsHoldFactory_NM.setCHolderMediator(this);
        subTabsHoldFactory_NM.initialize();
        registerMemberToMap(subTabsHoldFactory_NM);

        /* 5. Clinical Application and Analysis */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.CAAA.CAAA_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_CAAA =
                chMemberFactory.createCHolder("sub_tabs_holder_CAAA", "sub_tabs_holder_CAAA", authorYears[0]);
        subTabsHoldFactory_CAAA.setCHolderMediator(this);
        subTabsHoldFactory_CAAA.initialize();
        registerMemberToMap(subTabsHoldFactory_CAAA);

        /* 6. General Notes of DE */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult.GN.GN_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_GN =
                chMemberFactory.createCHolder("sub_tabs_holder_GN", "sub_tabs_holder_GN", authorYears[0]);
        subTabsHoldFactory_GN.setCHolderMediator(this);
        subTabsHoldFactory_GN.initialize();
        registerMemberToMap(subTabsHoldFactory_GN);

        //------------------------------------------

        /* Explanation Text Field */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.Explanation.ExplanationPanelHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember explanationPanelHolderFactory =
                chMemberFactory.createCHolder("explanation_panel_holder", "explanation panel holder", authorYears[0]);
        explanationPanelHolderFactory.setCHolderMediator(this);
        explanationPanelHolderFactory.initialize();
        registerMemberToMap(explanationPanelHolderFactory);

        //------------------------------------------

        /* 7. Common Part of QA */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QACM.QACM_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_QACM =
                chMemberFactory.createCHolder("sub_tabs_holder_QACM", "sub_tabs_holder_QADM", authorYears[0]);
        subTabsHoldFactory_QACM.setCHolderMediator(this);
        subTabsHoldFactory_QACM.initialize();
        registerMemberToMap(subTabsHoldFactory_QACM);

        /* 8. Normative Modeling Part of QA */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QANM.QANM_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_QANM =
                chMemberFactory.createCHolder("sub_tabs_holder_QANM", "sub_tabs_holder_QANM", authorYears[0]);
        subTabsHoldFactory_QANM.setCHolderMediator(this);
        subTabsHoldFactory_QANM.initialize();
        registerMemberToMap(subTabsHoldFactory_QANM);

        /* 9. Clinical Research Part of QA */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QACR.QACR_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_QACR =
                chMemberFactory.createCHolder("sub_tabs_holder_QACR", "sub_tabs_holder_QACR", authorYears[0]);
        subTabsHoldFactory_QACR.setCHolderMediator(this);
        subTabsHoldFactory_QACR.initialize();
        registerMemberToMap(subTabsHoldFactory_QACR);


        /* 7. Study Identification of QA */
        /*
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QASI.QASI_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_QASI =
                chMemberFactory.createCHolder("sub_tabs_holder_QASI", "sub_tabs_holder_QASI", authorYears[0]);
        subTabsHoldFactory_QASI.setCHolderMediator(this);
        subTabsHoldFactory_QASI.initialize();
        registerMemberToMap(subTabsHoldFactory_QASI);
        */


        /* 8. Quality Assessment Part 1 (v6) */
        /*
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QA1.QA1_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_QA1_v6 =
                chMemberFactory.createCHolder("sub_tabs_holder_QA1_v6", "sub_tabs_holder_QA1_v6", authorYears[0]);
        subTabsHoldFactory_QA1_v6.setCHolderMediator(this);
        subTabsHoldFactory_QA1_v6.initialize();
        registerMemberToMap(subTabsHoldFactory_QA1_v6);
        */

        /* 9. Quality Assessment Part 2 (v6) */
        /*
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QA2.QA2_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_QA2_v6 =
                chMemberFactory.createCHolder("sub_tabs_holder_QA2_v6", "sub_tabs_holder_QA2_v6", authorYears[0]);
        subTabsHoldFactory_QA2_v6.setCHolderMediator(this);
        subTabsHoldFactory_QA2_v6.initialize();
        registerMemberToMap(subTabsHoldFactory_QA2_v6);
        */

        /* 10. Additional Comment of QA */
        /*
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QAAC.QAAC_SubTabsHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_QAAC =
                chMemberFactory.createCHolder("sub_tabs_holder_QAAC", "sub_tabs_holder_QAAC", authorYears[0]);
        subTabsHoldFactory_QAAC.setCHolderMediator(this);
        subTabsHoldFactory_QAAC.initialize();
        registerMemberToMap(subTabsHoldFactory_QAAC);
        */

        //------------------------------------------

        /* 11. Summary Pane */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.componentholder.Concretes.DEQAResult.SummaryPane.SummaryPaneHolderFactory",
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember summaryPaneHoldFactory =
                chMemberFactory.createCHolder("summary_pane_holder", "summary pane holder", authorYears[0]);
        summaryPaneHoldFactory.setCHolderMediator(this);
        summaryPaneHoldFactory.initialize();
        registerMemberToMap(summaryPaneHoldFactory);

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
