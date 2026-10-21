import java.util.ArrayList;

/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall term 2026
 * Assignment: Program 05
 * Citations: None
 */

/**
 * This class holds a list of all the objeects that implement safetyCheckable
 * This class serves as the main class that uses everything else together
 * also generates a safety report
 */
public class DinoPark {
  private final ArrayList<SafetyCheckable> safetyCheckables = new ArrayList<>();

  /**
   * adds stuff to list
   * 
   * @param attraction - what you are adding
   */
  public void addAttraction(SafetyCheckable attraction) {
    if (attraction == null) {
      throw new IllegalArgumentException();
    }

    safetyCheckables.add(attraction);
  }

  /**
   * gives a safety report
   * 
   * @return - returns the report
   */
  public String generateSafetyReport() {
    String report = "=== SAFETY REPORT ===\n";

    for (SafetyCheckable safety : safetyCheckables) {
      report += safety.toString();
      report += "Human Safety Violations: " + safety.countHumanSafetyViolations();
      report += "\nDinosaur Safety Violations: " + safety.countDinoSafetyViolations() + "\n";
    }

    return report;
  }
}
