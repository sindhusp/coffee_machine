package in.sindhu.coffee_machine.beverage;

import java.util.Map;

public class Beverage {
  private final String name;
  private final Map<String, Integer> ingredients;

  public Beverage(String name, Map<String, Integer> ingredients) {
    this.name = name;
    this.ingredients = ingredients;
  }

  public Beverage make() {
    throw new RuntimeException("cant make beverage yet :)");
  }
}
