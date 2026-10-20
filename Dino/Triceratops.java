public class Triceratops extends Dinosaur {

  public Triceratops(String id, String name, int age) {
    super(id, name, age, FoodType.PLANTS, 6);
  }

  @Override
  public String roar() {
    return "HMMMPH!";
  }

  @Override
  public boolean canCoexistWith(Dinosaur dinosaur) {
    return dinosaur.getFoodType() != FoodType.MEAT ? true : false;
  }
}
