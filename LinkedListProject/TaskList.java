// TODO: File header comment

// TODO: Class header comment
// TODO: Class Signature
public class TaskList {

  /** The first node in the linked list (highest priority, earliest task). */
  private LinkedTask head;

  /** The last node in the linked list (lowest priority, latest task). */
  private LinkedTask tail;

  /** The total number of tasks in the list. */
  private int size;

  // ---- END OF PROVIDED CODE ----

  // TODO: Implement a no-argument constructor
  // - Initializes an empty list
  public TaskList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  // TODO: Implement size()
  // - Returns the total number of tasks in the list
  public int size() {
    return size;
  }

  // TODO: Implement isEmpty()
  // - Returns true if the list contains no tasks; false otherwise
  public boolean isEmpty() {
    if (head == null) {
      return true;
    } else {
      return false;
    }
  }

  // TODO: Implement addTask(Task task)
  // - Adds the task to the correct position based on its priority
  // - HIGH before MEDIUM before LOW
  // - Within the same priority group, new task goes at the END of the group
  // - Updates head/tail/size appropriately
  // - Throws IllegalArgumentException if task is null
  //
  // Hint: Consider four distinct cases:
  // (1) the list is empty
  // (2) the new task belongs at the front (before head)
  // (3) the new task belongs at the back (after tail)
  // (4) the new task belongs somewhere in the middle
  public void addTask(Task task) {
    LinkedTask linkedTask = new LinkedTask(task);
    if (isEmpty()) {
      head = linkedTask;
      tail = linkedTask;
      size++;
    } else if (task.getPriority().equals(TaskPriority.HIGH)) {
      if (!head.getTask().getPriority().equals(TaskPriority.HIGH)) {

        LinkedTask linkedTaskHead = new LinkedTask(task, head);
        head = linkedTaskHead;
        size++;
      } else {

        Task node = head.getTask();
        LinkedTask listNode = head;
        while (node.getPriority().equals(TaskPriority.HIGH)
            && listNode.getNext() != null
            && listNode.getNext().getTask().getPriority().equals(TaskPriority.HIGH)) {
          listNode = linkedTask.getNext();
          node = listNode.getTask();
        }
        LinkedTask nextLinkedTask = new LinkedTask(task, listNode.getNext());
        listNode.setNext(nextLinkedTask);
        size++;
        if (tail.equals(listNode)) {
          tail = nextLinkedTask;
        }
      }

    }
  }

  // TODO: Implement get(int index)
  // - Returns the task at the given zero-based index without removing it
  // - Throws IndexOutOfBoundsException if index < 0 or index >= size

  // TODO: Implement contains(Task task)
  // - Returns true if the list contains a task equal to the given task
  // (as determined by the task's equals() method); false otherwise

  // TODO: Implement remove(int index)
  // - Removes and returns the task at the given zero-based index
  // - Updates head/tail/size as needed
  // - Throws IndexOutOfBoundsException if index < 0 or index >= size
  //
  // Hint: index == 0 means removing the first element.
  // index == size - 1 means removing the last element.
  // Don't forget to update tail when removing the last node!

  // TODO: Implement clear()
  // - Removes all tasks from the list
  // - Resets all fields to their initial state
  public void clear() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  // TODO: Override toString()
  // - Returns a linked-list representation from head to tail
  // - Each node's toString() is concatenated together
  // - Example: "HIGH: Fix bug (1) -> MEDIUM: Deploy (2) -> LOW: Docs (3) -> END"
  // - Returns "EMPTY" for an empty list
  public String toString() {
    // TODO
    return null;
  }

}