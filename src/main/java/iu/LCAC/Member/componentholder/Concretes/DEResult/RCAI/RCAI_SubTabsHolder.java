package iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.One_DEResultPane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.ManagerOfSubTabBasePane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class RCAI_SubTabsHolder extends AbstCHolderMember {

    static String sectionName = "reference_cohort_and_imaging";

    JPanel panel = new JPanel(new BorderLayout());
    JTabbedPane baseTabPane = new JTabbedPane();

    // Reference Cohort and Imaging
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_DATASET_NAME = new ManagerOfSubTabBasePane("Dataset Name", "dataset_name");
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_HC_N = new ManagerOfSubTabBasePane("HC N", "hc_n");
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_HC_AGE = new ManagerOfSubTabBasePane("HC Age", "hc_age");
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_HC_SEX = new ManagerOfSubTabBasePane("HC Sex", "hc_sex");
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_IMAGING_MODALITY = new ManagerOfSubTabBasePane("Imaging Modality", "imaging_modality");
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_ANALYSIS_LEVEL = new ManagerOfSubTabBasePane("Analysis Level", "analysis_level");
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_PREPROCESSING_PIPELINE = new ManagerOfSubTabBasePane("Preprocessing Pipeline", "preprocessing_pipeline");
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_QUALITY_CHECKING = new ManagerOfSubTabBasePane("Quality Checking", "quality_checking");
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_QUALITY_CHECKING_DETAIL = new ManagerOfSubTabBasePane("Quality Checking Detail", "quality_checking_detail");
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_SITE_EFFECT_HANDLING = new ManagerOfSubTabBasePane("Site Effect Handling", "site_effect_handling");
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_SITE_EFFECT_HANDLING_DETAIL = new ManagerOfSubTabBasePane("Site Effect Handling Detail", "site_effect_handling_detail");

    ArrayList<ManagerOfSubTabBasePane> arrayList_of_ManagerOfSubTabBasePane = new ArrayList<>();

    public RCAI_SubTabsHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);

        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_DATASET_NAME);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_HC_N);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_HC_AGE);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_HC_SEX);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_IMAGING_MODALITY);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_ANALYSIS_LEVEL);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_PREPROCESSING_PIPELINE);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_QUALITY_CHECKING);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_QUALITY_CHECKING_DETAIL);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_SITE_EFFECT_HANDLING);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_SITE_EFFECT_HANDLING_DETAIL);

        // ./json下のすべてのJSONファイルを取得
        File jsonDir = new File("./json");
        File[] jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));

        if (jsonFiles != null) {
            for (File jsonFile : jsonFiles) {
                String jsonFileName = jsonFile.getName();
                mngrOfSubTabBasePane_DATASET_NAME.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "dataset_name"));
                mngrOfSubTabBasePane_HC_N.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "hc_n"));
                mngrOfSubTabBasePane_HC_AGE.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "hc_age"));
                mngrOfSubTabBasePane_HC_SEX.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "hc_sex"));
                mngrOfSubTabBasePane_IMAGING_MODALITY.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "imaging_modality"));
                mngrOfSubTabBasePane_ANALYSIS_LEVEL.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "analysis_level"));
                mngrOfSubTabBasePane_PREPROCESSING_PIPELINE.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "preprocessing_pipeline"));
                mngrOfSubTabBasePane_QUALITY_CHECKING.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "quality_checking"));
                mngrOfSubTabBasePane_QUALITY_CHECKING_DETAIL.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "quality_checking_detail"));
                mngrOfSubTabBasePane_SITE_EFFECT_HANDLING.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "site_effect_handling"));
                mngrOfSubTabBasePane_SITE_EFFECT_HANDLING_DETAIL.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "site_effect_detail"));
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

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_DATASET_NAME.getDePaneArray()) {
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

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_HC_N.getDePaneArray()) {
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

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_HC_AGE.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_HC_SEX.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_IMAGING_MODALITY.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_ANALYSIS_LEVEL.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_PREPROCESSING_PIPELINE.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_QUALITY_CHECKING.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_QUALITY_CHECKING_DETAIL.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_SITE_EFFECT_HANDLING.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : mngrOfSubTabBasePane_SITE_EFFECT_HANDLING_DETAIL.getDePaneArray()) {
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

        //System.out.println(mngrOfSubTabBasePane_DATASET_NAME.getDePaneArray().size());

        for (One_DEResultPane pane : mngrOfSubTabBasePane_DATASET_NAME.getDePaneArray()) {
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

        for (One_DEResultPane pane : mngrOfSubTabBasePane_HC_N.getDePaneArray()) {
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

        for (One_DEResultPane pane : mngrOfSubTabBasePane_HC_AGE.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_HC_SEX.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_IMAGING_MODALITY.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_ANALYSIS_LEVEL.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_PREPROCESSING_PIPELINE.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_QUALITY_CHECKING.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_QUALITY_CHECKING_DETAIL.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_SITE_EFFECT_HANDLING.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : mngrOfSubTabBasePane_SITE_EFFECT_HANDLING_DETAIL.getDePaneArray()) {
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
