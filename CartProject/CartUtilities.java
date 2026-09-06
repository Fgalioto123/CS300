package CartProject;
// TODO: file header
// TODO: class javadoc comment
public class CartUtilities {
  public static void main(String[] args) {
    
  }                   
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
    }
    
    return cartSize;
  }
  // TODO: method javadoc comment
  public static int removeItemFromCart(String[][] cart, int cartSize, int index) {
    return -1;
  }
  // TODO: method javadoc comment
  public static int getCostOfItem(String[] inventory, int[] costs, String
      description) {
    return -1;
  }
  // TODO: method javadoc comment
  public static int getTotalCost(String[][] cart, int cartSize, String[] inventory,
      int[] costs) {
    return -1;
  }
}
