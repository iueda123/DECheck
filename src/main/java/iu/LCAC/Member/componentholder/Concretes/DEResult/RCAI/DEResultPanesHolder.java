package iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class DEResultPanesHolder extends AbstCHolderMember {

    JPanel panel = new JPanel(new BorderLayout());
    JTabbedPane baseTabPane = new JTabbedPane();

    // Reference Cohort and Imaging
    JPanel basePaneOfSubTab_DATASET_NAME = new JPanel();
    ArrayList<DEResultPanes> paneArray_DATASET_NAME = new ArrayList<>();
    JPanel basePaneOfSubTab_HC_N = new JPanel();
    ArrayList<DEResultPanes> paneArray_HC_N = new ArrayList<>();
    JPanel basePaneOfSubTab_HC_Age = new JPanel();
    ArrayList<DEResultPanes> paneArray_HC_Age = new ArrayList<>();
    JPanel basePaneOfSubTab_HC_Sex = new JPanel();
    ArrayList<DEResultPanes> paneArray_HC_Sex = new ArrayList<>();
    JPanel basePaneOfSubTab_ImagingModality = new JPanel();
    ArrayList<DEResultPanes> paneArray_IMAGING_MODALITY = new ArrayList<>();
    JPanel basePaneOfSubTab_AnalysisLevel = new JPanel();
    ArrayList<DEResultPanes> paneArray_AnalysisLevel = new ArrayList<>();
    JPanel basePaneOfSubTab_PreprocessingPipeline = new JPanel();
    ArrayList<DEResultPanes> paneArray_PreprocessingPipeline = new ArrayList<>();
    JPanel basePaneOfSubTab_QualityChecking = new JPanel();
    ArrayList<DEResultPanes> paneArray_QualityChecking = new ArrayList<>();
    JPanel basePaneOfSubTab_QualityCheckingDetail = new JPanel();
    ArrayList<DEResultPanes> paneArray_QualityCheckingDetail = new ArrayList<>();
    JPanel basePaneOfSubTab_SiteEffectHandling = new JPanel();
    ArrayList<DEResultPanes> paneArray_SiteEffectHandling = new ArrayList<>();
    JPanel basePaneOfSubTab_SiteEffectDetail = new JPanel();
    ArrayList<DEResultPanes> paneArray_SiteEffectDetail = new ArrayList<>();

    public DEResultPanesHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);

        // 各パネルのレイアウトを設定
        basePaneOfSubTab_DATASET_NAME.setLayout(new BoxLayout(basePaneOfSubTab_DATASET_NAME, BoxLayout.Y_AXIS));
        basePaneOfSubTab_HC_N.setLayout(new BoxLayout(basePaneOfSubTab_HC_N, BoxLayout.Y_AXIS));
        basePaneOfSubTab_HC_Age.setLayout(new BoxLayout(basePaneOfSubTab_HC_Age, BoxLayout.Y_AXIS));
        basePaneOfSubTab_HC_Sex.setLayout(new BoxLayout(basePaneOfSubTab_HC_Sex, BoxLayout.Y_AXIS));
        basePaneOfSubTab_ImagingModality.setLayout(new BoxLayout(basePaneOfSubTab_ImagingModality, BoxLayout.Y_AXIS));
        basePaneOfSubTab_AnalysisLevel.setLayout(new BoxLayout(basePaneOfSubTab_AnalysisLevel, BoxLayout.Y_AXIS));
        basePaneOfSubTab_PreprocessingPipeline.setLayout(new BoxLayout(basePaneOfSubTab_PreprocessingPipeline, BoxLayout.Y_AXIS));
        basePaneOfSubTab_QualityChecking.setLayout(new BoxLayout(basePaneOfSubTab_QualityChecking, BoxLayout.Y_AXIS));
        basePaneOfSubTab_QualityCheckingDetail.setLayout(new BoxLayout(basePaneOfSubTab_QualityCheckingDetail, BoxLayout.Y_AXIS));
        basePaneOfSubTab_SiteEffectHandling.setLayout(new BoxLayout(basePaneOfSubTab_SiteEffectHandling, BoxLayout.Y_AXIS));
        basePaneOfSubTab_SiteEffectDetail.setLayout(new BoxLayout(basePaneOfSubTab_SiteEffectDetail, BoxLayout.Y_AXIS));

        // ./json下のすべてのJSONファイルを取得
        File jsonDir = new File("./json");
        File[] jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));

        if (jsonFiles != null) {
            for (File jsonFile : jsonFiles) {
                String jsonFileName = jsonFile.getName();

                paneArray_DATASET_NAME.add(new DEResultPanes(jsonFileName, "reference_cohort_and_imaging", "dataset_name"));
                paneArray_HC_N.add(new DEResultPanes(jsonFileName, "reference_cohort_and_imaging", "hc_n"));
                paneArray_HC_Age.add(new DEResultPanes(jsonFileName, "reference_cohort_and_imaging", "hc_age"));
                paneArray_HC_Sex.add(new DEResultPanes(jsonFileName, "reference_cohort_and_imaging", "hc_sex"));
                paneArray_IMAGING_MODALITY.add(new DEResultPanes(jsonFileName, "reference_cohort_and_imaging", "imaging_modality"));
                paneArray_AnalysisLevel.add(new DEResultPanes(jsonFileName, "reference_cohort_and_imaging", "analysis_level"));
                paneArray_PreprocessingPipeline.add(new DEResultPanes(jsonFileName, "reference_cohort_and_imaging", "preprocessing_pipeline"));
                paneArray_QualityChecking.add(new DEResultPanes(jsonFileName, "reference_cohort_and_imaging", "quality_checking"));
                paneArray_QualityCheckingDetail.add(new DEResultPanes(jsonFileName, "reference_cohort_and_imaging", "quality_checking_detail"));
                paneArray_SiteEffectHandling.add(new DEResultPanes(jsonFileName, "reference_cohort_and_imaging", "site_effect_handling"));
                paneArray_SiteEffectDetail.add(new DEResultPanes(jsonFileName, "reference_cohort_and_imaging", "site_effect_detail"));
            }
        }



        for (int i = 0; i < paneArray_DATASET_NAME.size(); i++) {
            basePaneOfSubTab_DATASET_NAME.add(new JLabel(" ")); // Separator
            DEResultPanes pane = paneArray_DATASET_NAME.get(i);
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            basePaneOfSubTab_DATASET_NAME.add(pane);
        }
        // パネル全体のPreferredSizeを明示的に計算
        int totalHeight = paneArray_DATASET_NAME.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePaneOfSubTab_DATASET_NAME.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_DatasetName = new JScrollPane(basePaneOfSubTab_DATASET_NAME,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_DatasetName.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_DatasetName.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Dataset Name", scrollPane_DatasetName);

        for (int i = 0; i < paneArray_HC_N.size(); i++) {
            basePaneOfSubTab_HC_N.add(new JLabel(" ")); // Separator
            DEResultPanes pane = paneArray_HC_N.get(i);
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            basePaneOfSubTab_HC_N.add(pane);
        }
        totalHeight = paneArray_HC_N.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePaneOfSubTab_HC_N.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_HC_N = new JScrollPane(basePaneOfSubTab_HC_N,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_HC_N.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_HC_N.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("HC N", scrollPane_HC_N);

        for (int i = 0; i < paneArray_HC_Age.size(); i++) {
            basePaneOfSubTab_HC_Age.add(new JLabel(" ")); // Separator
            DEResultPanes pane = paneArray_HC_Age.get(i);
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            basePaneOfSubTab_HC_Age.add(pane);
        }
        totalHeight = paneArray_HC_Age.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePaneOfSubTab_HC_Age.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_HC_Age = new JScrollPane(basePaneOfSubTab_HC_Age,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_HC_Age.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_HC_Age.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("HC Age", scrollPane_HC_Age);

        for (int i = 0; i < paneArray_HC_Sex.size(); i++) {
            basePaneOfSubTab_HC_Sex.add(new JLabel(" ")); // Separator
            DEResultPanes pane = paneArray_HC_Sex.get(i);
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            basePaneOfSubTab_HC_Sex.add(pane);
        }
        totalHeight = paneArray_HC_Sex.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePaneOfSubTab_HC_Sex.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_HC_Sex = new JScrollPane(basePaneOfSubTab_HC_Sex,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_HC_Sex.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_HC_Sex.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("HC Sex", scrollPane_HC_Sex);

        for (int i = 0; i < paneArray_IMAGING_MODALITY.size(); i++) {
            basePaneOfSubTab_ImagingModality.add(new JLabel(" ")); // Separator
            DEResultPanes pane = paneArray_IMAGING_MODALITY.get(i);
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            basePaneOfSubTab_ImagingModality.add(pane);
        }
        totalHeight = paneArray_IMAGING_MODALITY.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePaneOfSubTab_ImagingModality.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_ImagingModality = new JScrollPane(basePaneOfSubTab_ImagingModality,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_ImagingModality.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_ImagingModality.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Imaging Modality", scrollPane_ImagingModality);

        for (int i = 0; i < paneArray_AnalysisLevel.size(); i++) {
            basePaneOfSubTab_AnalysisLevel.add(new JLabel(" ")); // Separator
            DEResultPanes pane = paneArray_AnalysisLevel.get(i);
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            basePaneOfSubTab_AnalysisLevel.add(pane);
        }
        totalHeight = paneArray_AnalysisLevel.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePaneOfSubTab_AnalysisLevel.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_AnalysisLevel = new JScrollPane(basePaneOfSubTab_AnalysisLevel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_AnalysisLevel.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_AnalysisLevel.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Analysis Level", scrollPane_AnalysisLevel);

        for (int i = 0; i < paneArray_PreprocessingPipeline.size(); i++) {
            basePaneOfSubTab_PreprocessingPipeline.add(new JLabel(" ")); // Separator
            DEResultPanes pane = paneArray_PreprocessingPipeline.get(i);
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            basePaneOfSubTab_PreprocessingPipeline.add(pane);
        }
        totalHeight = paneArray_PreprocessingPipeline.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePaneOfSubTab_PreprocessingPipeline.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_PreprocessingPipeline = new JScrollPane(basePaneOfSubTab_PreprocessingPipeline,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_PreprocessingPipeline.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_PreprocessingPipeline.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Preprocessing Pipeline", scrollPane_PreprocessingPipeline);

        for (int i = 0; i < paneArray_QualityChecking.size(); i++) {
            basePaneOfSubTab_QualityChecking.add(new JLabel(" ")); // Separator
            DEResultPanes pane = paneArray_QualityChecking.get(i);
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            basePaneOfSubTab_QualityChecking.add(pane);
        }
        totalHeight = paneArray_QualityChecking.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePaneOfSubTab_QualityChecking.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_QualityChecking = new JScrollPane(basePaneOfSubTab_QualityChecking,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_QualityChecking.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_QualityChecking.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Quality Checking", scrollPane_QualityChecking);

        for (int i = 0; i < paneArray_QualityCheckingDetail.size(); i++) {
            basePaneOfSubTab_QualityCheckingDetail.add(new JLabel(" ")); // Separator
            DEResultPanes pane = paneArray_QualityCheckingDetail.get(i);
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            basePaneOfSubTab_QualityCheckingDetail.add(pane);
        }
        totalHeight = paneArray_QualityCheckingDetail.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePaneOfSubTab_QualityCheckingDetail.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_QualityCheckingDetail = new JScrollPane(basePaneOfSubTab_QualityCheckingDetail,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_QualityCheckingDetail.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_QualityCheckingDetail.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Quality Checking Detail", scrollPane_QualityCheckingDetail);

        for (int i = 0; i < paneArray_SiteEffectHandling.size(); i++) {
            basePaneOfSubTab_SiteEffectHandling.add(new JLabel(" ")); // Separator
            DEResultPanes pane = paneArray_SiteEffectHandling.get(i);
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            basePaneOfSubTab_SiteEffectHandling.add(pane);
        }
        totalHeight = paneArray_SiteEffectHandling.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePaneOfSubTab_SiteEffectHandling.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_SiteEffectHandling = new JScrollPane(basePaneOfSubTab_SiteEffectHandling,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_SiteEffectHandling.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_SiteEffectHandling.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Site Effect Handling", scrollPane_SiteEffectHandling);

        for (int i = 0; i < paneArray_SiteEffectDetail.size(); i++) {
            basePaneOfSubTab_SiteEffectDetail.add(new JLabel(" ")); // Separator
            DEResultPanes pane = paneArray_SiteEffectDetail.get(i);
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            basePaneOfSubTab_SiteEffectDetail.add(pane);
        }
        totalHeight = paneArray_SiteEffectDetail.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        basePaneOfSubTab_SiteEffectDetail.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_SiteEffectDetail = new JScrollPane(basePaneOfSubTab_SiteEffectDetail,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_SiteEffectDetail.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_SiteEffectDetail.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Site Effect Detail", scrollPane_SiteEffectDetail);

        panel.add(baseTabPane, BorderLayout.CENTER);

    }

    @Override
    public void postInitialize() {
        if (actionMediator != null) {

            for (DEResultPanes DEResultPanes : paneArray_DATASET_NAME) {
                String jsonName = DEResultPanes.getJsonName();
                String sectionName = DEResultPanes.getSectionName();
                String subSectionName = DEResultPanes.getSubSectionName();

                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPanes DEResultPanes : paneArray_HC_N) {
                String jsonName = DEResultPanes.getJsonName();
                String sectionName = DEResultPanes.getSectionName();
                String subSectionName = DEResultPanes.getSubSectionName();

                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPanes DEResultPanes : paneArray_HC_Age) {
                String jsonName = DEResultPanes.getJsonName();
                String sectionName = DEResultPanes.getSectionName();
                String subSectionName = DEResultPanes.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPanes DEResultPanes : paneArray_HC_Sex) {
                String jsonName = DEResultPanes.getJsonName();
                String sectionName = DEResultPanes.getSectionName();
                String subSectionName = DEResultPanes.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPanes DEResultPanes : paneArray_IMAGING_MODALITY) {
                String jsonName = DEResultPanes.getJsonName();
                String sectionName = DEResultPanes.getSectionName();
                String subSectionName = DEResultPanes.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPanes DEResultPanes : paneArray_AnalysisLevel) {
                String jsonName = DEResultPanes.getJsonName();
                String sectionName = DEResultPanes.getSectionName();
                String subSectionName = DEResultPanes.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPanes DEResultPanes : paneArray_PreprocessingPipeline) {
                String jsonName = DEResultPanes.getJsonName();
                String sectionName = DEResultPanes.getSectionName();
                String subSectionName = DEResultPanes.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPanes DEResultPanes : paneArray_QualityChecking) {
                String jsonName = DEResultPanes.getJsonName();
                String sectionName = DEResultPanes.getSectionName();
                String subSectionName = DEResultPanes.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPanes DEResultPanes : paneArray_QualityCheckingDetail) {
                String jsonName = DEResultPanes.getJsonName();
                String sectionName = DEResultPanes.getSectionName();
                String subSectionName = DEResultPanes.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPanes DEResultPanes : paneArray_SiteEffectHandling) {
                String jsonName = DEResultPanes.getJsonName();
                String sectionName = DEResultPanes.getSectionName();
                String subSectionName = DEResultPanes.getSubSectionName();
                ActionEvent customEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "initialize_de_result_pane " + jsonName + " " + sectionName + " " + subSectionName);
                actionMediator.getInstanceOfAMember("initialize_de_result_pane").perform(customEvent);
            }

            for (DEResultPanes DEResultPanes : paneArray_SiteEffectDetail) {
                String jsonName = DEResultPanes.getJsonName();
                String sectionName = DEResultPanes.getSectionName();
                String subSectionName = DEResultPanes.getSubSectionName();
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
    public DEResultPanes getResultPane(String jsonName, String sectionName, String subSectionName) {
        for (DEResultPanes pane : paneArray_DATASET_NAME) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPanes pane : paneArray_HC_N) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPanes pane : paneArray_HC_Age) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPanes pane : paneArray_HC_Sex) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPanes pane : paneArray_IMAGING_MODALITY) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPanes pane : paneArray_AnalysisLevel) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPanes pane : paneArray_PreprocessingPipeline) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPanes pane : paneArray_QualityChecking) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPanes pane : paneArray_QualityCheckingDetail) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPanes pane : paneArray_SiteEffectHandling) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (DEResultPanes pane : paneArray_SiteEffectDetail) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        return null;

    }

    public ArrayList<DEResultPanes> getPaneArray_of_DATASET_NAME() {
        return paneArray_DATASET_NAME;
    }

    public JPanel getBasePaneForSubTab_DATASET_NAME(){
        return basePaneOfSubTab_DATASET_NAME;
    }
}
