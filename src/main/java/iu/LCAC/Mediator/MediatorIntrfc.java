package iu.LCAC.Mediator;

import iu.LCAC.Member.MemberIntrfc;

import java.util.Map;

public interface MediatorIntrfc {

    public abstract void createMembers();
    public abstract void requestFromMember();

    public abstract Map<String, MemberIntrfc> getMemberMap();

}
