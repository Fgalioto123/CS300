/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 8
 * Citations: None
 */

import java.util.ArrayList;

/**
 * Represents a repository in a version control system.
 * Contains functionality to manage multiple branches.
 */
public class Repository {
  // User-provided repository name
  private String name;

  // Currently active branch
  private Branch activeBranch;

  // List of all branches in this repository
  private ArrayList<Branch> branches;

  /**
   * Creates a new Repository with the specified name.
   * 
   * @param name a String description of this repository.
   */
  public Repository(String name) {
    this.name = name;
    branches = new ArrayList<>();
    activeBranch = null;
  }

  /**
   * Retrieves the name of this repository.
   * 
   * @return a String containing the name of the repo
   */
  public String getName() {
    return name;
  }

  /**
   * Makes the specified branch the active (checked out) branch.
   * 
   * @param branch the branch to checkout/activate
   */
  public void checkout(Branch branch) {
    activeBranch = branch;
  }

  /**
   * Retrieves the currently active (checked out) branch.
   * 
   * @return the active branch
   */
  public Branch getActiveBranch() {
    return activeBranch;
  }

  /**
   * Retrieve a particular branch by name.
   * 
   * @param name the String name to search for among branches
   * @return the Branch with the specified name, if found, or null otherwise
   */
  public Branch getBranchByName(String name) {
    Branch aVarMaybe = null;
    for (Branch branch : branches) {
      if (branch.getName().equals(name)) {
        aVarMaybe = branch;
        return aVarMaybe;
      }
    }
    return aVarMaybe;
  }

  /**
   * Creates a new Branch with the specified name, branching from the head of the
   * currently
   * checked out branch, and adds the newly created Branch to this Repository.
   * This is equivalent to calling createBranch(name, head), where head is the
   * head commit of
   * the active branch (or null if the active branch has no commits). Because no
   * commits have been
   * pushed after the head, the new Branch starts with no commits.
   * 
   * @param name the name for the new branch
   * @return the newly created (empty) Branch
   */
  public Branch createBranch(String name) {
    Branch branch = new Branch(name, activeBranch);
    branches.add(branch);
    return branch;
  }

  /**
   * Creates a new Branch with the specified name, consisting of the commits
   * pushed to the active
   * branch after the specified commit (not including that commit), and adds the
   * newly created
   * Branch to this Repository. If the commit is null, the new Branch contains a
   * copy of the
   * active branch's entire history.
   * The specified commit, if not null, *must* be a pushed commit in the active
   * branch;
   * otherwise no Branch is created or added.
   * 
   * @param name   the name for the new branch
   * @param commit the commit to branch after, or null to copy the active branch's
   *               entire history
   * @return the newly created Branch, or null if the commit was not in the active
   *         branch
   */
  public Branch createBranch(String name, Commit commit) {

    try {
      Branch branch = activeBranch.createSubBranch(name, commit);
      branches.add(branch);
      return branch;
    } catch (Exception e) {
      return null;
    }

  }

  /**
   * Determines if this Repository contains the specified Branch
   * 
   * @param branch the Branch to search for
   * @return true only if the Branch is contained in this Repository
   */
  public boolean containsBranch(Branch branch) {
    for (Branch bran : branches) {
      if (branch == bran) {
        return true;
      }
    }
    return false;
  }

  /**
   * "Copies" the most recent commit from a specified source branch to a specified
   * target branch.
   * Creates a new, distinct commit with the same message and changes as the
   * source branch's
   * head commit, and makes it on the target branch (adding it to the target's
   * local commit queue).
   * The new commit is NOT pushed. Does not handle duplicate cases.
   * Throws BranchNotFoundException before checking whether the source branch is
   * empty.
   * 
   * @param source the source branch
   * @param target the target branch
   * @throws BranchNotFoundException if the source or target Branch(es) are not in
   *                                 this repo
   * @throws CommitNotFoundException if the source Branch is empty
   */
  public void copyLastCommit(Branch source, Branch target)
      throws CommitNotFoundException, BranchNotFoundException {

    if (!branches.contains(target) || !branches.contains(source)) {
      throw new BranchNotFoundException("branch not found");
    }

    if (source.isEmpty()) {
      throw new CommitNotFoundException("no comits in the source");
    }

    Commit commit = source.getHead();
    target.makeCommit(commit);
  }

  /**
   * Enacts a rebase-style merge from a source branch into a target branch.
   * Every commit pushed to the source branch after the most recent common
   * ancestor of the two
   * branches (or every pushed commit in the source, if there is no common
   * ancestor) is re-created
   * as a new, distinct commit with the same message and changes, and made on the
   * target branch in
   * chronological order (oldest first). All commits in the target's local commit
   * queue are then
   * pushed, so the newest merged commit becomes the target's head.
   * Throws BranchNotFoundException before checking whether the source branch is
   * empty.
   * 
   * @param source branch to "copy" commits from
   * @param target branch to apply new commits to
   * @throws BranchNotFoundException if the source or target Branch(es) are not in
   *                                 this repo
   * @throws CommitNotFoundException if the source Branch is empty
   */
  public void rebaseMerge(Branch source, Branch target)
      throws BranchNotFoundException, CommitNotFoundException {
    if (!branches.contains(target) || !branches.contains(source)) {
      throw new BranchNotFoundException("branch not found");
    }

    // 1. find common ancestor between source and target (may be null, which *is*
    // valid!)

    // 2. collect all commits from source between ancestor and most recent

    // 3. create new commit objects and apply to target branch

    // 4. push all commits to "remote" on target branch

  }

  // PROVIDED METHOD
  /**
   * Finds the most recent Commit present in both specified branches, comparing
   * pushed commits
   * by reference (the same Commit object), not by message or contents.
   * 
   * @param branch1 the first branch to compare
   * @param branch2 the second branch to compare
   * @return the most recent Commit shared by both branches, or null if they share
   *         no commits
   */
  private Commit findCommonAncestor(Branch branch1, Branch branch2) {
    // 1. create iterators and advance the longer one by the difference in length
    LinkedIterator<Commit> srcIter = branch1.iterator();
    LinkedIterator<Commit> targIter = branch2.iterator();
    int srcSize = branch1.getNumPushedCommits();
    int targSize = branch2.getNumPushedCommits();
    int sizeDiff = Math.abs(srcSize - targSize);

    LinkedIterator<Commit> longer = srcSize > targSize ? srcIter : targIter;
    for (int i = 0; i < sizeDiff; i++) {
      longer.next();
    }

    // 2. advance in lockstep until we find the common ancestor
    Commit currentSrcCommit = null;
    Commit currentTargCommit = null;
    while (srcIter.hasNext() && targIter.hasNext()) {
      currentSrcCommit = srcIter.next();
      currentTargCommit = targIter.next();
      if (currentSrcCommit == currentTargCommit) {
        return currentSrcCommit;
      }
    }

    return null; // if no common ancestor
  }

  // PROVIDED METHOD
  /**
   * Create a String representation of this Repository, consisting of the name
   * of the repo and the names of all branches.
   * 
   * @return a String describing this Repository
   */
  @Override
  public String toString() {
    String str = "Repository " + this.name;
    for (Branch branch : this.branches) {
      str += branch.getName();
      str += "\n";
    }
    return str;
  }

}