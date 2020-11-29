package in.sindhu.coffee_machine;

public class Constants {
  public static final String BEVERAGE_PREPARED_MSG = "%s is prepared.\n";
  public static final String BEVERAGE_COULDNT_BE_PREPPED_MSG =
      "%s could not be prepared because ingredient %s is not available. "
              + " Requested %s amount: %d, Available %s amount: %d \n";
  public static final String INSUFFICIENT_INGREDIENT_MSG =
      "Insufficient ingredient %s. Needed %d units but only %d units were available.\n";
  public static final String WELCOME_MSG = "\n\n========================== Starting Coffee Machine ========================== \n";
}
