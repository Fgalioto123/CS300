// TODO: File header comment

// TODO: Class header comment
public class TaskListTester {

  // TODO: Implement testLinkedTask()
  // - The constructors should handle null input correctly
  // (IllegalArgumentException)
  // - The toString() should return the correct format with and without a next
  // node
  public static boolean testLinkedTask() {
    boolean test = true;
    try {
      TaskPriority taskPriority = TaskPriority.HIGH;
      Task task = new Task("blah", taskPriority);
      // LinkedTask testTask = new LinkedTask(task);
      LinkedTask linkedTask = new LinkedTask(null, null);
      return false;
    } catch (IllegalArgumentException e) {
    }
    TaskPriority taskPriority = TaskPriority.HIGH;
    Task task = new Task("Homework", taskPriority);
    Task taskTwo = new Task("Homework Again", taskPriority);
    LinkedTask linkedTaskOne = new LinkedTask(task);
    LinkedTask linkedTaskTwo = new LinkedTask(taskTwo, linkedTaskOne);
    String outputOne = "" + linkedTaskOne;
    String outputTwo = "" + linkedTaskTwo;
    if (outputOne.equals("HIGH: Homework (2) -> END") &&
        outputTwo.equals("HIGH: Homework Again (3) -> ")) {

    } else {
      test = false;
    }

    return test;
  }

  // TODO: Implement testAddTask(), pay attention to the size
  // - Verify that adding to an empty list works correctly
  // - Verify that addTask() inserts tasks in the correct priority order
  // - Verify that tasks within the same priority group maintain insertion order
  // - Verify that null input is handled correctly (IllegalArgumentException)
  public static boolean testAddTask() {
    Task.resetIDGenerator();
    // TODO
    return false;
  }

  // TODO: Implement testRemove(), pay attention to the size
  // - Verify that removing the first element (index 0) works correctly
  // - Verify that removing the last element works correctly
  // - Verify that removing from the middle correctly relinks the remaining nodes
  // - Verify that removing the only element produces an empty list
  // - Verify that invalid indices throw IndexOutOfBoundsException
  public static boolean testRemove() {
    Task.resetIDGenerator();
    // TODO
    return false;
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
    // allPass &= testAddTask();
    // allPass &= testRemove();
    return allPass;
  }

  /**
   * Demonstrates the functionality of the TaskList with sample data.
   * See the assignment specification for expected output.
   */
  // public static void demo() {
  // Task.resetIDGenerator();
  // TaskList list = new TaskList();

  // list.addTask(new Task("Deploy to production", TaskPriority.MEDIUM));
  // list.addTask(new Task("Fix login bug", TaskPriority.HIGH));
  // list.addTask(new Task("Update README", TaskPriority.LOW));
  // list.addTask(new Task("Code review PR #42", TaskPriority.MEDIUM));
  // list.addTask(new Task("Patch security vuln", TaskPriority.HIGH));

  // System.out.println("List Before Completion:");
  // System.out.println(list);
  // System.out.println("Size: " + list.size());

  // list.get(0).markComplete();
  // list.get(2).markComplete();
  // list.get(4).markComplete();

  // System.out.println("\nList After Completion:");
  // System.out.println(list);

  // list.remove(4);
  // list.remove(2);
  // list.remove(0);

  // System.out.println("\nList After Deletion:");
  // System.out.println(list);
  // }

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
    // demo();
  }
}