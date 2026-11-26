/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 9
 * Citations: Samuel Bernardo - helped explain parts of what the methods were asking for
 * because I found them sort of confusing at first
 */

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
    // recursively goes through the tree adding one to num
    // then calling back up through the tree each time num
    // becomes larger and larger for each node
    // really hard to explain I can picture it in my head
    // or with diagrams/tracing it
    // but its hard for me to explain on paper
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
    // if it reaches to the end then this part is valid
    if (current == null) {
      return true;
    }

    // sets to true at the start of each call
    boolean isValid = true;
    // checks the bounds to make sure the order is still correctly sorted
    if (lowerBound != null && current.getData().compareTo(lowerBound) <= 0) {
      isValid = false;
    }
    if (upperBound != null && current.getData().compareTo(upperBound) >= 0) {
      isValid = false;
    }

    // goes to the left and if it ever returns false then set the whole thing to
    // false
    if (!isValidBSTHelper(current.getLeft(), lowerBound, current.getData())) {
      isValid = false;
    }

    // same with the right side
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
    // if it reaches the end without error then return true
    if (current == null) {
      return true;
    }
    // if both the childs are null then it is not degenerate because
    // it can only have one path
    if (current.getLeft() != null && current.getRight() != null) {
      return false;
    }

    // sets the variable to true everytime
    boolean isValid = true;

    // if going to the left ever returns false then set isValid to false
    if (!isDegenerateHelper(current.getLeft())) {
      isValid = false;
    }
    // same for rightside
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
    size++;
  }

  /**
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
    // if it is null then return the new task that is added which
    // gets called up so the rest of the tree now has the new task
    if (current == null) {
      return new TaskNode(task);
    }
    // if you get to the same task then return an error
    if (current.getData().equals(task)) {
      throw new IllegalArgumentException();
    }

    // first checks to see if its greater and if it is sets the left to
    // a call of the method going left to a smaller task because the current
    // task is too big and if implemented now would be in the wrong spot
    // if it is not greater then the same applies but just to the right because
    // current is currently too small
    if (current.getData().compareTo(task) > 0) {
      current.setLeft(addTaskHelper(task, current.getLeft()));
    } else {
      current.setRight(addTaskHelper(task, current.getRight()));
    }
    return current;
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
   * THIS METHOD MUST BE IMPLEMENTED RECURSIVELY.
   * Finds successor of target within subtree rooted at current.
   *
   * @param target    task whose successor we want
   * @param current   current subtree root
   * @param bestSoFar current best successor candidate
   * @return next larger task than target, or null if no such task exists
   */
  private CareTask getNextTaskHelper(CareTask target, TaskNode current, CareTask bestSoFar) {
    // if it is null then return the best one so far
    // you have reached the end of the tree
    if (current == null) {
      return bestSoFar;
    }
    // check to see if its greater or less than the target
    // if it is greater then calls the method to the left and sets the data
    // because left could be null and this call could be the best call
    if (current.getData().compareTo(target) > 0) {
      return getNextTaskHelper(target, current.getLeft(), current.getData());
    }

    // otherwise go right because right now it could not be the successor
    // because its too small so it goes right to find the target
    return getNextTaskHelper(target, current.getRight(), bestSoFar);
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
    root = completeTaskHelper(task, root);
    size--;
  }

  /**
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
    if (current == null) {
      throw new NoSuchElementException();
    }
    // first check for if there is only one path
    // left to take then go that direction
    // otherwise check
    if (current.getData().compareTo(target) == 0) {
      if (current.getLeft() == null) {
        return current.getRight();
      }
      if (current.getRight() == null) {
        return current.getLeft();
      }
      // gets the successor by getting the smallest node in the right side
      // then makes the new node to remove the old one
      // sets the correct sides and then returns it

      CareTask successorNode = getMinHelper(current.getRight());
      TaskNode newReplacementNode = new TaskNode(successorNode);
      newReplacementNode.setLeft(current.getLeft());
      newReplacementNode.setRight(completeTaskHelper(successorNode, current.getRight()));
      return newReplacementNode;
    }

    // navigating the tree going left or right based on if the current position
    // is greater or less then the target
    if (current.getData().compareTo(target) > 0) {
      current.setLeft(completeTaskHelper(target, current.getLeft()));
    } else {
      current.setRight(completeTaskHelper(target, current.getRight()));
    }

    return current;
  }

  /**
   * THIS METHOD MUST BE IMPLEMENTED RECURSIVELY.
   * Returns smallest task in subtree rooted at current.
   *
   * @param current subtree root
   * @return smallest task in this subtree
   */
  private CareTask getMinHelper(TaskNode current) {
    // if it cant go left anymore then return the data of the smallest one
    // all the way back up
    if (current.getLeft() == null) {
      return current.getData();
    }
    // calls the method going further down the tree to the left
    return getMinHelper(current.getLeft());
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
