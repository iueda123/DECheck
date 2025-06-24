package iu.LCAC.Framework.componentholder.member;

import iu.LCAC.Framework.componentholder.mediator.CHolderMediatorIntrfc;

public interface CHolderMemberIntrfc {

    public abstract void setCHMediator(CHolderMediatorIntrfc CHolderMediatorIntrfc);

    public abstract void initialize();

    public abstract void doWorkAsCHMember();

    public abstract String getMemberName();
}
