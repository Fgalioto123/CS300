package CartProject;
/*
 * Author: [Fox Galioto]
 * Email: [fgalioto@wisc.edu]
 * Course: [CS300], [Fall term 2026]
 * Assignment: [Program 01]
 * Citations: [Nathan and Samuel who helped explain how edge cases worked. Claude AI also helped explain edge cases althought it did not help too much.]
 */

/**
 * This class is used with a cart array to add, remove, find the location of, and get the cost of an item. It is also used to find the total cost of all items. It could be used by an online shopping company.
 */
public class CartUtilities {                  
  /**
   * This method finds the index of an item in the cart
   * @param cart - It expects a cart array which is a nested String array
   * @param cartSize - It expects a size of valid items in the cart which is a integer
   * @param description - it also expects the name of the item it is looking for in the cart
   * @return it returns the index of the item or -1 if it cant find it
   * @throws 
   */
  public static int indexOfItem(String[][] cart, int cartSize, String description) {
    //a for loop that goes through the array 
    for(int i = 0; i < cartSize; i++) {
      //checks if the name of the item is the same as the description. If it is that means that the items are the same and that i is the index of the item so it returns it.
      if(cart[i][0].equals(description)) {
        return i;
      }
    }

    return -1;
  }
  
  /**
   * this method takes an item name and adds it to the cart by either creating a new item in the cart of adding one to the amount of a previous item
   * @param cart - expects the cart array to be a nested String array
   * @param cartSize - expects the size of valid items in the cart
   * @param description - expects the name of the item wanted to add
   * @return it returns the cartsize which could be larger or the same depending on if the item is new or not
   * @throws
   */
  public static int addItemToCart(String[][] cart, int cartSize, String
      description) {
    //gets the index of the item in the array to see if it exists or not
    int index = indexOfItem(cart, cartSize, description);

    //if it does exist then it uses the index to find it and add one to the value
    if(index != -1) {
      String amount = cart[index][1];
      int intAmount = Integer.parseInt(amount) + 1;
      cart[index][1] = String.valueOf(intAmount);
    //if it does not exists then it checks to make sure there is space and if there is it adds the item to the end
    } else if(cart[cart.length - 1][0].equals("null")) {
      cart[cartSize][0] = description;
      cart[cartSize][1] = "1";
      cartSize++;
    }
    
    return cartSize;
  }
  /**
   * this method removes an item from the cart if the index given is valid and shifts the other items down if the index was somewhere before the last valid item
   * @param cart - expects the cart array which is a nested string array
   * @param cartSize - expects the size of the valid cart items
   * @param index - expects the index of the item wanting to be removed
   * @return returns the the cartsize which is either lower or the same depending if the index was valid or not
   * @throws
   */
  public static int removeItemFromCart(String[][] cart, int cartSize, int index) {
    //it starts at the index given and makes sure it is less then cartsize because if it is not then that would mean the index given is not a real item but a null
    for(int i = index; i < cartSize; i++) {
      //if the index is the last item then just turn it to null because there is nothing after it to move back in the array
      if(i == cartSize - 1) {
        cart[i][0] = "null";
        cart[i][1] = "null";
      //if there are valid items in front then it would continue the loop and keep settings the ones infront of it to the previous one so there is no gap in the cart array
      } else {
        cart[i][0] = cart[i+1][0];
        cart[i][1] = cart[i+1][1];
      }
    }

    //checking to see if it removed anything and if it did minus cartsize by one
    if(index < cartSize) {
      cartSize--;
    }

    return cartSize;
  }
  /**
   * this method gets the cost of an item if it is present in the inventory
   * @param inventory - expects a valid inventory string array to work
   * @param costs - expects a valid costs integer array and it to align to the inventory array
   * @param description - expects the name of the item it is finding the cost for
   * @return it returns the cost of the item if it is found and if not then returns 0
   * @throws
   */
  public static int getCostOfItem(String[] inventory, int[] costs, String
      description) {
    
    int cost = 0;
    //goes through the inventory until it finds the item and when it does it uses that i position to find the cost in costs and then returns cost.
    for(int i = 0; i < inventory.length; i++) {
      if(inventory[i].equals(description)) {
        cost = costs[i];
        return cost;
      }
    }   

    return cost;
  }
  /**
   * this methods gets the total cost of all the items in the cart and takes account if there are multiples of an item
   * @param cart - expects a valid cart array
   * @param cartSize - expects the size of the cart array of valid items
   * @param inventory - expects the inventroy of the items if the inventory has any items
   * @param costs - expects the costs to align up with the inventory
   * @return
   */
  public static int getTotalCost(String[][] cart, int cartSize, String[] inventory,
      int[] costs) {
    //sets the totalcost initially to 0
    int totalCost = 0;
    //goes through each valid item of the cart and sets the cost using the previous method. It then adds the cost multiplied by the amount (which is found using Integer.parse int) to total costs
    for(int i = 0; i < cartSize; i++) {
      int cost = getCostOfItem(inventory, costs, cart[i][0]);
      totalCost += (cost * Integer.parseInt(cart[i][1]));
    }

    return totalCost;
  }
}
