package iu.LCAC.CoreActions.SetAcceleratorOnNextClick;


import iu.LCAC.Framework.Mediator.action.ActionMediator;

public class AcceleratorSetupWindowFactory  {

    static AcceleratorSetupWindow ARSWindow;

    public static AcceleratorSetupWindow create(ActionMediator controller){
        if(ARSWindow == null){
            ARSWindow = new AcceleratorSetupWindow(controller);
        }
        return ARSWindow;
    }


}
