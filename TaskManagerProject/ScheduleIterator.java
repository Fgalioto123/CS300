/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 9
 * Citations: None
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for CareSchedule.
 * Traverses tasks in in-order (ascending) sequence.
 */
public class ScheduleIterator implements Iterator<CareTask> {

  private CareSchedule schedule;
  private CareTask current;

  /**
   * Constructor initializes iterator to the first task in sorted order.
   *
   * @param schedule schedule being iterated
   * @throws IllegalArgumentException if schedule is null
   */
  public ScheduleIterator(CareSchedule schedule) {
    if (schedule == null) {
      throw new IllegalArgumentException();
    }
    this.schedule = schedule;
    current = schedule.getFirstTask();
  }

  /**
   * Returns whether one more task is available.
   *
   * @return true if next() can be called safely
   */
  @Override
  public boolean hasNext() {
    // returns true if current is null or not
    return current != null;
  }

  /**
   * Returns next task and advances iterator state.
   *
   * @return next task in sorted order
   * @throws NoSuchElementException if no next task exists
   */
  @Override
  public CareTask next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    // first gets the next task
    CareTask task = current;
    current = schedule.getNextTask(current);
    // sets currents equal to it
    // returns the new task
    return task;

  }
}
