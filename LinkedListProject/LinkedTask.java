// TODO: File header comment

// TODO: Class header comment
public class LinkedTask {

  /** The task data stored in this node. */
  private Task task;

  /**
   * A reference to the next LinkedTask in the list, or null if this is the last
   * node.
   */
  private LinkedTask next;

  // TODO: Implement a one-argument constructor LinkedTask(Task task)
  // - Sets task to the given value and next to null
  // - Throws IllegalArgumentException if task is null
  public LinkedTask(Task task) {
    this.task = task;
    this.next = null;
  }

  // TODO: Implement a two-argument constructor LinkedTask(Task task, LinkedTask
  // next)
  // - Sets both fields to the given values
  // - Throws IllegalArgumentException if task is null
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

  // TODO: Implement setNext(LinkedTask next)
  // - Updates the next reference to the given value (may be null)
  public void setNext(LinkedTask next) {
    this.next = next;
  }

  // TODO: Override toString()
  // - Returns the task's string representation followed by " -> " if there is
  // a next node, or " -> END" if this is the last node
  // - Example with next: "HIGH: Fix bug (1) -> "
  // - Example without next: "HIGH: Fix bug (1) -> END"
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