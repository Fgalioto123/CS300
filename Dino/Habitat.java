import java.util.ArrayList;

/**
 * Represents a specific physical enclosure or living environment within the park.
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
   * @param name The name of the habitat; must not be null or blank.
   * @param area The total square footage/meters; must be greater than 0.
   * @param parkRangers The number of rangers assigned; must be non-negative.
   * @throws IllegalArgumentException if validation rules are violated.
   */
  public Habitat(String name, double area, int parkRangers) {
    // TODO: Implement validation for name, area, and parkRangers.
    // TODO: Initialize fields and instantiate the dinosaur collection.
  }

  // --- Getters ---

  /**
   * Retrieves the name of the habitat
   * @return The name of the habitat.
   */
  public String getName() {
    // TODO: Return the habitat name
    return null;
  }

  /**
   * Retrieves the area of the habitat
   * @return The total area of the habitat.
   */
  public double getArea() {
    // TODO: Return the area
    return 0.0;
  }

  /**
   * Retrieves the number of park rangers in this habitat
   * @return The number of rangers assigned to this habitat.
   */
  public int getParkRangers() {
    // TODO: Return the ranger count
    return 0;
  }

  /**
   * Returns a copy of the list of dinosaurs in this habitat.
   * @return A new ArrayList containing the resident dinosaurs.
   */
  public ArrayList<Dinosaur> getDinosaurs() {
    // TODO: Return a defensive copy of the dinosaur list to prevent external
    // modification.
    return null;
  }

  /**
   * Adds a dinosaur to this habitat.
   * @param dinosaur The dinosaur to add.
   * @throws IllegalArgumentException if the dinosaur is null.
   */
  public void addDinosaur(Dinosaur dinosaur) {
    // TODO: Validate input and add to the internal list.
  }

  /**
   * Removes a dinosaur from the habitat by its unique ID.
   * @param dinoId The ID of the dinosaur to remove.
   * @return The removed Dinosaur.
   * @throws IllegalArgumentException if dinoId is null or blank.
   * @throws DinoNotFoundException if no dinosaur with that ID exists in this habitat.
   */
  public Dinosaur removeDinosaur(String dinoId) throws DinoNotFoundException {
    // TODO: Validate dinoId.
    // TODO: Search the list for the dinosaur with the matching ID.
    // TODO: If found, remove it from the list and return it.
    // TODO: If the loop finishes without finding it, throw a DinoNotFoundException.
    return null;
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
    // TODO: Implement the safety math logic described in the Javadoc above.
    return 0;
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
    // TODO: Calculate total area required by all residents and compare to habitat
    // area.

    // TODO: Check if any resident is incompatible with any
    // other resident using the canCoexistWith() method.

    return 0;
  }

  /**
   * Returns a detailed string representing the habitat and all its residents.
   * @return A formatted multi-line string.
   */
  @Override
  public String toString() {
    StringBuilder habitatString = new StringBuilder(String.format(
        "Habitat: %s, area=%f m^2, parkRangers=%d\n",
        this.name,
        this.area,
        this.parkRangers
    ));
    for (Dinosaur dinosaur : this.dinosaurs) {
      habitatString.append(String.format("%s\n", dinosaur.toString()));
    }
    return habitatString.toString();
  }
}
