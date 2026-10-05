import java.util.ArrayList;

public class Student {

  // Grade scale constants
  public static final double A_THRESHOLD = 90.0;
  public static final double B_THRESHOLD = 80.0;
  public static final double C_THRESHOLD = 70.0;
  public static final double D_THRESHOLD = 60.0;
  public static final double MIN_GRADE = 0.0;
  public static final double MAX_GRADE = 100.0;

  private String name;
  private String studentId;
  private ArrayList<Double> grades;

  public Student(String name, String studentId) {
    // TODO Complete initialization of every instance field
  }

  public double getAverage() {
    // TODO Complete implementation
    return 0.0;
  }

  public String getLetterGrade() {
    // TODO Complete implementation
    return "";
  }
  
  public String getName() {
    return name;
  }

  public String getStudentId() {
    return studentId;
  }

  public ArrayList<Double> getGrades() {
    // Create a defensive copy to maintain encapsulation
    return new ArrayList<>(grades);
  }

  /**
   * Returns a string representation of the student including name, ID, average, and letter grade.
   * If no grades are recorded, indicates that no grades are available.
   *
   * @return a formatted string containing student information
   */
  @Override public String toString() {
    try {
      return String.format(
        "%s (ID: %s) - Average: %.2f (%s)",
        name,
        studentId,
        getAverage(),
        getLetterGrade()
      );
    } catch (NoGradesException e) {
      return String.format("%s (ID: %s) - No grades recorded", name, studentId);
    }
  }
}