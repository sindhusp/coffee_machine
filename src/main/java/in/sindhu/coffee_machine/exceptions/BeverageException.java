package in.sindhu.coffee_machine.exceptions;

import in.sindhu.coffee_machine.Constants;

public class BeverageException extends Exception {

  public BeverageException(String beverage, InsufficientIngredientException t) {
    super(getMsg(beverage, t), t);
  }

  private static String getMsg(String beverage, InsufficientIngredientException t) {
    return String.format(
        Constants.BEVERAGE_COULDNT_BE_PREPPED_MSG,
        beverage,
        t.getIngredientName(),
        t.getIngredientName(),
        t.getRequestedAmount(),
        t.getIngredientName(),
        t.getAvailableAmount());
  }
}
