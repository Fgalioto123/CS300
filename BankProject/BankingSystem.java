/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 3
 * Citations: None
 */

import java.util.ArrayList;

/**
 * This class creates a system of bank accounts which can be accessed to do
 * things like transfer between the two, create new accounts, find accounts, and
 * display them.
 */
public class BankingSystem {
  private ArrayList<BankAccount> accounts;

  /**
   * constructor for the class that just creates a empty arrayList which will be
   * the list that holds all the accounts
   */
  public BankingSystem() {
    accounts = new ArrayList<BankAccount>();
  }

  /**
   * This method creates the accounts checking to make sure the account doesn't
   * already exist and that you put in the correct values for the account
   * 
   * @param accountNumber  - The number of the account
   * @param name           - The name of the person who owns the account
   * @param initialDeposit - the amount of money that gets deposited as the
   *                       account gets created.
   * @throws InvalidAccountException - throws this exception if the account
   *                                 already exists. Otherwise uses a try catch to
   *                                 check to see if there are any errors in the
   *                                 input of creating the account
   */
  public void createAccount(
      String accountNumber, String name, double initialDeposit) throws InvalidAccountException {

    for (BankAccount account : accounts) {
      if (account.getAccountNumber().equals(accountNumber)) {
        throw new InvalidAccountException("The account already exists");
      }
    }

    try {
      BankAccount account = new BankAccount(accountNumber, name, initialDeposit);
      accounts.add(account);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  /**
   * this method finds an account using the account number that is given. If it is
   * not found then throw an exception
   * 
   * @param accountNumber - the number of the account wanted to be found
   * @return - returns the account if found otherwise and exception
   * @throws InvalidAccountException - exception thrown if the account was not
   *                                 found
   */
  public BankAccount findAccount(String accountNumber) throws InvalidAccountException {
    for (BankAccount account : accounts) {
      if (account.getAccountNumber().equals(accountNumber)) {
        return account;
      }
    }
    throw new InvalidAccountException("The account does not exist");
  }

  /**
   * This method transfers money from one account to the other while checking for
   * multiple exceptions
   * 
   * @param fromAccountNum - the account number of the account the money should be
   *                       withdrawn from
   * @param toAccountNum   - the account number the money should be deposited into
   * @param amount         - the amount of money that is being transfered.
   * @throws IllegalArgumentException   - throws if the amount is negative or if
   *                                    you are trying to transfer to the same
   *                                    account
   * @throws InsufficientFundsException - throws if you are trying to withdraw
   *                                    more than you have from an account
   * @throws InvalidAccountException    - if you try to transfer to an account
   *                                    that doesn't exist or take from it
   */
  public void transferMoney(
      String fromAccountNum, String toAccountNum, double amount)
      throws IllegalArgumentException, InsufficientFundsException, InvalidAccountException {
    try {
      BankAccount fromAccount = findAccount(fromAccountNum);
      BankAccount toAccount = findAccount(toAccountNum);

      if (amount < 0) {
        throw new IllegalArgumentException("The amount cannot be negative");
      }

      if (fromAccount.equals(toAccount)) {
        throw new IllegalArgumentException("You are trying to transfer from the same account");
      }

      fromAccount.withdraw(amount);
      toAccount.deposit(amount);

    } catch (InvalidAccountException e) {
      throw new InvalidAccountException("The account you are trying to transfer to does not exist");
    } catch (InsufficientFundsException e) {
      throw new InsufficientFundsException("The amount you are trying to transfer is more than you currently have");
    }

  }

  /**
   * this method displays the information of an account while checking for errors
   * 
   * @param accountNumber - the account number of the account being accessed.
   * @throws InvalidAccountException - thrown if the account does not exist.
   */
  public void displayAccountInfo(String accountNumber) throws InvalidAccountException {

    try {
      BankAccount account = findAccount(accountNumber);
      System.out.println(account.toString());
    } catch (InvalidAccountException e) {
      throw new InvalidAccountException("The account you are trying to display does not exist");
    }

  }

  /**
   * this method returns the total balance of every account
   * 
   * @return - returns a double which is the total of all the balances of all the
   *         accounts
   */
  public double getTotalBankBalance() {
    double total = 0;
    for (BankAccount account : accounts) {
      total += account.getBalance();
    }
    return total;
  }
}