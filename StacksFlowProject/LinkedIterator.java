/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 8
 * Citations: None
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic iterator for use with a data structure backed by a singly linked
 * list.
 *
 * @param <T> the type of data stored in each node of the corresponding linked
 *            data structure
 */
class LinkedIterator<T> implements Iterator<T> {

  LinkedNode<T> current;

  /**
   * Creates a new LinkedIterator over the provided linked list.
   * 
   * @param startNode the head of the linked list (or equivalent).
   */
  LinkedIterator(LinkedNode<T> startNode) {
    current = startNode;
  }

  /**
   * Checks if the next element in the linked data structure exists.
   * 
   * @return true only if the iterator has a "next" element
   */
  public boolean hasNext() {
    return current != null;
  }

  /**
   * Retrieves the data from the current node and advances the cursor.
   * 
   * @return the data from the current in the backing linked data structure
   */
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    T data = current.getData();
    current = current.getNext();
    return data;
  }
}