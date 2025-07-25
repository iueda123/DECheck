package iu.LCAC.Mediator.action;

public class ActionMediatorFactory {

  private static ActionMediator actionMediator;

  public static ActionMediator create() {
    if (actionMediator == null) {
      actionMediator = new ActionMediator();
    }
    return actionMediator;
  }
}
