package iu.LCAC.Mediator.componentholder;

public class CHolderMediatorFactory {

  private static CHolderMediator cholderMediator;

  public static CHolderMediator create() {
    if (cholderMediator == null) {
      cholderMediator = new CHolderMediator();
    }
    return cholderMediator;
  }
}
