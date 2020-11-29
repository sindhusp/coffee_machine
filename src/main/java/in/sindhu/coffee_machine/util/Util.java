package in.sindhu.coffee_machine.util;

import in.sindhu.coffee_machine.Constants;

public class Util {
  public static void printWelcome() {
    System.out.println(Constants.WELCOME_MSG);
  }

  public static void printWithStats(String msg) {
    System.out.print(getTimeStampAndOutlet() + msg);
  }

  private static String getTimeStampAndOutlet() {
    return String.format("At timestamp %d, In outlet %s :\t",
        System.currentTimeMillis(),
        Thread.currentThread().getName());
  }
}
