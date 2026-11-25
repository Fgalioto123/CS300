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
   * TODO:
   * Constructor initializes iterator to the first task in sorted order.
   *
   * @param schedule schedule being iterated
   * @throws IllegalArgumentException if schedule is null
   */
  public ScheduleIterator(CareSchedule schedule) {
  }

  /**
   * TODO:
   * Returns whether one more task is available.
   *
   * @return true if next() can be called safely
   */
  @Override
  public boolean hasNext() {
    return false;
  }

  /**
   * TODO:
   * Returns next task and advances iterator state.
   *
   * @return next task in sorted order
   * @throws NoSuchElementException if no next task exists
   */
  @Override
  public CareTask next() {
    return null;
  }
}
