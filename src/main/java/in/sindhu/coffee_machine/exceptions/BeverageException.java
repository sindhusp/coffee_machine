package in.sindhu.coffee_machine.exceptions;

public class BeverageException extends Exception {
  private static final String BEVERAGE_COULDNT_BE_PREPPED_MSG =
      "%s could not be prepared because ingredient %s is not available. "
              + "\nRequested %s amount: %d \nAvailable %s amount: %d \n";

  public BeverageException(String beverage, InsufficientIngredientException t) {
    super(getMsg(beverage, t), t);
  }

  private static String getMsg(String beverage, InsufficientIngredientException t) {
    return String.format(
        BEVERAGE_COULDNT_BE_PREPPED_MSG,
        beverage,
        t.getIngredientName(),
        t.getIngredientName(),
        t.getRequestedAmount(),
        t.getIngredientName(),
        t.getAvailableAmount());
  }
}
