import java.util.ArrayList;

public class GradeBook {
  private ArrayList<Student> students;

  public GradeBook() {
    this.students = new ArrayList<>();
  }
  
  public void addStudent(Student student) {
    //TODO complete
  }

  public double getClassAverage() throws NoGradesException {
    return 0.0; //TODO complete
  }

  public ArrayList<Student> getAllStudents() {
    // Create a defensive copy to maintain encapsulation
    return new ArrayList<>(students);
  }
}