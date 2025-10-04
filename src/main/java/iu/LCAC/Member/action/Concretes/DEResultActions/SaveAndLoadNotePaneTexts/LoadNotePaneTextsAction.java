package iu.LCAC.Member.action.Concretes.DEResultActions.SaveAndLoadNotePaneTexts;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.NotePane;
import iu.LCAC.Member.componentholder.Concretes.DEResult.Common.SubTabsHolderItrfc;
import iu.LCAC.Member.componentholder.Concretes.DEResult.NM.NM_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEResult.RCAI.RCAI_SubTabsHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class LoadNotePaneTextsAction extends AbstActionMember {

    String prop_file_path_str = "";

    public LoadNotePaneTextsAction(String action_name, String short_name) {
        super(action_name, short_name);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(
                        KeyStroke.getKeyStroke(
                                KeyEvent.VK_L,
                                InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("");
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        // TODO: 全セクションに拡張
        loadNotePaneTexts("RCAI", "./settings/NotePane/" + "reference_cohort_and_imaging" + ".prop");
        loadNotePaneTexts("NM", "./settings/NotePane/" + "normative_modeling" + ".prop");


    }

    /**
     *  REFERENCE COHORT AND IMAGING
     */
    private void loadNotePaneTexts(String member_name_key_word, String prop_file_path_str) {

        AbstCHolderMember member = null;
        SubTabsHolderItrfc subTabsHolder = null;
        switch (member_name_key_word) {
            case "RCAI":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_RCAI");
                subTabsHolder = (RCAI_SubTabsHolder) member;
                break;
            case "NM":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_NM");
                subTabsHolder = (NM_SubTabsHolder) member;
                break;
            default:
                System.out.println("未知のSection指定です");
        }

        String sectionName = subTabsHolder.getSectionName();
        System.out.println("----- Load texts on NotePanes of '" + sectionName + "' section -----");

        propManager = createPropertyManager(prop_file_path_str);
        //System.out.println("Properties file '" + deresultpane_order_setting_file_path_str + "' was loaded.");
        //propManager.listUpProperty();

        String loaded_text = "";
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane()) {
             String subSectionName = managerOfSubTabBasePane.getSubSectionName();
            //System.out.println("Now registering NotePane of  '" + subSectionName + "'");
            NotePane notePane = managerOfSubTabBasePane.getNotePane();

            for (String property_name : propManager.stringPropertyNames()) {
                loaded_text =  propManager.getValueOrCreateNew(property_name);
                if( property_name.equals( subSectionName + "." + "status" ) ){
                    notePane.setStatusText(loaded_text);
                }else if( property_name.equals( subSectionName + "." + "note")){
                    notePane.setNoteText(loaded_text);
                }
            }
            notePane.resetBackgroundColors();
            notePane.updateDefaultValues();
            notePane.updateTabTitle();
        }
        propManager = null; //Propの外部更新時のため（毎回新しいPropを呼ぶため）dispose
    }




    @Override
    public void setCHolderMediator(CHolderMediator cHolderMediator) {
        this.cholderMediator = cHolderMediator;
    }

    @Override
    public void setActionMediator(ActionMediator actionMediator) {
        super.actionMediator = actionMediator;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void doWorkAsMember() {
    }

}
