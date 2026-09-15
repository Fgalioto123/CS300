import java.io.File;
import processing.core.PImage;

/**
 * Provided Crewmate object for P2. This draggable object can represent a normal or impostor Crewmate
 * of several potential colors. When an impostor is dragged over a normal Crewmate, the normal one
 * becomes "dead" and the displayed image changes.
 */
public class Crewmate {
    
    // data fields
    private final boolean IS_IMPOSTOR;
    private boolean isDead;
    private float x;
    private float y;
    private final int COLOR;
    private PImage image;
    private PImage deadImage;
    private boolean isDragging;
    
    //private static float oldMouseX;
    //private static float oldMouseY;

  // translates the numerical system for colors for improved usability
  public static final int RED = 1;
  public static final int GREEN = 2;
  public static final int BLACK = 3;
    
    // constructors
    /**
     * Basic constructor to create your first normal Crewmate. This Crewmate appears at the center of the
     * game window.
     * @param color an integer representing the color index of this Crewmate, in {1,2,3}
     */
    public Crewmate(int color) {
        this(color, Utility.width()/2, Utility.height()/2, false);
    }
    
    /**
     * Advanced constructor, which allows creation of Crewmate at specified (x,y) locations and also
     * impostor Crewmate.
     * @param color an integer representing the color index of this Crewmate, in {1,2,3}
     * @param x the initial x-coordinate of this Crewmate
     * @param y the initial y-coordinate of this Crewmate
     * @param isImpostor true if this Crewmate is an impostor, false otherwise
     */
    public Crewmate(int color, float x, float y, boolean isImpostor) {
        if (color < 1 || color > 3) throw new IllegalArgumentException("Color must be 1, 2, or 3;"+
                " cannot create a crewmate with color "+color);
        COLOR = color;
        IS_IMPOSTOR = isImpostor;
        isDead = false;
        this.x = x;
        this.y = y;
        image = Utility.loadImage("images"+File.separator+"sprite"+COLOR+".png");
        deadImage = Utility.loadImage("images"+File.separator+"dead"+COLOR+".png");
    }
    
    // callback
    /**
     * Causes this Crewmate to be drawn to the game window. This method should be called in your
     * SpaceStation's draw() method for all non-null Crewmate objects.
     */
    public void draw() {
        if (this.isDragging) {
            float dx = Utility.mouseX()-Utility.pmouseX();
            float dy = Utility.mouseY()-Utility.pmouseY();
            this.x += dx;
            this.y += dy;
        }
        if (IS_IMPOSTOR) { Utility.reverseImage((isDead ? this.deadImage : this.image), x, y); }
        else { Utility.image((isDead ? this.deadImage : this.image), x, y); }
    }
    
    // accessors
    /**
     * Access the current x-coordinate of this Crewmate
     * @return the current x-coordinate of this Crewmate
     */
    public float getX() { return this.x; }
    
    /**
     * Access the current y-coordinate of this Crewmate
     * @return the current y-coordinate of this Crewmate
     */
    public float getY() { return this.y; }
    
    /**
     * Reports whether this Crewmate believes it is currently being dragged
     * @return true if this Crewmate is being dragged, false otherwise
     */
    public boolean isDragging() { return this.isDragging; }
    
    /**
     * Reports whether this Crewmate is an impostor
     * @return true if this Crewmate is an impostor, false otherwise
     */
    public boolean isImpostor() { return this.IS_IMPOSTOR; }
    
    /**
     * Provides the current image (dead or alive) of this Crewmate in the correct color
     * @return a reference to the correct image for this Crewmate given its state
     */
    public PImage image() { return isDead ? this.deadImage : this.image; }
    
    // mutators
    /**
     * Update the x-coordinate of this Crewmate
     * @param x the new x-coordinate for this Crewmate
     */
    public void setX(float x) { this.x = x; }
    
    /**
     * Update the y-coordinate of this Crewmate
     * @param y the new y-coordinate for this Crewmate
     */
    public void setY(float y) { this.y = y; }
    
    /**
     * Sets the dragging status of this Crewmate to true and begins recording mouse movements
     */
    public void startDragging() { 
        this.isDragging = true; 
    }
    
    /**
     * Sets the dragging status of this Crewmate to false
     */
    public void stopDragging() { this.isDragging = false; }
    
    /**
     * After calling this method, this Crewmate will be dead. This action is not reversible.
     */
    public void unalive() { if (!IS_IMPOSTOR) this.isDead = true; }

}
