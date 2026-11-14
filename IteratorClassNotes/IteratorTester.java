package IteratorClassNotes;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.EmptyStackException;

interface StackADT<Type> {
  public void push(Type newValue);

  public Type pop();

  public Type peek();

  public boolean isEmpty();
}

class StackIterator<T> implements Iterator<T> {
  // data fields
  private StackADT<T> stack; // this should be a deep copy of the
                             // stack because we're going to
                             // destroy it

  public StackIterator(StackADT<T> stack) {
    this.stack = stack;
  }

  /**
   * Returns true if the stack being iterated over
   * has more elements remaining
   */
  @Override
  public boolean hasNext() {
    return !(stack.isEmpty());
  }

  /**
   * Returns the element at the top of the stack and
   * advances the iterator
   */
  @Override
  public T next() throws NoSuchElementException {
    return stack.pop();
  }
}

class IterableStack<T> implements StackADT<T>, Iterable<T> {
  // data fields
  private ArrayList<T> data;

  public IterableStack() {
    data = new ArrayList<>();
  }

  // -------------------- ITERATOR STUFF ---------------------- //
  /**
   * Creates a new IterableStack which contains a deep copy of
   * this stack's data
   */
  public IterableStack(IterableStack<T> toCopy) {
    // TODO: use ArrayList's copy constructor so that
    // this.data is a deep copy of toCopy.data
    this.data = new ArrayList<T>(toCopy.data);
  }

  // from Iterable:
  @Override
  public Iterator<T> iterator() {
    // TODO: modify this method so it uses your copy constructor
    // instead of directly exposing the stack's internal data
    return new StackIterator(new IterableStack(this));
  }
  // ----------------- END ITERATOR STUFF --------------------- //

  // add method - adds to top of stack in O(1)
  public void push(T newValue) {
    data.add(newValue); // adds at the end of the array
  }

  // remove method - removes from top of stack in O(1)
  public T pop() {
    try {
      return data.remove(data.size() - 1);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new EmptyStackException();
    }
  }

  // get method - accesses value at top of stack in O(1)
  public T peek() {
    try {
      return data.get(data.size() - 1);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new EmptyStackException();
    }
  }

  // is the thing empty
  public boolean isEmpty() {
    return data.isEmpty();
  }
}

public class IteratorTester {
  // a simple testing method - no need to modify this
  public static void main(String[] args) {
    IterableStack<String> testStack = new IterableStack<>();
    testStack.push("Q");
    testStack.push("B");
    testStack.push("A");
    // A B Q -- should print twice
    for (String s : testStack) {
      System.out.print(s + " ");
    }
    for (String s : testStack) {
      System.out.print(s + " ");
    }
  }
}