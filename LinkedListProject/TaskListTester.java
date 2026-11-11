/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 7
 * Citations: None
 */

/**
 * 
 * TaskListTester tests the methods and linked list created by the other files
 * in this program
 */
public class TaskListTester {

  // - The constructors should handle null input correctly
  // (IllegalArgumentException)
  // - The toString() should return the correct format with and without a next
  // node
  /**
   * tests that creating a node works and has the correect tasks
   * 
   * @return - true or false
   */
  public static boolean testLinkedTask() {
    // resetting the taks ID's
    Task.resetIDGenerator();
    boolean test = true;
    TaskPriority taskPriority = TaskPriority.HIGH;
    // Task taskTest = new Task("blah", taskPriority);
    try {
      // tests creating a node with null as a task which correctly returns an exeption
      LinkedTask linkedTask = new LinkedTask(null, null);
      // System.out.println("turns1");
      test = false;
    } catch (IllegalArgumentException e) {
      // System.out.println(e.getMessage());
    }
    // TaskPriority taskPriority = TaskPriority.HIGH;
    // creates tasks and a node that points to null and one that points to the null
    // one
    Task taskOne = new Task("Homework", taskPriority);
    Task taskTwo = new Task("Homework Again", taskPriority);
    LinkedTask linkedTaskOne = new LinkedTask(taskOne);
    LinkedTask linkedTaskTwo = new LinkedTask(taskTwo, linkedTaskOne);
    String outputOne = "" + linkedTaskOne;
    String outputTwo = "" + linkedTaskTwo;
    // tests to see if the output is correct
    if (outputOne.equals("HIGH: Homework (1) -> END") &&
        outputTwo.equals("HIGH: Homework Again (2) -> ")) {
      // System.out.println("correct");
    } else {
      // System.out.println("turns2");
      test = false;
    }

    return test;
  }

  // - Verify that adding to an empty list works correctly
  // - Verify that addTask() inserts tasks in the correct priority order
  // - Verify that tasks within the same priority group maintain insertion order
  // - Verify that null input is handled correctly (IllegalArgumentException)
  /**
   * tests different ways of adding to the linked list and making sure it adds
   * based on the priority, updates head and tail, etc.
   * 
   * @return - true or false
   */
  public static boolean testAddTask() {
    Task.resetIDGenerator();
    boolean returnValue = true;

    TaskList taskList = new TaskList();
    Task taskOne = new Task("Homework", TaskPriority.HIGH);
    taskList.addTask(taskOne);
    // checks to see if the size and and its not empty and the right task gets added
    if (taskList.size() != 1 || taskList.isEmpty() == true
        || taskList.get(0).equals(taskOne) == false) {
      System.out.println("ran4");
      returnValue = false;
    }

    Task taskTwo = new Task("Lesser Homework", TaskPriority.MEDIUM);
    Task taskThree = new Task("Low Homework", TaskPriority.LOW);
    taskList.addTask(taskTwo);
    taskList.addTask(taskThree);
    // makes sure that the medium and lows get inserted correctly to the middle and
    // end of the list
    if (taskList.get(1).equals(taskTwo) == false
        || taskList.get(2).equals(taskThree) == false) {
      System.out.println("ran3");
      returnValue = false;
    }
    // makes sure that when multiple of the same type gets added the new one goes to
    // the end
    Task taskFour = new Task("Homework Two", TaskPriority.HIGH);
    taskList.addTask(taskFour);
    if (taskList.get(1).equals(taskFour) == false && taskList.size() == 4) {
      System.out.println("ran");
      returnValue = false;
    }

    try {
      // makes sure a expetion passes if you try to add a null task to the list
      taskList.addTask(null);
      System.out.println("ran2");
      returnValue = false;
    } catch (IllegalArgumentException e) {
      System.out.println("caught");
    }
    // System.out.println(taskList);

    return returnValue;
  }

  // - Verify that removing the first element (index 0) works correctly
  // - Verify that removing the last element works correctly
  // - Verify that removing from the middle correctly relinks the remaining nodes
  // - Verify that removing the only element produces an empty list
  // - Verify that invalid indices throw IndexOutOfBoundsException
  /**
   * tests removing from the linked list in different ways while making sure it
   * does the correct order and tail/head position
   * 
   * @return - true or false
   */
  public static boolean testRemove() {
    Task.resetIDGenerator();
    boolean returnValue = true;

    // creastes a list with 4 tasks
    TaskList taskList = new TaskList();
    Task taskOne = new Task("Homework", TaskPriority.HIGH);
    Task taskFour = new Task("Homework Two", TaskPriority.HIGH);
    Task taskTwo = new Task("Lesser Homework", TaskPriority.MEDIUM);
    Task taskThree = new Task("Low Homework", TaskPriority.LOW);
    taskList.addTask(taskOne);
    taskList.addTask(taskFour);
    taskList.addTask(taskTwo);
    taskList.addTask(taskThree);
    // System.out.println(taskList);

    // removeds the head and makes sure it gets removed properly
    Task headTask = taskList.remove(0);
    if (headTask.equals(taskOne) == false || taskList.size() != 3
        || taskList.get(0).equals(taskFour) == false) {
      returnValue = false;
    }

    // removes the tail making sure the tail is removed properly
    Task tailTask = taskList.remove(taskList.size() - 1);
    if (tailTask.equals(taskThree) == false || taskList.size() != 2
        || taskList.get(1).equals(taskTwo) == false) {
      returnValue = false;
    }

    // adds another task to the middle of the list then removes it making sure
    // removing from the middle works as intended
    Task middleTask = new Task("Middle", TaskPriority.HIGH);
    taskList.addTask(middleTask);
    Task removeMiddle = taskList.remove(1);
    if (removeMiddle.equals(middleTask) == false || taskList.size() != 2
        || taskList.get(0).equals(taskFour) == false) {
      returnValue = false;
    }
    // remocves the last ones and tests to make sure the isEmpty method works
    taskList.remove(0);
    taskList.remove(0);
    if (taskList.isEmpty() == false) {
      returnValue = false;
    }

    try {
      // test to make sure trying to remove an index that doesn't exist returns an
      // error
      taskList.remove(0);
      returnValue = false;
    } catch (Exception e) {
      // System.out.println("caught");
    }

    // System.out.println(taskList);
    return returnValue;
  }

  // ---- PROVIDED METHODS (do not modify) ----

  /**
   * Calls all test methods and returns true if and only if all tests pass.
   *
   * @return true if every test method returns true; false otherwise
   */
  public static boolean runAllTests() {
    boolean allPass = true;
    allPass &= testLinkedTask();
    allPass &= testAddTask();
    allPass &= testRemove();
    return allPass;
  }

  /**
   * Demonstrates the functionality of the TaskList with sample data.
   * See the assignment specification for expected output.
   */
  public static void demo() {
    Task.resetIDGenerator();
    TaskList list = new TaskList();

    list.addTask(new Task("Deploy to production", TaskPriority.MEDIUM));
    list.addTask(new Task("Fix login bug", TaskPriority.HIGH));
    list.addTask(new Task("Update README", TaskPriority.LOW));
    list.addTask(new Task("Code review PR #42", TaskPriority.MEDIUM));
    list.addTask(new Task("Patch security vuln", TaskPriority.HIGH));

    System.out.println("List Before Completion:");
    System.out.println(list);
    System.out.println("Size: " + list.size());

    list.get(0).markComplete();
    list.get(2).markComplete();
    list.get(4).markComplete();

    System.out.println("\nList After Completion:");
    System.out.println(list);

    list.remove(4);
    list.remove(2);
    list.remove(0);

    System.out.println("\nList After Deletion:");
    System.out.println(list);
  }

  /**
   * Main method. Runs all tests and prints results, then runs the demo.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("testLinkedTask: " + testLinkedTask());
    System.out.println("testAddTask: " + testAddTask());
    System.out.println("testRemove: " + testRemove());
    System.out.println("runAllTests: " + runAllTests());

    System.out.println("\n========== DEMO ==========\n");
    demo();
  }
}