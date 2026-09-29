/*
 * Author: Cole Nelson
 * Email: ctnelson2@wisc.edu
 * Course: CS300, F26
 * Assignment: Exceptions Exercise
 * Citations: None.
 */

import java.util.Scanner;

/**
 * Class that demonstrates how to handle exceptions that a method throws.
 */
public class Starter {

  public static void main(String[] args) {

    // TODO 3: Close with try-with-resources
    Scanner scanner = new Scanner(System.in); // don't forget to close this!

    // TODO 1a: Handle any exceptions that may occur appropriately
    System.out.println("Please enter the details for Person 1...");
    Person p1 = readPerson(scanner);

    System.out.println("Please enter the details for Person 2...");
    Person p2 = readPerson(scanner);

    // Autounboxed: safe comparison
    if (p1.getAge() > p2.getAge()) {
      System.out.println(p1.getName() + " is older.");
    } else if (p2.getAge() > p1.getAge()) {
      System.out.println(p2.getName() + " is older.");
    } else {
      System.out.println(p1.getName() + " and " + p2.getName() + " are the same age!");
    }
  }

  /**
   * Reads a person, calling readName and readAge.
   * 
   * @param scanner scanner object
   * @return person based on user input
   */
  public static Person readPerson(Scanner scanner) {

    System.out.println("called method");
    String name;
    Integer age;
    while (true) {
      try {
        name = readName(scanner);
        age = readAge(scanner);
        break;
      } catch (NumberFormatException e) {
        System.out.println("Try again");
        continue;
      }
    }

    return new Person(name, age);
  }

  /**
   * Reads the name of the person
   * 
   * @param scanner scanner object
   * @return the name of the person
   */
  public static String readName(Scanner scanner) {
    System.out.println("What is your name?");
    String name = scanner.nextLine();
    return name;
  }

  /**
   * Reads the age of a person
   * 
   * @param scanner scanner object
   * @throws NumberFormatException    if a non-integer is entered
   * @throws IllegalArgumentException if an age outside [1, 100] is entered.
   * @return the age of person, or throws an error if invalid
   */
  public static Integer readAge(Scanner scanner)
      throws NumberFormatException, IllegalArgumentException {
    System.out.println("What is your age?");
    String ageStr = scanner.nextLine(); // yes, there is nextInt, but let's pretend not!

    int age = Integer.parseInt(ageStr);

    // If outside of [1, 100]
    if (age < 1 || age > 100) {

      // TODO 2: Throw your own exception!
      throw new IllegalArgumentException("age of " + age + " is not between 1 and 100!");
    }

    return age;
  }

}
