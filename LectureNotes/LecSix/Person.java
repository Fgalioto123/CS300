/*
 * Author: Cole Nelson
 * Email: ctnelson2@wisc.edu
 * Course: CS300, F26
 * Assignment: Exceptions Exercise
 * Citations: None.
 */

/**
 * Class that represents a Person with a name and age.
 */
public class Person {
  private String name;
  private Integer age;
  
  /**
   * Constructs a person with a given name and age.
   * 
   * @param n name of person
   * @param a age of person
   */
  public Person(String n, Integer a) {
    name = n;
    age = a;
  }
  
  /**
   * Returns the name of the person
   * 
   * @return name
   */
  public String getName() {
    return name;
  }
  
  /**
   * Returns the age of the person
   * 
   * @return age
   */
  public Integer getAge() {
    return age;
  }
  
}
