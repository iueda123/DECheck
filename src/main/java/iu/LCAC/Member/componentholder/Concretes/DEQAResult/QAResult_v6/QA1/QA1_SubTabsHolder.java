package iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v6.QA1;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_ARSL_Style_Pane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.SubTabsHolderItrfc;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class QA1_SubTabsHolder extends AbstCHolderMember implements SubTabsHolderItrfc {

    static String sectionName = "assessment_items_group_a";

    static String subSection_1_Name = "clarity_of_research_objectives";
    static String subSection_2_Name = "clear_definition_of_target_population";
    static String subSection_3_Name = "clarity_of_inclusion_and_exclusion_criteria";
    static String subSection_4_Name = "validity_of_normative_modeling_outcome_measures";
    static String subSection_5_Name = "handling_of_confounding_variables";
    static String subSection_6_Name = "clarity_of_data_sources";
    static String subSection_7_Name = "description_of_image_acquisition_protocol";

    static String subSection_1_TabName = "Research Objective";
    static String subSection_2_TabName = "Target Population";
    static String subSection_3_TabName = "I&E Criteria";
    static String subSection_4_TabName = "Validity of NM Measure";
    static String subSection_5_TabName = "Handling Confounds";
    static String subSection_6_TabName = "Data Source";
    static String subSection_7_TabName = "Acquisition Protocol";


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

    ArrayList<ManagerOfSubTabBasePane> arrayList_of_ManagerOfSubTabBasePane = new ArrayList<>();
    private final String authorYear;

    public QA1_SubTabsHolder(String cholder_name, String short_name, String authorYear) {
        super(cholder_name, short_name);

        this.authorYear = authorYear;

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
            //System.out.println("jsonFileName: " + jsonFileName);
            mngrOfSubTabBasePane_1.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_1_Name));
            mngrOfSubTabBasePane_2.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_2_Name));
            mngrOfSubTabBasePane_3.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_3_Name));
            mngrOfSubTabBasePane_4.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_4_Name));
            mngrOfSubTabBasePane_5.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_5_Name));
            mngrOfSubTabBasePane_6.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_6_Name));
            mngrOfSubTabBasePane_7.addToTheDePaneArray(new One_ARSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_7_Name));
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
            for (One_DEQAResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_1.getDeqaPaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_2.getDeqaPaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_3.getDeqaPaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_4.getDeqaPaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_5.getDeqaPaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_6.getDeqaPaneArray()) {
                oneDEResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEResultPane : mngrOfSubTabBasePane_7.getDeqaPaneArray()) {
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
    public One_DEQAResult_Pane_Abs getResultPane(String jsonName, String sectionName, String subSectionName) {
        //System.out.println("Start searching the DEResultPane with following: ");
        //System.out.println("  JSON Name: " + jsonName);
        //System.out.println("  Section Name: " + sectionName);
        //System.out.println("  Subsection Name: " + subSectionName);

        //System.out.println(mngrOfSubTabBasePane_MODEL_ORIGIN.getDePaneArray().size());

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_1.getDeqaPaneArray()) {
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

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_2.getDeqaPaneArray()) {
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

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_3.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_4.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_5.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_6.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_7.getDeqaPaneArray()) {
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
    public One_DEQAResult_Pane_Abs getTheFirstJsonPanel() {
        return null;
    }

    @Override
    public String getAuthorYear() {
        return this.authorYear;
    }

    public String getSectionName() {
        return sectionName;
    }


}
