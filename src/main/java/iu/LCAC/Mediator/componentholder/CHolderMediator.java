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
                    "iu.LCAC.Member.componentholder.Concretes.DEResult.DEResultPaneHolderFactory",
                    AbstCHolderMemberFactory.class);
    AbstCHolderMember aResultPaneHolder =
            chMemberFactory.createCHolder("tab_of_reference_cohort_and_imaging_holder", "tab_of_reference_cohort_and_imaging_holder");
    aResultPaneHolder.setCHolderMediator(this);
    aResultPaneHolder.initialize();
    registerMemberToMap(aResultPaneHolder);




  }

  private void registerMemberToMap(AbstCHolderMember member) {
    memberMap.put(member.getMemberName(), member);
  }

  @Override
  public void requestFromMember() {}

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
