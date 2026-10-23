public class doubling {
  public static void main(String[] args) {
    System.out.println(doublin(2));
  }

  public static int doublin(int n) {
    if (n == 1) {
      return 1;
    }
    return 2 * doublin(n - 1);
  }
}
