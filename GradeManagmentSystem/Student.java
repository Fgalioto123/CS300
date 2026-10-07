/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 4
 * Citations: Stack overflow for isBlank vs isEmpty
 * https://stackoverflow.com/questions/23419087/stringutils-isblank-vs-string-isempty
 */

import java.util.ArrayList;

/**
 * This class creates students that can be used to add to gradebook.
 * You can add grades to the students and get the average of their grades aswell
 */
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

  /**
   * Constructor for class. also sets the grades arratlist to a new arraylist
   * 
   * @param name      - name of student
   * @param studentId - student ID
   */
  public Student(String name, String studentId) {
    this.name = name;
    this.studentId = studentId;
    this.grades = new ArrayList<Double>();
  }

  /**
   * adds a grade to student.
   * 
   * @param grade - grade wanting to be added
   * @throws InvalidGradeException - throws if the grade you are
   *                               trying to add is higher or lower that the
   *                               max/min grade
   */
  public void addGrade(double grade) throws InvalidGradeException {
    if (grade > MAX_GRADE || grade < MIN_GRADE) {
      throw new InvalidGradeException("The grade is not valid");
    }
    grades.add(grade);
  }

  /**
   * gets the average of all the grades a student has by adding up all the grades
   * then returning them divided by how many grades there were.
   * 
   * @return - returns the grade as a double
   * @throws NoGradesException - returns if the grades array is blank. (trying to
   *                           get an average of grades that dont exist)
   */
  public double getAverage() throws NoGradesException {
    if (grades.isEmpty()) {
      throw new NoGradesException("There are no grades to " +
          " take an average from.");
    }

    double avg = 0;
    for (Double d : grades) {
      avg += d;
    }

    return avg / ((double) grades.size());
  }

  /**
   * gets the letter grade by using if and elif statements
   * 
   * @return - returns a string which is the letter grade
   * @throws NoGradesException - It will throw an error when
   *                           calling getAverage if
   *                           grades is empty.
   */
  public String getLetterGrade() throws NoGradesException {
    double avg = getAverage();
    if (avg >= A_THRESHOLD) {
      return "A";
    } else if (avg >= B_THRESHOLD) {
      return "B";
    } else if (avg >= C_THRESHOLD) {
      return "C";
    } else if (avg >= D_THRESHOLD) {
      return "D";
    } else {
      return "F";
    }
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
   * Returns a string representation of the student including name, ID, average,
   * and letter grade.
   * If no grades are recorded, indicates that no grades are available.
   *
   * @return a formatted string containing student information
   */
  @Override
  public String toString() {
    try {
      return String.format(
          "%s (ID: %s) - Average: %.2f (%s)",
          name,
          studentId,
          getAverage(),
          getLetterGrade());
    } catch (NoGradesException e) {
      return String.format("%s (ID: %s) - No grades recorded", name, studentId);
    }
  }
}