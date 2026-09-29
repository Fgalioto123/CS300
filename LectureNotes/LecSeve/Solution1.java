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
public class Solution1 {
  
  public static void main(String[] args) {
    
    Scanner scanner = new Scanner(System.in);
    
    try {
      Person p1 = readPersonAndThrow(scanner);
      Person p2 = readPersonAndThrow(scanner);

      if (p1.getAge() > p2.getAge()) {
        System.out.println(p1.getName() + " is older.");
      } else if (p2.getAge() > p1.getAge()) {
        System.out.println(p2.getName() + " is older.");
      } else {
        System.out.println(p1.getName() + " and " + p2.getName() + " are the same age!");
      } 
    } catch (NumberFormatException e) {
      System.out.println("One or more ages was not an integer.");
    } catch (IllegalArgumentException e) {
      System.out.println("One or more ages was not between 1 and 100.");
    } finally {
      scanner.close();
    }
    
    System.out.println("Thank you!");
    
    // We could be less specific if we wanted to...
    //  } catch (Exception e) {
    //    System.out.println("One or more ages is invalid, cannot continue.");
    //  }
    //
    // But, could we also do?...
    //  } catch (NumberFormatException | IllegalArgumentException e) {
    //    System.out.println("One or more ages is invalid, cannot continue.");
    //  }
    //
    //  Surprisingly, no. Because NumberFormatException
    //  is a type of IllegalArgumentException
    //
    //  So, if we want one message for both, we catch the shared parent!
    //  } catch (IllegalArgumentException e) {
    //    System.out.println("One or more ages is invalid, cannot continue.");
    //  }
  }
  
  /**
   * Reads a person and throws an exception if an invalid age is entered.
   * 
   * @param scanner scanner object to read from
   * 
   * @throws NumberFormatException if age is not an integer
   * @throws IllegalArgumentException if age is not between 1 and 100
   * 
   * @return Person with name and age read in
   */
  public static Person readPersonAndThrow(Scanner scanner)
    throws NumberFormatException, IllegalArgumentException {
    
    System.out.println("What is your name?");
    String name = scanner.nextLine();
    
    System.out.println("What is your age?");
    String ageStr = scanner.nextLine(); // yes, there is nextInt, but let's pretend not!
    int age = Integer.parseInt(ageStr);
    
    if (age < 1 || age > 100) {
      throw new IllegalArgumentException("age of " + age + " is not between 1 and 100!");
    }
    
    return new Person(name, age);
  }
}
