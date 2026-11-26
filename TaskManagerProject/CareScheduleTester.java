/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 9
 * Citations: Asked Samuel Bernardo what exactly it wants from me in the testers
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Tester class for CareSchedule.
 */
public class CareScheduleTester {

  //////////////////////////////////////// HELPER METHODS

  /**
   * Builds a manual example tree for testing purposes.
   * This method demonstrates how to manually construct a binary tree.
   * Students can use this as a reference for creating their own test trees.
   *
   * Tree structure:
   * 50
   * / \
   * 20 80
   * / \ / \
   * 10 30 70 90
   *
   * @return a CareSchedule object with the above tree structure.
   */
  private static CareSchedule buildManualBaseTree() {
    CareSchedule schedule = new CareSchedule();

    TaskNode n50 = new TaskNode(new CareTask(50, "N50"));
    TaskNode n20 = new TaskNode(new CareTask(20, "N20"));
    TaskNode n80 = new TaskNode(new CareTask(80, "N80"));
    TaskNode n10 = new TaskNode(new CareTask(10, "N10"));
    TaskNode n30 = new TaskNode(new CareTask(30, "N30"));
    TaskNode n70 = new TaskNode(new CareTask(70, "N70"));
    TaskNode n90 = new TaskNode(new CareTask(90, "N90"));

    n50.setLeft(n20);
    n50.setRight(n80);
    n20.setLeft(n10);
    n20.setRight(n30);
    n80.setLeft(n70);
    n80.setRight(n90);

    schedule.setRoot(n50);
    return schedule;
  }

  //////////////////////////////////////// CARETASK ////////////////////////////////////////

  /**
   * Tests the compareTo functionality of CareTask.
   * 
   * @return true if all compareTo tests pass, false otherwise.
   */
  public static boolean testCareTask() {
    boolean t1 = testCompareDifferentUrgency();
    boolean t2 = testCompareSameUrgencyDifferentName();
    boolean t3 = testCompareEqual();
    boolean t4 = testCompareNullThrows();

    if (!t1) {
      System.out.println("testCompareDifferentUrgency FAIL");
    }
    if (!t2) {
      System.out.println("testCompareSameUrgencyDifferentName FAIL");
    }
    if (!t3) {
      System.out.println("testCompareEqual FAIL");
    }
    if (!t4) {
      System.out.println("testCompareNullThrows FAIL");
    }

    return t1 && t2 && t3 && t4;
  }

  private static boolean testCompareDifferentUrgency() {
    CareTask ta = new CareTask(1, "A");
    CareTask tb = new CareTask(2, "B");
    return ta.compareTo(tb) < 0 && tb.compareTo(ta) > 0;
  }

  private static boolean testCompareSameUrgencyDifferentName() {
    CareTask ta = new CareTask(5, "A");
    CareTask tb = new CareTask(5, "B");
    return ta.compareTo(tb) < 0 && tb.compareTo(ta) > 0;
  }

  private static boolean testCompareEqual() {
    CareTask ta = new CareTask(7, "Same");
    CareTask tb = new CareTask(7, "Same");
    return ta.compareTo(tb) == 0;
  }

  private static boolean testCompareNullThrows() {
    CareTask ta = new CareTask(7, "Same");
    try {
      ta.compareTo(null);
      return false;
    } catch (IllegalArgumentException e) {
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  ////////////////////////////////////////// ADD //////////////////////////////////////////

  /**
   * Tests the addTask functionality of CareSchedule.
   * Students should implement this test to verify the correctness of adding
   * tasks.
   * 
   * @return true if all addTask tests pass, false otherwise.
   */
  public static boolean testAdd() {
    boolean isValid = true;
    // creates a base schedule then adds a new task to it
    CareSchedule schedule = buildManualBaseTree();
    CareTask task = new CareTask(55, "55");
    schedule.addTask(task);
    // then finds the task from where it should be on a correct implentation
    TaskNode node = schedule.getRoot();
    CareTask correcettask = node.getRight().getLeft().getLeft().getData();
    // checks to see if it is the right one

    if (!correcettask.equals(task)) {
      isValid = false;
    }

    try {
      schedule.addTask(task);
      isValid = false;
    } catch (Exception e) {
      System.out.println("caught exception correctly");
    }
    return isValid;
  }

  ////////////////////////////////////////// NEXT //////////////////////////////////////////

  /**
   * Tests the getNextTask functionality of CareSchedule.
   * Students should implement this test to verify the correctness of finding the
   * next task.
   * 
   * @return true if all getNextTask tests pass, false otherwise.
   */
  public static boolean testNext() {
    // creates a schedule
    CareSchedule schedule = buildManualBaseTree();
    // creates the root and then gets the next
    TaskNode root = schedule.getRoot();
    CareTask next = schedule.getNextTask(root.getData());
    // gets the correct one manually
    CareTask correctNext = root.getRight().getLeft().getData();
    // checks to make sure they are the same
    if (next.equals(correctNext)) {
      return true;
    }
    return false;
  }

  ///////////////////////////////////////// REMOVE /////////////////////////////////////////

  /**
   * Tests the completeTask functionality of CareSchedule.
   * Students should implement this test to verify the correctness of removing
   * tasks.
   * 
   * @return true if all completeTask tests pass, false otherwise.
   */
  public static boolean testRemove() {
    // creates a schedule using the pre built method
    CareSchedule schedule = buildManualBaseTree();

    // gets a new task that is in the schedule to remove it
    CareTask task = new CareTask(80, "N80");
    // removes then creates the task that replaced it
    schedule.completeTask(task);
    TaskNode root = schedule.getRoot();
    CareTask correctTask = new CareTask(90, "N90");
    // tests to make sure that task replaced it correctly
    if (schedule.size() != 6) {
      return false;
    }
    if (root.getRight().getData().compareTo(correctTask) == 0) {
      return true;
    }
    return false;
  }

  ///////////////////////////////////////// ITERATOR /////////////////////////////////////////

  /**
   * Tests the iterator functionality of CareSchedule.
   * Students should implement this test to verify the iterator traverses the tree
   * correctly.
   * 
   * @return true if all iterator tests pass, false otherwise.
   */
  public static boolean testIterator() {
    // creates a schedule then iterates through it setting the last one
    // to the new object
    CareSchedule schedule = buildManualBaseTree();
    CareTask lastTask = null;
    ScheduleIterator iterator = new ScheduleIterator(schedule);
    while (iterator.hasNext()) {
      lastTask = iterator.next();
      // System.out.println(lastTask);
    }
    // checks that the last object is the correct one
    if (lastTask == null ||
        lastTask.compareTo(new CareTask(90, "N90")) != 0) {
      return false;
    }

    try {
      iterator.next();
      return false; // no exception, so the implementation is broken
    } catch (NoSuchElementException e) {
      return true; // correct behavior
    } catch (Exception e) {
      return false; // wrong type of exception
    }
  }

  ///////////////////////////////////////// STRUCTURE /////////////////////////////////////////

  /**
   * Tests the structure-related functionalities of CareSchedule.
   * Students should implement this to test BST validity and degenerate tree
   * checks.
   * 
   * @return true if all structure-related tests pass, false otherwise.
   */
  public static boolean testStructureChecks() {
    boolean isValid = true;
    CareSchedule schedule = buildManualBaseTree();
    isValid = schedule.isDegenerate();
    isValid = schedule.isValidBST();

    TaskNode n50 = new TaskNode(new CareTask(50, "N50"));
    TaskNode n20 = new TaskNode(new CareTask(20, "N20"));
    TaskNode n80 = new TaskNode(new CareTask(80, "N80"));
    TaskNode n10 = new TaskNode(new CareTask(10, "N10"));
    TaskNode n30 = new TaskNode(new CareTask(30, "N30"));
    TaskNode n70 = new TaskNode(new CareTask(70, "N70"));
    TaskNode n90 = new TaskNode(new CareTask(90, "N90"));

    n50.setLeft(n10);
    n50.setRight(n80);
    n10.setLeft(n20);
    n10.setRight(n30);
    n80.setLeft(n70);
    n80.setRight(n90);

    CareSchedule badschedule = new CareSchedule();
    badschedule.setRoot(n50);
    if (badschedule.isValidBST()) {

      isValid = false;
    }

    return isValid;
  }

  ////////////////////////////////////////// MAIN //////////////////////////////////////////

  public static void main(String[] args) {
    System.out.println("CareTask tests: " + (testCareTask() ? "PASS" : "FAIL"));
    System.out.println("CareSchedule add tests: " + (testAdd() ? "PASS" : "FAIL"));
    System.out.println("CareSchedule next tests: " + (testNext() ? "PASS" : "FAIL"));
    System.out.println("CareSchedule remove tests: " + (testRemove() ? "PASS" : "FAIL"));
    System.out.println("Iterator tests: " + (testIterator() ? "PASS" : "FAIL"));
    System.out.println("Structure tests: " + (testStructureChecks() ? "PASS" : "FAIL"));
  }
}
