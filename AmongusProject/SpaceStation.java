import processing.core.PImage;
/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 2
 * Citations: None
 */

/**
 * This class draws the among us game and lets you interact with many parts of
 * the characters.
 * You can add or remove players, kill them, and drag them around.
 */
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

  /**
   * this methods draws the game and the crew members
   * 
   */
  public static void draw() {
    // draw the background image, centered in the application window
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    // loops through the crew members and draws them all
    for (int i = 0; i < crew.length; i++) {
      if (crew[i] != null) {
        crew[i].draw();
      }
    }

    System.out.println(overlap(crew[0], crew[1]));

    // this loops through the array to find a imposter
    // if it does it then loops through the array again to see if the imposter is
    // touching any of the crew players
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

  /**
   * this method checks if keys a, i, or r is pressed
   * if any of them are pressed then it acts accordinly either spawning a player,
   * imposter
   * or removing one of them
   * 
   * @param key takes a key from the player
   */
  public static void keyPressed(char key) {
    // if either a or i is pressed then get a random number between 1 and 3 that
    // will act as the color
    // then uses the key to check whether the new character is an imposter or not
    // loops through the crew array until it finds an open slot
    // if it does it sets that open slot to the new crew member then stops the loop
    if (key == 'a' || key == 'i') {
      int rndNum = Utility.randGen.nextInt(3) + 1;
      boolean imposter = key == 'a' ? false : true;
      for (int i = 0; i < crew.length; i++) {
        if (crew[i] == null) {
          crew[i] = new Crewmate(rndNum, Utility.mouseX(), Utility.mouseY(), imposter);
          break;
        }
      }

      // if the key is r then run through the loop to check each crew member to see if
      // the mouse is over it using the isMouseOver method.
      // if it is then set that spot in the array to null then stop the loop to
      // prevent multiple crew members getting deleted from one click
    } else if (key == 'r') {
      for (int i = 0; i < crew.length; i++) {
        if (crew[i] != null && isMouseOver(crew[i])) {
          crew[i] = null;
          break;
        }
      }
    }
  }

  /**
   * checks to see if the mouse if over a crewmate
   * 
   * @param mate - the mate passed into the paramater is the one that gets checked
   *             to see if the mouse is over
   * @return true if the mouse is over the mate passed into the paramater
   */
  public static boolean isMouseOver(Crewmate mate) {
    if (mate == null) {
      return false;
    }
    // variables for the positions of the mouse and crewmate
    int mouseX = Utility.mouseX();
    int mouseY = Utility.mouseY();
    float leftX = mate.getX() - 60;
    float rightX = mate.getX() + 60;
    float top = mate.getY() + 60;
    float bottom = mate.getY() - 60;

    // checks to see if the mouse is in the box by making sure the x is to the left
    // of the right boundary and to the right of the left boundary
    // checks the same for the y values aswell
    if (mouseX >= leftX && mouseX <= rightX && mouseY <= top && mouseY >= bottom) {
      return true;
    }
    return false;
  }

  /**
   * when the mouse is pressed it checks if the mouse is over a character and if
   * it does then let the character drag it
   * 
   */
  public static void mousePressed() {
    // loops throught the crewmate array and checks to see if the mouse if over it
    // if it is then call the start dragging method on it which allows it to be
    // dragged
    // then stops the array so that multiple crewmates dont get dragged
    for (int i = 0; i < crew.length; i++) {
      if (crew[i] != null && isMouseOver(crew[i])) {
        System.out.println(overlap(crew[0], crew[1]));
        crew[i].startDragging();
        break;
      }
    }
  }

  /**
   * when the mouse is realsed it calls the stop dragging on all characters in the
   * array
   */
  public static void mouseReleased() {
    // loops through the array and if the index of the array isn't null then calls
    // the stop dragging method on it stopping the dragging of the crewmate
    for (int i = 0; i < crew.length; i++) {
      if (crew[i] != null) {
        crew[i].stopDragging();
      }
    }
  }

  /**
   * gets two crewmates and sees if they overlap
   * 
   * @param mate1 - crewmate used to check overlap with other
   * @param mate2 - other crewmate that get checked if its overlapping with
   * @return true if the two crewmates overlap
   */
  public static boolean overlap(Crewmate mate1, Crewmate mate2) {
    if (mate1 == null || mate2 == null) {
      return false;
    }
    // sets the boundaries to compare for the two crewmates
    float mateOneLeftBoundary = mate1.getX() - 60;
    float mateOneRightBoundary = mate1.getX() + 60;
    float mateTwoLeftBoundary = mate2.getX() - 60;
    float mateTwoRightBoundary = mate2.getX() + 60;

    float onetop = mate1.getY() + 60;
    float onebot = mate1.getY() - 60;
    float twotop = mate2.getY() + 60;
    float twobot = mate2.getY() - 60;

    if ((mateOneLeftBoundary >= mateTwoRightBoundary && onetop >= twobot && mateOneLeftBoundary <= mateTwoLeftBoundary
        && onetop <= twotop)
        || (mateTwoLeftBoundary >= mateOneRightBoundary && twotop >= onebot
            && mateOneLeftBoundary <= mateOneLeftBoundary && twotop <= onetop)) {
      return true;
    }

    if ((mateOneRightBoundary >= mateTwoLeftBoundary && onetop >= twobot && mateOneRightBoundary <= mateTwoRightBoundary
        && onetop <= twotop)
        || (mateTwoRightBoundary >= mateOneLeftBoundary && twotop >= onebot
            && mateTwoRightBoundary <= mateOneRightBoundary && twotop <= onetop)) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    Utility.runApplication();
  }
}