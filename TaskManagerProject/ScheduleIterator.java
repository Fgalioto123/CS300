/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 9
 * Citations: None
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.text.Caret;

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
    // returns if current has a next by seeing if schedule.getNexTask returns null
    // or not
    // it would return null if there is no next task which then returns false
    return schedule.getNextTask(current) != null;
  }

  /**
   * Returns next task and advances iterator state.
   *
   * @return next task in sorted order
   * @throws NoSuchElementException if no next task exists
   */
  @Override
  public CareTask next() {
    // first gets the next task

    CareTask task = schedule.getNextTask(current);
    if (task == null) {
      throw new NoSuchElementException();
    }
    // sets currents equal to it
    current = task;
    // returns the new task
    return task;

  }
}
