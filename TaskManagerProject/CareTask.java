
/**
 * This class models one care task used by CareSchedule.
 *
 * Ordering rule:
 * - compare urgency first
 * - if urgency ties, compare task name
 */
public class CareTask implements Comparable<CareTask> {

  private int urgencyLevel;
  private String taskName;

  /**
   * Creates a task.
   *
   * @param urgencyLevel urgency level
   * @param taskName     task label
   * @throws IllegalArgumentException if urgency is negative or name is blank
   */
  public CareTask(int urgencyLevel, String taskName) {
    if (urgencyLevel < 0 || taskName == null || taskName.trim().isEmpty()) {
      throw new IllegalArgumentException("Invalid task parameters.");
    }
    this.urgencyLevel = urgencyLevel;
    this.taskName = taskName;
  }

  /**
   * @return urgency level of this task
   */
  public int getUrgencyLevel() {
    return urgencyLevel;
  }

  /**
   * @return task name of this task
   */
  public String getTaskName() {
    return taskName;
  }

  /**
   *
   * @param other other task to compare with
   * @return negative if this is smaller, 0 if equal, positive if larger
   * @throws IllegalArgumentException if other is null
   */
  @Override
  public int compareTo(CareTask other) {
    if (other == null) {
      throw new IllegalArgumentException("Cannot compare to null task.");
    }
    if (this.urgencyLevel < other.urgencyLevel) {
      return -1;
    } else if (this.urgencyLevel > other.urgencyLevel) {
      return 1;
    }
    return this.taskName.compareTo(other.taskName);
  }

  @Override
  public String toString() {
    return "[" + urgencyLevel + "] " + taskName;
  }
}
