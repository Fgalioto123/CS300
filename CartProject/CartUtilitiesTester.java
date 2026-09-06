package CartProject;
/**
 * Contains testing methods for each method in CartUtilities.
 * Each testing method returns true when all its test cases pass, otherwise false.
 */
public class CartUtilitiesTester {

  // no need for testing method header comments as these are self-explanatory.
  public static boolean testIndexOfItem() {
    // TODO: test a "normal" case for this method
    String[][] cart = {{"bread", "1"}, {"milk", "2"}, {"null", "null"}};
    int cartSize = 2;
    if(CartUtilities.indexOfItem(cart, cartSize, "bread") == -1) {
      return false;
    }

    // TODO: test a case where the method should behave differently (an "edge" case)
    return true;
  }

  public static boolean testAddItemToCart() {
    // TODO: test a case where the item is not already present AND there is room to add it

    // TODO: test a case where the item IS already present
    return false;
  }

  public static boolean testRemoveItemFromCart() {
    // TODO: test a "normal" case for this method

    // TODO: test a case where the method should behave differently (an "edge" case)
    return false;
  }

  public static boolean testGetCostOfItem() {
    // TODO: test a "normal" case for this method

    // TODO: test a case where the method should behave differently (an "edge" case)
    return false;
  }

  public static boolean testGetTotalCost() {
    // TODO: test a case where all items are present in inventory and have quantity 1

    // TODO: test a case where at least one item is not present

    // TODO: test a case where at least one item has quantity > 1
    return false;
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