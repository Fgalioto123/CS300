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

  public BankingSystem() {
    accounts = new ArrayList<BankAccount>();
  }

  // ADD: Exception handling for duplicate accounts
  public void createAccount(
      String accountNumber, String name, double initialDeposit) {

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

  public BankAccount findAccount(String accountNumber) {
    for (BankAccount account : accounts) {
      if (account.getAccountNumber().equals(accountNumber)) {
        return account;
      }
    }
    throw new InvalidAccountException("The account does not exist");
  }

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

  public void displayAccountInfo(String accountNumber) throws InvalidAccountException {

    try {
      BankAccount account = findAccount(accountNumber);
      System.out.println(account.toString());
    } catch (InvalidAccountException e) {
      throw new InvalidAccountException("The account you are trying to display does not exist");
    }

  }

  public double getTotalBankBalance() {
    double total = 0;
    for (BankAccount account : accounts) {
      total += account.getBalance();
    }
    return total;
  }
}