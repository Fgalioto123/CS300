import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a Binary Search Tree of CareTask objects.
 * Tree order is defined by CareTask.compareTo().
 */
public class CareSchedule implements Iterable<CareTask> {
  private TaskNode root;
  private int size;

  /**
   * Constructor: creates an empty schedule.
   */
  public CareSchedule() {
    this.root = null;
    this.size = 0;
  }

  /**
   * @return number of tasks currently in the tree
   */
  public int size() {
    return this.size;
  }

  /**
   * @return true when the tree has no tasks
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Returns root so testers can manually build trees.
   *
   * @return root node
   */
  protected TaskNode getRoot() {
    return this.root;
  }

  /**
   * Sets root so testers can manually build trees.
   *
   * @param root the new root
   */
  protected void setRoot(TaskNode root) {
    this.root = root;
    this.size = countNodes();
  }

  /**
   * Count the total number of TaskNodes in this tree.
   * This should be equal to the current size of the tree and is used to
   * initialize
   * the size field when using the protected setRoot() method above.
   *
   * @return the total number of TaskNodes contained in this tree
   */
  public int countNodes() {
    return countNodesHelper(this.root);
  }

  /**
   * @param current current node
   * @return the total number of nodes in the subtree rooted at current
   *         (which is 0 when current is null)
   */
  private int countNodesHelper(TaskNode current) {
    if (current == null) {
      return 0;
    }
    int num = 1;
    num += countNodesHelper(current.getLeft());
    return num + countNodesHelper(current.getRight());

  }

  /**
   * Check whether this tree satisfies BST ordering rules.
   * This should verify the full tree, not just parent/child pairs.
   *
   * @return true if valid BST (or it is an empty tree), false otherwise
   */
  public boolean isValidBST() {
    return isValidBSTHelper(this.root, null, null);
  }

  /**
   * 
   * @param current    current node
   * @param lowerBound all nodes in this subtree must be larger than this value
   *                   (if not null)
   * @param upperBound all nodes in this subtree must be smaller than this value
   *                   (if not null)
   * @return true if subtree rooted at current satisfies BST ordering
   */
  private boolean isValidBSTHelper(TaskNode current, CareTask lowerBound, CareTask upperBound) {
    if (current == null) {
      return true;
    }

    boolean isValid = true;
    if (lowerBound != null && current.getData().compareTo(lowerBound) <= 0) {
      isValid = false;
    }
    if (upperBound != null && current.getData().compareTo(upperBound) >= 0) {
      isValid = false;
    }

    if (!isValidBSTHelper(current.getLeft(), lowerBound, current.getData())) {
      isValid = false;
    }

    if (!isValidBSTHelper(current.getRight(), current.getData(), upperBound)) {
      isValid = false;
    }
    return isValid;
  }

  /**
   * Check whether this tree is degenerate (just a single chain).
   * A degenerate tree has at most one child at every node.
   *
   * @return true when every node has at most one child (or it is an empty tree)
   */
  public boolean isDegenerate() {
    return isDegenerateHelper(root);
  }

  /**
   * @param current current node
   * @return true if subtree rooted at current forms a single chain
   */
  private boolean isDegenerateHelper(TaskNode current) {
    if (current == null) {
      return true;
    }
    if (current.getLeft() != null && current.getRight() != null) {
      return false;
    }

    boolean isValid = true;

    if (!isDegenerateHelper(current.getLeft())) {
      isValid = false;
    }
    if (!isDegenerateHelper(current.getRight())) {
      isValid = false;
    }

    return isValid;

  }

  /**
   * Adds a task to the schedule and updates root when needed.
   * Duplicate tasks are not allowed.
   *
   * @param task task to add
   */
  public void addTask(CareTask task) {
    if (task == null) {
      throw new IllegalArgumentException("Task cannot be null.");
    }
    root = addTaskHelper(task, root);
  }

  /**
   * TODO:
   * THIS METHOD MUST BE IMPLEMENTED RECURSIVELY.
   * Inserts task into the subtree rooted at current and returns the
   * (possibly updated) subtree root.
   *
   * @param task    task to insert
   * @param current current subtree root
   * @return updated subtree root after insertion
   * @throws IllegalArgumentException if task is a duplicate of an existing task
   */
  private TaskNode addTaskHelper(CareTask task, TaskNode current) {
    return null;
  }

  /**
   * Returns the next larger task than currentTask, or null.
   * If no larger task exists, returns null.
   *
   * @param currentTask reference task
   * @return successor task or null
   */
  public CareTask getNextTask(CareTask currentTask) {
    if (currentTask == null) {
      throw new IllegalArgumentException("Task cannot be null.");
    }
    return getNextTaskHelper(currentTask, root, null);
  }

  /**
   * Returns the smallest task in this tree, or null when empty.
   * Used by iterator initialization.
   *
   * @return the minimum task in this schedule
   */
  public CareTask getFirstTask() {
    if (root == null) {
      return null;
    }
    return getMinHelper(root);
  }

  /**
   * TODO:
   * THIS METHOD MUST BE IMPLEMENTED RECURSIVELY.
   * Finds successor of target within subtree rooted at current.
   *
   * @param target    task whose successor we want
   * @param current   current subtree root
   * @param bestSoFar current best successor candidate
   * @return next larger task than target, or null if no such task exists
   */
  private CareTask getNextTaskHelper(CareTask target, TaskNode current, CareTask bestSoFar) {
    return null;
  }

  /**
   * Removes a task from this schedule.
   * Throws NoSuchElementException when task is not in the tree.
   *
   * @param task task to remove
   * @throws NoSuchElementException if the task does not exist
   */
  public void completeTask(CareTask task) {
    if (task == null) {
      throw new IllegalArgumentException("Task cannot be null.");
    }
    size--;
    root = completeTaskHelper(task, root);
  }

  /**
   * TODO:
   * THIS METHOD MUST BE IMPLEMENTED RECURSIVELY.
   * Removes target from subtree rooted at current and returns updated root
   * of that subtree.
   *
   * @param target  task to remove
   * @param current current subtree root
   * @return updated subtree root after removal
   * @throws NoSuchElementException if target is not found in this subtree
   */
  private TaskNode completeTaskHelper(CareTask target, TaskNode current) {
    return null;
  }

  /**
   * TODO:
   * THIS METHOD MUST BE IMPLEMENTED RECURSIVELY.
   * Returns smallest task in subtree rooted at current.
   *
   * @param current subtree root
   * @return smallest task in this subtree
   */
  private CareTask getMinHelper(TaskNode current) {
    return null;
  }

  /**
   * Creates a tree-formatted string of this schedule.
   * Intended for visual debugging and testing.
   *
   * @return pretty tree string
   */
  public String prettyPrint() {
    return prettyPrintHelper("", root, false);
  }

  /**
   * Recursive helper for prettyPrint().
   *
   * @param prefix  text prefix used for indentation and branch marks
   * @param current current node
   * @param isLeft  whether current node is shown as a left branch
   * @return formatted string for this subtree
   */
  private String prettyPrintHelper(String prefix, TaskNode current, boolean isLeft) {
    if (current == null) {
      return "";
    }
    String str = prefix + (isLeft ? "|-- " : "\\-- ") + current.getData().toString() + "\n";
    str += prettyPrintHelper(prefix + (isLeft ? "|   " : "    "), current.getLeft(), true);
    str += prettyPrintHelper(prefix + (isLeft ? "|   " : "    "), current.getRight(), false);
    return str;
  }

  /**
   * Returns an iterator over this schedule in sorted order.
   *
   * @return iterator visiting tasks from smallest to largest
   */
  @Override
  public Iterator<CareTask> iterator() {
    return new ScheduleIterator(this);
  }
}
