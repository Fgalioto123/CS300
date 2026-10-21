/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall term 2026
 * Assignment: Program 05
 * Citations: None
 */

/**
 * extends dinsoaur class to make a subclass of a type of dinosaur
 */
public class TRex extends Dinosaur {

  /**
   * constructor for dinosaur
   * 
   * @param id   - id of dino
   * @param name - name of dino
   * @param age  - age of dino
   */
  public TRex(String id, String name, int age) {
    super(id, name, age, FoodType.MEAT, 20);
  }

  /**
   * (non-Javadoc)
   * overdides the previous roar
   * 
   * @see Dinosaur#roar()
   */
  @Override
  public String roar() {
    return "ROOOAAAR!";
  }

  /**
   * (non-Javadoc)
   * overrides the coexist method
   * 
   * @see Dinosaur#canCoexistWith(Dinosaur)
   */
  @Override
  public boolean canCoexistWith(Dinosaur dinosaur) {
    return dinosaur == this ? true : false;
  }
}
