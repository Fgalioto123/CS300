/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall term 2026
 * Assignment: Program 05
 * Citations: None
 */

/**
 * Test class for DinoPark system functionality.
 * Tests DinoPark, Habitat, and LabTour classes with comprehensive coverage
 * including edge cases, exception handling and normal cases.
 */
public class DinoParkTester {

  /**
   * Calls the testing methods and prints out the results.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("=== DINOPARK TESTING SUITE ===\n");

    boolean allPassed = allTests();

    System.out.println("\n=== FINAL TEST RESULTS ===");
    if (allPassed) {
      System.out.println("ALL TESTS PASSED!");
    } else {
      System.out.println("Some tests failed. Review output above.");
    }
  }

  /**
   * Calls all the individual testing methods.
   *
   * @return true if all tests passed, false if any test failed.
   */
  public static boolean allTests() {
    boolean allPassed = true;
    allPassed &= testLabTourSafetyChecks();
    allPassed &= testVelociraptorCoexist();
    allPassed &= testTRexCoexist();
    allPassed &= testTriceratopsCoexist();
    allPassed &= testSpinosaurusCoexist();
    allPassed &= testHabitatAddValidation();
    allPassed &= testHabitatRemoveValidation();
    allPassed &= testHabitatHumanSafetyChecks();
    allPassed &= testHabitatDinoSafetyChecks();
    allPassed &= testCompleteWorkflow();
    return allPassed;
  }

  /**
   * tests the labtour
   * 
   * @return - false or true is the checks pass
   */
  public static boolean testLabTourSafetyChecks() {
    System.out.println("Testing LabTour safety checks...");
    boolean allTestsPassed = true;

    // PROVIDED SCENARIO (Normal Case): Test with total people UNDER capacity
    // Total people is 9, capacity is 10. Safety violations should be 0
    LabTour labTourUnderCapacity = new LabTour("Mr. DNA", 4);
    labTourUnderCapacity.addExperiment(new Experiment("Hatching raptors", 3));
    labTourUnderCapacity.addExperiment(new Experiment("Genome splicing", 2));

    int dinoViolationsUnderCapacity = labTourUnderCapacity.countDinoSafetyViolations();
    int humanViolationsUnderCapacity = labTourUnderCapacity.countHumanSafetyViolations();

    // Note that dino safety violations should *always* be zero,
    // since there should not be any dinos in the lab.
    if (dinoViolationsUnderCapacity != 0) {
      System.out.println("LabTour countDinoSafetyViolations incorrect for case: "
          + "Total people under capacity.\n"
          + "Expected: 0\n"
          + "Actual: " + dinoViolationsUnderCapacity);
      allTestsPassed = false;
    }
    if (humanViolationsUnderCapacity != 0) {
      System.out.println("LabTour countHumanSafetyViolations incorrect for case: "
          + "Total people under capacity.\n"
          + "Expected: 0\n"
          + "Actual: " + humanViolationsUnderCapacity);
      allTestsPassed = false;
    }

    LabTour labTourAboveCapacity = new LabTour("Mr. DNA", 6);
    labTourAboveCapacity.addExperiment(new Experiment("Hatching raptors", 3));
    labTourAboveCapacity.addExperiment(new Experiment("Genome splicing", 2));

    int dinoViolationsAboveCapacity = labTourAboveCapacity.countDinoSafetyViolations();
    int humanViolationsAboveCapacity = labTourAboveCapacity.countHumanSafetyViolations();

    // Note that dino safety violations should *always* be zero,
    // since there should not be any dinos in the lab.
    if (dinoViolationsAboveCapacity != 0) {
      System.out.println("LabTour countDinoSafetyViolations incorrect for case: "
          + "Total people under capacity.\n"
          + "Expected: 0\n"
          + "Actual: " + dinoViolationsUnderCapacity);
      allTestsPassed = false;
    }
    if (humanViolationsAboveCapacity != 1) {
      System.out.println("LabTour countHumanSafetyViolations incorrect for case: "
          + "Total people Above capacity.\n"
          + "Expected: 1\n"
          + "Actual: " + humanViolationsAboveCapacity);
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  // PROVIDED example test method for Velociraptor
  /**
   * tests velociraptor being able to coexist
   * 
   * @return - true or false
   */
  public static boolean testVelociraptorCoexist() {
    System.out.println("Testing Velociraptor canCoexistWith...");
    boolean allTestsPassed = true;

    Velociraptor velociraptor = new Velociraptor("raptor1", "Alan", 8);

    // PROVIDED: Test where otherDinosaur has food type MEAT. Should return true
    Velociraptor meatDinosaur = new Velociraptor("raptor2", "Radia", 12);
    boolean velociraptorCoexistWithMeat = velociraptor.canCoexistWith(meatDinosaur);
    if (!velociraptorCoexistWithMeat) {
      System.out.println("Velociraptor canCoexistWith incorrect for case: "
          + "Other Dinosaur has food type MEAT.\n"
          + "Expected: true\n"
          + "Actual: false");
      allTestsPassed = false;
    }

    // PROVIDED: Test where otherDinosaur has food type PLANTS. Should return false
    Brachiosaurus plantsDinosaur = new Brachiosaurus("longneck1", "Littlefoot", 4);
    boolean velociraptorCoexistWithPlants = velociraptor.canCoexistWith(plantsDinosaur);
    if (velociraptorCoexistWithPlants) {
      System.out.println("Velociraptor canCoexistWith incorrect for case: "
          + "Other Dinosaur has food type PLANTS.\n"
          + "Expected: false\n"
          + "Actual: true");
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testTRexCoexist() {
    System.out.println("Testing TRex canCoexistWith...");
    boolean allTestsPassed = true;

    TRex trex = new TRex("trex1", "Sue", 24);

    // TODO: Test calling canCoexistWith any other dinosaur. Should return false
    Spinosaurus spiny = new Spinosaurus("spiny1", "spiny", 21);
    boolean trexCoexistWithItself = trex.canCoexistWith(spiny);
    if (trexCoexistWithItself) {
      System.out.println("TRex canCoexistWith incorrect for case: "
          + "Can coexist with spiny.\n"
          + "Expected: false\n"
          + "Actual: true");
      allTestsPassed = false;
    }

    // PROVIDED SCENARIO: Test calling canCoexistWith on itself. Should return true
    // Uncomment once you have added the TRex class:

    trexCoexistWithItself = trex.canCoexistWith(trex);
    if (!trexCoexistWithItself) {
      System.out.println("TRex canCoexistWith incorrect for case: "
          + "Can coexist with itself.\n"
          + "Expected: true\n"
          + "Actual: false");
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * tests if triceratops can coexist
   * 
   * @return - true or false
   */
  public static boolean testTriceratopsCoexist() {
    System.out.println("Testing Triceratops canCoexistWith...");
    boolean allTestsPassed = true;

    Triceratops triny = new Triceratops("Triny1", "Triny", 12);

    Spinosaurus spiny = new Spinosaurus("spiny1", "spiny", 21);
    boolean trinyCoexistWithItself = triny.canCoexistWith(spiny);
    if (trinyCoexistWithItself) {
      System.out.println("Triceratops canCoexistWith incorrect for case: "
          + "Can coexist with spiny.\n"
          + "Expected: false\n"
          + "Actual: true");
      allTestsPassed = false;
    }
    // TODO: Test where other Dinosaur has food type MEAT. Should return false

    // TODO: Test where other Dinosaur has food type PLANTS. Should return true
    Brachiosaurus brachy = new Brachiosaurus("brachy1", "brachy", 5);
    trinyCoexistWithItself = triny.canCoexistWith(brachy);
    if (!trinyCoexistWithItself) {
      System.out.println("Triceratops canCoexistWith incorrect for case: "
          + "Can coexist with brachy.\n"
          + "Expected: true\n"
          + "Actual: false");
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * if spiny can coexist
   * 
   * @return - true or false
   */
  public static boolean testSpinosaurusCoexist() {
    System.out.println("Testing Spinosaurus canCoexistWith...");
    boolean allTestsPassed = true;

    // TODO: Test calling canCoexistWith on itself. Should return true
    Spinosaurus spiny = new Spinosaurus("spiny1", "spiny", 21);
    boolean spinyCoexistWithItself = spiny.canCoexistWith(spiny);
    if (!spinyCoexistWithItself) {
      System.out.println("Spiny canCoexistWith incorrect for case: "
          + "Can coexist with spiny.\n"
          + "Expected: true\n"
          + "Actual: false");
      allTestsPassed = false;
    }

    // TODO: Test calling canCoexistWith on a different Spinosaurus. Should return
    // true
    Spinosaurus spiny2 = new Spinosaurus("spiny2", "spiny2", 22);
    spinyCoexistWithItself = spiny.canCoexistWith(spiny2);
    if (!spinyCoexistWithItself) {
      System.out.println("Spiny canCoexistWith incorrect for case: "
          + "Can coexist with spiny2.\n"
          + "Expected: true\n"
          + "Actual: false");
      allTestsPassed = false;
    }

    // TODO: Test where other Dinosaur's canCoexistWith returns true. Should return
    // true.
    Brachiosaurus brachy = new Brachiosaurus("brachy1", "brachy", 5);
    spinyCoexistWithItself = spiny.canCoexistWith(brachy);
    if (!spinyCoexistWithItself) {
      System.out.println("Spiny canCoexistWith incorrect for case: "
          + "Can coexist with brachy.\n"
          + "Expected: true\n"
          + "Actual: false");
      allTestsPassed = false;
    }

    // TODO: Test where other Dinosaur's canCoexistWith returns false. Should return
    // false.
    Triceratops triny = new Triceratops("Triny1", "Triny", 12);
    spinyCoexistWithItself = spiny.canCoexistWith(triny);
    if (spinyCoexistWithItself) {
      System.out.println("Spiny canCoexistWith incorrect for case: "
          + "Can coexist with triny.\n"
          + "Expected: false\n"
          + "Actual: true");
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * testing adding stuff to habitat
   * 
   * @return - true or false
   */
  public static boolean testHabitatAddValidation() {
    System.out.println("Testing Habitat addDinosaur validation...");
    boolean allTestsPassed = true;

    Habitat habitat = new Habitat("habitat1", 1, 1);

    // PROVIDED SCENARIO (Normal Case): Test adding a valid dinosaur to a habitat
    try {
      // Code that should NOT throw exception
      habitat.addDinosaur(new Velociraptor("raptor1", "Alan", 2));

      // Check that the dino was actually added
      if (habitat.getDinosaurs().size() != 1) {
        allTestsPassed = false;
      } else if (!habitat.getDinosaurs().get(0).getId().equals("raptor1")) {
        allTestsPassed = false;
      }
    } catch (Exception e) {
      // Should not throw any exception
      System.out.println("Habitat addDinosaur with a valid dinosaur threw exception: "
          + e.getMessage());
      allTestsPassed = false;
    }

    try {
      habitat.addDinosaur(null);
      allTestsPassed = false;
    } catch (IllegalArgumentException e) {
      System.out.println("Exepction caught like expected when adding a null to habitat");
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * testing removing dinos from habitat
   * 
   * @return - true or false
   */
  public static boolean testHabitatRemoveValidation() {
    System.out.println("Testing Habitat removeDinosaur validation...");
    boolean allTestsPassed = true;

    // TODO: Normal Case: Test removing a valid dinosaur from the habitat
    Habitat habitat = new Habitat("habitat", 20, 2);
    habitat.addDinosaur(new Spinosaurus("spiny1", "spiny", 2));
    try {
      habitat.removeDinosaur("spiny1");
    } catch (Exception e) {
      System.out.println("Exeption caught when none was supposed to be thrown");
      allTestsPassed = false;
    }

    // TODO: Test trying to remove null. Should throw IllegalArgumentException
    try {
      habitat.removeDinosaur(null);
      allTestsPassed = false;
    } catch (Exception e) {
      System.out.println("succesfsully caught exeption");
    }

    // TODO: Test trying to remove dino that doesn't exist. Should throw
    // DinoNotFoundException
    try {
      habitat.removeDinosaur("spiny2");
      allTestsPassed = false;
    } catch (DinoNotFoundException e) {
      System.out.println("Succesfully caught exeption");
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * testing the human safety check method
   * 
   * @return - true or false
   */
  public static boolean testHabitatHumanSafetyChecks() {
    System.out.println("Testing Habitat human safety checks...");
    boolean allTestsPassed = true;

    // PROVIDED SCENARIO (Normal Case): Test with sufficient rangers. Should return
    // 0
    Habitat habitatEnoughRangers = new Habitat("habitat1", 4, 3);
    habitatEnoughRangers.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatEnoughRangers.addDinosaur(new Velociraptor("raptor2", "Ellie", 2));
    habitatEnoughRangers.addDinosaur(new Velociraptor("raptor3", "Ian", 2));

    int violationsEnoughRangers = habitatEnoughRangers.countHumanSafetyViolations();

    if (violationsEnoughRangers != 0) {
      System.out.println("Habitat countHumanSafetyViolations incorrect for case: "
          + "Enough rangers for area and dinosaurs.\n"
          + "Expected: 0\n"
          + "Actual: " + violationsEnoughRangers);
      allTestsPassed = false;
    }

    // PROVIDED SCENARIO (Edge Case): Test with enough rangers for the number of
    // dinosaurs,
    // but not enough for area size
    Habitat habitatAreaTooLarge = new Habitat("habitat2", 40, 3);

    int violationsAreaTooLarge = habitatAreaTooLarge.countHumanSafetyViolations();

    if (violationsAreaTooLarge != 1) {
      System.out.println("Habitat countHumanSafetyViolations incorrect for case: "
          + "Not too many dinosaurs but area too large.\n"
          + "Expected: 1\n"
          + "Actual: " + violationsAreaTooLarge);
      allTestsPassed = false;
    }

    // TODO: Test with enough rangers for habitat area, but not enough for the
    // number of dinosaurs
    Habitat habitat3 = new Habitat("habitat3", 10, 1);
    habitat3.addDinosaur(new Spinosaurus("spiny1", "Spiny", 10));
    habitat3.addDinosaur(new Spinosaurus("spiny2", "Spiny Jr", 10));

    if (habitat3.countHumanSafetyViolations() != 1) {
      System.out.println("Should be 1 but got " + habitat3.countDinoSafetyViolations());
      allTestsPassed = false;
    }

    // TODO: Test with not enough rangers for habitat area AND not enough for the
    // number of dinosaurs
    Habitat habitat4 = new Habitat("habitat4", 15, 1);
    habitat4.addDinosaur(new Spinosaurus("spiny1", "Spiny", 10));
    habitat4.addDinosaur(new Spinosaurus("spiny2", "Spiny Jr", 10));

    if (habitat4.countHumanSafetyViolations() != 2) {
      System.out.println("Should be 2 but got " + habitat4.countDinoSafetyViolations());
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * tests the dino safety checks
   * 
   * @return - true of false
   */
  public static boolean testHabitatDinoSafetyChecks() {
    System.out.println("Testing Habitat Dinosaur safety checks...");
    boolean allTestsPassed = true;

    // PROVIDED SCENARIO (Normal Case): Test with sufficient area and all dinos can
    // coexist.
    // Should return 0
    Habitat habitatEnoughAreaAndCoexist = new Habitat("habitat1", 16, 0);
    habitatEnoughAreaAndCoexist.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatEnoughAreaAndCoexist.addDinosaur(new Velociraptor("raptor2", "Ellie", 2));

    int dinoSafetyEnoughAreaAndCoexist = habitatEnoughAreaAndCoexist.countDinoSafetyViolations();

    if (dinoSafetyEnoughAreaAndCoexist != 0) {
      System.out.println("Habitat countDinoSafetyViolations incorrect for case: "
          + "Enough area and all dinos can coexist.\n"
          + "Expected: 0\n"
          + "Actual: " + dinoSafetyEnoughAreaAndCoexist);
      allTestsPassed = false;
    }

    // TODO: Test with all dinos can coexist but not enough area
    Habitat habitat = new Habitat("habitat", 16, 2);
    habitat.addDinosaur(new Spinosaurus("spiny1", "Spiny", 10));
    habitat.addDinosaur(new Spinosaurus("spiny2", "Spiny Jr", 10));

    if (habitat.countDinoSafetyViolations() != 1) {
      System.out.println("total violations should be 1 but got "
          + habitat.countDinoSafetyViolations() + " for dino safety");
      allTestsPassed = false;
    }

    // TODO: Test with enough area but not all dinos can coexist
    Habitat habitat2 = new Habitat("habitat", 50, 5);
    habitat2.addDinosaur(new Spinosaurus("spiny1", "Spiny", 10));
    habitat2.addDinosaur(new TRex("TRex1", "BigScaryGuy", 12));

    if (habitat2.countDinoSafetyViolations() != 1) {
      System.out.println("total violations should be 1 but got "
          + habitat2.countDinoSafetyViolations() + " for dino safety");
      allTestsPassed = false;
    }

    // TODO: Test where not enough area AND not all dinos can coexist
    Habitat habitat3 = new Habitat("habitat", 20, 5);
    habitat3.addDinosaur(new Spinosaurus("spiny1", "Spiny", 10));
    habitat3.addDinosaur(new TRex("TRex1", "BigScaryGuy", 12));

    if (habitat3.countDinoSafetyViolations() != 2) {
      System.out.println("total violations should be 2 but got "
          + habitat3.countDinoSafetyViolations() + " for dino safety");
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * tests everything workin together
   * 
   * @return - true or false
   */
  public static boolean testCompleteWorkflow() {
    System.out.println("Testing complete DinoPark workflow integration...");
    boolean allTestsPassed = true;

    try {
      // TODO: Create a DinoPark
      DinoPark park = new DinoPark();

      // TODO: Create a lab tour with 2 experiments
      LabTour lab = new LabTour("Lab", 5);
      lab.addExperiment(new Experiment("chemical", 2));
      lab.addExperiment(new Experiment("nuclear", 2));

      // TODO: Create 2 habitats with a variety of dinosaurs
      Habitat habitat1 = new Habitat("habitat1", 20, 2);
      habitat1.addDinosaur(new Spinosaurus("spiny1", "Spiny", 10));
      habitat1.addDinosaur(new TRex("TRex1", "BigScaryGuy", 12));

      Habitat habitat2 = new Habitat("habitat2", 40, 6);
      habitat2.addDinosaur(new Triceratops("triny1", "Triny", 10));
      habitat2.addDinosaur(new Velociraptor("Velociraptor1", "Velocy", 12));

      // TODO: Add your lab tour and 2 habitats to the park
      park.addAttraction(habitat2);
      park.addAttraction(habitat1);
      park.addAttraction(lab);

      // TODO: Call generateSafetyReport and verify no crashes
      System.out.println(park.generateSafetyReport());

    } catch (Exception e) {
      System.out.println("Complete workflow threw exception: "
          + e.getMessage());
      System.out.println("ruh roh raggy");
      allTestsPassed = false;
    }

    return allTestsPassed;
  }
}
