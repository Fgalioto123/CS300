package lectureRecursion;

public class Fib {
  public static void main(String[] args) {
    System.out.println(fibSequenceRecursion(4));
  }

  public static int fibSequenceRecursion(int n) {
    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    }
    return fibSequenceRecursion(n - 1) + fibSequenceRecursion(n - 2);
  }
}
