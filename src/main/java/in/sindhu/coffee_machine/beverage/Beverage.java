package in.sindhu.coffee_machine.beverage;

import in.sindhu.coffee_machine.IngredientManager;
import in.sindhu.coffee_machine.exceptions.BeverageException;
import in.sindhu.coffee_machine.exceptions.InsufficientIngredientException;
import java.util.Map;

public class Beverage {
  private final String name;
  private final Map<String, Integer> ingredients;
  private final IngredientManager ingredientManager;

  public Beverage(
      String name,
      Map<String, Integer> ingredients,
      IngredientManager ingredientManager) {
    this.name = name;
    this.ingredients = ingredients;
    this.ingredientManager = ingredientManager;
  }

  public void make() throws BeverageException {
    try {
      ingredientManager.fetchIngredients(ingredients);
      // beverage is made. Dispense beverage
      dispense();
    } catch (InsufficientIngredientException t) {
      throw new BeverageException(name, t);
    }
  }

  private void dispense() {
    System.out.println(String.format("%s is prepared. Enjoy :)\n", name));
  }
}
