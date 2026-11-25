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
    // TODO: Implement this test case.
    return false; // Replace with actual test logic
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
    // TODO: Implement this test case.
    return false; // Replace with actual test logic
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
    // TODO: Implement this test case.
    return false; // Replace with actual test logic
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
    // TODO: Implement this test case.
    return false; // Replace with actual test logic
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
    // TODO: Implement this test case.
    return false; // Replace with actual test logic
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
