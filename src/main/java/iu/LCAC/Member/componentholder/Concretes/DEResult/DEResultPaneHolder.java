package iu.LCAC.Member.componentholder.Concretes.DEResult;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class DEResultPaneHolder extends AbstCHolderMember {

    JPanel panel = new JPanel();

    JTabbedPane baseTabPane = new JTabbedPane();

    // Reference Cohort and Imaging
    JTabbedPane tabPane_ReferenceCohortAndImaging = new JTabbedPane();
    Box subTab_DatasetName = Box.createVerticalBox();
    ArrayList<DEResultPane> paneArray_DatasetName = new ArrayList<>();
    Box subTab_HC_N = Box.createVerticalBox();
    ArrayList<DEResultPane> paneArray_HC_N = new ArrayList<>();

    // Normative Modeling
    JTabbedPane tabPane_NormativeModeling = new JTabbedPane();
    JTabbedPane tabPane_ModelOrigin = new JTabbedPane();
    ArrayList<DEResultPane> array_ModelOrigin = new ArrayList<>();
    JTabbedPane tabPane_ModelDetail = new JTabbedPane();
    ArrayList<DEResultPane> array_ModelDetail = new ArrayList<>();

    public DEResultPaneHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);

        paneArray_DatasetName.add(new DEResultPane("Example9997.json", "reference_cohort_and_imaging", "dataset_name"));
        paneArray_DatasetName.add(new DEResultPane("Example9996.json", "reference_cohort_and_imaging", "dataset_name"));
        for (DEResultPane DEResultPane : paneArray_DatasetName) {
            subTab_DatasetName.add(DEResultPane);
        }
        baseTabPane.add("Dataset Name", subTab_DatasetName);

        paneArray_HC_N.add(new DEResultPane("Example9997.json", "reference_cohort_and_imaging", "hc_n"));
        paneArray_HC_N.add(new DEResultPane("Example9996.json", "reference_cohort_and_imaging", "hc_n"));
        for (DEResultPane DEResultPane : paneArray_HC_N) {
            subTab_HC_N.add(DEResultPane);
        }
        baseTabPane.add("HC N", subTab_HC_N);

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
                        "initialize_a_result_pane " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_a_result_pane").perform(customEvent);
            }

            for (DEResultPane DEResultPane : paneArray_HC_N) {
                String jsonName = DEResultPane.getJsonName();
                String sectionName = DEResultPane.getSectionName();
                String subSectionName = DEResultPane.getSubSectionName();

                // jsonNameを引数として渡すためにActionEventを作成
                ActionEvent customEvent = new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "initialize_a_result_pane " + jsonName + " " + sectionName + " " + subSectionName
                );
                actionMediator.getInstanceOfAMember("initialize_a_result_pane").perform(customEvent);
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
     * paneArray_DatasetName と paneArray_HC_N の要素の中から、
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

        //System.exit(1);

        return null;

    }
}
