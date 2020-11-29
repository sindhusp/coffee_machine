package in.sindhu.coffee_machine;

import static java.lang.Thread.sleep;

import java.io.IOException;

public class CoffeeMachineRunner {

  public static void main(String[] args) {
    try {
      CoffeeMachine.run("config/sample.json");
      sleep(1000);
      CoffeeMachine.run("config/test1.json");
      sleep(1000);
      CoffeeMachine.run("config/test2.json");
      sleep(1000);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
