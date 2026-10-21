/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall term 2026
 * Assignment: Program 05
 * Citations: None
 */

/**
 * 
 * Spinosaurus class extends dino to create a new subclass of it
 */
public class Spinosaurus extends Dinosaur {

  /**
   * contructor for spinosaurus
   * 
   * @param id   - id of dino
   * @param name - name of dino
   * @param age  - age of dino
   */
  public Spinosaurus(String id, String name, int age) {
    super(id, name, age, FoodType.MEAT, 16);
  }

  /**
   * (non-Javadoc)
   * overrides roar
   * 
   * @see Dinosaur#roar()
   */
  @Override
  public String roar() {
    return "GRRRRR!";
  }

  /**
   * (non-Javadoc)
   * overrides coexist
   * 
   * @see Dinosaur#canCoexistWith(Dinosaur)
   */
  @Override
  public boolean canCoexistWith(Dinosaur dinosaur) {
    if (dinosaur instanceof Spinosaurus) {
      return true;
    }

    return dinosaur.canCoexistWith(this) ? true : false;
  }
}