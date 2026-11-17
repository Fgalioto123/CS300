// TODO: File Header

/**
 * The CommitStack class represents a stack of commits that have been pushed to a remote repository.
 * This class provides basic stack operations such as push, pop, peek, clear, and contains.
 */
public class CommitStack extends LinkedStructure<Commit> implements StackADT<Commit> {

  // TODO: Implement and @Override StackADT methods
  // Note that isEmpty(), size(), clear(), and contains() are implemented in LinkedStructure.

  /**
   * Print all commits in this CommitStack in reverse chronological order (newest first).
   */
  public void printAllCommitsNewestFirst() {
    // TODO: Implement
  }

  /**
   * Print all commits in this CommitStack in chronological order (oldest first).
   */
  public void printAllCommitsOldestFirst() {
    // TODO: Implement (tip: use a recursive helper method)
  }
}
