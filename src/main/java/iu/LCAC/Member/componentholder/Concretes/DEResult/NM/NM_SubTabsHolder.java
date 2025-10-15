package iu.LCAC.Member.componentholder.Concretes.DEResult.NM;

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

public class NM_SubTabsHolder extends AbstCHolderMember implements SubTabsHolderItrfc {

    static final String jsonFolderPathString = "./json/DE";

    static String sectionName = "normative_modeling";

    static String subSection_1_Name = "model_origin";
    static String subSection_2_Name = "model_origin_detail";
    static String subSection_3_Name = "modeling_method";
    static String subSection_4_Name = "software_tool";
    static String subSection_5_Name = "response_variable";
    static String subSection_6_Name = "predictor_variables";
    static String subSection_7_Name = "predictor_effects";
    static String subSection_8_Name = "nm_vldtn_handle_ns";
    static String subSection_9_Name = "nm_vldtn_same_domain_nonindep";
    static String subSection_10_Name = "nm_vldtn_same_domain_indep";
    static String subSection_11_Name = "nm_vldtn_diff_domain";

    static String subSection_1_TabName = "Model Origin";
    static String subSection_2_TabName = "Model Origin Detail";
    static String subSection_3_TabName = "Modeling Method";
    static String subSection_4_TabName = "Software and Tool";
    static String subSection_5_TabName = "Response Variable";
    static String subSection_6_TabName = "Predictor Variables";
    static String subSection_7_TabName = "Predictor Effects";
    static String subSection_8_TabName = "Validation - Handing Nuisance Structure";
    static String subSection_9_TabName = "Validation - Same Domain Non-independent Dataset";
    static String subSection_10_TabName = "Validation - Same Domain Independent Dataset";
    static String subSection_11_TabName = "Validation - Different Domain";


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

    public NM_SubTabsHolder(String cholder_name, String short_name) {
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

        for (File jsonFile : jsonFiles) {
            String jsonFileName = jsonFile.getName();
            System.out.println("jsonFileName: " + jsonFileName);
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

        //個々のサブタブの中身の準備と配備
        for (ManagerOfSubTabBasePane managerOfSubTabBasePaneNM : arrayList_of_ManagerOfSubTabBasePane) {
            baseTabPane.add(managerOfSubTabBasePaneNM.getTabName(), managerOfSubTabBasePaneNM.constructBasePaneOfSubTab());
        }

        panel.add(baseTabPane, BorderLayout.CENTER);
    }

    @Override
    public void postInitialize() {
        //System.out.println("postInitialize() @ NM_SubTabsHolder.java");
        if (actionMediator != null) {

            //ToDo: この部分で新規JSONの再探索とパネルの再構築も行いたい。
            // 問題は、パネルサイズ計算等もやり直すことになりレイアウトが崩れぬようにする手間がかかるということ
            // JSONの探索、パネルの構築、値の流し込み部分の切り分け、
            // もう少しスマートな構成にしないといけない。

            /* 値を流し込む */
            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_1.getDePaneArray()) {
                String jsonName = oneDEResultPane.getJsonName();
                String sectionName = oneDEResultPane.getSectionName();
                String subSectionName = oneDEResultPane.getSubSectionName();

                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_nm_tabpanes " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_nm_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_2.getDePaneArray()) {
                String jsonName = oneDEResultPane.getJsonName();
                String sectionName = oneDEResultPane.getSectionName();
                String subSectionName = oneDEResultPane.getSubSectionName();
                AbstActionMember abstActionMember = actionMediator.getInstanceOfAMember("initialize_nm_tabpanes");
                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_nm_tabpanes " + jsonName + " " + sectionName + " " + subSectionName
                );
                abstActionMember.perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_3.getDePaneArray()) {
                String jsonName = oneDEResultPane.getJsonName();
                String sectionName = oneDEResultPane.getSectionName();
                String subSectionName = oneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_nm_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_nm_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_4.getDePaneArray()) {
                String jsonName = oneDEResultPane.getJsonName();
                String sectionName = oneDEResultPane.getSectionName();
                String subSectionName = oneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_nm_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_nm_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_5.getDePaneArray()) {
                String jsonName = oneDEResultPane.getJsonName();
                String sectionName = oneDEResultPane.getSectionName();
                String subSectionName = oneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_nm_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_nm_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_6.getDePaneArray()) {
                String jsonName = oneDEResultPane.getJsonName();
                String sectionName = oneDEResultPane.getSectionName();
                String subSectionName = oneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_nm_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_nm_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_7.getDePaneArray()) {
                String jsonName = oneDEResultPane.getJsonName();
                String sectionName = oneDEResultPane.getSectionName();
                String subSectionName = oneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_nm_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_nm_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_8.getDePaneArray()) {
                String jsonName = oneDEResultPane.getJsonName();
                String sectionName = oneDEResultPane.getSectionName();
                String subSectionName = oneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_nm_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_nm_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_9.getDePaneArray()) {
                String jsonName = oneDEResultPane.getJsonName();
                String sectionName = oneDEResultPane.getSectionName();
                String subSectionName = oneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_nm_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_nm_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_10.getDePaneArray()) {
                String jsonName = oneDEResultPane.getJsonName();
                String sectionName = oneDEResultPane.getSectionName();
                String subSectionName = oneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_nm_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_nm_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_11.getDePaneArray()) {
                String jsonName = oneDEResultPane.getJsonName();
                String sectionName = oneDEResultPane.getSectionName();
                String subSectionName = oneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_nm_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_nm_tabpanes").perform(customEvent);
            }

            //baseTabPane.revalidate();
            //baseTabPane.repaint();

            //for (ManagerOfSubTabBasePane managerOfSubTabBasePane : arrayList_of_ManagerOfSubTabBasePane) {
            //    managerOfSubTabBasePane.revalidateChildren();
            //}

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

        //System.out.println(mngrOfSubTabBasePane_MODEL_ORIGIN.getDePaneArray().size());

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
