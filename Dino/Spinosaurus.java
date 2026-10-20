public class Spinosaurus extends Dinosaur {

  public Spinosaurus(String id, String name, int age) {
    super(id, name, age, FoodType.MEAT, 16);
  }

  @Override
  public String roar() {
    return "GRRRRR!";
  }

  @Override
  public boolean canCoexistWith(Dinosaur dinosaur) {
    if (dinosaur == this) {
      return true;
    }

    return dinosaur.canCoexistWith(this) ? true : false;
  }
}