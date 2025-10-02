package iu.LCAC.Member.componentholder.Concretes.DEResult;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class DEResultPaneHolder extends AbstCHolderMember {

    JPanel panel = new JPanel(new BorderLayout());
    JTabbedPane baseTabPane = new JTabbedPane();

    // Reference Cohort and Imaging
    JPanel paneForSubTab_DatasetName = new JPanel();
    ArrayList<DEResultPane> paneArray_DatasetName = new ArrayList<>();
    JPanel paneForSubTab_HC_N = new JPanel();
    ArrayList<DEResultPane> paneArray_HC_N = new ArrayList<>();
    JPanel paneForSubTab_HC_Age = new JPanel();
    ArrayList<DEResultPane> paneArray_HC_Age = new ArrayList<>();
    JPanel paneForSubTab_HC_Sex = new JPanel();
    ArrayList<DEResultPane> paneArray_HC_Sex = new ArrayList<>();
    JPanel paneForSubTab_ImagingModality = new JPanel();
    ArrayList<DEResultPane> paneArray_ImagingModality = new ArrayList<>();
    JPanel paneForSubTab_AnalysisLevel = new JPanel();
    ArrayList<DEResultPane> paneArray_AnalysisLevel = new ArrayList<>();
    JPanel paneForSubTab_PreprocessingPipeline = new JPanel();
    ArrayList<DEResultPane> paneArray_PreprocessingPipeline = new ArrayList<>();
    JPanel paneForSubTab_QualityChecking = new JPanel();
    ArrayList<DEResultPane> paneArray_QualityChecking = new ArrayList<>();
    JPanel paneForSubTab_QualityCheckingDetail = new JPanel();
    ArrayList<DEResultPane> paneArray_QualityCheckingDetail = new ArrayList<>();
    JPanel paneForSubTab_SiteEffectHandling = new JPanel();
    ArrayList<DEResultPane> paneArray_SiteEffectHandling = new ArrayList<>();
    JPanel paneForSubTab_SiteEffectDetail = new JPanel(new BorderLayout());
    ArrayList<DEResultPane> paneArray_SiteEffectDetail = new ArrayList<>();

    public DEResultPaneHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);

        // 各パネルのレイアウトを設定
        paneForSubTab_DatasetName.setLayout(new BoxLayout(paneForSubTab_DatasetName, BoxLayout.Y_AXIS));
        paneForSubTab_HC_N.setLayout(new BoxLayout(paneForSubTab_HC_N, BoxLayout.Y_AXIS));
        paneForSubTab_HC_Age.setLayout(new BoxLayout(paneForSubTab_HC_Age, BoxLayout.Y_AXIS));
        paneForSubTab_HC_Sex.setLayout(new BoxLayout(paneForSubTab_HC_Sex, BoxLayout.Y_AXIS));
        paneForSubTab_ImagingModality.setLayout(new BoxLayout(paneForSubTab_ImagingModality, BoxLayout.Y_AXIS));
        paneForSubTab_AnalysisLevel.setLayout(new BoxLayout(paneForSubTab_AnalysisLevel, BoxLayout.Y_AXIS));
        paneForSubTab_PreprocessingPipeline.setLayout(new BoxLayout(paneForSubTab_PreprocessingPipeline, BoxLayout.Y_AXIS));
        paneForSubTab_QualityChecking.setLayout(new BoxLayout(paneForSubTab_QualityChecking, BoxLayout.Y_AXIS));
        paneForSubTab_QualityCheckingDetail.setLayout(new BoxLayout(paneForSubTab_QualityCheckingDetail, BoxLayout.Y_AXIS));
        paneForSubTab_SiteEffectHandling.setLayout(new BoxLayout(paneForSubTab_SiteEffectHandling, BoxLayout.Y_AXIS));
        paneForSubTab_SiteEffectDetail.setLayout(new BoxLayout(paneForSubTab_SiteEffectDetail, BoxLayout.Y_AXIS));

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
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            paneForSubTab_DatasetName.add(pane);
        }
        // パネル全体のPreferredSizeを明示的に計算
        int totalHeight = paneArray_DatasetName.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        paneForSubTab_DatasetName.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_DatasetName = new JScrollPane(paneForSubTab_DatasetName,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_DatasetName.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_DatasetName.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Dataset Name", scrollPane_DatasetName);

        for (DEResultPane pane : paneArray_HC_N) {
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            paneForSubTab_HC_N.add(pane);
        }
        totalHeight = paneArray_HC_N.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        paneForSubTab_HC_N.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_HC_N = new JScrollPane(paneForSubTab_HC_N,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_HC_N.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_HC_N.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("HC N", scrollPane_HC_N);

        for (DEResultPane pane : paneArray_HC_Age) {
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            paneForSubTab_HC_Age.add(pane);
        }
        totalHeight = paneArray_HC_Age.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        paneForSubTab_HC_Age.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_HC_Age = new JScrollPane(paneForSubTab_HC_Age,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_HC_Age.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_HC_Age.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("HC Age", scrollPane_HC_Age);

        for (DEResultPane pane : paneArray_HC_Sex) {
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            paneForSubTab_HC_Sex.add(pane);
        }
        totalHeight = paneArray_HC_Sex.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        paneForSubTab_HC_Sex.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_HC_Sex = new JScrollPane(paneForSubTab_HC_Sex,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_HC_Sex.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_HC_Sex.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("HC Sex", scrollPane_HC_Sex);

        for (DEResultPane pane : paneArray_ImagingModality) {
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            paneForSubTab_ImagingModality.add(pane);
        }
        totalHeight = paneArray_ImagingModality.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        paneForSubTab_ImagingModality.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_ImagingModality = new JScrollPane(paneForSubTab_ImagingModality,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_ImagingModality.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_ImagingModality.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Imaging Modality", scrollPane_ImagingModality);

        for (DEResultPane pane : paneArray_AnalysisLevel) {
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            paneForSubTab_AnalysisLevel.add(pane);
        }
        totalHeight = paneArray_AnalysisLevel.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        paneForSubTab_AnalysisLevel.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_AnalysisLevel = new JScrollPane(paneForSubTab_AnalysisLevel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_AnalysisLevel.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_AnalysisLevel.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Analysis Level", scrollPane_AnalysisLevel);

        for (DEResultPane pane : paneArray_PreprocessingPipeline) {
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            paneForSubTab_PreprocessingPipeline.add(pane);
        }
        totalHeight = paneArray_PreprocessingPipeline.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        paneForSubTab_PreprocessingPipeline.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_PreprocessingPipeline = new JScrollPane(paneForSubTab_PreprocessingPipeline,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_PreprocessingPipeline.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_PreprocessingPipeline.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Preprocessing Pipeline", scrollPane_PreprocessingPipeline);

        for (DEResultPane pane : paneArray_QualityChecking) {
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            paneForSubTab_QualityChecking.add(pane);
        }
        totalHeight = paneArray_QualityChecking.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        paneForSubTab_QualityChecking.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_QualityChecking = new JScrollPane(paneForSubTab_QualityChecking,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_QualityChecking.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_QualityChecking.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Quality Checking", scrollPane_QualityChecking);

        for (DEResultPane pane : paneArray_QualityCheckingDetail) {
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            paneForSubTab_QualityCheckingDetail.add(pane);
        }
        totalHeight = paneArray_QualityCheckingDetail.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        paneForSubTab_QualityCheckingDetail.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_QualityCheckingDetail = new JScrollPane(paneForSubTab_QualityCheckingDetail,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_QualityCheckingDetail.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_QualityCheckingDetail.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Quality Checking Detail", scrollPane_QualityCheckingDetail);

        for (DEResultPane pane : paneArray_SiteEffectHandling) {
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            paneForSubTab_SiteEffectHandling.add(pane);
        }
        totalHeight = paneArray_SiteEffectHandling.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        paneForSubTab_SiteEffectHandling.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_SiteEffectHandling = new JScrollPane(paneForSubTab_SiteEffectHandling,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_SiteEffectHandling.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_SiteEffectHandling.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        baseTabPane.add("Site Effect Handling", scrollPane_SiteEffectHandling);


        for (DEResultPane pane : paneArray_SiteEffectDetail) {
            pane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
            pane.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            paneForSubTab_SiteEffectDetail.add(pane, BorderLayout.CENTER);
        }
        totalHeight = paneArray_SiteEffectDetail.stream().mapToInt(p -> p.getPreferredSize().height).sum();
        //paneForSubTab_SiteEffectDetail.setPreferredSize(new java.awt.Dimension(600, totalHeight));
        JScrollPane scrollPane_SiteEffectDetail = new JScrollPane(paneForSubTab_SiteEffectDetail,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollPane_SiteEffectDetail.getVerticalScrollBar().setUnitIncrement(16);
        //scrollPane_SiteEffectDetail.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        //scrollPane_SiteEffectDetail.setPreferredSize(new Dimension(600, 600));
        baseTabPane.add("Site Effect Detail", scrollPane_SiteEffectDetail);

        panel.add(baseTabPane, BorderLayout.CENTER);

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
