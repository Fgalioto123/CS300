/*
 * Author: Fox Galioto
 * Email: fgalioto@wisc.edu
 * Course: CS300, Fall 2026
 * Assignment: Program 3
 * Citations: None
 */

/**
 * This contains test cases to comprehensively test the BankAccount and
 * BankingSystem
 * classes.
 * 
 * @author Jim Williams and Hobbes
 * @author Fox Galioto
 */
public class BankingSystemTester {

  /**
   * This calls all the allTests method and prints out an appropriate
   * message.
   * 
   * @param args unused.
   */
  public static void main(String[] args) {
    if (allTests()) {
      System.out.println("All tests passed.");
    } else {
      System.out.println("At least one test failed.");
    }
  }

  /**
   * calls all the testing methods returning true if all pass,
   * false otherwise.
   */
  public static boolean allTests() {
    boolean allPassed = true;

    // BankAccount constructor tests
    allPassed &= testBankAccountConstructorValid();
    allPassed &= testBankAccountConstructorInvalidAccountNumber();
    allPassed &= testBankAccountConstructorNullName();
    allPassed &= testBankAccountConstructorEmptyName();
    allPassed &= testBankAccountConstructorNegativeBalance();

    // BankAccount deposit tests
    allPassed &= testDepositValid();
    allPassed &= testDepositNegativeAmount();

    // BankAccount withdraw tests
    allPassed &= testWithdrawValid();
    allPassed &= testWithdrawNegativeAmount();
    allPassed &= testWithdrawInsufficientFunds();

    // BankingSystem createAccount tests
    allPassed &= testCreateAccountValid();
    allPassed &= testCreateAccountDuplicate();

    // BankingSystem findAccount tests
    allPassed &= testFindAccountValid();
    allPassed &= testFindAccountNotFound();

    // BankingSystem transferMoney tests
    allPassed &= testTransferMoneyValid();
    allPassed &= testTransferMoneyNegativeAmount();
    allPassed &= testTransferMoneySameAccount();
    allPassed &= testTransferMoneyFromAccountNotFound();
    allPassed &= testTransferMoneyToAccountNotFound();
    allPassed &= testTransferMoneyInsufficientFunds();

    // BankingSystem displayAccountInfo tests
    allPassed &= testDisplayAccountInfoValid();
    allPassed &= testDisplayAccountInfoNotFound();

    return allPassed;
  }

  // =================== BankAccount Constructor Tests ===================

  public static boolean testBankAccountConstructorValid() {
    System.out.print("testBankAccountConstructorValid ");
    try {
      new BankAccount("12345678", "John Doe", 100.0);
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  public static boolean testBankAccountConstructorInvalidAccountNumber() {
    System.out.print("testBankAccountConstructorInvalidAccountNumber ");

    try {
      new BankAccount("123456789", "John Doe", 100.0);
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }

  }

  public static boolean testBankAccountConstructorNullName() {
    System.out.print("testBankAccountConstructorNullName ");
    try {
      new BankAccount("12345678", null, 100.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("FAIL");
      return false;
    }
  }

  public static boolean testBankAccountConstructorEmptyName() {
    System.out.print("testBankAccountConstructorEmptyName ");

    try {
      new BankAccount("12345678", "", 100.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }

  }

  public static boolean testBankAccountConstructorNegativeBalance() {
    System.out.print("testBankAccountConstructorNegativeBalance ");

    try {
      new BankAccount("12345678", "Fox", -100.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }

  }

  // =================== BankAccount Deposit Tests ===================

  public static boolean testDepositValid() {
    System.out.print("testDepositValid ");
    try {
      BankAccount account = new BankAccount("12345678", "John Doe", 100.0);
      double originalBalance = account.getBalance();
      account.deposit(50.0);
      if (account.getBalance() == originalBalance + 50.0) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  public static boolean testDepositNegativeAmount() {
    System.out.print("testDepositNegativeAmount ");

    try {
      BankAccount myAccount = new BankAccount("12345678", "Fox", 100.0);
      myAccount.deposit(-10);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }

  }

  // =================== BankAccount Withdraw Tests ===================

  public static boolean testWithdrawValid() {
    System.out.print("testWithdrawValid ");
    try {
      BankAccount account = new BankAccount("12345678", "John Doe", 100.0);
      double originalBalance = account.getBalance();
      account.withdraw(30.0);
      if (account.getBalance() == originalBalance - 30.0) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  public static boolean testWithdrawNegativeAmount() {
    System.out.print("testWithdrawNegativeAmount ");

    try {
      BankAccount myAccount = new BankAccount("12345678", "Fox", 100.0);
      myAccount.withdraw(-10);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }

  }

  public static boolean testWithdrawInsufficientFunds() {
    System.out.print("testWithdrawInsufficientFunds ");

    try {
      BankAccount myAccount = new BankAccount("12345678", "Fox", 100.0);
      myAccount.withdraw(200);
      System.out.println("FAIL");
      return false;
    } catch (InsufficientFundsException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }

  }

  // =============== BankingSystem CreateAccount Tests ===============

  public static boolean testCreateAccountValid() {
    System.out.print("testCreateAccountValid ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Jane Smith", 200.0);
      BankAccount account = system.findAccount("12345678");
      if (account != null && account.getBalance() == 200.0) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  public static boolean testCreateAccountDuplicate() {
    System.out.print("testCreateAccountDuplicate ");

    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Jane Smith", 200.0);
      system.createAccount("12345678", "Jane Smith", 200.0);
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }

  }

  // =============== BankingSystem FindAccount Tests ===============

  public static boolean testFindAccountValid() {
    System.out.print("testFindAccountValid ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Jane Smith", 200.0);
      BankAccount account = system.findAccount("12345678");
      if (account != null && account.getAccountNumber().equals("12345678")) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  public static boolean testFindAccountNotFound() {
    System.out.print("testFindAccountNotFound ");

    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Jane Smith", 200.0);
      system.findAccount("123");
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }

  }

  // =============== BankingSystem TransferMoney Tests ===============

  public static boolean testTransferMoneyValid() {
    System.out.print("testTransferMoneyValid ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);
      system.createAccount("87654321", "Bob", 100.0);

      system.transferMoney("12345678", "87654321", 50.0);

      BankAccount fromAccount = system.findAccount("12345678");
      BankAccount toAccount = system.findAccount("87654321");

      if (fromAccount.getBalance() == 250.0 && toAccount.getBalance() == 150.0) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  public static boolean testTransferMoneyNegativeAmount() {
    System.out.print("testTransferMoneyNegativeAmount ");

    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);
      system.createAccount("87654321", "Bob", 100.0);

      system.transferMoney("12345678", "87654321", -50.0);

      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }

  }

  public static boolean testTransferMoneySameAccount() {
    System.out.print("testTransferMoneySameAccount ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);

      system.transferMoney("12345678", "12345678", 50.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  public static boolean testTransferMoneyFromAccountNotFound() {
    System.out.print("testTransferMoneyFromAccountNotFound ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("87654321", "Bob", 100.0);

      system.transferMoney("99999999", "87654321", 50.0);
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  public static boolean testTransferMoneyToAccountNotFound() {
    System.out.print("testTransferMoneyToAccountNotFound ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);

      system.transferMoney("12345678", "99999999", 50.0);
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  public static boolean testTransferMoneyInsufficientFunds() {
    System.out.print("testTransferMoneyInsufficientFunds ");

    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);
      system.createAccount("99999999", "Alice", 300.0);

      system.transferMoney("12345678", "99999999", 500.0);
      System.out.println("FAIL");
      return false;
    } catch (InsufficientFundsException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }

  }

  // =============== BankingSystem DisplayAccountInfo Tests ===============

  public static boolean testDisplayAccountInfoValid() {
    System.out.print("testDisplayAccountInfoValid ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Test User", 500.0);
      system.displayAccountInfo("12345678"); // Should not throw exception
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  public static boolean testDisplayAccountInfoNotFound() {
    System.out.print("testDisplayAccountInfoNotFound ");

    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);

      system.displayAccountInfo("12345677");
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }

  }
}