/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall term 2026
 * Assignment: Program 05
 * Citations: None
 */

/**
 * 
 * Triceratops extends from dino. type of dino.
 */
public class Triceratops extends Dinosaur {

  /**
   * constructor for triceratops
   * 
   * @param id   - id of dino
   * @param name - id of name
   * @param age  - id of age
   */
  public Triceratops(String id, String name, int age) {
    super(id, name, age, FoodType.PLANTS, 6);
  }

  /**
   * (non-Javadoc)
   * overrides roar
   * 
   * @see Dinosaur#roar()
   */
  @Override
  public String roar() {
    return "HMMMPH!";
  }

  /**
   * (non-Javadoc)
   * overrides the previous canCoexist
   * 
   * @see Dinosaur#canCoexistWith(Dinosaur)
   */
  @Override
  public boolean canCoexistWith(Dinosaur dinosaur) {
    return dinosaur.getFoodType() != FoodType.MEAT ? true : false;
  }
}
