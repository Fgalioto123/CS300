import java.util.ArrayList;

public class GradeBook {
  private ArrayList<Student> students;

  public GradeBook() {
    this.students = new ArrayList<>();
  }

  public void addStudent(Student student) throws DuplicateStudentException {
    if (student == null) {
      throw new IllegalArgumentException();
    }

    for (Student s : students) {
      if (s.getStudentId().equals(student.getStudentId())) {
        throw new DuplicateStudentException("Duplicate Student IDs");
      }
    }

    students.add(student);
  }

  public Student findStudent(String studentId) throws StudentNotFoundException {
    if (studentId == null || studentId.isBlank()) {
      throw new IllegalArgumentException();
    }

    for (Student s : students) {
      if (s.getStudentId().equals(studentId)) {
        return s;
      }
    }

    throw new StudentNotFoundException("Student you are trying to " +
        " find with that ID does not exist");
  }

  public double getClassAverage() throws NoGradesException {
    if (students.isEmpty()) {
      throw new NoGradesException("There are no students when you are trying " +
          " to get the class average.");
    }

    double avg = 0.0;
    double counter = 0.0;
    boolean gradesExist = false;

    for (Student s : students) {
      for (Double d : s.getGrades()) {
        avg += d;
        gradesExist = true;
        counter += 1.0;
      }
    }

    if (!gradesExist) {
      throw new NoGradesException("The students do not have any grades");
    }

    return avg / counter;
  }

  public ArrayList<Student> getAllStudents() {
    // Create a defensive copy to maintain encapsulation
    return new ArrayList<>(students);
  }

  public ArrayList<Student> getHonorsStudents() {
    ArrayList<Student> honorsStudents = new ArrayList<>();

    for (Student s : students) {
      try {
        if (s.getAverage() >= Student.A_THRESHOLD) {
          honorsStudents.add(s);
        }
      } catch (NoGradesException e) {
        System.out.println("Student having no grades but trying to get avg caught." +
            " Resuming honors student calculation.");
      }
    }

    return honorsStudents;
  }

  public String generateReport() {
    String report = "=== GRADEBOOK REPORT ===";

    if (students.isEmpty()) {
      return report += "\nNo students in gradebook.";
    }

    int totalStudents = students.size();
    report += "\nTotal Students: " + totalStudents;
    report += "\nStudent Details: ";
    report += "\n================  ";
    for (Student s : students) {
      report += "\n" + s.toString();
    }

    double classAverage;
    try {
      classAverage = getClassAverage();
      report += "\nClass Average: " + classAverage;
    } catch (NoGradesException e) {
    }

    ArrayList<Student> honorStudents = getHonorsStudents();
    report += "\nHonors Students (A average): " + honorStudents.size();
    for (Student s : honorStudents) {
      System.out.println(" - " + s.getName());
    }

    report += "\n================";
    return report;
  }
}