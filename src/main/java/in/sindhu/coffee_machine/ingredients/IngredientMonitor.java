package in.sindhu.coffee_machine.ingredients;

import static in.sindhu.coffee_machine.Constants.INGREDIENT_S_IS_RUNNING_LOW_PLZ_REFILL;
import static in.sindhu.coffee_machine.Constants.LOAD_STORE;

import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IngredientMonitor {

  // Ingredient monitor will send alerts if any ingredient is below the defined threshold
  public static final int INGREDIENT_THRESHOLD = 5;
  public static final String INGREDIENT_IS_REFILLED_MSG = "Ingredient %s is refilled. Available amount %d";
  public static final String ENTER_INGREDIENT_NAME_MSG = "You chose to refill an ingredient. Please enter ingredientName";
  public static final String YOU_ARE_REFILLING_MSG = "You are refilling %s. Please enter the number of units to add.";
  private final IngredientManager ingredientManager;

  public IngredientMonitor(IngredientManager ingredientManager) {
    this.ingredientManager = ingredientManager;
    monitor();
  }

  // can be enhanced to trigger when an ingredient amount is edited
  private void monitor() {
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

    // Ingredient monitor is scheduled to check every 5 seconds whether any ingredient is running low
    scheduler.scheduleAtFixedRate(monitorIngredientStore(), 1, 5, TimeUnit.SECONDS);
  }

  private Runnable monitorIngredientStore() {
    return () -> {
      if (ingredientManager.getIngredientStore() == null || ingredientManager.getIngredientStore().isEmpty()) {
        printLoadStore();
        return;
      }
      ingredientManager.getIngredientStore().entrySet()
          .stream()
          .filter(ingredient -> ingredient.getValue() <= INGREDIENT_THRESHOLD)
          .forEach(this::printAlert);
    };
  }

  private void printLoadStore() {
    System.out.println(LOAD_STORE);
  }

  private void printAlert(Entry<String, Integer> lowRunningIngredient) {
    System.out.println(String.format(INGREDIENT_S_IS_RUNNING_LOW_PLZ_REFILL, lowRunningIngredient.getKey()));
    ingredientManager.processRefilling();
  }

}
