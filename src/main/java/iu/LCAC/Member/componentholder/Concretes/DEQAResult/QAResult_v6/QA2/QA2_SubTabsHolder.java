package iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QA2;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.One_ARSL_Style_Pane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.One_DEResult_Pane_Abs;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.SubTabsHolderItrfc;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class QA2_SubTabsHolder extends AbstCHolderMember implements SubTabsHolderItrfc {

    static String sectionName = "assessment_items_group_b";

    static String subSection_1_Name = "details_of_data_preprocessing";
    static String subSection_2_Name = "clarity_of_data_partitioning_methods";
    static String subSection_3_Name = "details_of_normative_modeling_approach";
    static String subSection_4_Name = "details_of_training_algorithm";
    static String subSection_5_Name = "model_performance_evaluation_metrics";
    static String subSection_6_Name = "implementation_of_internal_validation";
    static String subSection_7_Name = "external_data_validation";
    static String subSection_8_Name = "description_of_dataset_characteristics";
    static String subSection_9_Name = "performance_metrics_and_statistical_uncertainty";
    static String subSection_10_Name = "consideration_for_reproducibility";
    static String subSection_11_Name = "interpretation_specific_to_normative_modeling";

    static String subSection_1_TabName = "Data Prproc";
    static String subSection_2_TabName = "Data Partitioning";
    static String subSection_3_TabName = "Details of NM";
    static String subSection_4_TabName = "Details of Training";
    static String subSection_5_TabName = "Performance Evaluation";
    static String subSection_6_TabName = "Internal Validation";
    static String subSection_7_TabName = "External Validation";
    static String subSection_8_TabName = "Dataset Characteristics";
    static String subSection_9_TabName = "PM and SU";
    static String subSection_10_TabName = "Reproducibility";
    static String subSection_11_TabName = "NM Interpretation";

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

    public QA2_SubTabsHolder(String cholder_name, String short_name, String authorYear) {
        super(cholder_name, short_name);

        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_1);
        mngrOfSubTabBasePane_1.registerSubTansHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_2);
        mngrOfSubTabBasePane_2.registerSubTansHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_3);
        mngrOfSubTabBasePane_3.registerSubTansHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_4);
        mngrOfSubTabBasePane_4.registerSubTansHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_5);
        mngrOfSubTabBasePane_5.registerSubTansHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_6);
        mngrOfSubTabBasePane_6.registerSubTansHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_7);
        mngrOfSubTabBasePane_7.registerSubTansHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_8);
        mngrOfSubTabBasePane_8.registerSubTansHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_9);
        mngrOfSubTabBasePane_9.registerSubTansHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_10);
        mngrOfSubTabBasePane_10.registerSubTansHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_11);
        mngrOfSubTabBasePane_11.registerSubTansHolder(this);

        // ./json/ フォルダの確認
        Path jsonFolderPathString = Paths.get("./QA/json");
        jsonFolderPathString = Paths.get("./" + authorYear + "/").resolve(jsonFolderPathString);
        File jsonDir = jsonFolderPathString.toFile();

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
            mngrOfSubTabBasePane_1.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_1_Name));
            mngrOfSubTabBasePane_2.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_2_Name));
            mngrOfSubTabBasePane_3.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_3_Name));
            mngrOfSubTabBasePane_4.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_4_Name));
            mngrOfSubTabBasePane_5.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_5_Name));
            mngrOfSubTabBasePane_6.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_6_Name));
            mngrOfSubTabBasePane_7.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_7_Name));
            mngrOfSubTabBasePane_8.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_8_Name));
            mngrOfSubTabBasePane_9.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_9_Name));
            mngrOfSubTabBasePane_10.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_10_Name));
            mngrOfSubTabBasePane_11.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_11_Name));
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
                oneDEResultPane.loadJson();
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_2.getDePaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_3.getDePaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_4.getDePaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_5.getDePaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_6.getDePaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_7.getDePaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_8.getDePaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_9.getDePaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_10.getDePaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_11.getDePaneArray()) {
                oneDEResultPane.loadJson();
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
