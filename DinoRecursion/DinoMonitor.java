/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 6
 * Citations: Samuel Benardo - talked over problem 2 a lot with him and wrote together on the white board to solve it.
 */

import java.util.ArrayList;

/**
 * This class contains methods to solve variants of the dinosaur monitoring
 * coverage problem:
 * given a set of researchers with different monitoring capabilities,
 * determine which researchers to hire to maximize coverage subject to certain
 * constraints.
 */
public class DinoMonitor {
  /**
   * Greedy monitoring approach: iteratively select the researcher that covers
   * the most previously-uncovered monitoring stations.
   *
   * This method works recursively: at each step, identify which remaining
   * researcher
   * would increase the total coverage the most, add them to the hired list, and
   * recurse.
   *
   * @param candidates available researchers not yet considered for hiring
   * @param hired      currently hired researchers
   * @param hiresLeft  number of additional researchers we can hire
   * @return the selected team of researchers (union of hired and greedily
   *         selected)
   */
  public static ResearcherList greedyMonitoring(ResearcherList candidates, ResearcherList hired,
      int hiresLeft) {
    if (candidates == null) {
      return hired;
    }

    if (hiresLeft == 0) {
      return hired;
    }
    if (candidates.isEmpty()) {
      return hired;
    }

    int bestIndex = 0;
    int highest = 0;
    boolean[] stationsCovered = accurateBooleanArray(candidates, hired);
    for (int i = 0; i < candidates.size(); i++) {
      boolean[] researcherArr = candidates.get(i).getStationCapability();
      int totalStations = 0;

      for (int j = 0; j < researcherArr.length; j++) {
        if (researcherArr[j] == true && stationsCovered[j] == false) {
          totalStations++;
        }
      }

      if (totalStations > highest) {
        highest = totalStations;
        bestIndex = i;
      }
    }

    ResearcherList newHired = hired.copyAndAddResearcher(candidates.get(bestIndex));
    ResearcherList newCandidates = candidates.copyAndRemoveResearcher(candidates.get(bestIndex));
    hiresLeft--;
    return greedyMonitoring(newCandidates, newHired, hiresLeft);
  }

  private static boolean[] accurateBooleanArray(ResearcherList candidates, ResearcherList hired) {
    boolean[] arr = new boolean[candidates.get(0).getStationCapability().length];

    for (Researcher researcher : hired) {
      boolean[] researcherArr = researcher.getStationCapability();
      for (int i = 0; i < researcherArr.length; i++) {
        if (researcherArr[i]) {
          arr[i] = true;
        }
      }
    }

    return arr;
  }

  /**
   * Optimal monitoring approach: exhaustively search all possible teams
   * up to the hire limit to find the team covering the most unique stations.
   *
   * This method works recursively: try adding each remaining researcher to the
   * hired set,
   * recurse, and keep track of the solution with the highest coverage.
   *
   * @param candidates available researchers not yet considered for hiring
   * @param hired      currently hired researchers
   * @param hiresLeft  number of additional researchers we can hire
   * @return the optimal team of researchers (union of hired and optimally
   *         selected)
   */
  public static ResearcherList optimalMonitoring(ResearcherList candidates, ResearcherList hired, int hiresLeft) {
    if (hiresLeft == 0) {
      return hired;
    }
    if (candidates.isEmpty()) {
      return hired;
    }

    ResearcherList bestResearcherList = hired;
    int bestTotal = hired.numCoveredStations();
    for (Researcher researcher : candidates) {
      ResearcherList newCandidates = candidates.copyAndRemoveResearcher(researcher);
      ResearcherList newHired = hired.copyAndAddResearcher(researcher);

      ResearcherList recursedHired = optimalMonitoring(newCandidates, newHired, hiresLeft - 1);
      if (recursedHired.numCoveredStations() > bestTotal) {
        bestResearcherList = recursedHired;
        bestTotal = recursedHired.numCoveredStations();
      }

    }
    return bestResearcherList;
  }

  /**
   * Cost-aware monitoring: find the least expensive team that covers
   * at least the required number of monitoring stations.
   *
   * This method works recursively: try adding each remaining researcher to the
   * hired set,
   * recurse until minimum coverage is achieved, and keep track of the solution
   * with
   * the minimum total annual salary.
   *
   * @param candidates  available researchers not yet considered for hiring
   * @param hired       currently hired researchers
   * @param minStations minimum monitoring stations that must be covered
   * @return the lowest-cost team meeting the coverage requirement, or null if
   *         impossible
   */
  public static ResearcherList minCostMonitoring(ResearcherList candidates, ResearcherList hired, int minStations) {
    if (candidates.isEmpty()) {
      return null;
    }
    if (hired.numCoveredStations() >= minStations) {
      return hired;
    }
    // System.out.println(hired.numCoveredStations());

    ResearcherList bestResearcherList = null;
    int cheapest = candidates.totalAnnualCost();
    for (Researcher researcher : candidates) {
      ResearcherList newCandidates = candidates.copyAndRemoveResearcher(researcher);
      ResearcherList newHired = hired.copyAndAddResearcher(researcher);
      if (newHired.numCoveredStations() >= minStations && newHired.totalAnnualCost() < cheapest) {
        cheapest = newHired.totalAnnualCost();
        bestResearcherList = newHired;
      }

      ResearcherList recursedHired = minCostMonitoring(newCandidates, newHired, minStations);
      if (recursedHired != null && recursedHired.numCoveredStations() >= minStations
          && recursedHired.totalAnnualCost() < cheapest) {
        cheapest = recursedHired.totalAnnualCost();
        bestResearcherList = recursedHired;
      }

    }
    return bestResearcherList;
  }
}
