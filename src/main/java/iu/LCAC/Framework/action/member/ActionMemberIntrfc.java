package iu.LCAC.Framework.action.member;

import iu.LCAC.Framework.action.mediator.ActionMediatorIntrfc;

public interface ActionMemberIntrfc {

    public abstract void setActnMediator(ActionMediatorIntrfc actionMediatorIntrfc);
    public abstract void doWorkAsActnMember();

    public abstract void initialize();

    public abstract String getMemberName();
}
