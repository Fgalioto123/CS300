/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 8
 * Citations: used GFG to re-learn .asList.
 * https://www.geeksforgeeks.org/java/arrays-aslist-method-in-java-with-examples/
 */

/**
 * Represents a single commit in a version control system.
 * Implemented as a simplified model that tracks only which files were changed.
 * Commits are immutable; merges require creating a *new* commit.
 * @author Max Phillips
 * @author Fox Galioto
 */

import java.util.ArrayList;

/**
 * words on a page
 * Commit - yes
 */
public class Commit {
  // Auto-generated commit ID (short-hash style, e.g., a1cb2f).
  private String id;

  // User-provided commit message (description of changes).
  private String message;

  // List of (String) filenames that were changed in this commit.
  private ArrayList<String> changedFiles;

  /**
   * Creates a new Commit with the specified message and change list.
   * 
   * @param message      a String description of the changes in this Commit.
   * @param changedFiles the list of files changed in this Commit.
   */
  public Commit(String message, ArrayList<String> changedFiles) {
    this.id = HashGenerator.generateShortHash(message); // keep this line as is
    this.message = message;
    this.changedFiles = new ArrayList<>(changedFiles);
  }

  /**
   * returns the id
   * 
   * @return - id
   */
  public String getId() {
    return id;
  }

  /**
   * returns the message string
   * 
   * @return - message String
   */
  public String getMessage() {
    return message;
  }

  /**
   * returns a defensive copy of changedFiles by using the built in
   * new ArrayList<>(list) function
   * 
   * @return - changedFiles arrayList
   */
  public ArrayList<String> getChangedFiles() {
    return new ArrayList<>(changedFiles);
  }

  // PROVIDED METHOD
  /**
   * Convert this commit to a String representation.
   * 
   * @return a String representing this Commit
   */
  @Override
  public String toString() {
    return "" + this.id + " '" + this.message + "' : " + this.changedFiles;
  }
}