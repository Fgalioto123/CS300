public class TRex extends Dinosaur {

  public TRex(String id, String name, int age) {
    super(id, name, age, FoodType.MEAT, 20);
  }

  @Override
  public String roar() {
    return "ROOOAAAR!";
  }

  @Override
  public boolean canCoexistWith(Dinosaur dinosaur) {
    return dinosaur == this ? true : false;
  }
}
