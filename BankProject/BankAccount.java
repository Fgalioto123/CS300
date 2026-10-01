/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 3
 * Citations: None
 */

/**
 * this class creates bank accounts and has a few methods that go with it like
 * withdrawing and depositing to and from the account
 */
public class BankAccount {
  private String accountNumber;
  private double balance;
  private String accountHolderName;

  /**
   * the constructor of the class to create the accounts while checking for
   * exceptions
   * 
   * @param accountNumber     - the number of the account
   * @param accountHolderName - the name of the person who owns the account
   * @param initialBalance    - the starting balance of the account when it is
   *                          created
   * @throws InvalidAccountException  - throws if the account number is not
   *                                  exactly 8 digits
   * @throws IllegalArgumentException - throws if either the account name is empty
   *                                  or null. also throws if the initial balance
   *                                  is negative
   */
  public BankAccount(
      String accountNumber,
      String accountHolderName,
      double initialBalance) throws InvalidAccountException, IllegalArgumentException {

    if (accountNumber.length() != 8) {
      throw new InvalidAccountException("Account number is not exactly 8 digits");
    }

    if (accountHolderName == null || accountHolderName.isEmpty()) {
      throw new IllegalArgumentException("Account name is empty or null");
    }

    if (initialBalance < 0) {
      throw new IllegalArgumentException("Account balance is negative");
    }

    this.accountNumber = accountNumber;
    this.accountHolderName = accountHolderName;
    this.balance = initialBalance;
  }

  /**
   * deposits money into the account
   * 
   * @param amount - amount getting deposited
   * @throws IllegalArgumentException - throws if the amount you are trying to
   *                                  deposit is negative
   */
  public void deposit(double amount) throws IllegalArgumentException {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount that you are trying to deposit is negative");
    }
    balance += amount;
  }

  /**
   * withdraws money from an account
   * 
   * @param amount - the amount trying to be withdrawn
   * @throws InsufficientFundsException - throws if the amount you are trying to
   *                                    withdraw is more than you have in your
   *                                    bank account.
   */
  public void withdraw(double amount) throws InsufficientFundsException {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount you are trying to withdraw is negative");
    } else if (amount > balance) {
      throw new InsufficientFundsException("Amount you are trying to withdraw is more than you have in your account.");
    }

    balance -= amount;
  }

  /**
   * this method returns the balance of the bank account
   * 
   * @return - returns a double which is the balance of the bank account
   */
  public double getBalance() {
    return balance;
  }

  /**
   * returns the account number of the account
   * 
   * @return - returns a String which is the account number of the account
   */
  public String getAccountNumber() {
    return accountNumber;
  }

  /**
   * returns the name of the account
   * 
   * @return - returns a String which is the name of the person who owns the
   *         account
   */
  public String getAccountHolderName() {
    return accountHolderName;
  }

  /**
   * Returns a String which is the account number, name, and balance.
   * 
   * @return - returns the string from above.
   */

  public String toString() {
    return String.format(
        "Account: %s, Holder: %s, Balance: $%.2f",
        accountNumber,
        accountHolderName,
        balance);
  }
}