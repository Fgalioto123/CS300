/*
 * Author: Cole Nelson
 * Email: ctnelson2@wisc.edu
 * Course: CS300, F26
 * Assignment: Throwing Exceptions Exercise
 * Citations: None.
 */

/**
 * Thrown when a person gives an age outside the range we allow.
 */
public class InvalidAgeException extends IllegalArgumentException {
  
  // An IllegalArgumentException is the right choice of parent here, but
  // how would our code change if it was a child of Exception instead?
  
  public InvalidAgeException(String message) {
    super(message);
  }

}
