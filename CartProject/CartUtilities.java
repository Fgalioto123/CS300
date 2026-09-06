package CartProject;
// TODO: file header
// TODO: class javadoc comment
public class CartUtilities {                  
// TODO: method javadoc comment
  public static int indexOfItem(String[][] cart, int cartSize, String description) {
    for(int i = 0; i < cartSize; i++) {
      if(cart[i][0].equals(description)) {
        return i;
      }
    }

    return -1;
  }
  
  // TODO: method javadoc comment
  public static int addItemToCart(String[][] cart, int cartSize, String
      description) {

    int index = indexOfItem(cart, cartSize, description);

    if(index != -1) {
      String amount = cart[index][1];
      int intAmount = Integer.parseInt(amount) + 1;
      cart[index][1] = String.valueOf(intAmount);
    } else if(cart[cart.length - 1][0].equals("null")) {
      cart[cartSize - 1][0] = description;
      cart[cartSize - 1][1] = "1";
      cartSize++;
    }
    
    return cartSize;
  }
  // TODO: method javadoc comment
  public static int removeItemFromCart(String[][] cart, int cartSize, int index) {
    for(int i = index; i < cartSize; i++) {
      if(i == cart.length - 1) {
        cart[i][0] = "null";
        cart[i][1] = "null";
      } else {
        cart[i][0] = cart[i+1][0];
        cart[i][1] = cart[i+1][1];
      }
    }

    if(index < cartSize) {
      cartSize--;
    }

    return cartSize;
  }
  // TODO: method javadoc comment
  public static int getCostOfItem(String[] inventory, int[] costs, String
      description) {
    
    int cost = -1;
    for(int i = 0; i < inventory.length; i++) {
      if(inventory[i].equals(description)) {
        cost = costs[i];
        return cost;
      }
    }   

    return cost;
  }
  // TODO: method javadoc comment
  public static int getTotalCost(String[][] cart, int cartSize, String[] inventory,
      int[] costs) {

    int totalCost = 0;
    for(String[] arr : cart) {
      int cost = getCostOfItem(inventory, costs, arr[0]);
      totalCost += (cost * Integer.parseInt(arr[1]));
    }

    return totalCost;
  }
}
