package iu.LCAC.CoreActions.SetAcceleratorOnNextClick;


import iu.LCAC.Framework.action.mediator.ActionMediator;

public class AcceleratorSetupWindowFactory  {

    static AcceleratorSetupWindow ARSWindow;

    public static AcceleratorSetupWindow create(ActionMediator controller){
        if(ARSWindow == null){
            ARSWindow = new AcceleratorSetupWindow(controller);
        }
        return ARSWindow;
    }


}
