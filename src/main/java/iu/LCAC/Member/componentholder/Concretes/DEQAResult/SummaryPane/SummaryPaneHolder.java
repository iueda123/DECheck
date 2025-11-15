package iu.LCAC.Member.componentholder.Concretes.DEQAResult.SummaryPane;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_ACRSL_Style_Pane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_ARNSL_Style_Pane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_A_Style_Pane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.SubTabsHolderItrfc;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.SummaryPane.units.SummaryBoxUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.TreeMap;

public class SummaryPaneHolder extends AbstCHolderMember {

    String[] memberNames = {"sub_tabs_holder_SI", "sub_tabs_holder_SC", "sub_tabs_holder_RCAI", "sub_tabs_holder_NM",
            "sub_tabs_holder_CAAA", "sub_tabs_holder_GN",
            //"sub_tabs_holder_QASI",
            //"sub_tabs_holder_QA1_v6",
            //"sub_tabs_holder_QA2_v6",
            //"sub_tabs_holder_QAAC",
            "sub_tabs_holder_QACM",
            "sub_tabs_holder_QANM",
            "sub_tabs_holder_QACR"
    };

    JPanel basePane = new JPanel();
    Box baseOfNorth = Box.createHorizontalBox();
    Box baseOfCenter = Box.createVerticalBox();

    Box box_NM = Box.createVerticalBox();
    private TreeMap<String, SummaryBoxUnit> summaryBoxUnitTreeMap = new TreeMap<>();

    JButton checkProgressButton = new JButton("check progress");

    JButton runScriptButton = new JButton("Run A Script");

    SubTabsHolderItrfc subTabsHolder;


    public SummaryPaneHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);


        baseOfNorth.add(checkProgressButton);
        checkProgressButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkProgress();
            }
        });

        //baseOfNorth.add(runScriptButton);
        runScriptButton.addActionListener(
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (actionMediator != null) {

                            // 引数なし
                            //actionMediator.getInstanceOfAMember("run_a_bash_script").perform(actionEvent);

                            // 何かしらの文字列を引数として渡すには以下のようにしてActionEventを作成
                            AbstActionMember abstActionMember = actionMediator.getInstanceOfAMember("run_a_bash_script");
                            ActionEvent customEvent_with_ActionNameAndArgs = new ActionEvent(
                                    this,
                                    ActionEvent.ACTION_PERFORMED,
                                    "DummyActionName RunBashPanelHolderから渡した引数1 " + "RunBashPanelHolderから渡した引数2 "

                                    // AbstrActionMember#getActionCommandAndArgs()の自分が決めた仕様で、
                                    // ActionEventオブジェクトに格納された文字列の１つ目の要素はアクション名、
                                    // ２つ目以降の要素は引数扱い。
                                    // なので ここでは１つ目を DummyActionName としている。

                            );
                            abstActionMember.perform(customEvent_with_ActionNameAndArgs);

                        } else {
                            System.err.println("Action Starter is null! @ " + this.getClass().toString());
                        }
                    }
                });

        baseOfCenter.add(box_NM);

        // Finalization
        basePane.setLayout(new BorderLayout());
        basePane.add(baseOfNorth, BorderLayout.NORTH);
        basePane.add(baseOfCenter, BorderLayout.CENTER);

    }

    private void checkProgress() {
        for (String memberName : memberNames) {

            if (cholderMediator != null) {
                subTabsHolder = (SubTabsHolderItrfc) cholderMediator.getInstanceOfAMember(memberName);

                ArrayList<ManagerOfSubTabBasePane> arrayListOfManagerOfSubTabBasePane_NM = subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();
                for (ManagerOfSubTabBasePane managerOfSubTabBasePane : arrayListOfManagerOfSubTabBasePane_NM) {
                    String subSectionName = managerOfSubTabBasePane.getSubSectionName();

                    ArrayList<One_DEQAResult_Pane_Abs> arrayOf_one_deqaResult_pane = managerOfSubTabBasePane.getDeqaPaneArray();
                    for (One_DEQAResult_Pane_Abs one_deqaResult_pane : arrayOf_one_deqaResult_pane) {
                        if (one_deqaResult_pane.getJsonName().toLowerCase().contains("human")) {

                            String value ="";
                            if( one_deqaResult_pane instanceof One_A_Style_Pane ) {
                                value = ((One_A_Style_Pane) one_deqaResult_pane).gettArea_Answer().getText();
                            } else if ( one_deqaResult_pane instanceof One_ARNSL_Style_Pane) {
                                value = ((One_ARNSL_Style_Pane) one_deqaResult_pane).gettArea_Answer().getText();
                            } else if ( one_deqaResult_pane instanceof One_ACRSL_Style_Pane) {
                                value = ((One_ACRSL_Style_Pane) one_deqaResult_pane).gettArea_Answer().getText();
                            }

                            SummaryBoxUnit summaryBoxUnit=null;
                            if (value.equals("")) {
                                summaryBoxUnitTreeMap.get(memberName).setStatus(subSectionName, "□", "");
                            } else {
                                summaryBoxUnitTreeMap.get(memberName).setStatus(subSectionName, "■", value);
                            }
                        }
                    }
                }
            }
        }

    }


    @Override
    public void postInitialize() {

        for (String memberName : memberNames) {

            if (cholderMediator != null) {
               ArrayList<ManagerOfSubTabBasePane> arrayListOfManagerOfSubTabBasePane = ((SubTabsHolderItrfc)cholderMediator.getInstanceOfAMember(memberName)).getArrayList_of_ManagerOfSubTabBasePane();

                SummaryBoxUnit summaryBox = new SummaryBoxUnit(arrayListOfManagerOfSubTabBasePane);
                summaryBoxUnitTreeMap.put(memberName, summaryBox);
                baseOfCenter.add(summaryBox);
            }

        }

        // checkProgress() is now called from Initialize_All_TabPanes_Action after all data is loaded
        // checkProgress();

    }

    @Override
    public JComponent getBaseComponent() {
        return this.basePane;
    }

    public void setTextToTheButton(String text) {
        this.runScriptButton.setText(text);
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
