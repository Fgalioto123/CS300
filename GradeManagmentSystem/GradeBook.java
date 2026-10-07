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
 * the gradebook class keeps a list of students which then it can access and use
 * for different methods like getting a class average or a report.
 * 
 */
public class GradeBook {
  private ArrayList<Student> students;

  /**
   * constructor for the class
   */
  public GradeBook() {
    this.students = new ArrayList<>();
  }

  /**
   * this method adds a student after checking to make sure the student exists. It
   * also checks by going through each current student that there is no
   * duplication before adding.
   * 
   * @param student - the student wanting to be added
   * @throws DuplicateStudentException - thrown if the student already exists in
   *                                   the students list
   */
  public void addStudent(Student student) throws DuplicateStudentException {
    if (student == null) {
      throw new IllegalArgumentException();
    }

    for (Student stud : students) {
      if (stud.getStudentId().equals(student.getStudentId())) {
        throw new DuplicateStudentException("Duplicate Student IDs");
      }
    }

    students.add(student);
  }

  /**
   * finds a student using their id by going through the current students list. if
   * it is found then it gets returned if not an exception gets thrown. Also
   * checks to make sure the id is not blank or null
   * 
   * @param studentId - the id of the student
   * @return - the student that is trying to be found if correctly found
   * @throws StudentNotFoundException - if the student is not found it throws this
   *                                  exception
   */
  public Student findStudent(String studentId) throws StudentNotFoundException {
    if (studentId == null || studentId.isBlank()) {
      throw new IllegalArgumentException();
    }

    for (Student stud : students) {
      if (stud.getStudentId().equals(studentId)) {
        return stud;
      }
    }

    throw new StudentNotFoundException("Student you are trying to " +
        " find with that ID does not exist");
  }

  /**
   * this method gets the class average by adding up all the grades for each
   * student then dividing it by the amount of grades that were added
   * 
   * @return - returns the average grade if all went succesfully
   * @throws NoGradesException - thrown if all the students dont have any grades
   */
  public double getClassAverage() throws NoGradesException {
    if (students.isEmpty()) {
      throw new NoGradesException("There are no students when you are trying " +
          " to get the class average.");
    }

    double avg = 0.0;
    double counter = 0.0;
    boolean gradesExist = false;

    for (Student stud : students) {
      for (Double doubl : stud.getGrades()) {
        avg += doubl;
        gradesExist = true;
        counter += 1.0;
      }
    }

    if (!gradesExist) {
      throw new NoGradesException("The students do not have any grades");
    }

    return avg / counter;
  }

  /**
   * gets the students
   * 
   * @return - returns the students
   */
  public ArrayList<Student> getAllStudents() {
    // Create a defensive copy to maintain encapsulation
    return new ArrayList<>(students);
  }

  /**
   * this method returns the number of honors students by going through each
   * student in the students list and checking if there grade average is above or
   * equal to 90.0.
   * 
   * @return - returns the list of students that are honors
   */
  public ArrayList<Student> getHonorsStudents() {
    ArrayList<Student> honorsStudents = new ArrayList<>();

    for (Student stud : students) {
      try {
        if (stud.getAverage() >= Student.A_THRESHOLD) {
          honorsStudents.add(stud);
        }
      } catch (NoGradesException e) {
        // System.out.println("Student has no grades but trying to get avg caught." +
        // " Resuming honors student calculation.");
      }
    }

    return honorsStudents;
  }

  /**
   * generates a report by re creating the same string variable over and over
   * again. uses \n to create a new line for each string added.
   * 
   * @return - returns the string which is the report.
   */
  public String generateReport() {
    String report = "=== GRADEBOOK REPORT ===";
    if (students.size() == 0) {
      report += "\n failed";
      return report;
    }

    if (students.isEmpty()) {
      return report += "\nNo students in gradebook.";
    }

    int totalStudents = students.size();
    report += "\nTotal Students:" + totalStudents;
    report += "\nStudent Details:";
    report += "\n================";
    for (Student stud : students) {
      report += "\n" + stud.toString();
    }

    double classAverage;
    try {
      classAverage = getClassAverage();
      report += "\nClass Average: " + classAverage;
    } catch (NoGradesException e) {
      System.out.println("got error when getting grades");
    }

    ArrayList<Student> honorStudents = getHonorsStudents();
    report += "\nHonors Students (A average): " + honorStudents.size();
    for (Student stud : honorStudents) {
      report += "\n - " + stud.getName();
    }

    report += "\n================";
    return report;
  }
}