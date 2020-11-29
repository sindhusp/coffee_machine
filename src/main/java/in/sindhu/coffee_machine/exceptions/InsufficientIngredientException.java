package in.sindhu.coffee_machine.exceptions;

import in.sindhu.coffee_machine.Constants;

public class InsufficientIngredientException extends Exception {

  private String ingredientName;
  private Integer requestedAmount;
  private Integer availableAmount;

  public InsufficientIngredientException(
      String ingredientName,
      Integer requestedAmount) {
    this(ingredientName, requestedAmount, 0);
  }

  public InsufficientIngredientException(
      String ingredientName,
      Integer requestedAmount,
      Integer availableAmount) {
    super(generateError(ingredientName, requestedAmount, availableAmount));
    this.ingredientName = ingredientName;
    this.requestedAmount = requestedAmount;
    this.availableAmount = availableAmount;
  }

  private static String generateError(
      String ingredientName,
      Integer requestedAmount,
      Integer availableAmount) {
    return String.format(
        Constants.INSUFFICIENT_INGREDIENT_MSG, ingredientName, requestedAmount, availableAmount);
  }

  public String getIngredientName() {
    return ingredientName;
  }

  public Integer getRequestedAmount() {
    return requestedAmount;
  }

  public Integer getAvailableAmount() {
    return availableAmount;
  }
}
