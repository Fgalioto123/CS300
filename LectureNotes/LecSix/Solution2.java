/*
 * Author: Cole Nelson
 * Email: ctnelson2@wisc.edu
 * Course: CS300, F26
 * Assignment: Exceptions Exercise
 * Citations: None.
 */

import java.util.Scanner;

/**
 * Class that demonstrates how to handle exceptions within a method rather than throwing it up.
 */
public class Solution2 {
  
  public static void main(String[] args) {
    
    Scanner scanner = new Scanner(System.in);
    
    Person p1 = readPersonAndHandle(scanner);
    Person p2 = readPersonAndHandle(scanner);

    if (p1.getAge() == null || p2.getAge() == null) {
      System.out.println("One or more ages is invalid, cannot continue.");
    } else {
      if (p1.getAge() > p2.getAge()) {
        System.out.println(p1.getName() + " is older.");
      } else if (p2.getAge() > p1.getAge()) {
        System.out.println(p2.getName() + " is older.");
      } else {
        System.out.println(p1.getName() + " and " + p2.getName() + " are the same age!");
      }
    }
    
    scanner.close();
    
    System.out.println("Thank you!");
  }
  
  /**
   * Reads a person and defaults age to null if an invalid age is entered.
   * 
   * @param scanner scanner object to read from
   * @return Person with name and age read in (else default)
   */
  public static Person readPersonAndHandle(Scanner scanner) {
    
    System.out.println("What is your name?");
    String name = scanner.nextLine();
    
    System.out.println("What is your age?");
    String ageStr = scanner.nextLine(); // yes, there is nextInt, but let's pretend not!
    
    Integer age;
    try {
      age = Integer.parseInt(ageStr); 
      
      if (age < 1 || age > 100) {
        age = null;
      }
    } catch (NumberFormatException e) {
      age = null;
    }
    
    return new Person(name, age);
  } 
}
