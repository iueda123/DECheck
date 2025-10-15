package iu.LCAC.Member.componentholder.Concretes.DEResult.SI;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class SI_SubTabsHolder extends AbstCHolderMember implements SubTabsHolderItrfc {

    static final String jsonFolderPathStr = "./json/DE";

    static String sectionName = "study_identification";

    static String subSection_1_Name = "study_id";
    static String subSection_2_Name = "reference_file_names";
    static String subSection_3_Name = "author_journal_year";
    static String subSection_4_Name = "title";
    static String subSection_5_Name = "doi";

    static String subSection_1_TabName = "Study ID";
    static String subSection_2_TabName = "Reference File Names";
    static String subSection_3_TabName = "Author Journal Year";
    static String subSection_4_TabName = "TItle";
    static String subSection_5_TabName = "DOI";


    JPanel panel = new JPanel(new BorderLayout());
    JTabbedPane baseTabPane = new JTabbedPane();

    // Reference Cohort and Imaging
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_1 = new ManagerOfSubTabBasePane(subSection_1_TabName, sectionName,subSection_1_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_2 = new ManagerOfSubTabBasePane(subSection_2_TabName, sectionName,subSection_2_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_3 = new ManagerOfSubTabBasePane(subSection_3_TabName, sectionName,subSection_3_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_4 = new ManagerOfSubTabBasePane(subSection_4_TabName, sectionName,subSection_4_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_5 = new ManagerOfSubTabBasePane(subSection_5_TabName, sectionName,subSection_5_Name, baseTabPane);

    ArrayList<ManagerOfSubTabBasePane> arrayList_of_ManagerOfSubTabBasePane = new ArrayList<>();

    public SI_SubTabsHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);

        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_1);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_2);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_3);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_4);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_5);

        panel.add(baseTabPane, BorderLayout.CENTER);
    }

    public One_DEResult_Pane_Abs getTheFirstJsonPanel(){
        //panel の baseTabPane の 0 番目 の 中で一番上に配置されている OneDEResult_Pane_Abs クラスオブジェクトを取得する
        if (baseTabPane.getTabCount() == 0) {
            return null;
        }

        // baseTabPane の 0 番目のタブのコンポーネントを取得
        Component firstTabComponent = baseTabPane.getComponentAt(0);
        if (firstTabComponent == null) {
            return null;
        }

        // そのタブに対応する ManagerOfSubTabBasePane を検索
        ManagerOfSubTabBasePane targetManager = null;
        for (ManagerOfSubTabBasePane manager : arrayList_of_ManagerOfSubTabBasePane) {
            JPanel basePaneForDEResultPanes = manager.getBasePaneForDEResultPanes();
            // basePaneForDEResultPanes が firstTabComponent の子孫かどうかを確認
            Container parent = basePaneForDEResultPanes.getParent();
            while (parent != null) {
                if (parent == firstTabComponent) {
                    targetManager = manager;
                    break;
                }
                parent = parent.getParent();
            }
            if (targetManager != null) {
                break;
            }
        }

        if (targetManager == null) {
            return null;
        }

        // basePaneForDEResultPanes から実際の表示順序で一番上の One_DEResult_Pane_Abs を取得
        JPanel basePaneForDEResultPanes = targetManager.getBasePaneForDEResultPanes();
        Component[] components = basePaneForDEResultPanes.getComponents();

        for (Component component : components) {
            if (component instanceof One_DEResult_Pane_Abs) {
                return (One_DEResult_Pane_Abs) component;
            }
        }

        return null;
    }

    @Override
    public void postInitialize() {
        //System.out.println("postInitialize() @ SI_SubTabsHolder.java");
        if (actionMediator != null) {

            // ./json/ フォルダの確認
            File jsonDir = new File(jsonFolderPathStr);
            // jsonディレクトリが存在しない、またはディレクトリではない場合
            if (!jsonDir.exists() || !jsonDir.isDirectory()) {
                JOptionPane.showMessageDialog(
                        null,
                        "json/フォルダが見つかりません。\n" + jsonFolderPathStr + "/フォルダを作成し、JSONファイルを格納してください。",
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

                    if (this.actionMediator != null) {
                        System.err.println("The actionMediator is not null.");
                    }else {
                        System.err.println("The actionMediator is null. @ SI_SubTabsHolder.java");
                    }

                    mngrOfSubTabBasePane_1.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathStr, jsonFileName, sectionName, subSection_1_Name, this.cholderMediator, this.actionMediator));
                    mngrOfSubTabBasePane_2.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathStr, jsonFileName, sectionName, subSection_2_Name, this.cholderMediator, this.actionMediator));
                    mngrOfSubTabBasePane_3.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathStr, jsonFileName, sectionName, subSection_3_Name, this.cholderMediator, this.actionMediator));
                    mngrOfSubTabBasePane_4.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathStr,  jsonFileName, sectionName, subSection_4_Name, this.cholderMediator, this.actionMediator));
                    mngrOfSubTabBasePane_5.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathStr, jsonFileName, sectionName, subSection_5_Name, this.cholderMediator, this.actionMediator));
                }
            }

            for (ManagerOfSubTabBasePane managerOfSubTabBasePaneRCAI : arrayList_of_ManagerOfSubTabBasePane) {
                baseTabPane.add(managerOfSubTabBasePaneRCAI.getTabName(), managerOfSubTabBasePaneRCAI.constructBasePaneOfSubTab());
            }

            /* 値を流し込む */
            for (One_DEResult_Pane_Abs oneDeResultPaneAbs : mngrOfSubTabBasePane_1.getDePaneArray()) {
                String jsonName = oneDeResultPaneAbs.getJsonName();
                String sectionName = oneDeResultPaneAbs.getSectionName();
                String subSectionName = oneDeResultPaneAbs.getSubSectionName();

                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_si_tabpanes " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_si_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDeResultPaneAbs : mngrOfSubTabBasePane_2.getDePaneArray()) {
                String jsonName = oneDeResultPaneAbs.getJsonName();
                String sectionName = oneDeResultPaneAbs.getSectionName();
                String subSectionName = oneDeResultPaneAbs.getSubSectionName();

                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_si_tabpanes " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_si_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDeResultPaneAbs : mngrOfSubTabBasePane_3.getDePaneArray()) {
                String jsonName = oneDeResultPaneAbs.getJsonName();
                String sectionName = oneDeResultPaneAbs.getSectionName();
                String subSectionName = oneDeResultPaneAbs.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_si_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_si_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDeResultPaneAbs : mngrOfSubTabBasePane_4.getDePaneArray()) {
                String jsonName = oneDeResultPaneAbs.getJsonName();
                String sectionName = oneDeResultPaneAbs.getSectionName();
                String subSectionName = oneDeResultPaneAbs.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_si_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_si_tabpanes").perform(customEvent);
            }

            for (One_DEResult_Pane_Abs oneDeResultPaneAbs : mngrOfSubTabBasePane_5.getDePaneArray()) {
                String jsonName = oneDeResultPaneAbs.getJsonName();
                String sectionName = oneDeResultPaneAbs.getSectionName();
                String subSectionName = oneDeResultPaneAbs.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_si_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_si_tabpanes").perform(customEvent);
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

        return null;

    }

    public ArrayList<ManagerOfSubTabBasePane> getArrayList_of_ManagerOfSubTabBasePane() {
        return arrayList_of_ManagerOfSubTabBasePane;
    }

    public String getSectionName() {
        return sectionName;
    }

}
