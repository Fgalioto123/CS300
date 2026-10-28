/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 6
 * Citations: Samuel Benardo - talked over problem 2 a lot with him 
 * and wrote together on the white board to solve it.
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * Test harness for the Dinosaur Monitoring implementation.
 */
public class DinoMonitorTesting {

  /**
   * Main method to run this tester class.
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.print("Test testGreedyMonitoring: ");
    boolean greedyPass = testGreedyMonitoring();
    System.out.println(greedyPass ? "Passed" : "Failed");

    System.out.print("\n\nTest testOptimalMonitoring: ");
    boolean optPass = testOptimalMonitoring();
    System.out.println(optPass ? "Passed" : "Failed");

    System.out.print("\n\nTest testMinCostMonitoring: ");
    boolean minCostPass = testMinCostMonitoring();
    System.out.println(minCostPass ? "Passed" : "Failed");

    boolean allPass = greedyPass && optPass && minCostPass;

    System.out.println("-----------------------------------------------");
    System.out.println("Overall: " + (allPass ? "Pass" : "Failed!"));
  }

  ////////////////////////////// GREEDY MONITORING TESTS
  /// 
  public static boolean testGreedyMonitoring() {
    return greedyMonitoringBaseTest() &&
        greedyMonitoringRecursiveTest();
  }

  /**
   * tests when hires is 0 so should instantly return an empty ResearcherList
   * which then returns true
   * 
   * @return - true or false if it returns an empty ResearcherList
   */
  public static boolean greedyMonitoringBaseTest() {
    // PROVIDED as a model for how to write tests with the provided utilities

    // Create a list of potential candidates with various station coverage
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(new boolean[][] {
        { true, false, false, false }, // Researcher 0: covers station 0
        { false, true, true, true }, // Researcher 1: covers stations 1,2,3
        { true, false, true, false }, // Researcher 2: covers stations 0,2
        { true, true, false, false } }, // Researcher 3: covers stations 0,1
        new int[] { 20, 25, 30, 15 }); // Researcher salaries: $20k, $25k, $30k, $15k

    // For the base case, we expect no one to be added to the list
    ResearcherList expectedSolution = new ResearcherList();

    // Call the method with hiresLeft at 0 (base case)
    ResearcherList actualSolution = DinoMonitor.greedyMonitoring(candidates,
        new ResearcherList(), 0);

    // Use the testing utilities provided to compare the contents of these two lists
    return DinoMonitorTestingUtilities.compareResearcherLists(expectedSolution, actualSolution);
  }

  /**
   * tests the greedyMonitoringRecursive method with 2 hires and uses methods from
   * monotiring utilities
   * 
   * @return - true or false if the method works and returns the correct solutions
   */
  public static boolean greedyMonitoringRecursiveTest() {
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(new boolean[][] {
        { true, false, false, false }, // Researcher 0: covers station 0
        { false, true, true, true }, // Researcher 1: covers stations 1,2,3
        { true, false, true, false }, // Researcher 2: covers stations 0,2
        { true, true, false, false } }, // Researcher 3: covers stations 0,1
        new int[] { 20, 25, 30, 15 }); // Researcher salaries: $20k, $25k, $30k, $15k

    ArrayList<ResearcherList> correctList = DinoMonitorTestingUtilities.allOptimalSolutions(
        candidates, 2);

    ResearcherList actualSolution = DinoMonitor.greedyMonitoring(candidates,
        new ResearcherList(), 2);

    // Use the testing utilities provided to compare the contents of these two lists
    return DinoMonitorTestingUtilities.compareResearcherLists(correctList, actualSolution);
  }

  ////////////////////////////// OPTIMAL MONITORING TESTS
  /// 
  public static boolean testOptimalMonitoring() {
    return optimalMonitoringBaseTest() &&
        optimalMonitoringRecursiveTest() &&
        optimalMonitoringFuzzTest();

  }

  /**
   * tests the method with 0 hires which should give a empty ResearchList which
   * would then return true
   * 
   * @return - true or false
   */
  public static boolean optimalMonitoringBaseTest() {
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(new boolean[][] {
        { true, false, false, false }, // Researcher 0: covers station 0
        { false, true, true, true }, // Researcher 1: covers stations 1,2,3
        { true, false, true, false }, // Researcher 2: covers stations 0,2
        { true, true, false, false } }, // Researcher 3: covers stations 0,1
        new int[] { 20, 25, 30, 15 }); // Researcher salaries: $20k, $25k, $30k, $15k

    ArrayList<ResearcherList> correctList = DinoMonitorTestingUtilities.allOptimalSolutions(
        candidates, 0);

    ResearcherList actualSolution = DinoMonitor.optimalMonitoring(candidates,
        new ResearcherList(), 0);

    // Use the testing utilities provided to compare the contents of these two lists
    return DinoMonitorTestingUtilities.compareResearcherLists(correctList, actualSolution);
  }

  /**
   * tests the recursive method with hires being 2. should return one of the
   * possible optimal solutions
   * 
   * @return - true or false if it returns one of the possible optimal solutions
   */
  public static boolean optimalMonitoringRecursiveTest() {
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(new boolean[][] {
        { true, false, false, false }, // Researcher 0: covers station 0
        { false, true, true, true }, // Researcher 1: covers stations 1,2,3
        { true, false, true, false }, // Researcher 2: covers stations 0,2
        { true, true, false, false } }, // Researcher 3: covers stations 0,1
        new int[] { 20, 25, 30, 15 }); // Researcher salaries: $20k, $25k, $30k, $15k

    ArrayList<ResearcherList> correctList = DinoMonitorTestingUtilities.allOptimalSolutions(
        candidates, 2);

    ResearcherList actualSolution = DinoMonitor.optimalMonitoring(candidates,
        new ResearcherList(), 2);

    // Use the testing utilities provided to compare the contents of these two lists
    return DinoMonitorTestingUtilities.compareResearcherLists(correctList, actualSolution);
  }

  /**
   * tests the recursive method by testing 120 different versions to make sure
   * everything works
   * 
   * @return - true or false if it returns all of the correct optimal solutions
   *         for each test
   */
  public static boolean optimalMonitoringFuzzTest() {
    Random random = new Random();

    for (int i = 0; i < 120; i++) {
      int stations = random.nextInt(1, 6);
      int researchers = random.nextInt(1, 11);
      int hires = random.nextInt(1, researchers + 1);
      ResearcherList list = new ResearcherList();

      for (int j = 0; j < researchers; j++) {
        boolean[] boolArr = new boolean[stations];

        for (int k = 0; k < stations; k++) {
          if (random.nextInt(1, 3) == 1) {
            boolArr[k] = true;
          }
        }
        Researcher researcher = new Researcher(boolArr);
        list.copyAndAddResearcher(researcher);
      }

      ArrayList<ResearcherList> correctList = DinoMonitorTestingUtilities.allOptimalSolutions(
          list, hires);

      ResearcherList actualSolution = DinoMonitor.optimalMonitoring(list,
          new ResearcherList(), hires);

      // Use the testing utilities provided to compare the contents of these two lists
      boolean bool = DinoMonitorTestingUtilities.compareResearcherLists(correctList, actualSolution);
      if (bool == false) {
        return bool;
      }
    }

    return true;
  }

  ////////////////////////////// MIN COST MONITORING TESTS
  /// 
  public static boolean testMinCostMonitoring() {
    return minCostMonitoringBaseTest() &&
        minCostMonitoringRecursiveTest() &&
        minCostMonitoringFuzzTest();

  }

  /**
   * tests when minstations is 0 for the min cost method. the method should give
   * an empty hired back
   * 
   * @return - true or false depending on if it returned an empty hired
   */
  public static boolean minCostMonitoringBaseTest() {
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(new boolean[][] {
        { true, false, false, false }, // Researcher 0: covers station 0
        { false, true, true, true }, // Researcher 1: covers stations 1,2,3
        { true, false, true, false }, // Researcher 2: covers stations 0,2
        { true, true, false, false } }, // Researcher 3: covers stations 0,1
        new int[] { 20, 25, 30, 15 }); // Researcher salaries: $20k, $25k, $30k, $15k

    ArrayList<ResearcherList> correctList = DinoMonitorTestingUtilities.allMinCostSolutions(
        candidates, 0);

    ResearcherList actualSolution = DinoMonitor.minCostMonitoring(candidates,
        new ResearcherList(), 0);

    // Use the testing utilities provided to compare the contents of these two lists
    return DinoMonitorTestingUtilities.compareResearcherLists(correctList, actualSolution);
  }

  /**
   * tests the method with a single ResearcherList making sure it returns the
   * optimal solutions
   * 
   * @return - true or false depending if it returned the optimal solution
   */
  public static boolean minCostMonitoringRecursiveTest() {
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(new boolean[][] {
        { true, false, false, false }, // Researcher 0: covers station 0
        { false, true, true, true }, // Researcher 1: covers stations 1,2,3
        { true, false, true, false }, // Researcher 2: covers stations 0,2
        { true, true, false, false } }, // Researcher 3: covers stations 0,1
        new int[] { 20, 25, 30, 15 }); // Researcher salaries: $20k, $25k, $30k, $15k

    ArrayList<ResearcherList> correctList = DinoMonitorTestingUtilities.allMinCostSolutions(
        candidates, 4);

    ResearcherList actualSolution = DinoMonitor.minCostMonitoring(candidates,
        new ResearcherList(), 4);

    // Use the testing utilities provided to compare the contents of these two lists
    return DinoMonitorTestingUtilities.compareResearcherLists(correctList, actualSolution);
  }

  /**
   * tests min cost method with 120 different or same ResearchLists to test all
   * posibilities
   * 
   * @return - true or false depending if it returned all the optimal solutions
   */
  public static boolean minCostMonitoringFuzzTest() {
    Random random = new Random();

    for (int i = 0; i < 120; i++) {
      int stations = random.nextInt(1, 6);
      int researchers = random.nextInt(1, 11);
      int minStations = random.nextInt(1, stations + 1);
      ResearcherList list = new ResearcherList();

      for (int j = 0; j < researchers; j++) {
        boolean[] boolArr = new boolean[stations];

        for (int k = 0; k < stations; k++) {
          if (random.nextInt(1, 3) == 1) {
            boolArr[k] = true;
          }
        }
        int salary = random.nextInt(0, researchers / 2 + 1);
        Researcher researcher = new Researcher(boolArr, salary);
        list.copyAndAddResearcher(researcher);
      }

      ArrayList<ResearcherList> correctList = DinoMonitorTestingUtilities.allOptimalSolutions(
          list, minStations);

      ResearcherList actualSolution = DinoMonitor.optimalMonitoring(list,
          new ResearcherList(), minStations);

      // Use the testing utilities provided to compare the contents of these two lists
      boolean bool = DinoMonitorTestingUtilities.compareResearcherLists(correctList, actualSolution);
      if (bool == false) {
        return bool;
      }
    }

    return true;

  }
}
