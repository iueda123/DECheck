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

    JPanel panel = new JPanel(new BorderLayout());
    JTabbedPane baseTabPane = new JTabbedPane();

    // Reference Cohort and Imaging
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_MODEL_ORIGIN = new ManagerOfSubTabBasePane("Model Origin", subSection_1_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_MODEL_ORIGIN_DETAIL = new ManagerOfSubTabBasePane("Model Origin Detail", subSection_2_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_MODELING_METHOD = new ManagerOfSubTabBasePane("Modeling Method", subSection_3_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_SOFTWARE_TOOL = new ManagerOfSubTabBasePane("Software and Tool", subSection_4_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_RESPONSE_VARIABLE = new ManagerOfSubTabBasePane("Response Variable", subSection_5_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_PREDICTOR_VARIABLES = new ManagerOfSubTabBasePane("Predictor Variables", subSection_6_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_PREDICTOR_EFFECTS = new ManagerOfSubTabBasePane("Predictor Effects", subSection_7_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_NM_VLDTN_HANDLE_NS = new ManagerOfSubTabBasePane("Validation - Handing Nuisance Structure", subSection_8_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_NM_VLDTN_SAME_DOMAIN_NONINDEP = new ManagerOfSubTabBasePane("Validation - Same Domain Non-independent Dataset", subSection_9_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_NM_VLDTN_SAME_DOMAIN_INDEP = new ManagerOfSubTabBasePane("Validation - Same Domain Independent Dataset", subSection_10_Name);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_NM_VLDTN_DIFF_DOMAIN = new ManagerOfSubTabBasePane("Validation - Different Domain", subSection_11_Name);

    ArrayList<ManagerOfSubTabBasePane> arrayList_of_ManagerOfSubTabBasePane = new ArrayList<>();

    public NM_SubTabsHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);

        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_MODEL_ORIGIN);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_MODEL_ORIGIN_DETAIL);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_MODELING_METHOD);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_SOFTWARE_TOOL);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_RESPONSE_VARIABLE);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_PREDICTOR_VARIABLES);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_PREDICTOR_EFFECTS);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_NM_VLDTN_HANDLE_NS);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_NM_VLDTN_SAME_DOMAIN_NONINDEP);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_NM_VLDTN_SAME_DOMAIN_INDEP);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_NM_VLDTN_DIFF_DOMAIN);

        // ./json下のすべてのJSONファイルを取得
        File jsonDir = new File("./json");
        File[] jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));

        if (jsonFiles != null) {
            for (File jsonFile : jsonFiles) {
                String jsonFileName = jsonFile.getName();
                mngrOfSubTabBasePane_MODEL_ORIGIN.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName,subSection_1_Name));
                mngrOfSubTabBasePane_MODEL_ORIGIN_DETAIL.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_2_Name));
                mngrOfSubTabBasePane_MODELING_METHOD.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_3_Name));
                mngrOfSubTabBasePane_SOFTWARE_TOOL.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_4_Name));
                mngrOfSubTabBasePane_RESPONSE_VARIABLE.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_5_Name));
                mngrOfSubTabBasePane_PREDICTOR_VARIABLES.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_6_Name));
                mngrOfSubTabBasePane_PREDICTOR_EFFECTS.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_7_Name));
                mngrOfSubTabBasePane_NM_VLDTN_HANDLE_NS.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_8_Name));
                mngrOfSubTabBasePane_NM_VLDTN_SAME_DOMAIN_NONINDEP.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_9_Name));
                mngrOfSubTabBasePane_NM_VLDTN_SAME_DOMAIN_INDEP.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_10_Name));
                mngrOfSubTabBasePane_NM_VLDTN_DIFF_DOMAIN.addToTheDePaneArray(new One_DEResultPane(jsonFileName, sectionName, subSection_11_Name));
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

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_MODEL_ORIGIN.getDePaneArray()) {
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

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_MODEL_ORIGIN_DETAIL.getDePaneArray()) {
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

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_MODELING_METHOD.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_SOFTWARE_TOOL.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_RESPONSE_VARIABLE.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_PREDICTOR_VARIABLES.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_PREDICTOR_EFFECTS.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_NM_VLDTN_HANDLE_NS.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_NM_VLDTN_SAME_DOMAIN_NONINDEP.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_NM_VLDTN_SAME_DOMAIN_INDEP.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_NM_VLDTN_DIFF_DOMAIN.getDePaneArray()) {
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

        for (One_DEResultPane pane : mngrOfSubTabBasePane_MODEL_ORIGIN.getDePaneArray()) {
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

        for (One_DEResultPane pane : mngrOfSubTabBasePane_MODEL_ORIGIN_DETAIL.getDePaneArray()) {
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

        for (One_DEResultPane pane : mngrOfSubTabBasePane_MODELING_METHOD.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_SOFTWARE_TOOL.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_RESPONSE_VARIABLE.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_PREDICTOR_VARIABLES.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_PREDICTOR_EFFECTS.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_NM_VLDTN_HANDLE_NS.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_NM_VLDTN_SAME_DOMAIN_NONINDEP.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_NM_VLDTN_SAME_DOMAIN_INDEP.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_NM_VLDTN_DIFF_DOMAIN.getDePaneArray()) {
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
