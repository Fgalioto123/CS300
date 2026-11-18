/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 8
 * Citations: None
 */

/**
 * The CommitQueue class represents a queue of commits to be pushed to a remote
 * repository.
 * This class provides basic queue operations such as enqueue, dequeue, peek,
 * clear, and contains.
 */
public class CommitQueue extends LinkedStructure<Commit> implements QueueADT<Commit> {

  /**
   * Add a new element to the back of the queue, assumed to be non-null.
   *
   * @param value the value to add
   */
  @Override
  public void enqueue(Commit value) {
    if (size == 0) {
      LinkedNode<Commit> firstNode = new LinkedNode<Commit>(value);
      head = firstNode;
      tail = firstNode;
      size++;
    } else {
      LinkedNode<Commit> linkedNode = new LinkedNode<Commit>(value);
      LinkedNode<Commit> tailLinkedNode = tail;
      tailLinkedNode.setNext(linkedNode);
      tail = linkedNode;
      size++;
    }

  }

  /**
   * Removes and returns the value added to this queue least recently.
   *
   * @return the least recently-added value, or null if the queue is empty
   */
  @Override
  public Commit dequeue() {
    if (size == 0) {
      return null;
    }
    if (size == 1) {
      LinkedNode<Commit> lastNode = head;
      clear();
      return lastNode.getData();
    }

    LinkedNode<Commit> linkedNode = head;
    head = head.getNext();
    size--;
    return linkedNode.getData();
  }

  /**
   * Accesses the value added to this queue least recently, without modifying the
   * queue.
   *
   * @return the least recently-added value, or null if the queue is empty
   */
  @Override
  public Commit peek() {
    if (size == 0) {
      return null;
    }
    return head.getData();
  }

}
