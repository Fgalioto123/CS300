/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 8
 * Citations: None
 */

/**
 * The CommitStack class represents a stack of commits that have been pushed to
 * a remote repository.
 * This class provides basic stack operations such as push, pop, peek, clear,
 * and contains.
 */
public class CommitStack extends LinkedStructure<Commit> implements StackADT<Commit> {

  /**
   * Add a new element to the top of this stack, assumed to be non-null.
   *
   * @param value the value to add
   */
  @Override
  public void push(Commit value) {
    if (size == 0) {
      LinkedNode<Commit> firstNode = new LinkedNode<Commit>(value);
      head = firstNode;
      tail = firstNode;
      size++;

    } else {
      LinkedNode<Commit> node = head;
      LinkedNode<Commit> newNode = new LinkedNode<Commit>(value, node);
      size++;
      head = newNode;
    }
  }

  /**
   * Removes and returns the value added to this stack most recently.
   *
   * @return the most recently-added value, or null if the stack is empty
   */
  @Override
  public Commit pop() {
    if (size == 1) {
      LinkedNode<Commit> lastNode = head;
      clear();
      return lastNode.getData();
    }
    LinkedNode<Commit> node = head;
    LinkedNode<Commit> newNode = head.getNext();
    head = newNode;
    size--;
    return node.getData();
  }

  /**
   * Accesses the value added to this stack most recently, without modifying the
   * stack.
   *
   * @return the most recently-added value, or null if the stack is empty
   */
  @Override
  public Commit peek() {
    return head.getData();
  }

  /**
   * Print all commits in this CommitStack in reverse chronological order (newest
   * first).
   */
  public void printAllCommitsNewestFirst() {
    LinkedNode<Commit> node = head;
    while (node != null) {
      System.out.println(node.getData());
      node = node.getNext();
    }
  }

  /**
   * Print all commits in this CommitStack in chronological order (oldest first).
   */
  public void printAllCommitsOldestFirst() {
    printRecursion(head);
  }

  /**
   * helper method for the printallOldestfirst
   * 
   * @param - node
   */
  private void printRecursion(LinkedNode<Commit> node) {
    if (node == null) {
      return;
    }

    printRecursion(node.getNext());
    System.out.println(node.getData());
  }
}
