package CartProject;


/**
 * Contains testing methods for each method in CartUtilities.
 * Each testing method returns true when all its test cases pass, otherwise false.
 */
public class CartUtilitiesTester {

  // no need for testing method header comments as these are self-explanatory.
  public static boolean testIndexOfItem() {
    String[][] cart = {{"bread", "1"}, {"milk", "2"}, {"null", "null"}};
    int cartSize = 2;
    
    //this test case tests if the methods runs correctly with bread being the description and if it doesn't return false.
    if(CartUtilities.indexOfItem(cart, cartSize, "bread") == -1) {
      return false;
    }
    /*the edge case is if they mistype the description of the word and if it ever returns a valid index then return false because it should always return -1.(I am not sure if this is considered an edge case and if this is wrong and I am still struggling with them on the next program I will most likely come in for office hours next week.)
    */
    if(CartUtilities.indexOfItem(cart, cartSize, "beed") != -1) {
      return false;
    }

    return true;
  }

  public static boolean testAddItemToCart() {
    String[][] cart = {{"bread", "1"}, {"milk", "2"}, {"null", "null"}};
    int cartSize = 2;

    //checks to see that the cartsize changed and if it didn't return false
    if(CartUtilities.addItemToCart(cart, cartSize, "cheese") == cartSize) {
      return false;
    }
    
    //checks to see if the cartsize did change this time when the item was already in there then return false
    if(CartUtilities.addItemToCart(cart, cartSize, "cheese") == cartSize + 1) {
      return false;
    }

    return true;
  }

  public static boolean testRemoveItemFromCart() {
    String[][] cart = {{"bread", "1"}, {"milk", "2"}, {"cheese", "3"}, {"null", "null"}};
    int cartSize = 3;

    //checks that cartsize is not two and if it is not two then return false because if it is not two then that means that cartsize did not go down and the item was not removed.
    if(CartUtilities.removeItemFromCart(cart, cartSize, 1) != cartSize - 1) {
      return false;
    }

    //checks to see if it removes a null and counts that as the cartsize shrinking which it should not because you should not be removing null because it is not an item.
    if(CartUtilities.removeItemFromCart(cart, cartSize, 3) != cartSize) {
      return false;
    }
    
    return true;
  }

  public static boolean testGetCostOfItem() {
    String[][] cart = {{"bread", "1"}, {"milk", "2"}, {"cheese", "3"}, {"null", "null"}};
    String[] inventory = {"bread", "milk", "cheese"};
    int[] costs = {5, 3, 4};
    int cartSize = 3;

    //makes sure that when you get the cost of cheese it is four and if it is not then return false because the price of cheese is four.
    if(CartUtilities.getCostOfItem(inventory, costs, "cheese") != 4) {
      return false;
    }

    /*edge case where the user could ender a capital C instead of a lowercase one and if the method returns four then return false because it should never return four because it is not the correct item.
    */
    if(CartUtilities.getCostOfItem(inventory, costs, "Cheese") == 4) {
      return false;
    }
    
    return true;
  }

  public static boolean testGetTotalCost() {
    String[][] cart = {{"bread", "1"}, {"milk", "1"}, {"cheese", "1"}, {"null", "null"}};
    int cartSize = 3;
    String[] inventory = {"bread", "milk", "cheese"};
    int[] costs = {5, 3, 4};
    
    //checks to see if the total costs of all the items is 12 and if it is not then return false because it should be 12.
    if(CartUtilities.getTotalCost(cart, cartSize, inventory, costs) != 12) {
      return false;
    }

    String[] inventoryTwo = {"bread", "milk"};
    int[] costsTwo = {5, 3};

    //now does the same method but this time the inventory doesn't have cheese so it makes sure the answer is eight and if it is not return false because the two items add up to eight.
    if(CartUtilities.getTotalCost(cart, cartSize, inventoryTwo, costsTwo) != 8) {
      return false;
    }
   
    String[][] cartTwo = {{"bread", "4"}, {"milk", "1"}, {"cheese", "1"}, {"null", "null"}};

    /*this one uses a different cart where there is four bread so it checks to make sure the method can calculate multiple of an item. Checks to make sure it returns 27 cause that is the total cost and if it doesn't then return false.
    */
    if(CartUtilities.getTotalCost(cartTwo, cartSize, inventory, costs) != 27) {
      return false;
    }
    
    return true;
  }

  public static void main(String[] args) {
    System.out.println("=== CART UTILITIES TESTER ===");

    boolean allPass = true, testPass = true;

    System.out.println("testIndexOfItem():");
    testPass = testIndexOfItem();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));
    
    allPass &= testPass;

    System.out.println("testAddItemToCart():");
    testPass = testAddItemToCart();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testRemoveItemFromCart():");
    testPass = testRemoveItemFromCart();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testGetCostOfItem():");
    testPass = testGetCostOfItem();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testGetTotalCost():");
    testPass = testGetTotalCost();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    if (allPass) {
      System.out.println("\nCONGRATULATIONS! All of your tests passed.");
    }
  }

}