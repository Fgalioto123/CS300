import java.util.Arrays;

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
    // QUESTION: why doesn't this variable need to be declared here?
    crew = new Crewmate[NUM_PLAYERS];

    // TODO remove this line: add a green crewmate at the center of the window
    crew[3] = new Crewmate(Crewmate.GREEN);
  }

  public static void draw() {
    // draw the background image, centered in the application window
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    // TODO: draw each crewmate in the crew array at its current position
    for (int i = 0; i < crew.length; i++) {
      if (crew[i] != null) {
        crew[i].draw();
      }
    }

    // final TODO: if a crewmate is an impostor, check whether its image overlaps
    // any other crewmate's image and ...handle accordingly
  }

  public static void keyPressed(char key) {
    if (key == 'a' || key == 'i') {
      // TODO: add a new crewmate (or impostor!) at the current mouse location
    } else if (key == 'r') {
      // TODO: remove a crewmate if the mouse is over one
    }
  }

  public static boolean isMouseOver(Crewmate mate) {
    // TODO: check if the mouse is over the crewmate object in the parameter ONLY
    return false;
  }

  public static void mousePressed() {
    // TODO: take any actions required when the mouse is clicked
  }

  public static void mouseReleased() {
    // TODO: take any actions required when the mouse is released
  }

  public static boolean overlap(Crewmate mate1, Crewmate mate2) {
    // TODO: check whether the images of mate1 and mate2 overlap
    return false;

  }

  public static void main(String[] args) {
    Utility.runApplication();
  }
}