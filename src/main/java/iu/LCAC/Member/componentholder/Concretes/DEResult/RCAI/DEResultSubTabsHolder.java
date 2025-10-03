package iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.One_DEResultPane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.Parts.constructorOfSubTabBasePane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class DEResultSubTabsHolder extends AbstCHolderMember {

    JPanel panel = new JPanel(new BorderLayout());
    JTabbedPane baseTabPane = new JTabbedPane();

    // Reference Cohort and Imaging
    constructorOfSubTabBasePane cnstrctrSubTabBasePane_DATASET_NAME = new constructorOfSubTabBasePane();
    constructorOfSubTabBasePane cnstrctrSubTabBasePane_HC_N = new constructorOfSubTabBasePane();
    constructorOfSubTabBasePane cnstrctrSubTabBasePane_HC_AGE = new constructorOfSubTabBasePane();
    constructorOfSubTabBasePane cnstrctrSubTabBasePane_HC_SEX = new constructorOfSubTabBasePane();
    constructorOfSubTabBasePane cnstrctrSubTabBasePane_IMAGING_MODALITY = new constructorOfSubTabBasePane();
    constructorOfSubTabBasePane cnstrctrSubTabBasePane_ANALYSIS_LEVEL = new constructorOfSubTabBasePane();
    constructorOfSubTabBasePane cnstrctrSubTabBasePane_PREPROCESSING_PIPELINE = new constructorOfSubTabBasePane();
    constructorOfSubTabBasePane cnstrctrSubTabBasePane_QUALITY_CHECKING = new constructorOfSubTabBasePane();
    constructorOfSubTabBasePane cnstrctrSubTabBasePane_QUALITY_CHECKING_DETAIL = new constructorOfSubTabBasePane();
    constructorOfSubTabBasePane cnstrctrSubTabBasePane_SITE_EFFECT_HANDLING = new constructorOfSubTabBasePane();
    constructorOfSubTabBasePane cnstrctrSubTabBasePane_SITE_EFFECT_DETAIL = new constructorOfSubTabBasePane();

    public DEResultSubTabsHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);

        // ./json下のすべてのJSONファイルを取得
        File jsonDir = new File("./json");
        File[] jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));

        if (jsonFiles != null) {
            for (File jsonFile : jsonFiles) {
                String jsonFileName = jsonFile.getName();
                cnstrctrSubTabBasePane_DATASET_NAME.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "dataset_name"));
                cnstrctrSubTabBasePane_HC_N.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "hc_n"));
                cnstrctrSubTabBasePane_HC_AGE.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "hc_age"));
                cnstrctrSubTabBasePane_HC_SEX.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "hc_sex"));
                cnstrctrSubTabBasePane_IMAGING_MODALITY.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "imaging_modality"));
                cnstrctrSubTabBasePane_ANALYSIS_LEVEL.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "analysis_level"));
                cnstrctrSubTabBasePane_PREPROCESSING_PIPELINE.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "preprocessing_pipeline"));
                cnstrctrSubTabBasePane_QUALITY_CHECKING.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "quality_checking"));
                cnstrctrSubTabBasePane_QUALITY_CHECKING_DETAIL.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "quality_checking_detail"));
                cnstrctrSubTabBasePane_SITE_EFFECT_HANDLING.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "site_effect_handling"));
                cnstrctrSubTabBasePane_SITE_EFFECT_DETAIL.addToTheDePaneArray(new One_DEResultPane(jsonFileName, "reference_cohort_and_imaging", "site_effect_detail"));
            }
        }

        baseTabPane.add("Dataset Name", cnstrctrSubTabBasePane_DATASET_NAME.constructBasePaneOfSubTab());
        baseTabPane.add("HC N", cnstrctrSubTabBasePane_HC_N.constructBasePaneOfSubTab());
        baseTabPane.add("HC Age", cnstrctrSubTabBasePane_HC_AGE.constructBasePaneOfSubTab());
        baseTabPane.add("HC Sex", cnstrctrSubTabBasePane_HC_SEX.constructBasePaneOfSubTab());
        baseTabPane.add("Imaging Modality", cnstrctrSubTabBasePane_IMAGING_MODALITY.constructBasePaneOfSubTab());
        baseTabPane.add("Analysis Level", cnstrctrSubTabBasePane_ANALYSIS_LEVEL.constructBasePaneOfSubTab());
        baseTabPane.add("Preprocessing Pipeline", cnstrctrSubTabBasePane_PREPROCESSING_PIPELINE.constructBasePaneOfSubTab());
        baseTabPane.add("Quality Checking", cnstrctrSubTabBasePane_QUALITY_CHECKING.constructBasePaneOfSubTab());
        baseTabPane.add("Quality Checking Detail", cnstrctrSubTabBasePane_QUALITY_CHECKING_DETAIL.constructBasePaneOfSubTab());
        baseTabPane.add("Site Effect Handling", cnstrctrSubTabBasePane_SITE_EFFECT_HANDLING.constructBasePaneOfSubTab());
        baseTabPane.add("Site Effect Detail", cnstrctrSubTabBasePane_SITE_EFFECT_DETAIL.constructBasePaneOfSubTab());

        panel.add(baseTabPane, BorderLayout.CENTER);
    }

    @Override
    public void postInitialize() {
        if (actionMediator != null) {

            for (One_DEResultPane One_DEResultPane : cnstrctrSubTabBasePane_DATASET_NAME.getDePaneArray()) {
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

            for (One_DEResultPane One_DEResultPane : cnstrctrSubTabBasePane_HC_N.getDePaneArray()) {
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

            for (One_DEResultPane One_DEResultPane : cnstrctrSubTabBasePane_HC_AGE.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : cnstrctrSubTabBasePane_HC_SEX.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : cnstrctrSubTabBasePane_IMAGING_MODALITY.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : cnstrctrSubTabBasePane_ANALYSIS_LEVEL.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : cnstrctrSubTabBasePane_PREPROCESSING_PIPELINE.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : cnstrctrSubTabBasePane_QUALITY_CHECKING.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : cnstrctrSubTabBasePane_QUALITY_CHECKING_DETAIL.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : cnstrctrSubTabBasePane_SITE_EFFECT_HANDLING.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (One_DEResultPane One_DEResultPane : cnstrctrSubTabBasePane_SITE_EFFECT_DETAIL.getDePaneArray()) {
                String jsonName = One_DEResultPane.getJsonName();
                String sectionName = One_DEResultPane.getSectionName();
                String subSectionName = One_DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
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
    public One_DEResultPane getResultPane(String jsonName, String sectionName, String subSectionName) {
        //System.out.println("Start searching the DEResultPane with following: ");
        //System.out.println("  JSON Name: " + jsonName);
        //System.out.println("  Section Name: " + sectionName);
        //System.out.println("  Subsection Name: " + subSectionName);

        /*
        for (One_DEResultPane pane : paneArray_DATASET_NAME) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }
        */

        System.out.println(cnstrctrSubTabBasePane_DATASET_NAME.getDePaneArray().size());

        for (One_DEResultPane pane : cnstrctrSubTabBasePane_DATASET_NAME.getDePaneArray()) {
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

        for (One_DEResultPane pane : cnstrctrSubTabBasePane_HC_N.getDePaneArray()) {
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

        for (One_DEResultPane pane : cnstrctrSubTabBasePane_HC_AGE.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : cnstrctrSubTabBasePane_HC_SEX.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : cnstrctrSubTabBasePane_IMAGING_MODALITY.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : cnstrctrSubTabBasePane_ANALYSIS_LEVEL.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : cnstrctrSubTabBasePane_PREPROCESSING_PIPELINE.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : cnstrctrSubTabBasePane_QUALITY_CHECKING.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : cnstrctrSubTabBasePane_QUALITY_CHECKING_DETAIL.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : cnstrctrSubTabBasePane_SITE_EFFECT_HANDLING.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEResultPane pane : cnstrctrSubTabBasePane_SITE_EFFECT_DETAIL.getDePaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        return null;

    }

}
