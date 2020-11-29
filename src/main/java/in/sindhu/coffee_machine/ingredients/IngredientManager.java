package in.sindhu.coffee_machine.ingredients;

import in.sindhu.coffee_machine.exceptions.InsufficientIngredientException;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

//Singleton
public class IngredientManager {
  private final ConcurrentHashMap<String, Integer> ingredientStore;

  public IngredientManager(Map<String, Integer> ingredients) {
    this.ingredientStore = new ConcurrentHashMap<>(ingredients);
  }

  public synchronized void fetchIngredients(Map<String, Integer> requestedIngredients)
      throws InsufficientIngredientException {
    for (Map.Entry<String, Integer> requested : requestedIngredients.entrySet()) {
      checkIngredientAvailability(requested.getKey(), requested.getValue());
    }

    // All the requested ingredientStore are present if the code reaches here. Lets recalibrate the
    // store and take these ingredients to make our beverage.
    requestedIngredients.forEach((key, value) -> {
      Integer present = ingredientStore.get(key);
      ingredientStore.put(key, present - value);
    });
  }

  /**
   * @param name - name of the requested ingredient
   * @param requestedAmount - the amount of ingredient requested
   * @throws InsufficientIngredientException - thrown if the requested ingredient is not present,
   * or not adequate.
   *
   * Skipped the keyword synchronized here because:
   * 1. This is a private method and it is only being accessed by fetchIngredients(), that method is synchronized.
   * 2. This method only does reads on a concurrent hash map, an object that doesn't need synchronization.
   */

  private void checkIngredientAvailability(String name, Integer requestedAmount)
      throws InsufficientIngredientException {
    // If the ingredient is not available, throw an InsufficientIngredientException
    if (ingredientStore == null ||
        ingredientStore.isEmpty() ||
        !ingredientStore.containsKey(name) ||
        ingredientStore.get(name) == null) {
      throw new InsufficientIngredientException(name, requestedAmount);
    }

    Integer availableAmount = ingredientStore.get(name);
    if (availableAmount < requestedAmount) {
      throw new InsufficientIngredientException(name, requestedAmount, availableAmount);
    }
    // the ingredient is present if the code reaches here. return
  }

  //this method is package private so that Beverages can't access this method.
  ConcurrentHashMap<String, Integer> getIngredientStore() {
    return ingredientStore;
  }

  public void refillIngredient(String name, Integer amountBeingAdded) {
    ingredientStore.put(name, ingredientStore.get(name) + amountBeingAdded);
  }

  //Triggered by IngredientMonitor.
  void processRefilling() {
    Scanner in = new Scanner(System.in);
    String s = in.nextLine();
    if ("Y".equalsIgnoreCase(s)) {
      System.out.println(IngredientMonitor.ENTER_INGREDIENT_NAME_MSG);
    }
    String ingName = in.nextLine();
    System.out.println(String.format(IngredientMonitor.YOU_ARE_REFILLING_MSG, ingName));

    Long amountToAdd = in.nextLong();
    //TODO: Add validation to the amount here

    refillIngredient(ingName, amountToAdd != null ? amountToAdd.intValue() : 0);
    System.out.println(String.format(IngredientMonitor.INGREDIENT_IS_REFILLED_MSG, ingName, getIngredientStore().get(ingName)));
  }
}
