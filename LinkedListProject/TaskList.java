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
 * 
 */
public class TaskList implements SortedTaskListADT<Task> {

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
    // checking to see if the head is null and if it is return true else return
    // false
    // because if it is null then it is empty
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
  @Override
  public void addTask(Task task) {
    // first checks to see if the task is null and if it is throws and exception
    if (task == null) {
      throw new IllegalArgumentException();
    }
    // uses a series of If and Else Ifs to check for all the different cases
    // the first on is if it is emtpy
    if (isEmpty()) {
      // if it is empty then create a new LinkedTask node with the task that is wanted
      // to be added
      LinkedTask linkedTask = new LinkedTask(task);
      // set the head and tail to the new node and add one to the size
      head = linkedTask;
      tail = linkedTask;
      size++;
      // next checks if the priority is High
    } else if (task.getPriority().equals(TaskPriority.HIGH)) {
      // checks to see if there is no high already present
      if (!head.getTask().getPriority().equals(TaskPriority.HIGH)) {
        // if there is not then creates a new node and sets its next to the current
        // head\
        // this adds the node to the front of the list and then sets the new head to it
        LinkedTask linkedTaskHead = new LinkedTask(task, head);
        head = linkedTaskHead;
        size++;
      } else {
        // loops through the list until it reaches the last HIGH
        Task node = head.getTask();
        LinkedTask listNode = head;
        while (node.getPriority().equals(TaskPriority.HIGH)
            && listNode.getNext() != null
            && listNode.getNext().getTask().getPriority().equals(TaskPriority.HIGH)) {
          listNode = listNode.getNext();
          node = listNode.getTask();
        }
        // once it reaches the last high it creates a new node with its next pointing to
        // the current listNodes next node
        LinkedTask nextLinkedTask = new LinkedTask(task, listNode.getNext());
        // then sets the current listnodes next to the new node putting the new node
        // right after it
        listNode.setNext(nextLinkedTask);
        size++;
        // checks to make sure the tail is not changing and if it is then set the tail
        // to the new node
        if (tail.equals(listNode)) {
          tail = nextLinkedTask;
        }
      }

      // same exact process but for the medium priority
    } else if (task.getPriority().equals(TaskPriority.MEDIUM)) {
      if (head.getTask().getPriority().equals(TaskPriority.LOW)) {
        // checks for if there are no medium or highs in the list
        LinkedTask linkedTask = new LinkedTask(task, head);
        head = linkedTask;
        size++;
      } else {

        // goes to the last medium or high if there is no medium
        LinkedTask listNode = head;
        while (listNode.getNext() != null
            && (listNode.getNext().getTask().getPriority().equals(TaskPriority.HIGH)
                || listNode.getNext().getTask().getPriority().equals(TaskPriority.MEDIUM))) {
          listNode = listNode.getNext();
        }
        // ads the new node to the end of the mediums/highs
        LinkedTask nextLinkedTask = new LinkedTask(task, listNode.getNext());
        listNode.setNext(nextLinkedTask);
        size++;
        if (tail.equals(listNode)) {
          tail = nextLinkedTask;
        }
      }
    } else {
      // if the priority is low then just add the node to the end of the list
      // it does this by creating a new node with the nesxt pointer to null
      LinkedTask linkedTask = new LinkedTask(task, null);
      // it its the tailnode and sets that node to the new node created
      LinkedTask tailTask = tail;
      tailTask.setNext(linkedTask);
      // sets the tail to the new node
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
  @Override
  public Task get(int index) {
    // makes sure the index is in the range and if not throws and exception
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    // traverses through the list until it gets to the index
    LinkedTask linkedTask = head;
    for (int i = 0; i < index; i++) {
      linkedTask = linkedTask.getNext();
    }
    // returns the task at that index
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
  @Override
  public boolean contains(Task task) {
    // traverses through the list until it finds the task and if it does not returns
    // false
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
  @Override
  public Task remove(int index) {
    // makes sure the index is in the range of the list
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    LinkedTask linkedTask = head;
    // if the size is one then just clear it because after its removed there is
    // nothing left
    if (size == 1) {
      clear();
      return linkedTask.getTask();
    }
    // if it wants to remove the head then just move head up one
    if (index == 0) {
      head = linkedTask.getNext();
      size--;
      return linkedTask.getTask();
    }

    // goes to the index -1 of what is wanting to be removed
    for (int i = 0; i < index - 1; i++) {
      linkedTask = linkedTask.getNext();
    }
    // if its the tail then set the tail aswell
    if (tail.equals(linkedTask.getNext())) {
      tail = linkedTask;
    }
    // skips over the next node in over to cut it out of the linked list and retuns
    // the task getting removed
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
    // sets all values to null or 0
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
   * @return - the data of the list
   */
  public String toString() {
    // checks to see if it is empty and if it is return EMPTY
    if (size == 0) {
      return "EMPTY";
    }

    // crestes a blank string then adds all of the nodes to it using the nodes
    // toString method then gets the next node and repeats
    String result = "";
    LinkedTask linkedTask = head;
    for (int i = 0; i < size; i++) {
      result += linkedTask;
      linkedTask = linkedTask.getNext();
    }

    return result;
  }

}