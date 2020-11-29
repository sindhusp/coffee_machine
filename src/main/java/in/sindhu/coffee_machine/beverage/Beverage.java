package in.sindhu.coffee_machine.beverage;

import static in.sindhu.coffee_machine.util.Util.printWithStats;

import in.sindhu.coffee_machine.Constants;
import in.sindhu.coffee_machine.ingredients.IngredientManager;
import in.sindhu.coffee_machine.exceptions.BeverageException;
import in.sindhu.coffee_machine.exceptions.InsufficientIngredientException;
import java.util.Map;

public class Beverage {
  private final String name;
  private final Map<String, Integer> ingredients;

  public Beverage(
      String name,
      Map<String, Integer> ingredients) {
    this.name = name;
    this.ingredients = ingredients;
  }

  public void make(IngredientManager ingredientManager) throws BeverageException {
    try {
      ingredientManager.fetchIngredients(ingredients);
      // Beverage is made. Dispense beverage
      dispense();
    } catch (InsufficientIngredientException t) {
      throw new BeverageException(name, t);
    }
  }

  private void dispense() {
    printWithStats(String.format(Constants.BEVERAGE_PREPARED_MSG, name));
  }

  public String getName() {
    return name;
  }

}
