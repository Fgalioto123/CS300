/*
 * Author: YOUR NAME HERE.
 * Email: YOUR EMAIL HERE.
 * Course: CS300, F26
 * Assignment: Exceptions Exercise
 * Citations: None.
 */

import java.util.Scanner;

public class Starter {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // TODO we should close this when we're done!

    // TODO:
    // If a non-integer age OR an integer outside of the
    // bounds [1, 100] is entered for p1 or p2, print a message
    // saying the age is invalid and that we cannot continue.
    //
    // Otherwise, compare their ages.
    // If p1 is older, print "P1_NAME is older."
    // If p2 is older, print "P2_NAME is older."
    // If p1 is the same age as p2, print "P1_NAME and P2_NAME are the same age!"
    Person p1 = readPersonAndThrow(scanner);
    Person p2 = readPersonAndThrow(scanner);

    printOlder(p1, p2);
    System.out.println("Thank you!");
  }

  public static void printOlder(Person p1, Person p2) {
    System.out.println(p1.getAge());
    if (p1.getAge() == null || p2.getAge() == null || p1.getAge() < 1 || p1.getAge() > 100 || p2.getAge() < 1
        || p2.getAge() > 100) {
      System.out.println("the age is either too young or too old or not a number");
    } else {
      if (p1.getAge() > p2.getAge()) {
        System.out.println(p1.getName() + " is older.");
      } else if (p2.getAge() > p1.getAge()) {
        System.out.println(p2.getName() + " is older.");
      } else {
        System.out.println(p1.getName() + " and " + p2.getName() + " are the same age!");
      }
    }

  }

  /**
   * Reads a person and throws an exception if an invalid age is entered.
   * 
   * @param scanner scanner object to read from
   * @return Person with name and age read in
   * @throws NumberFormatException when age is not a int given to the scanner
   */
  public static Person readPersonAndThrow(Scanner scanner) {

    System.out.println("What is your name?");
    String name = scanner.nextLine();

    System.out.println("What is your age?");
    String ageStr = scanner.nextLine(); // yes, there is nextInt, but let's pretend not!
    Integer age;
    try {
      age = Integer.parseInt(ageStr);
    } catch (NumberFormatException e) {
      age = null;
    }
    // TODO should add throws to signature, not technically required as it is
    // unchecked.

    return new Person(name, age);
  }

  /**
   * Reads a person and defaults age to null if an invalid age is entered.
   * 
   * @param scanner scanner object to read from
   * @return Person with name and age read in (else default)
   */
  public static Person readPersonAndHandle(Scanner scanner) {

    // TODO Do this one yourself!

    return new Person("Bucky", null);
  }

}
