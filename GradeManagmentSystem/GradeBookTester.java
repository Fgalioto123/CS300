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
 * Test class for GradeBook system functionality.
 * Tests both Student and GradeBook classes with comprehensive coverage
 * including edge cases, exception handling and normal cases.
 */
public class GradeBookTester {

  /**
   * Calls the testing methods and prints out the results.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("=== GRADEBOOK TESTING SUITE ===\n");

    boolean allPassed = allTests();

    System.out.println("\n=== FINAL TEST RESULTS ===");
    if (allPassed) {
      System.out.println("ALL TESTS PASSED!");
    } else {
      System.out.println("Some tests failed. Review output above.");
    }
  }

  /**
   * Calls all the individual testing methods.
   * 
   * @return true if all tests passed, false if any test failed.
   */
  public static boolean allTests() {
    boolean allPassed = true;
    allPassed &= testAddGradeValidation();
    allPassed &= testAverageCalculation();
    allPassed &= testStudentManagement();
    allPassed &= testClassAverageCalculation();
    allPassed &= testHonorsIdentification();
    allPassed &= testCompleteWorkflow();
    return allPassed;
  }

  public static boolean testAddGradeValidation() {
    System.out.println("Testing addGrade validation...");

    try {
      Student student = new Student("Fox", "001");
      student.addGrade(91.00);
      System.out.println("Correctly added grade");
    } catch (Exception e) {
      System.out.println("Caught an error trying to add a grade");
      return false;
    }

    try {
      Student student = new Student("Fox", "001");
      student.addGrade(-91.00);
      return false;
    } catch (Exception e) {
      System.out.println("Caught an error trying to add a grade (Correct)");
    }

    try {
      Student student = new Student("Fox", "001");
      student.addGrade(991.00);
      return false;
    } catch (Exception e) {
      System.out.println("Caught an error trying to add a grade (Correct)");
    }

    try {
      Student student = new Student("Fox", "001");
      student.addGrade(Double.MAX_VALUE);
      return false;
    } catch (Exception e) {
      System.out.println("Caught an error trying to add a grade (Correct)");
    }

    return true;
  }

  // ---- PROVIDED
  public static boolean testAverageCalculation() {
    System.out.println("Testing average calculation...");
    boolean allTestsPassed = true;

    // Test average with several valid grades
    // - manually calculated: (80+90+85+70)/4 = 81.25
    try {
      Student student = new Student("Average Test", "004");
      student.addGrade(80.0);
      student.addGrade(90.0);
      student.addGrade(85.0);
      student.addGrade(70.0);

      double average = student.getAverage();
      if (Math.abs(average - 81.25) > 0.001) {
        System.out.println("Average calculation incorrect - expected 81.25, got "
            + average);
        allTestsPassed = false;
      } else {
        System.out.println("Multiple grades average calculated correctly (81.25)");
      }
    } catch (Exception e) {
      System.out.println("Average calculation threw unexpected exception: " + e.getMessage());
      allTestsPassed = false;
    }

    // Test single grade average
    try {
      Student student = new Student("Single Grade", "005");
      student.addGrade(95.0);

      double average = student.getAverage();
      if (Math.abs(average - 95.0) > 0.001) {
        System.out.println("Single grade average incorrect - expected 95.0, got "
            + average);
        allTestsPassed = false;
      } else {
        System.out.println("Single grade average calculated correctly (95.0)");
      }
    } catch (Exception e) {
      System.out.println("Single grade average threw unexpected exception: "
          + e.getMessage());
      allTestsPassed = false;
    }

    // Test NoGradesException when no grades exist
    try {
      Student emptyStudent = new Student("No Grades", "006");
      emptyStudent.getAverage();
      System.out.println("getAverage() with no grades should throw NoGradesException");
      allTestsPassed = false;
    } catch (NoGradesException e) {
      if (e.getMessage() == null || e.getMessage().isBlank()) {
        System.out.println("No message found in NoGradesException");
        allTestsPassed = false;
      }
      System.out.println("No grades correctly throws NoGradesException");
    } catch (Exception e) {
      System.out.println("No grades threw wrong exception type: "
          + e.getClass().getSimpleName());
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testStudentManagement() {
    System.out.println("Testing student add/find operations...");

    GradeBook gradeBook = new GradeBook();
    try {
      gradeBook.addStudent(new Student("Fox", "001"));
      gradeBook.addStudent(new Student("Bob", "002"));
    } catch (DuplicateStudentException e) {
      System.out.println("Duplicate student was attempted to add");
      return false;
    }

    try {
      Student newStudent = gradeBook.findStudent("001");
    } catch (IllegalArgumentException e) {
      System.out.println("The id number was either blank or null(incorrect)");
      return false;
    } catch (StudentNotFoundException e) {
      System.out.println("Student was not found(incorrect)");
      return false;
    }

    try {
      gradeBook.addStudent(new Student("Fox", "001"));
      return false;
    } catch (DuplicateStudentException e) {
      System.out.println("Caught duplicate student trying to be added (correct)");
    }

    // TODO: Test StudentNotFoundException when searching non-existent ID
    try {
      Student newStudent = gradeBook.findStudent("005");
    } catch (StudentNotFoundException e) {
      System.out.println("Student not found with that ID (correct)");
    }

    return true;
  }

  public static boolean testClassAverageCalculation() {
    System.out.println("Testing class average calculation...");
    boolean allTestsPassed = true;

    // Create 2 students with grades and test class average
    // Student 1: 85, 95 (avg: 90)
    // Student 2: 70, 80, 90 (avg: 80)
    // Class average: (85+95+70+80+90)/5 = 84.0
    try {
      GradeBook gradeBook = new GradeBook();
      Student student1 = new Student("High Achiever", "600");
      Student student2 = new Student("Steady Worker", "700");

      student1.addGrade(85.0);
      student1.addGrade(95.0);
      student2.addGrade(70.0);
      student2.addGrade(80.0);
      student2.addGrade(90.0);

      gradeBook.addStudent(student1);
      gradeBook.addStudent(student2);

      double classAverage = gradeBook.getClassAverage();
      if (Math.abs(classAverage - 84.0) > 0.001) {
        System.out.println("Class average incorrect - expected 84.0, got "
            + classAverage);
        allTestsPassed = false;
      } else {
        System.out.println("Class average calculated correctly (84.0)");
      }
    } catch (Exception e) {
      System.out.println("Class average calculation threw unexpected exception: "
          + e.getMessage());
      allTestsPassed = false;
    }

    // Test NoGradesException with empty gradebook
    try {
      GradeBook emptyGradeBook = new GradeBook();
      emptyGradeBook.getClassAverage();
      System.out.println("Empty gradebook should throw NoGradesException");
      allTestsPassed = false;
    } catch (NoGradesException e) {
      if (e.getMessage() == null || e.getMessage().isBlank()) {
        System.out.println("No message found in NoGradesException");
        allTestsPassed = false;
      }
      System.out.println("Empty gradebook correctly throws NoGradesException");
    } catch (Exception e) {
      System.out.println("Empty gradebook threw wrong exception type: "
          + e.getClass().getSimpleName());
      allTestsPassed = false;
    }

    // Test NoGradesException with students but no grades
    try {
      GradeBook gradeBook = new GradeBook();
      Student studentNoGrades1 = new Student("No Grades 1", "800");
      Student studentNoGrades2 = new Student("No Grades 2", "900");
      gradeBook.addStudent(studentNoGrades1);
      gradeBook.addStudent(studentNoGrades2);

      gradeBook.getClassAverage();
      System.out.println("Students with no grades should throw NoGradesException");
      allTestsPassed = false;
    } catch (NoGradesException e) {
      if (e.getMessage() == null || e.getMessage().isBlank()) {
        System.out.println("No message found in NoGradesException");
        allTestsPassed = false;
      }
      System.out.println("Students with no grades correctly throws NoGradesException");
    } catch (Exception e) {
      System.out.println("Students with no grades threw wrong exception type: "
          + e.getClass().getSimpleName());
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testHonorsIdentification() {
    System.out.println("Testing honors student identification...");

    try {
      GradeBook gradeBook = new GradeBook();

      Student studentOne = new Student("Fox", "001");
      studentOne.addGrade(91);
      studentOne.addGrade(96);
      gradeBook.addStudent(studentOne);

      Student studentTwo = new Student("Bob", "002");
      studentTwo.addGrade(71);
      studentTwo.addGrade(86);
      gradeBook.addStudent(studentTwo);

      Student studentThree = new Student("Jeff", "003");
      gradeBook.addStudent(studentThree);

      ArrayList<Student> honors = gradeBook.getHonorsStudents();
      // need to fix honors getting added.
      if (honors.size() != 1) {
        System.out.println("Honors students returned more than 1 (incorrect)");
        return false;
      }

      if (honors.get(0).getName() != "Fox") {
        System.out.println("Wrong honors student was gotten");
        return false;
      }

    } catch (Exception e) {
      System.out.println("Honors identification threw unexpected exception: "
          + e.getMessage());
      return false;
    }

    return true;
  }

  public static boolean testCompleteWorkflow() {
    System.out.println("Testing complete workflow...");

    GradeBook gradeBook = new GradeBook();

    try {
      Student studentOne = new Student("Fox", "001");
      studentOne.addGrade(90);
      studentOne.addGrade(90);
      gradeBook.addStudent(studentOne);

      Student studentTwo = new Student("Bob", "002");
      studentTwo.addGrade(80);
      studentTwo.addGrade(80);
      gradeBook.addStudent(studentTwo);

      Student studentThree = new Student("Jeff", "003");
      gradeBook.addStudent(studentThree);

      Student testStudent = gradeBook.findStudent("001");
      double avg = gradeBook.getClassAverage();

      try {
        GradeBook testBook = new GradeBook();
        testBook.getClassAverage();
        System.out.println("Ran getClassAverage method and it did not " +
            " return an error when it should have on an empty students array");
        return false;
      } catch (Exception e) {
        System.out.println("Correctly got an error when running getClassAverage");
      }

      if (gradeBook.getHonorsStudents().size() != 1) {
        System.out.println("Gradebook got either 0 or more than one honors students");
        return false;
      }

      System.out.println(gradeBook.generateReport());
      if (gradeBook.generateReport().isBlank()) {
        System.out.println("Gradebook returned empty when it should have not");
        return false;
      }

    } catch (InvalidGradeException e) {
      System.out.println("Grade was not able to be added (incorrect)");
      return false;
    } catch (DuplicateStudentException e) {
      System.out.println("Duplicate student was found to be added (incorrect)");
      return false;
    } catch (StudentNotFoundException e) {
      System.out.println("Student was not found (inccorect)");
      return false;
    } catch (NoGradesException e) {
      System.out.println("No grades were found for any of the students (incorrect)");
      return false;
    }

    // TODO: Calls generateReport(), verifies report has some student information
    // and verifies no crashes

    return true;
  }
}