package iu.LCAC.Framework.Member;

import iu.LCAC.Framework.Mediator.action.ActionMediator;
import iu.LCAC.Framework.Mediator.componentholder.CHolderMediator;

public interface MemberIntrfc {

    public abstract void setCHolderMediator(CHolderMediator cHolderMediator);
    public abstract void setActionMediator(ActionMediator actionMediator);

    public abstract void initialize();

    public abstract void doWorkAsMember();

    public abstract String getMemberName();
}
