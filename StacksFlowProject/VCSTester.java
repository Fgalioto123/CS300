/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 8
 * Citations: None
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Tester class for a version control system.
 */
public class VCSTester {

  public static void main(String[] args) {
    if (runAllTests()) {
      System.out.println("All tests passed!");
    } else {
      System.out.println("Some tests failed.");
    }
  }

  /**
   * Runs all test methods
   * 
   * @return true if all tests pass
   */
  public static boolean runAllTests() {
    boolean allPass = true;

    allPass &= testCommitChangesDefensiveCopy();
    allPass &= testStackPushPop();
    allPass &= testQueueEnqueueDequeue();
    allPass &= testLinkedIterator();
    allPass &= testBranchMakeAndPushCommit();
    allPass &= testRepositoryBasics();

    return allPass;
  }

  /**
   * Tests that Commit makes defensive copies of the changedFiles list.
   * 
   * @return true if all tests pass
   */
  public static boolean testCommitChangesDefensiveCopy() {

    ArrayList<String> testArr = new ArrayList<>(Arrays.asList("file1", "file2", "file3"));
    Commit commit = new Commit("Testing defensive copy", testArr);
    testArr.remove(0);
    if (testArr.equals(commit.getChangedFiles())) {
      return false;
    }

    ArrayList<String> testArr2 = new ArrayList<>(Arrays.asList("file1", "file2", "file3"));
    Commit commit2 = new Commit("Testing defensive copy", testArr2);
    ArrayList<String> commitedArr = commit2.getChangedFiles();
    commitedArr.remove(0);
    if (commitedArr.equals(testArr2)) {
      return false;
    }

    return true;
  }

  /**
   * Tests the push and pop methods of CommitStack.
   * 
   * @return true if all tests pass
   */
  public static boolean testStackPushPop() {
    // Case 1: Push to an empty stack
    try {
      CommitStack stack = new CommitStack();
      Commit commit = new Commit("First commit", new ArrayList<>(Arrays.asList("a.java")));
      stack.push(commit);
      if (stack.size() != 1) {
        System.out.println("Case 1 failed:");
        System.out.println("Actual Size: " + stack.size());
        System.out.println("Expected Size: 1");
        return false;
      }
      if (!stack.peek().equals(commit)) {
        System.out.println("Case 1 failed:");
        System.out.println("Top element should be the most recently pushed commit");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Case 1 failed with exception: " + e);
      return false;
    }

    try {
      CommitStack stack = new CommitStack();
      Commit commit = new Commit("First commit", new ArrayList<>(Arrays.asList("a.java")));
      Commit commit2 = new Commit("Second Message", new ArrayList<>(Arrays.asList("b.java")));
      stack.push(commit);
      stack.push(commit2);
      if (stack.size() != 2) {
        System.out.println("Case 2 failed:");
        System.out.println("Actual Size: " + stack.size());
        System.out.println("Expected Size: 2");
        return false;
      }
      if (!stack.peek().equals(commit2)) {
        System.out.println("Case 2 failed:");
        System.out.println("Top element should be the most recently pushed commit");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Case 2 failed with exception: " + e);
      return false;
    }

    try {
      CommitStack stack = new CommitStack();
      Commit commit = new Commit("First commit", new ArrayList<>(Arrays.asList("a.java")));
      Commit commit2 = new Commit("Second Message", new ArrayList<>(Arrays.asList("b.java")));
      stack.push(commit);
      stack.push(commit2);
      Commit commit3 = stack.pop();
      if (stack.size() != 1) {
        System.out.println("Case 3 failed:");
        System.out.println("Actual Size: " + stack.size());
        System.out.println("Expected Size: 1");
        return false;
      }
      if (!commit3.equals(commit2)) {
        System.out.println("Case 3 failed:");
        System.out.println("Commit 3 and 2 should be equal");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Case 3 failed with exception: " + e);
      return false;
    }

    try {
      CommitStack stack = new CommitStack();
      Commit commit = stack.pop();
      System.out.println("Should of gave error on test 4");
      return false;
    } catch (Exception e) {
      System.out.println("Case 4 CORRECTLY failed with exception: " + e);
    }

    return true;
  }

  /**
   * Tests the enqueue and dequeue methods of CommitQueue.
   * 
   * @return true if all tests pass
   */
  public static boolean testQueueEnqueueDequeue() {
    // Case 1: Enqueue to an empty queue
    try {
      CommitQueue queue = new CommitQueue();
      Commit commit = new Commit("First commit", new ArrayList<>(Arrays.asList("a.java")));
      queue.enqueue(commit);
      if (queue.size() != 1) {
        System.out.println("Case 1 failed:");
        System.out.println("Actual Size: " + queue.size());
        System.out.println("Expected Size: 1");
        return false;
      }
      if (!queue.peek().equals(commit)) {
        System.out.println("Case 1 failed:");
        System.out.println("Front element should be the first enqueued commit");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Case 1 failed with exception: " + e);
      return false;
    }

    // Case 2: Enqueue to a non-empty queue
    try {
      CommitQueue queue = new CommitQueue();
      Commit commit = new Commit("First commit", new ArrayList<>(Arrays.asList("a.java")));
      Commit commit2 = new Commit("Second Message", new ArrayList<>(Arrays.asList("b.java")));
      queue.enqueue(commit);
      queue.enqueue(commit2);
      if (queue.size() != 2) {
        System.out.println("Case 2 failed:");
        System.out.println("Actual Size: " + queue.size());
        System.out.println("Expected Size: 2");
        return false;
      }
      if (!queue.peek().equals(commit)) {
        System.out.println("Case 2 failed:");
        System.out.println("Front element should be the first enqueued commit");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Case 1 failed with exception: " + e);
      return false;
    }

    // Case 3: Dequeue from a non-empty queue
    try {
      CommitQueue queue = new CommitQueue();
      Commit commit = new Commit("First commit", new ArrayList<>(Arrays.asList("a.java")));
      Commit commit2 = new Commit("Second Message", new ArrayList<>(Arrays.asList("b.java")));
      queue.enqueue(commit);
      queue.enqueue(commit2);
      Commit commit3 = queue.dequeue();
      if (queue.size() != 1) {
        System.out.println("Case 3 failed:");
        System.out.println("Actual Size: " + queue.size());
        System.out.println("Expected Size: 1");
        return false;
      }
      if (!commit3.equals(commit)) {
        System.out.println("Case 3 failed:");
        System.out.println("commit 3 should be the same as commit");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Case 1 failed with exception: " + e);
      return false;
    }
    // Case 4: Dequeue from an empty queue
    try {
      CommitQueue queue = new CommitQueue();
      queue.dequeue();
      System.out.println("Should of gotten an error in case 4");
      return false;
    } catch (Exception e) {
      System.out.println("Case 4 correctly failed with exception: " + e);

    }
    return true;
  }

  /**
   * Tests the LinkedIterator class functionality directly.
   * 
   * @return true if all tests pass
   */
  public static boolean testLinkedIterator() {
    boolean testValue = true;

    LinkedNode node4 = new LinkedNode<String>("message4");
    LinkedNode node3 = new LinkedNode<String>("message3", node4);
    LinkedNode node2 = new LinkedNode<String>("message2", node3);
    LinkedNode node1 = new LinkedNode<String>("message1", node2);

    LinkedIterator linkedIterator = new LinkedIterator<String>(node1);
    String completeMessage = "message1 message2 message3 message4 ";
    String concatenatedMessage = "";

    while (linkedIterator.hasNext()) {
      concatenatedMessage += linkedIterator.next() + " ";
    }
    if (!completeMessage.equals(concatenatedMessage)) {
      testValue = false;
    }

    if (linkedIterator.hasNext()) {
      testValue = false;
    }

    LinkedIterator nullLinkedIterator = new LinkedIterator<>(null);
    while (nullLinkedIterator.hasNext()) {
      testValue = false;
    }

    return testValue;
  }

  /**
   * Tests makeCommit and pushAllCommits methods of Branch.
   * 
   * @return true if all tests pass
   */
  public static boolean testBranchMakeAndPushCommit() {

    // Case 1: Make at least 3 commits without pushing

    try {
      Branch branch = new Branch("Branch One");
      Commit commit = new Commit("First commit", new ArrayList<>(Arrays.asList("a.java")));
      Commit commit2 = new Commit("Second commit", new ArrayList<>(Arrays.asList("b.java")));
      Commit commit3 = new Commit("Third commit", new ArrayList<>(Arrays.asList("c.java")));
      branch.makeCommit(commit);
      branch.makeCommit(commit2);
      branch.makeCommit(commit3);

      // Case 2: Push all commits
      branch.pushAllCommits();
      // Case 3: Check that head is the most recently pushed commit
      boolean value = branch.getHead().equals(commit);
      if (value == false) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("Exception incorrectly caught");
      return false;
    }

    return true;
  }

  /**
   * Tests basic Repository functionality.
   * 
   * @return true if all tests pass
   */
  public static boolean testRepositoryBasics() {

    // Case 1: Verify that active branch is initialized
    Repository repo = new Repository("Repo One");
    if (repo.getActiveBranch() != null) {
      return false;
    }

    // Case 2: Make and push commits to main branch
    Commit commit = new Commit("First commit", new ArrayList<>(Arrays.asList("a.java")));
    Commit commit2 = new Commit("Second commit", new ArrayList<>(Arrays.asList("b.java")));
    Branch branch = new Branch("BranchOne");
    branch.makeCommit(commit);
    branch.makeCommit(commit2);
    repo.checkout(branch);
    repo.createBranch("Branch");

    // Case 3: Get an existing and nonexistent branch by name

    Branch b1 = repo.getBranchByName("Branch");
    Branch b2 = repo.getBranchByName("Falalal");
    if (b1 == null || b2 != null) {
      return false;
    }

    // Case 4: Create and checkout a new branch
    Branch branch2 = new Branch("Branch Two");
    repo.checkout(branch2);

    return true;
  }
}