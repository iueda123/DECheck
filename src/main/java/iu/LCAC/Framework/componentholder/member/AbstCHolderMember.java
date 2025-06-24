package iu.LCAC.Framework.componentholder.member;

import iu.LCAC.Framework.action.mediator.ActionMediator;
import iu.LCAC.Framework.componentholder.mediator.CHolderMediator;
import iu.LCAC.Framework.action.member.ActionMemberIntrfc;
import iu.LCAC.Tools.PropertyManager_v5;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstCHolderMember implements CHolderMemberIntrfc, ActionMemberIntrfc {

    protected ActionMediator actionMediator;

    protected CHolderMediator cholderMediator;

    protected String componentName;
    protected String shortName;

    public AbstCHolderMember(String componentName, String shortName) {
        this.componentName = componentName;
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }


    final public void setName(String componentName){
        this.componentName = componentName;
    };

    final public String getName(){
        return this.componentName;
    }

    public abstract JComponent getBaseComponent();

    /**
     * Post initialization after the component is displayed
     */
    public abstract void postInitialize();


    public PropertyManager_v5 propManager;

    protected PropertyManager_v5 createPropertyManager(String property_file_path) {
        System.out.println("A property file '" + property_file_path + "' is about to load.");
        Path setting_file_path = Paths.get(property_file_path);

        if (propManager == null) {
            propManager = new PropertyManager_v5(new File(property_file_path));
        }

        System.out.println("    Properties file '" + setting_file_path.getFileName() + "' was loaded.");

        //List up
        propManager.listUpProperty();

        return propManager;
    }


}
