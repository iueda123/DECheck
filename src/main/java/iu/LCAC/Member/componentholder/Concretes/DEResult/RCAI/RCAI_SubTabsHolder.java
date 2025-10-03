package iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.RCAI_OneDEResultPane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.ManagerOfSubTabBasePane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class RCAI_SubTabsHolder extends AbstCHolderMember {

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
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_1 = new ManagerOfSubTabBasePane(subSection_1_TabName, subSection_1_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_2 = new ManagerOfSubTabBasePane(subSection_2_TabName, subSection_2_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_3 = new ManagerOfSubTabBasePane(subSection_3_TabName, subSection_3_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_4 = new ManagerOfSubTabBasePane(subSection_4_TabName, subSection_4_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_5 = new ManagerOfSubTabBasePane(subSection_5_TabName, subSection_5_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_6 = new ManagerOfSubTabBasePane(subSection_6_TabName, subSection_6_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_7 = new ManagerOfSubTabBasePane(subSection_7_TabName, subSection_7_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_8 = new ManagerOfSubTabBasePane(subSection_8_TabName, subSection_8_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_9 = new ManagerOfSubTabBasePane(subSection_9_TabName, subSection_9_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_10 = new ManagerOfSubTabBasePane(subSection_10_TabName, subSection_10_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_11 = new ManagerOfSubTabBasePane(subSection_11_TabName, subSection_11_Name);

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

        // ./json下のすべてのJSONファイルを取得
        File jsonDir = new File("./json");
        File[] jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));

        if (jsonFiles != null) {
            for (File jsonFile : jsonFiles) {
                String jsonFileName = jsonFile.getName();
                mngrOfSubTabBasePane_1.addToTheDePaneArray(new RCAI_OneDEResultPane(jsonFileName, sectionName, subSection_1_Name));
                mngrOfSubTabBasePane_2.addToTheDePaneArray(new RCAI_OneDEResultPane(jsonFileName, sectionName, subSection_2_Name));
                mngrOfSubTabBasePane_3.addToTheDePaneArray(new RCAI_OneDEResultPane(jsonFileName, sectionName, subSection_3_Name));
                mngrOfSubTabBasePane_4.addToTheDePaneArray(new RCAI_OneDEResultPane(jsonFileName, sectionName, subSection_4_Name));
                mngrOfSubTabBasePane_5.addToTheDePaneArray(new RCAI_OneDEResultPane(jsonFileName, sectionName, subSection_5_Name));
                mngrOfSubTabBasePane_6.addToTheDePaneArray(new RCAI_OneDEResultPane(jsonFileName, sectionName, subSection_6_Name));
                mngrOfSubTabBasePane_7.addToTheDePaneArray(new RCAI_OneDEResultPane(jsonFileName, sectionName, subSection_7_Name));
                mngrOfSubTabBasePane_8.addToTheDePaneArray(new RCAI_OneDEResultPane(jsonFileName, sectionName, subSection_8_Name));
                mngrOfSubTabBasePane_9.addToTheDePaneArray(new RCAI_OneDEResultPane(jsonFileName, sectionName, subSection_9_Name));
                mngrOfSubTabBasePane_10.addToTheDePaneArray(new RCAI_OneDEResultPane(jsonFileName, sectionName, subSection_10_Name));
                mngrOfSubTabBasePane_11.addToTheDePaneArray(new RCAI_OneDEResultPane(jsonFileName, sectionName, subSection_11_Name));
            }
        }

        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : arrayList_of_ManagerOfSubTabBasePane) {
            baseTabPane.add(managerOfSubTabBasePane.getTabName(), managerOfSubTabBasePane.constructBasePaneOfSubTab());
        }

        panel.add(baseTabPane, BorderLayout.CENTER);
    }

    @Override
    public void postInitialize() {
        if (actionMediator != null) {

            /* 値を流し込む */

            for (RCAI_OneDEResultPane RCAI_OneDEResultPane : mngrOfSubTabBasePane_1.getDePaneArray()) {
                String jsonName = RCAI_OneDEResultPane.getJsonName();
                String sectionName = RCAI_OneDEResultPane.getSectionName();
                String subSectionName = RCAI_OneDEResultPane.getSubSectionName();

                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (RCAI_OneDEResultPane RCAI_OneDEResultPane : mngrOfSubTabBasePane_2.getDePaneArray()) {
                String jsonName = RCAI_OneDEResultPane.getJsonName();
                String sectionName = RCAI_OneDEResultPane.getSectionName();
                String subSectionName = RCAI_OneDEResultPane.getSubSectionName();

                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (RCAI_OneDEResultPane RCAI_OneDEResultPane : mngrOfSubTabBasePane_3.getDePaneArray()) {
                String jsonName = RCAI_OneDEResultPane.getJsonName();
                String sectionName = RCAI_OneDEResultPane.getSectionName();
                String subSectionName = RCAI_OneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (RCAI_OneDEResultPane RCAI_OneDEResultPane : mngrOfSubTabBasePane_4.getDePaneArray()) {
                String jsonName = RCAI_OneDEResultPane.getJsonName();
                String sectionName = RCAI_OneDEResultPane.getSectionName();
                String subSectionName = RCAI_OneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (RCAI_OneDEResultPane RCAI_OneDEResultPane : mngrOfSubTabBasePane_5.getDePaneArray()) {
                String jsonName = RCAI_OneDEResultPane.getJsonName();
                String sectionName = RCAI_OneDEResultPane.getSectionName();
                String subSectionName = RCAI_OneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (RCAI_OneDEResultPane RCAI_OneDEResultPane : mngrOfSubTabBasePane_6.getDePaneArray()) {
                String jsonName = RCAI_OneDEResultPane.getJsonName();
                String sectionName = RCAI_OneDEResultPane.getSectionName();
                String subSectionName = RCAI_OneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (RCAI_OneDEResultPane RCAI_OneDEResultPane : mngrOfSubTabBasePane_7.getDePaneArray()) {
                String jsonName = RCAI_OneDEResultPane.getJsonName();
                String sectionName = RCAI_OneDEResultPane.getSectionName();
                String subSectionName = RCAI_OneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (RCAI_OneDEResultPane RCAI_OneDEResultPane : mngrOfSubTabBasePane_8.getDePaneArray()) {
                String jsonName = RCAI_OneDEResultPane.getJsonName();
                String sectionName = RCAI_OneDEResultPane.getSectionName();
                String subSectionName = RCAI_OneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (RCAI_OneDEResultPane RCAI_OneDEResultPane : mngrOfSubTabBasePane_9.getDePaneArray()) {
                String jsonName = RCAI_OneDEResultPane.getJsonName();
                String sectionName = RCAI_OneDEResultPane.getSectionName();
                String subSectionName = RCAI_OneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (RCAI_OneDEResultPane RCAI_OneDEResultPane : mngrOfSubTabBasePane_10.getDePaneArray()) {
                String jsonName = RCAI_OneDEResultPane.getJsonName();
                String sectionName = RCAI_OneDEResultPane.getSectionName();
                String subSectionName = RCAI_OneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }

            for (RCAI_OneDEResultPane RCAI_OneDEResultPane : mngrOfSubTabBasePane_11.getDePaneArray()) {
                String jsonName = RCAI_OneDEResultPane.getJsonName();
                String sectionName = RCAI_OneDEResultPane.getSectionName();
                String subSectionName = RCAI_OneDEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_rcai_tabpanes " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_rcai_tabpanes").perform(customEvent);
            }


            /* パネル順序のロード */
            actionMediator
                    .getInstanceOfAMember("load_pane_order")
                    .perform(new ActionEvent(this, 0, "Load Panel Order."));

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
    public RCAI_OneDEResultPane getResultPane(String jsonName, String sectionName, String subSectionName) {
        //System.out.println("Start searching the DEResultPane with following: ");
        //System.out.println("  JSON Name: " + jsonName);
        //System.out.println("  Section Name: " + sectionName);
        //System.out.println("  Subsection Name: " + subSectionName);

        //System.out.println(mngrOfSubTabBasePane_1.getDePaneArray().size());

        for (RCAI_OneDEResultPane pane : mngrOfSubTabBasePane_1.getDePaneArray()) {
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

        for (RCAI_OneDEResultPane pane : mngrOfSubTabBasePane_2.getDePaneArray()) {
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

        for (RCAI_OneDEResultPane pane : mngrOfSubTabBasePane_3.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (RCAI_OneDEResultPane pane : mngrOfSubTabBasePane_4.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (RCAI_OneDEResultPane pane : mngrOfSubTabBasePane_5.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (RCAI_OneDEResultPane pane : mngrOfSubTabBasePane_6.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (RCAI_OneDEResultPane pane : mngrOfSubTabBasePane_7.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (RCAI_OneDEResultPane pane : mngrOfSubTabBasePane_8.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (RCAI_OneDEResultPane pane : mngrOfSubTabBasePane_9.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (RCAI_OneDEResultPane pane : mngrOfSubTabBasePane_10.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (RCAI_OneDEResultPane pane : mngrOfSubTabBasePane_11.getDePaneArray()) {
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
