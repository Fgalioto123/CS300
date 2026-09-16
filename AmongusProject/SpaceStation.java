import processing.core.PImage;

public class SpaceStation {
  // Class variables, which persis for the entire run of the program
  private static PImage backgroundImage; // the spaceship lobby image
  private static Crewmate[] crew; // the crewmates in the spaceship
  private static final int NUM_PLAYERS = 8; // the maximum number of crewmates

  /**
   * This method is called by the GUI library one (1) time at the beginning of the
   * program.
   * We use it to initialize any class variables for the program.
   */
  public static void setup() {
    // initialize the background image using a method from Utility
    backgroundImage = Utility.loadImage("images/background.jpeg");

    // initialize the crew array to hold a maximum of NUM_PLAYERS
    // QUESTION: why doesn't this variable need to be declared here? because it is
    // already declared outsidee the methods at the top.
    crew = new Crewmate[NUM_PLAYERS];

  }

  public static void draw() {
    // draw the background image, centered in the application window
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    for (int i = 0; i < crew.length; i++) {
      if (crew[i] != null) {
        crew[i].draw();
      }
    }

    // final TODO: if a crewmate is an impostor, check whether its image overlaps
    for (int j = 0; j < crew.length; j++) {
      if (crew[j] != null && crew[j].isImpostor()) {
        for (int k = 0; k < crew.length; k++) {
          if (crew[k] != null && overlap(crew[j], crew[k])) {
            crew[k].unalive();
          }
        }
      }
    }

  }

  public static void keyPressed(char key) {
    if (key == 'a' || key == 'i') {
      int rndNum = Utility.randGen.nextInt(3) + 1;
      boolean imposter = key == 'a' ? false : true;
      for (int i = 0; i < crew.length; i++) {
        if (crew[i] == null) {
          crew[i] = new Crewmate(rndNum, Utility.mouseX(), Utility.mouseY(), imposter);
          break;
        }
      }
    } else if (key == 'r') {
      for (int i = 0; i < crew.length; i++) {
        if (crew[i] != null && isMouseOver(crew[i])) {
          crew[i] = null;
        }
      }
    }
  }

  public static boolean isMouseOver(Crewmate mate) {
    int mouseX = Utility.mouseX();
    int mouseY = Utility.mouseY();
    float leftX = mate.getX() - 60;
    float rightX = mate.getX() + 60;
    float top = mate.getY() + 60;
    float bottom = mate.getY() - 60;
    if (mouseX >= leftX && mouseX <= rightX && mouseY <= top && mouseY >= bottom) {
      return true;
    }
    return false;
  }

  public static void mousePressed() {
    for (int i = 0; i < crew.length; i++) {
      if (crew[i] != null && isMouseOver(crew[i])) {
        System.out.println(overlap(crew[0], crew[1]));
        crew[i].startDragging();
        break;
      }
    }
  }

  public static void mouseReleased() {
    for (int i = 0; i < crew.length; i++) {
      if (crew[i] != null) {
        crew[i].stopDragging();
      }
    }
  }

  public static boolean overlap(Crewmate mate1, Crewmate mate2) {
    float mateOneLeftBoundary = mate1.getX() - 60;
    float mateOneRightBoundary = mate1.getX() + 60;
    float mateTwoLeftBoundary = mate2.getX() - 60;
    float mateTwoRightBoundary = mate2.getX() + 60;

    if ((mateOneLeftBoundary >= mateTwoLeftBoundary && mateOneLeftBoundary <= mateTwoRightBoundary)
        || (mateOneRightBoundary <= mateTwoRightBoundary && mateOneRightBoundary >= mateTwoLeftBoundary)) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    Utility.runApplication();
  }
}