package in.sindhu.coffee_machine.config;

import in.sindhu.coffee_machine.beverage.Beverage;
import java.util.List;
import java.util.Map;

// Note to reader: Defining my own config format since the problem statement said that test cases
// provided by me would be used to run the project. I can stick to the format provided in the example if
// there are existing test cases in that format that Dunzo would like to run.

public class CoffeeMachineConfig {

  private Integer outlets;
  private Map<String, Integer> totalIngredients;
  private List<Beverage> beverages;

  public Integer getOutlets() {
    return outlets;
  }

  public Map<String, Integer> getTotalIngredients() {
    return totalIngredients;
  }

  public List<Beverage> getBeverages() {
    return beverages;
  }
}
