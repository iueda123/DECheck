package iu.LCAC.Framework.Member.componentholder;

import iu.LCAC.Framework.Mediator.action.ActionMediator;
import iu.LCAC.Framework.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Framework.Member.MemberIntrfc;
import iu.LCAC.Tools.PropertyManager_v5;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstCHolderMember implements MemberIntrfc {

    protected ActionMediator actionMediator;

    protected CHolderMediator cholderMediator;

    protected String cholderName;
    protected String shortName;

    public AbstCHolderMember(String cholderName, String shortName) {
        this.cholderName = cholderName;
        this.shortName = shortName;
    }

    final public String getShortName() {
        return shortName;
    }

    final public String getMemberName(){
        return this.cholderName;
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
