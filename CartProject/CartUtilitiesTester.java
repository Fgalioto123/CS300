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

    if(CartUtilities.indexOfItem(cart, cartSize, "bread") == -1) {
      return false;
    }

    if(CartUtilities.indexOfItem(cart, cartSize, "beed") != -1) {
      return false;
    }

    return true;
  }

  public static boolean testAddItemToCart() {
    String[][] cart = {{"bread", "1"}, {"milk", "2"}, {"null", "null"}};
    int cartSize = 2;
    if(CartUtilities.addItemToCart(cart, cartSize, "cheese") == cartSize) {
      return false;
    }
    
    if(CartUtilities.addItemToCart(cart, cartSize, "cheese") == cartSize + 1) {
      return false;
    }

    return true;
  }

  public static boolean testRemoveItemFromCart() {
    String[][] cart = {{"bread", "1"}, {"milk", "2"}, {"cheese", "3"}, {"null", "null"}};
    int cartSize = 3;
    if(CartUtilities.removeItemFromCart(cart, cartSize, 1) != 2) {
      return false;
    }

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

    if(CartUtilities.getCostOfItem(inventory, costs, "cheese") != 4) {
      return false;
    }

    // if(CartUtilities.getCostOfItem(inventory, costs, "Cheese") == 4) {
    //   return false;
    // }
    // TODO: test a case where the method should behave differently (an "edge" case)
    return true;
  }

  public static boolean testGetTotalCost() {
    String[][] cart = {{"bread", "1"}, {"milk", "1"}, {"cheese", "1"}, {"null", "null"}};
    int cartSize = 3;
    String[] inventory = {"bread", "milk", "cheese"};
    int[] costs = {5, 3, 4};
    
    if(CartUtilities.getTotalCost(cart, cartSize, inventory, costs) != 12) {
      return false;
    }

    String[] inventoryTwo = {"bread", "milk"};
    int[] costsTwo = {5, 3};
    
    if(CartUtilities.getTotalCost(cart, cartSize, inventoryTwo, costsTwo) != 8) {
      return false;
    }
    // TODO: test a case where at least one item is not present

    // TODO: test a case where at least one item has quantity > 1
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