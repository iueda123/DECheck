package iu.LCAC.Member.componentholder.Concretes.DEResult.NM;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.Parts.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.Parts.One_DEResultPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class NM_SubTabsHolder extends AbstCHolderMember {

    static String sectionName = "normative_modeling";

    static String subSection_1_Name = "model_origin";
    static String subSection_2_Name = "model_origin_detail";
    static String subSection_3_Name = "modeling_method";
    static String subSection_4_Name = "software_tool";
    static String subSection_5_Name = "response_variable";
    static String subSection_6_Name = "predictor_variables";
    static String subSection_7_Name = "predictor_effects";
    static String subSection_8_Name = "nm_vldtn_handle_ns";
    static String subSection_9_Name = "normative_modeling";
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
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_1 = new ManagerOfSubTabBasePane(subSection_1_TabName, subSection_1_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_2 = new ManagerOfSubTabBasePane(subSection_2_TabName, subSection_2_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_3 = new ManagerOfSubTabBasePane(subSection_3_TabName,  subSection_3_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_4 = new ManagerOfSubTabBasePane(subSection_4_TabName, subSection_4_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_5 = new ManagerOfSubTabBasePane(subSection_5_TabName, subSection_5_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_6 = new ManagerOfSubTabBasePane(subSection_6_TabName, subSection_6_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_7 = new ManagerOfSubTabBasePane(subSection_7_TabName, subSection_7_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_8 = new ManagerOfSubTabBasePane(subSection_8_TabName, subSection_8_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_9 = new ManagerOfSubTabBasePane(subSection_9_TabName, subSection_9_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_10 = new ManagerOfSubTabBasePane(subSection_10_TabName, subSection_10_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_11 = new ManagerOfSubTabBasePane(subSection_11_TabName, subSection_11_Name);

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

        // ./json下のすべてのJSONファイルを取得
        File jsonDir = new File("./json");
        File[] jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));

        if (jsonFiles != null) {
            for (File jsonFile : jsonFiles) {
                String jsonFileName = jsonFile.getName();
                mngrOfSubTabBasePane_1.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName,subSection_1_Name));
                mngrOfSubTabBasePane_2.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_2_Name));
                mngrOfSubTabBasePane_3.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_3_Name));
                mngrOfSubTabBasePane_4.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_4_Name));
                mngrOfSubTabBasePane_5.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_5_Name));
                mngrOfSubTabBasePane_6.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_6_Name));
                mngrOfSubTabBasePane_7.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_7_Name));
                mngrOfSubTabBasePane_8.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_8_Name));
                mngrOfSubTabBasePane_9.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_9_Name));
                mngrOfSubTabBasePane_10.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_10_Name));
                mngrOfSubTabBasePane_11.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_11_Name));
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

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_1.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();

                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_2.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();

                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_3.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_4.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_5.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_6.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_7.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_8.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_9.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_10.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_11.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
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
    public One_DEResultPane getResultPane(String jsonName, String sectionName, String subSectionName) {
        //System.out.println("Start searching the DEResultPane with following: ");
        //System.out.println("  JSON Name: " + jsonName);
        //System.out.println("  Section Name: " + sectionName);
        //System.out.println("  Subsection Name: " + subSectionName);

        //System.out.println(mngrOfSubTabBasePane_MODEL_ORIGIN.getDePaneArray().size());

        for (One_DEResultPane pane : mngrOfSubTabBasePane_1.getDePaneArray()) {
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

        for (One_DEResultPane pane : mngrOfSubTabBasePane_2.getDePaneArray()) {
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

        for (One_DEResultPane pane : mngrOfSubTabBasePane_3.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_4.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_5.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_6.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_7.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_8.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_9.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_10.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_11.getDePaneArray()) {
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
