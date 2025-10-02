package iu.LCAC.Member.componentholder.Concretes.DEResult;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class DEResultPaneHolder extends AbstCHolderMember {

    JPanel panel = new JPanel();

    JTabbedPane baseTabPane = new JTabbedPane();

    // Reference Cohort and Imaging
    Box subTab_DatasetName = Box.createVerticalBox();
    ArrayList<DEResultPane> paneArray_DatasetName = new ArrayList<>();
    Box subTab_HC_N = Box.createVerticalBox();
    ArrayList<DEResultPane> paneArray_HC_N = new ArrayList<>();
    Box subTab_HC_Age = Box.createVerticalBox();
    ArrayList<DEResultPane> paneArray_HC_Age = new ArrayList<>();
    Box subTab_HC_Sex = Box.createVerticalBox();
    ArrayList<DEResultPane> paneArray_HC_Sex = new ArrayList<>();
    Box subTab_ImagingModality = Box.createVerticalBox();
    ArrayList<DEResultPane> paneArray_ImagingModality = new ArrayList<>();
    Box subTab_AnalysisLevel = Box.createVerticalBox();
    ArrayList<DEResultPane> paneArray_AnalysisLevel = new ArrayList<>();
    Box subTab_PreprocessingPipeline = Box.createVerticalBox();
    ArrayList<DEResultPane> paneArray_PreprocessingPipeline = new ArrayList<>();
    Box subTab_QualityChecking = Box.createVerticalBox();
    ArrayList<DEResultPane> paneArray_QualityChecking = new ArrayList<>();
    Box subTab_QualityCheckingDetail = Box.createVerticalBox();
    ArrayList<DEResultPane> paneArray_QualityCheckingDetail = new ArrayList<>();
    Box subTab_SiteEffectHandling = Box.createVerticalBox();
    ArrayList<DEResultPane> paneArray_SiteEffectHandling = new ArrayList<>();
    Box subTab_SiteEffectDetail = Box.createVerticalBox();
    ArrayList<DEResultPane> paneArray_SiteEffectDetail = new ArrayList<>();

    public DEResultPaneHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);

        // ./json下のすべてのJSONファイルを取得
        File jsonDir = new File("./json");
        File[] jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));

        if (jsonFiles != null) {
            for (File jsonFile : jsonFiles) {
                String jsonFileName = jsonFile.getName();

                paneArray_DatasetName.add(new DEResultPane(jsonFileName, "reference_cohort_and_imaging", "dataset_name"));
                paneArray_HC_N.add(new DEResultPane(jsonFileName, "reference_cohort_and_imaging", "hc_n"));
                paneArray_HC_Age.add(new DEResultPane(jsonFileName, "reference_cohort_and_imaging", "hc_age"));
                paneArray_HC_Sex.add(new DEResultPane(jsonFileName, "reference_cohort_and_imaging", "hc_sex"));
                paneArray_ImagingModality.add(new DEResultPane(jsonFileName, "reference_cohort_and_imaging", "imaging_modality"));
                paneArray_AnalysisLevel.add(new DEResultPane(jsonFileName, "reference_cohort_and_imaging", "analysis_level"));
                paneArray_PreprocessingPipeline.add(new DEResultPane(jsonFileName, "reference_cohort_and_imaging", "preprocessing_pipeline"));
                paneArray_QualityChecking.add(new DEResultPane(jsonFileName, "reference_cohort_and_imaging", "quality_checking"));
                paneArray_QualityCheckingDetail.add(new DEResultPane(jsonFileName, "reference_cohort_and_imaging", "quality_checking_detail"));
                paneArray_SiteEffectHandling.add(new DEResultPane(jsonFileName, "reference_cohort_and_imaging", "site_effect_handling"));
                paneArray_SiteEffectDetail.add(new DEResultPane(jsonFileName, "reference_cohort_and_imaging", "site_effect_detail"));
            }
        }

        for (DEResultPane pane : paneArray_DatasetName) {
            subTab_DatasetName.add(pane);
        }
        baseTabPane.add("Dataset Name", subTab_DatasetName);

        for (DEResultPane pane : paneArray_HC_N) {
            subTab_HC_N.add(pane);
        }
        baseTabPane.add("HC N", subTab_HC_N);

        for (DEResultPane pane : paneArray_HC_Age) {
            subTab_HC_Age.add(pane);
        }
        baseTabPane.add("HC Age", subTab_HC_Age);

        for (DEResultPane pane : paneArray_HC_Sex) {
            subTab_HC_Sex.add(pane);
        }
        baseTabPane.add("HC Sex", subTab_HC_Sex);

        for (DEResultPane pane : paneArray_ImagingModality) {
            subTab_ImagingModality.add(pane);
        }
        baseTabPane.add("Imaging Modality", subTab_ImagingModality);

        for (DEResultPane pane : paneArray_AnalysisLevel) {
            subTab_AnalysisLevel.add(pane);
        }
        baseTabPane.add("Analysis Level", subTab_AnalysisLevel);

        for (DEResultPane pane : paneArray_PreprocessingPipeline) {
            subTab_PreprocessingPipeline.add(pane);
        }
        baseTabPane.add("Preprocessing Pipeline", subTab_PreprocessingPipeline);

        for (DEResultPane pane : paneArray_QualityChecking) {
            subTab_QualityChecking.add(pane);
        }
        baseTabPane.add("Quality Checking", subTab_QualityChecking);

        for (DEResultPane pane : paneArray_QualityCheckingDetail) {
            subTab_QualityCheckingDetail.add(pane);
        }
        baseTabPane.add("Quality Checking Detail", subTab_QualityCheckingDetail);

        for (DEResultPane pane : paneArray_SiteEffectHandling) {
            subTab_SiteEffectHandling.add(pane);
        }
        baseTabPane.add("Site Effect Handling", subTab_SiteEffectHandling);

        for (DEResultPane pane : paneArray_SiteEffectDetail) {
            subTab_SiteEffectDetail.add(pane);
        }
        baseTabPane.add("Site Effect Detail", subTab_SiteEffectDetail);

        panel.add(baseTabPane);

    }

    @Override
    public void postInitialize() {
        if (actionMediator != null) {

            for (DEResultPane DEResultPane : paneArray_DatasetName) {
                String jsonName = DEResultPane.getJsonName();
                String sectionName = DEResultPane.getSectionName();
                String subSectionName = DEResultPane.getSubSectionName();

                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPane DEResultPane : paneArray_HC_N) {
                String jsonName = DEResultPane.getJsonName();
                String sectionName = DEResultPane.getSectionName();
                String subSectionName = DEResultPane.getSubSectionName();

                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPane DEResultPane : paneArray_HC_Age) {
                String jsonName = DEResultPane.getJsonName();
                String sectionName = DEResultPane.getSectionName();
                String subSectionName = DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPane DEResultPane : paneArray_HC_Sex) {
                String jsonName = DEResultPane.getJsonName();
                String sectionName = DEResultPane.getSectionName();
                String subSectionName = DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPane DEResultPane : paneArray_ImagingModality) {
                String jsonName = DEResultPane.getJsonName();
                String sectionName = DEResultPane.getSectionName();
                String subSectionName = DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPane DEResultPane : paneArray_AnalysisLevel) {
                String jsonName = DEResultPane.getJsonName();
                String sectionName = DEResultPane.getSectionName();
                String subSectionName = DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPane DEResultPane : paneArray_PreprocessingPipeline) {
                String jsonName = DEResultPane.getJsonName();
                String sectionName = DEResultPane.getSectionName();
                String subSectionName = DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPane DEResultPane : paneArray_QualityChecking) {
                String jsonName = DEResultPane.getJsonName();
                String sectionName = DEResultPane.getSectionName();
                String subSectionName = DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPane DEResultPane : paneArray_QualityCheckingDetail) {
                String jsonName = DEResultPane.getJsonName();
                String sectionName = DEResultPane.getSectionName();
                String subSectionName = DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPane DEResultPane : paneArray_SiteEffectHandling) {
                String jsonName = DEResultPane.getJsonName();
                String sectionName = DEResultPane.getSectionName();
                String subSectionName = DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPane DEResultPane : paneArray_SiteEffectDetail) {
                String jsonName = DEResultPane.getJsonName();
                String sectionName = DEResultPane.getSectionName();
                String subSectionName = DEResultPane.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

        } else {
            System.err.println("actionMediator is null in postInitialize() @ " + this.getClass().toString());
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
    public DEResultPane getResultPane(String jsonName, String sectionName, String subSectionName) {
        for (DEResultPane pane : paneArray_DatasetName) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPane pane : paneArray_HC_N) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPane pane : paneArray_HC_Age) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPane pane : paneArray_HC_Sex) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPane pane : paneArray_ImagingModality) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPane pane : paneArray_AnalysisLevel) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPane pane : paneArray_PreprocessingPipeline) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPane pane : paneArray_QualityChecking) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPane pane : paneArray_QualityCheckingDetail) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPane pane : paneArray_SiteEffectHandling) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPane pane : paneArray_SiteEffectDetail) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        return null;

    }
}
