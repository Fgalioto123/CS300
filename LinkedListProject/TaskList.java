/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 7
 * Citations: None
 */

/**
 * 
 * TaskList class holds head, tail, and size. It is the linked List containing
 * the head and tail. In this class you can add, remove, and other essential
 * things pertaining to a linked list
 */
public class TaskList {

  /** The first node in the linked list (highest priority, earliest task). */
  private LinkedTask head;

  /** The last node in the linked list (lowest priority, latest task). */
  private LinkedTask tail;

  /** The total number of tasks in the list. */
  private int size;

  // ---- END OF PROVIDED CODE ----

  // - Initializes an empty list
  /**
   * initializes everything to an empty linked list
   */
  public TaskList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  // - Returns the total number of tasks in the list
  /**
   * returns the size
   * 
   * @return - returns the current size
   */
  public int size() {
    return size;
  }

  // - Returns true if the list contains no tasks; false otherwise
  /**
   * returns true of the list contains no tasks; false otherwise
   * 
   * @return - true or false
   */
  public boolean isEmpty() {
    if (head == null) {
      return true;
    } else {
      return false;
    }
  }

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
  /**
   * adds a task based on priority and updates head and tail as necessary
   * 
   * @param task - the task being added
   */
  public void addTask(Task task) {

    if (task == null) {
      throw new IllegalArgumentException();
    }
    if (isEmpty()) {
      LinkedTask linkedTask = new LinkedTask(task);
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
          listNode = listNode.getNext();
          node = listNode.getTask();
        }
        LinkedTask nextLinkedTask = new LinkedTask(task, listNode.getNext());
        listNode.setNext(nextLinkedTask);
        size++;
        if (tail.equals(listNode)) {
          tail = nextLinkedTask;
        }
      }

    } else if (task.getPriority().equals(TaskPriority.MEDIUM)) {
      if (head.getTask().getPriority().equals(TaskPriority.LOW)) {
        LinkedTask linkedTask = new LinkedTask(task, head);
        head = linkedTask;
        size++;
      } else {

        LinkedTask listNode = head;
        while (listNode.getNext() != null
            && (listNode.getNext().getTask().getPriority().equals(TaskPriority.HIGH)
                || listNode.getNext().getTask().getPriority().equals(TaskPriority.MEDIUM))) {
          listNode = listNode.getNext();
        }
        LinkedTask nextLinkedTask = new LinkedTask(task, listNode.getNext());
        listNode.setNext(nextLinkedTask);
        size++;
        if (tail.equals(listNode)) {
          tail = nextLinkedTask;
        }
      }
    } else {
      LinkedTask linkedTask = new LinkedTask(task, null);
      LinkedTask tailTask = tail;
      tailTask.setNext(linkedTask);
      tail = linkedTask;
      size++;
    }
  }

  // - Returns the task at the given zero-based index without removing it
  // - Throws IndexOutOfBoundsException if index < 0 or index >= size
  /**
   * returns the task based on the index given
   * 
   * @param index - index of the task being returned
   * @return - the task
   */
  public Task get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    LinkedTask linkedTask = head;
    for (int i = 0; i < index; i++) {
      linkedTask = linkedTask.getNext();
    }
    return linkedTask.getTask();
  }

  // - Returns true if the list contains a task equal to the given task
  // (as determined by the task's equals() method); false otherwise
  /**
   * checks to see if the linkedList has the task
   * 
   * @param task - task being checked to see if it exists
   * @return - true or false
   */
  public boolean contains(Task task) {

    LinkedTask linkedTask = head;
    for (int i = 0; i < size; i++) {
      if (linkedTask.getTask().equals(task)) {
        return true;
      }
      linkedTask = linkedTask.getNext();
    }
    return false;
  }

  // - Removes and returns the task at the given zero-based index
  // - Updates head/tail/size as needed
  // - Throws IndexOutOfBoundsException if index < 0 or index >= size
  //
  // Hint: index == 0 means removing the first element.
  // index == size - 1 means removing the last element.
  // Don't forget to update tail when removing the last node!
  /**
   * removes the index given and if the index is incorrect it throws an error.
   * 
   * @param index - index wanting to be removed
   * @return - the task of the index removed
   */
  public Task remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    LinkedTask linkedTask = head;
    if (size == 1) {
      clear();
      return linkedTask.getTask();
    }
    if (index == 0) {
      head = linkedTask.getNext();
      size--;
      return linkedTask.getTask();
    }

    for (int i = 0; i < index - 1; i++) {
      linkedTask = linkedTask.getNext();
    }

    if (tail.equals(linkedTask.getNext())) {
      tail = linkedTask;
    }
    Task returnTask = linkedTask.getNext().getTask();
    linkedTask.setNext(linkedTask.getNext().getNext());
    size--;
    return returnTask;

  }

  // - Removes all tasks from the list
  // - Resets all fields to their initial state
  /**
   * clears the linked list
   */
  public void clear() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  // - Returns a linked-list representation from head to tail
  // - Each node's toString() is concatenated together
  // - Example: "HIGH: Fix bug (1) -> MEDIUM: Deploy (2) -> LOW: Docs (3) -> END"
  // - Returns "EMPTY" for an empty list
  /**
   * (non-Javadoc)
   * prints out the linked list
   * 
   * @see java.lang.Object#toString()
   */
  public String toString() {
    if (size == 0) {
      return "EMPTY";
    }

    String result = "";
    LinkedTask linkedTask = head;
    for (int i = 0; i < size; i++) {
      result += linkedTask;
      linkedTask = linkedTask.getNext();
    }

    return result;
  }

}