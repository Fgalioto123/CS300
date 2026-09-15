import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class exposes the Processing graphics library's functionality for static use in
 * pre-OOP CS300 GUI assignments
 * @author Mouna, Hobbes, Dahl
 */
public class Utility {
  protected static PApplet processing;
  public static Random randGen;
  
  // TODO: modify this variable with your project's main class name
  private static final String mainClass = "SpaceStation";

  /**
   * Creates a new graphical window for this program, calls FishTank.setup() once, 
   * and then repeatedly calls FishTank.draw() until the program is terminated.
   */
  public static void runApplication() {
    PApplet.main((Class<?>)Utility.Driver.class, new String[0]);
  }

  private static void processingCheck() {
        if (processing == null) {
            throw new IllegalStateException("Utility.runApplication() must be called before other Utility methods.");
        }
    }
  
  /**
   * Clears the entire application window by filling it with the specified color.
   * @param color the color being set
   */
  public static void background(final int color) {
    processingCheck();
    Utility.processing.background(color);
  }

  /**
   * Loads an image into a variable of type PImage. Four types of images ( .gif, .jpg, .tga, .png) 
   * may be loaded. To load correctly, images must be located in the current project directory. 
   * In most cases, load all images in setup() to preload them at the start of the program. Loading 
   * images inside draw() will reduce the speed of a program.
   * @param filename the name of the file to load. Can be .gif, .jpg, .tga, or a handful of other types
   * @return a reference of type processing PImage to the loaded image
   */
  public static PImage loadImage(final String filename) {
    processingCheck();
    return Utility.processing.loadImage(filename);
  }
 
  /**
   * Displays images to the screen.
   * @param img the image to display
   * @param x the x-coordinate of the center of the image
   * @param y the y-coordinate of the center of the image
   */
  public static void image(PImage img, float x, float y){
    processingCheck();
    Utility.processing.image(img, x, y, img.width*.8f, img.height*.8f);
  }

  public static void reverseImage(PImage img, float x, float y) {
    processingCheck();
        processing.pushMatrix();
        processing.scale(-1.0F, 1.0F);
        processing.image(img, -x, y, img.width*.8f, img.height*.8f);
        processing.popMatrix();
  }
    
  /**
   * Returns the current horizontal coordinate of the mouse.
   * @return the current x-position of the mouse
   */
  public static int mouseX() {
    processingCheck();
    return Utility.processing.mouseX;
  }
  
  /**
   * Returns the current vertical coordinate of the mouse.
   * @return the current y-position of the mouse
   */
  public static int mouseY() {
    processingCheck();
    return Utility.processing.mouseY;
  }
  
  /**
   * Returns the previous horizontal coordinate of the mouse
   * @return the previous x-position of the mouse
   */
  public static int pmouseX() {
    processingCheck();
    return Utility.processing.pmouseX;
  }

  /**
   * Returns the previous vertical coordinate of the mouse
   * @return the previous y-position of the mouse
   */
  public static int pmouseY() {
    processingCheck();
    return Utility.processing.pmouseY;
  }

  /**
   * Returns the width of the display window
   * @return the width of the display window
   */
  public static int width() {
    processingCheck();
    return Utility.processing.width;
  }
  
  /**
   * Returns the height of the display window
   * @return the height of the display window
   */
  public static int height() {
    processingCheck();
    return Utility.processing.height;
  }
  
  public static int color(final int red, final int green, final int blue) {
    processingCheck();
    return Utility.processing.color(red, green, blue);
  }

  public static int lerpColor(final int colorA, final int colorB, final float amount) {
    processingCheck();
    return Utility.processing.lerpColor(colorA, colorB, amount);
  }

  public static void fill(final int color) {
    processingCheck();
    Utility.processing.fill(color);
  }

  public static void fill(final int color, final int transparency) {
    processingCheck();
    Utility.processing.fill(color, (float) transparency);
  }
  
  public static void text(final String text, final float x, final float y) {
    processingCheck();
    Utility.processing.text(text, x, y);
  }
  
  public static void text(final String text, final float x, final float y, final float size) {
    processingCheck();
    Utility.processing.textSize(size);
    Utility.processing.text(text, x, y);
  }

  public static void circle(final float x, final float y, final float size) {
    processingCheck();
    Utility.processing.ellipse(x, y, size, size);
  }

  public static void rect(final float x, final float y, final float w, final float h) {
    processingCheck();
    Utility.processing.rect(x, y, w, h);
  }
  
  public static void save(final String filename) {
    processingCheck();
    Utility.processing.save(filename);
  }

  private Utility() {
  }

  /**
   * This method is only here to help display an error message when students attempt to run their 
   * program by executing the contents of this Jar file instead of their own class
   * @param args command line arguments
   */
  public static void main(final String[] args) {
    if (Utility.processing == null) {
      throw new IllegalStateException(
          "This jar file is not meant to be executed directly.\nEnsure that the Run Configuration in Eclipse references main in your "+mainClass+" class.");
    }
  }

  public static class Driver extends PApplet {
    private final int WINDOW_WIDTH = 800;
        private final int WINDOW_HEIGHT = 600;
        private final String WINDOW_TITLE = "CS300 Among Us";
    private boolean lastKeyPressed;
    private static boolean reportedFirstMissingMethodMessage;
    private boolean click = false;

    static {
      Driver.reportedFirstMissingMethodMessage = false;
    }

    public Driver() {
      this.lastKeyPressed = false;
    }

    public void settings() {
      this.size(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void setup() {
      // TODO: set the title to appear on the application window
      this.getSurface().setTitle(WINDOW_TITLE);
      
      this.textAlign(CENTER, CENTER);
      this.imageMode(CENTER);
      this.rectMode(CENTER);
      this.ellipseMode(2);
      this.noStroke();
      this.focused = true;
      Utility.processing = this;
      
      // TODO: set up the static PApplet reference for any classes which need it
      
      
      Utility.randGen = new Random();
      callMethod(mainClass, "setup");
    }

    public void draw() {
      callMethod(mainClass, "draw");
      if (this.mousePressed && !click) {
        callMethod(mainClass, "mousePressed");
        click = true;
      }
      if (this.keyPressed && !this.lastKeyPressed) {
        callMethod(mainClass, "keyPressed", this.key);
      }
      this.lastKeyPressed = this.keyPressed;
    }
    
    public void mouseReleased() {
      click = false;
      callMethod(mainClass, "mouseReleased");
    }

    private static Object callMethod(final String className, final String methodName,
        final Object... args) {
      try {
        Class<?>[] argTypes = new Class[0];
        try {
          final Class<?> studentClass = ClassLoader.getSystemClassLoader().loadClass(className);
          argTypes = getArgClasses(args);
          final Method studentMethod = findMethod(studentClass, methodName, argTypes);
          studentMethod.setAccessible(true);
          return studentMethod.invoke(null, args);
        } catch (InvocationTargetException e) {
          if (e.getTargetException() instanceof RuntimeException)
            throw (RuntimeException) e.getTargetException();
          final String msg = "Encountered trouble running " + className + "." + methodName
              + " with arguments: " + Arrays.toString(argTypes) + ".";
          throw new RuntimeException(msg, e);
        }
        catch (IllegalAccessException | ClassNotFoundException ex2) {
          final ReflectiveOperationException ex = null;
          final ReflectiveOperationException e = ex;
          final String msg = "Encountered trouble running " + className + "." + methodName
              + " with arguments: " + Arrays.toString(argTypes) + ".";
          throw new RuntimeException(msg, e);
        }
      } catch (Driver.MissingMethodException e2) {
        if (!Driver.reportedFirstMissingMethodMessage) {
          System.err.println("ERROR: " + e2.getMessage());
          Driver.reportedFirstMissingMethodMessage = true;
        }
        return null;
      } catch (RuntimeException e3) {
        if (e3.getCause() != null && e3.getCause().getCause() != null
            && e3.getCause().getCause() instanceof RuntimeException) {
          throw (RuntimeException) e3.getCause().getCause();
        }
        throw e3;
      }
    }

    private static Object callMethod(final String className, final String methodName) {
      return callMethod(className, methodName, (Object[]) null);
    }

    private static Class<?>[] getArgClasses(final Object[] args) {
      if (args == null || args.length == 0) {
        return (Class<?>[]) new Class[0];
      }
      final Class<?>[] classes = new Class[args.length];
      for (int i = 0; i < classes.length; ++i) {
        if (args[i] != null) {
          classes[i] = args[i].getClass();
        }
      }
      return (Class<?>[]) classes;
    }

    private static Method findMethod(final Class<?> theClass, final String methodName,
        final Class<?>[] argTypes) {
      return findMethodHelper(theClass, methodName, argTypes, theClass.getSimpleName());
    }

    private static Method findMethodHelper(Class<?> theClass, final String methodName,
        final Class<?>[] argTypes, final String originalClassName) {
      Method[] declaredMethods;
      for (int length = (declaredMethods = theClass.getDeclaredMethods()).length, j =
          0; j < length; ++j) {
        final Method m = declaredMethods[j];
        if (m.getName().equals(methodName)) {
          final Class<?>[] paramTypes = m.getParameterTypes();
          if (argTypes.length == paramTypes.length) {
            boolean parametersMatch = true;
            for (int i = 0; i < argTypes.length && parametersMatch; parametersMatch &=
                compatibleTypes(paramTypes[i], argTypes[i]), ++i) {
            }
            if (parametersMatch) {
              return m;
            }
          }
        }
      }
      theClass = theClass.getSuperclass();
      if (theClass != Object.class) {
        return findMethodHelper(theClass, methodName, argTypes, originalClassName);
      }
      throw new Driver.MissingMethodException(
          "Could not find method named " + methodName + " that can take arguments "
              + Arrays.toString(argTypes) + " in class " + originalClassName + ".");
    }

    private static boolean compatibleTypes(final Class<?> paramType, Class<?> argType) {
      if (argType == null) {
        return !paramType.isPrimitive();
      }
      if (paramType.isPrimitive()) {
        if (argType == Boolean.class) {
          argType = Boolean.TYPE;
        } else if (argType == Byte.class) {
          argType = Byte.TYPE;
        } else if (argType == Character.class) {
          argType = Character.TYPE;
        } else if (argType == Short.class) {
          argType = Short.TYPE;
        } else if (argType == Integer.class) {
          argType = Integer.TYPE;
        } else if (argType == Long.class) {
          argType = Long.TYPE;
        } else if (argType == Float.class) {
          argType = Float.TYPE;
        } else if (argType == Double.class) {
          argType = Double.TYPE;
        }
      }
      return paramType.isAssignableFrom(argType);
    }

    public static class MissingMethodException extends RuntimeException {
      public MissingMethodException(final String s) {
        super(s);
      }
    }
  }



  
  
}
