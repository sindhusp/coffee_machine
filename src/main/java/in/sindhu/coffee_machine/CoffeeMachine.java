package in.sindhu.coffee_machine;

import static in.sindhu.coffee_machine.util.Util.printWelcome;

import com.google.gson.Gson;
import in.sindhu.coffee_machine.config.CoffeeMachineConfig;
import in.sindhu.coffee_machine.exceptions.BeverageException;
import in.sindhu.coffee_machine.ingredients.IngredientManager;
import in.sindhu.coffee_machine.ingredients.IngredientMonitor;
import in.sindhu.coffee_machine.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoffeeMachine {
  private static final Gson GSON = new Gson();

  public static void main(String[] args) {
    try {
      run(getConfigLocation(args));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String getConfigLocation(String[] args) throws IOException {
    if (args.length > 0) {
      return args[0];
    }
    return "config/sample.json";
  }

  static void run(String configLocation) throws IOException {
    printWelcome();
    CoffeeMachineConfig config = fetchMachineConfig(configLocation);
    IngredientManager ingredientManager = new IngredientManager(config.getTotalIngredients());
    IngredientMonitor ingredientMonitor = new IngredientMonitor(ingredientManager);
    ExecutorService executorService = Executors.newFixedThreadPool(config.getOutlets());

    config.getBeverages()
        .stream()
        .forEach(beverage -> executorService.submit(() -> {
          try {
            Util.printWithStats("Starting " + beverage.getName() + " prep\n");
            beverage.make(ingredientManager);
          } catch (BeverageException t) {
            Util.printWithStats(t.getMessage());
          }
        }));
  }

  private static CoffeeMachineConfig fetchMachineConfig(String fileName)
      throws IOException {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream in = classloader.getResourceAsStream(fileName);
    Reader reader = new InputStreamReader(in, "UTF-8");
    return GSON.fromJson(reader, CoffeeMachineConfig.class);
  }
}
