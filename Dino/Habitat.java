import java.util.ArrayList;

/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall term 2026
 * Assignment: Program 05
 * Citations: None
 */

/**
 * Represents a specific physical enclosure or living environment within the
 * park.
 *
 * A Habitat tracks its size, the staff assigned to it, and a collection of
 * dinosaurs currently residing within it. It implements SafetyCheckable
 * to provide audits on space requirements and species compatibility.
 *
 */
public class Habitat implements SafetyCheckable {
  private String name;
  private double area;
  private int parkRangers;

  // A private list to store the Dinosaurs currently in this habitat.
  private ArrayList<Dinosaur> dinosaurs;

  /**
   * Constructs a new Habitat with the specified name, size, and staffing level.
   * 
   * @param name        The name of the habitat; must not be null or blank.
   * @param area        The total square footage/meters; must be greater than 0.
   * @param parkRangers The number of rangers assigned; must be non-negative.
   * @throws IllegalArgumentException if validation rules are violated.
   */
  public Habitat(String name, double area, int parkRangers) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException();
    }

    if (area <= 0) {
      throw new IllegalArgumentException();
    }

    if (parkRangers < 0) {
      throw new IllegalArgumentException();
    }

    this.name = name;
    this.area = area;
    this.parkRangers = parkRangers;
    this.dinosaurs = new ArrayList<Dinosaur>();
  }

  // --- Getters ---

  /**
   * Retrieves the name of the habitat
   * 
   * @return The name of the habitat.
   */
  public String getName() {
    return name;
  }

  /**
   * Retrieves the area of the habitat
   * 
   * @return The total area of the habitat.
   */
  public double getArea() {
    return area;
  }

  /**
   * Retrieves the number of park rangers in this habitat
   * 
   * @return The number of rangers assigned to this habitat.
   */
  public int getParkRangers() {
    return parkRangers;
  }

  /**
   * Returns a copy of the list of dinosaurs in this habitat.
   * 
   * @return A new ArrayList containing the resident dinosaurs.
   */
  public ArrayList<Dinosaur> getDinosaurs() {
    ArrayList<Dinosaur> copyList = new ArrayList<>(dinosaurs);
    return copyList;
  }

  /**
   * Adds a dinosaur to this habitat.
   * 
   * @param dinosaur The dinosaur to add.
   * @throws IllegalArgumentException if the dinosaur is null.
   */
  public void addDinosaur(Dinosaur dinosaur) {
    if (dinosaur == null) {
      throw new IllegalArgumentException();
    }

    dinosaurs.add(dinosaur);
  }

  /**
   * Removes a dinosaur from the habitat by its unique ID.
   * 
   * @param dinoId The ID of the dinosaur to remove.
   * @return The removed Dinosaur.
   * @throws IllegalArgumentException if dinoId is null or blank.
   * @throws DinoNotFoundException    if no dinosaur with that ID exists in this
   *                                  habitat.
   */
  public Dinosaur removeDinosaur(String dinoId) throws DinoNotFoundException {
    if (dinoId == null || dinoId.isBlank()) {
      throw new IllegalArgumentException();
    }

    for (Dinosaur d : dinosaurs) {
      if (d.getId().equals(dinoId)) {
        dinosaurs.remove(d);
        return d;
      }
    }
    throw new DinoNotFoundException("The id is" +
        "not found");

  }

  /**
   * Evaluates human safety based on staffing ratios.
   *
   * Violations occur if:
   * 1. The area per ranger is too high (e.g., area > rangers * 10).
   * 2. There are more dinosaurs than rangers.
   *
   * @return The total count of human safety violations.
   */
  @Override
  public int countHumanSafetyViolations() {
    int totalViolations = 0;

    if (area > parkRangers * 10) {
      totalViolations++;
    }

    if (dinosaurs.size() > parkRangers) {
      totalViolations++;
    }

    return totalViolations;
  }

  /**
   * Evaluates dinosaur safety based on space and social compatibility.
   *
   * Violations occur if:
   * 1. The sum of all dinosaurs' preferredArea exceeds the habitat's area.
   * 2. Any two dinosaurs in the habitat cannot coexist (check every pair).
   *
   * @return The total count of dinosaur safety violations.
   */
  @Override
  public int countDinoSafetyViolations() {
    int totalViolations = 0;
    int totalAreaRequired = 0;

    for (Dinosaur d : dinosaurs) {
      totalAreaRequired += d.getPreferredArea();
    }
    if (totalAreaRequired > area) {
      totalViolations++;
    }

    int value = 0;
    for (int i = 0; i < dinosaurs.size(); i++) {
      for (int j = i + 1; j < dinosaurs.size(); j++) {
        if (!dinosaurs.get(i).canCoexistWith(dinosaurs.get(j))) {
          value = 1;
          break;
        }
      }
    }

    totalViolations += value;

    return totalViolations;
  }

  /**
   * Returns a detailed string representing the habitat and all its residents.
   * 
   * @return A formatted multi-line string.
   */
  @Override
  public String toString() {
    StringBuilder habitatString = new StringBuilder(String.format(
        "Habitat: %s, area=%f m^2, parkRangers=%d\n",
        this.name,
        this.area,
        this.parkRangers));
    for (Dinosaur dinosaur : this.dinosaurs) {
      habitatString.append(String.format("%s\n", dinosaur.toString()));
    }
    return habitatString.toString();
  }
}
