package iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.One_ARSL_Style_Pane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.One_DEResult_Pane_Abs;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.SubTabsHolderItrfc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class RCAI_SubTabsHolder extends AbstCHolderMember implements SubTabsHolderItrfc {

    static final String jsonFolderPathString = "./json/DE";

    static String sectionName = "reference_cohort_and_imaging";

    static String subSection_1_Name = "dataset_name";
    static String subSection_2_Name = "hc_n";
    static String subSection_3_Name = "hc_age";
    static String subSection_4_Name = "hc_sex";
    static String subSection_5_Name = "imaging_modality";
    static String subSection_6_Name = "analysis_level";
    static String subSection_7_Name = "preprocessing_pipeline";
    static String subSection_8_Name = "quality_checking";
    static String subSection_9_Name = "quality_checking_detail";
    static String subSection_10_Name = "site_effect_handling";
    static String subSection_11_Name = "site_effect_handling_detail";

    static String subSection_1_TabName = "Dataset Name";
    static String subSection_2_TabName = "HC N";
    static String subSection_3_TabName = "HC Age";
    static String subSection_4_TabName = "HC Sex";
    static String subSection_5_TabName = "Imaging Modality";
    static String subSection_6_TabName = "Analysis Level";
    static String subSection_7_TabName = "Preprocessing Pipeline";
    static String subSection_8_TabName = "Quality Checking";
    static String subSection_9_TabName = "Quality Checking Detail";
    static String subSection_10_TabName = "Site Effect Handling";
    static String subSection_11_TabName = "Site Effect Handling Detail";


    JPanel panel = new JPanel(new BorderLayout());
    JTabbedPane baseTabPane = new JTabbedPane();

    // Reference Cohort and Imaging
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_1 = new ManagerOfSubTabBasePane(subSection_1_TabName, sectionName, subSection_1_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_2 = new ManagerOfSubTabBasePane(subSection_2_TabName, sectionName, subSection_2_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_3 = new ManagerOfSubTabBasePane(subSection_3_TabName, sectionName, subSection_3_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_4 = new ManagerOfSubTabBasePane(subSection_4_TabName, sectionName, subSection_4_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_5 = new ManagerOfSubTabBasePane(subSection_5_TabName, sectionName, subSection_5_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_6 = new ManagerOfSubTabBasePane(subSection_6_TabName, sectionName, subSection_6_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_7 = new ManagerOfSubTabBasePane(subSection_7_TabName, sectionName, subSection_7_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_8 = new ManagerOfSubTabBasePane(subSection_8_TabName, sectionName, subSection_8_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_9 = new ManagerOfSubTabBasePane(subSection_9_TabName, sectionName, subSection_9_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_10 = new ManagerOfSubTabBasePane(subSection_10_TabName, sectionName, subSection_10_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_11 = new ManagerOfSubTabBasePane(subSection_11_TabName, sectionName, subSection_11_Name, baseTabPane);

    ArrayList<ManagerOfSubTabBasePane> arrayList_of_ManagerOfSubTabBasePane = new ArrayList<>();

    public RCAI_SubTabsHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);

        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_1);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_2);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_3);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_4);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_5);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_6);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_7);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_8);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_9);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_10);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_11);

        // ./json/ フォルダの確認
        File jsonDir = new File(jsonFolderPathString);
        // jsonディレクトリが存在しない、またはディレクトリではない場合
        if (!jsonDir.exists() || !jsonDir.isDirectory()) {
            JOptionPane.showMessageDialog(
                    null,
                    "json/フォルダが見つかりません。\n" + jsonFolderPathString + "/フォルダを作成し、JSONファイルを格納してください。",
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }
        // ./json下のすべてのJSONファイルを取得
        File[] jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));
        // jsonFiles に格納されているもののうち、ファイル名の先頭が "_" で始まるものを先頭に持ってくる
        if (jsonFiles != null) {
            Arrays.sort(jsonFiles, (f1, f2) -> {
                boolean f1StartsWithUnderscore = f1.getName().startsWith("_");
                boolean f2StartsWithUnderscore = f2.getName().startsWith("_");
                return Boolean.compare(f2StartsWithUnderscore, f1StartsWithUnderscore);
            });
        }

        if (jsonFiles != null) {
            for (File jsonFile : jsonFiles) {
                String jsonFileName = jsonFile.getName();
                mngrOfSubTabBasePane_1.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString, jsonFileName, sectionName, subSection_1_Name));
                mngrOfSubTabBasePane_2.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString, jsonFileName, sectionName, subSection_2_Name));
                mngrOfSubTabBasePane_3.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString, jsonFileName, sectionName, subSection_3_Name));
                mngrOfSubTabBasePane_4.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString, jsonFileName, sectionName, subSection_4_Name));
                mngrOfSubTabBasePane_5.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString, jsonFileName, sectionName, subSection_5_Name));
                mngrOfSubTabBasePane_6.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString, jsonFileName, sectionName, subSection_6_Name));
                mngrOfSubTabBasePane_7.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString, jsonFileName, sectionName, subSection_7_Name));
                mngrOfSubTabBasePane_8.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString, jsonFileName, sectionName, subSection_8_Name));
                mngrOfSubTabBasePane_9.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString, jsonFileName, sectionName, subSection_9_Name));
                mngrOfSubTabBasePane_10.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString, jsonFileName, sectionName, subSection_10_Name));
                mngrOfSubTabBasePane_11.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString, jsonFileName, sectionName, subSection_11_Name));
            }
        }

        for (ManagerOfSubTabBasePane managerOfSubTabBasePaneRCAI : arrayList_of_ManagerOfSubTabBasePane) {
            baseTabPane.add(managerOfSubTabBasePaneRCAI.getTabName(), managerOfSubTabBasePaneRCAI.constructBasePaneOfSubTab());
        }

        panel.add(baseTabPane, BorderLayout.CENTER);
    }

    @Override
    public void postInitialize() {
        //System.out.println("postInitialize() @ RCAI_SubTabsHolder.java");
        if (actionMediator != null) {


            /* 値を流し込む */

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_1.getDePaneArray()) {
                String jsonName = oneDEResultPane.getJsonName();
                String sectionName = oneDEResultPane.getSectionName();
                String subSectionName = oneDEResultPane.getSubSectionName();

                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_2.getDePaneArray()) {
                String jsonName = oneDEResultPane.getJsonName();
                String sectionName = oneDEResultPane.getSectionName();
                String subSectionName = oneDEResultPane.getSubSectionName();
                AbstActionMember abstActionMember = actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes");
                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName
                );
                abstActionMember.perform(customEvent);
            }

            for (One_DEResult_Pane_Abs NM_OneACNRSLPane : mngrOfSubTabBasePane_3.getDePaneArray()) {
                String jsonName = NM_OneACNRSLPane.getJsonName();
                String sectionName = NM_OneACNRSLPane.getSectionName();
                String subSectionName = NM_OneACNRSLPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs NM_OneACNRSLPane : mngrOfSubTabBasePane_4.getDePaneArray()) {
                String jsonName = NM_OneACNRSLPane.getJsonName();
                String sectionName = NM_OneACNRSLPane.getSectionName();
                String subSectionName = NM_OneACNRSLPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs NM_OneACNRSLPane : mngrOfSubTabBasePane_5.getDePaneArray()) {
                String jsonName = NM_OneACNRSLPane.getJsonName();
                String sectionName = NM_OneACNRSLPane.getSectionName();
                String subSectionName = NM_OneACNRSLPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs NM_OneACNRSLPane : mngrOfSubTabBasePane_6.getDePaneArray()) {
                String jsonName = NM_OneACNRSLPane.getJsonName();
                String sectionName = NM_OneACNRSLPane.getSectionName();
                String subSectionName = NM_OneACNRSLPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs NM_OneACNRSLPane : mngrOfSubTabBasePane_7.getDePaneArray()) {
                String jsonName = NM_OneACNRSLPane.getJsonName();
                String sectionName = NM_OneACNRSLPane.getSectionName();
                String subSectionName = NM_OneACNRSLPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs NM_OneACNRSLPane : mngrOfSubTabBasePane_8.getDePaneArray()) {
                String jsonName = NM_OneACNRSLPane.getJsonName();
                String sectionName = NM_OneACNRSLPane.getSectionName();
                String subSectionName = NM_OneACNRSLPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs NM_OneACNRSLPane : mngrOfSubTabBasePane_9.getDePaneArray()) {
                String jsonName = NM_OneACNRSLPane.getJsonName();
                String sectionName = NM_OneACNRSLPane.getSectionName();
                String subSectionName = NM_OneACNRSLPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs NM_OneACNRSLPane : mngrOfSubTabBasePane_10.getDePaneArray()) {
                String jsonName = NM_OneACNRSLPane.getJsonName();
                String sectionName = NM_OneACNRSLPane.getSectionName();
                String subSectionName = NM_OneACNRSLPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs NM_OneACNRSLPane : mngrOfSubTabBasePane_11.getDePaneArray()) {
                String jsonName = NM_OneACNRSLPane.getJsonName();
                String sectionName = NM_OneACNRSLPane.getSectionName();
                String subSectionName = NM_OneACNRSLPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

        } else {
            System.err.println("actionMediator is null in postInitialize() @ " + this.getClass());
        }
    }

    @Override
    public JComponent getBaseComponent() {
        return this.panel;
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


    /**
     * すべての paneArray の要素の中から、
     * jsonName, sectionName, subSectionName が一致するものを返す。
     */
    public One_DEResult_Pane_Abs getResultPane(String jsonName, String sectionName, String subSectionName) {
        //System.out.println("Start searching the DEResultPane with following: ");
        //System.out.println("  JSON Name: " + jsonName);
        //System.out.println("  Section Name: " + sectionName);
        //System.out.println("  Subsection Name: " + subSectionName);

        //System.out.println(mngrOfSubTabBasePane_1.getDePaneArray().size());

        for (One_DEResult_Pane_Abs pane : mngrOfSubTabBasePane_1.getDePaneArray()) {
            //System.out.println("Candidate Info: ");
            //System.out.println("  JSON Name: " + pane.getJsonName());
            //System.out.println("  Section Name: " + pane.getSectionName());
            //System.out.println("  Subsection Name: " + pane.getSubSectionName());
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResult_Pane_Abs pane : mngrOfSubTabBasePane_2.getDePaneArray()) {
            //System.out.println("Candidate Info: ");
            //System.out.println("  JSON Name: " + pane.getJsonName());
            //System.out.println("  Section Name: " + pane.getSectionName());
            //System.out.println("  Subsection Name: " + pane.getSubSectionName());
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResult_Pane_Abs pane : mngrOfSubTabBasePane_3.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResult_Pane_Abs pane : mngrOfSubTabBasePane_4.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResult_Pane_Abs pane : mngrOfSubTabBasePane_5.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResult_Pane_Abs pane : mngrOfSubTabBasePane_6.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResult_Pane_Abs pane : mngrOfSubTabBasePane_7.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResult_Pane_Abs pane : mngrOfSubTabBasePane_8.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResult_Pane_Abs pane : mngrOfSubTabBasePane_9.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResult_Pane_Abs pane : mngrOfSubTabBasePane_10.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResult_Pane_Abs pane : mngrOfSubTabBasePane_11.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        return null;

    }

    public ArrayList<ManagerOfSubTabBasePane> getArrayList_of_ManagerOfSubTabBasePane() {
        return arrayList_of_ManagerOfSubTabBasePane;
    }

    @Override
    public One_DEResult_Pane_Abs getTheFirstJsonPanel() {
        return null;
    }

    public String getSectionName() {
        return sectionName;
    }

}
