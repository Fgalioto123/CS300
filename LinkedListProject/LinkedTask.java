/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 7
 * Citations: None
 */

/**
 * The linkedTask class are the nodes of the linked list. They contain the data
 * or task
 * they also contain the next pointer which points to the next node or null if
 * there isnt any
 */
public class LinkedTask {

  /** The task data stored in this node. */
  private Task task;

  /**
   * A reference to the next LinkedTask in the list, or null if this is the last
   * node.
   */
  private LinkedTask next;

  // - Sets task to the given value and next to null
  // - Throws IllegalArgumentException if task is null
  /**
   * constructor that sets the task and the next pointer to null
   * 
   * @param task - the task
   */
  public LinkedTask(Task task) {
    this.task = task;
    this.next = null;
  }

  // - Sets both fields to the given values
  // - Throws IllegalArgumentException if task is null
  /**
   * sets both of the fields correctly
   * 
   * @param task
   * @param next
   */
  public LinkedTask(Task task, LinkedTask next) {
    if (task == null) {
      throw new IllegalArgumentException();
    }
    this.task = task;
    this.next = next;
  }

  // ---- PROVIDED ACCESSORS (do not modify) ----

  /**
   * Returns the Task stored in this node.
   *
   * @return the task data
   */
  public Task getTask() {
    return this.task;
  }

  /**
   * Returns the next LinkedTask in the list.
   *
   * @return the next node, or null if this is the last node
   */
  public LinkedTask getNext() {
    return this.next;
  }

  // ---- END OF PROVIDED CODE ----

  // - Updates the next reference to the given value (may be null)
  /**
   * changes the next pointer to point to a different node
   * 
   * @param next - the node the current node is now pointing to
   */
  public void setNext(LinkedTask next) {
    this.next = next;
  }

  // - Returns the task's string representation followed by " -> " if there is
  // a next node, or " -> END" if this is the last node
  // - Example with next: "HIGH: Fix bug (1) -> "
  // - Example without next: "HIGH: Fix bug (1) -> END"
  /**
   * (non-Javadoc)
   * prints out the task and the link between the next one or the END
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    String print = "" + task;
    if (this.next == null) {
      print += " -> END";
    } else {
      print += " -> ";
    }
    return print;
  }

}