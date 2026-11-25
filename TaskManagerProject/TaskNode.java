/**
 * Node class used by CareSchedule.
 *
 * NOTE:
 * This support class is not part of the student implementation focus.
 * It stores one task plus references to left and right children.
 */
public class TaskNode {
  private CareTask data;
  private TaskNode left;
  private TaskNode right;

  /**
   * Constructor.
   *
   * @param data task stored in this node
   */
  public TaskNode(CareTask data) {
    if (data == null) {
      throw new IllegalArgumentException("TaskNode data cannot be null.");
    }
    this.data = data;
    this.left = null;
    this.right = null;
  }

  // Getters
  /**
   * @return task stored in this node
   */
  public CareTask getData() {
    return data;
  }

  /**
   * @return left child reference
   */
  public TaskNode getLeft() {
    return left;
  }

  /**
   * @return right child reference
   */
  public TaskNode getRight() {
    return right;
  }

  // Setters
  /**
   * @param data new task to store
   */
  public void setData(CareTask data) {
    if (data == null) {
      throw new IllegalArgumentException("TaskNode data cannot be null.");
    }
    this.data = data;
  }

  /**
   * @param left new left child reference
   */
  public void setLeft(TaskNode left) {
    this.left = left;
  }

  /**
   * @param right new right child reference
   */
  public void setRight(TaskNode right) {
    this.right = right;
  }
}
