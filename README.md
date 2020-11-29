# CoffeeMachine

Hi! This project describes a working **CoffeeMachine** that adheres to the following design spec. 

1. It supports multiple beverage outlets that can parallelly dispense beverages.
2. Each beverage has some ingredients with specified quantities. A beverage can only be brewed if all of its ingredients, in the required quantity, are present.
3. Time to prepare each beverage is assumed to be the same (and constant).
4. It supports an ingredient monitor which alerts the user that a particular ingredient is running low. One can choose to refill the missing (or any) ingredient at this time.

# Some Design Considerations

* Pricing and money management are out of scope for our coffee machine. 
* The problem statement specified an example format to manage the configuration of the coffee machine (you can find it [here](https://www.npoint.io/docs/e8cd5a9bbd1331de326a)). I have taken the liberty of modifying this format to a normalized one found [here](https://www.npoint.io/docs/32d9bb0c18571c90b485).  Our coffee machine will be using the latter format. 
* Typically, the coffee machine can support an admin mode and a user mode. In the admin mode, the admin of the machine can load up ingredients, program new beverage recipes, and schedule a maintenance for the machine. In the user mode, the user can request beverages. To cut down scope, I've **not implemented** these two different modes. 
* Because there's no differentiation between the user mode and the admin mode, there's also no differentiation between when we are programming a beverage into the coffee machine and when we are requesting that beverage. i.e, in a real world Coffee machine, one can just press the `Cappucino` button to request a `Cappucino`. In our machine, one has to specify the recipe / ingredients of the `Cappucino` while requesting it. 
* The output of this program contains the timestamp at the time of printing and outlet (thread) using which the beverage was processed. This is to showcase the concurrent processing of beverages.
* This project uses Google's Gson lib to help with serialization.

# Usage
## PreRequisites
* This project needs Java 8 to compile
* This project needs mvn to be installed. 

## To Run 
1. In the project dir, run `mvn clean package` to build the jar. 
2. Once done, cd to `target` folder. Run `java -jar coffee_machine-1.0-SNAPSHOT.jar` to run with the sample test case provided with the project. 
3. To run any other test case, edit the file `src/main/resources/config/sample.json`. 
4. Alternatively, you can add a test case json file to the `src/main/resources/config` directory and invoke the jar with the filename argument.  For example, to invoke the jar with file `src/main/resources/config/test2.json`, run `java -jar coffee_machine-1.0-SNAPSHOT.jar config/test2.json`


# Components
## CoffeeMachineConfig

This file is the input for the coffee machine. It configures the coffee machine, including the number of outlets of the coffee machine, ingredients present, and a list of beverages to brew along with their recipes/ingredients.  See example [here](https://www.npoint.io/docs/32d9bb0c18571c90b485)

## CoffeeMachine
This is the storefront side of our Coffee Machine project. This class takes in the config file's location and sets up the coffee machine. It sets up the various outlets, ingredient store, monitor and brews the beverages.

## Beverage
This class represents a Beverage. Each beverage knows what its name is, what its ingredients are and how to brew itself. 

## IngredientManager
This class manages and stores the ingredients present in the coffee machine. It offers thread-safe access for beverages to access its ingredients across outlets. It also has a refill method to refill low-running ingredients, which is triggered by the IngredientMonitor.

## IngredientMonitor

This class monitors periodically whether any ingredient is running low and sends alerts. It also invokes `IngredientManager`'s refill methods if the user chooses to refill an ingredient.

## CoffeeMachineRunner

This is a tester class that runs various CoffeeMachine configs on our CoffeeMachine so that we can see different test cases operating on it producing different outputs. 

# A word on testing

The problem statement for this project repeatedly specified that the ask is to write `functional integration test cases`. Writing functional tests without using tools like Selenium proved to be a challenge in the given timeframe of our project, since I didn't think spinning up a server just for Selenium integration was a great idea; it seemed like an overkill. Alternatively, I provided functional test cases using the `CoffeeMachineRunner` class which runs them one after the other. I hope this is acceptable. This is also why I skipped unit test cases using Junit. I could have written them, but considering that we have functional tests in this format, they didn't seem necessary. 
