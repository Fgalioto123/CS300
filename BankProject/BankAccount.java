public class BankAccount {
  private String accountNumber;
  private double balance;
  private String accountHolderName;

  // Constructor - ADD: Validation and exception throwing
  public BankAccount(
    String accountNumber,
    String accountHolderName,
    double initialBalance
  ) {
    // TODO: Validate accountNumber is exactly 8 digits (8 numbers in String)(throw
    // InvalidAccountException if not)
    // TODO: Validate accountHolderName is not null or empty (throw
    // IllegalArgumentException if invalid)
    // TODO: Validate initialBalance is not negative (throw IllegalArgumentException
    // if negative)

    this.accountNumber = accountNumber;
    this.accountHolderName = accountHolderName;
    this.balance = initialBalance;
  }

  // ADD: throws declaration and validation
  public void deposit(double amount) {
    // TODO: Throw IllegalArgumentException for negative amounts
    balance += amount;
  }

  // ADD: throws declaration and validation
  public void withdraw(double amount) {
    // TODO: Throw IllegalArgumentException for negative amounts
    // TODO: Throw InsufficientFundsException if amount > balance
    balance -= amount;
  }

  public double getBalance() {
    return balance;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getAccountHolderName() {
    return accountHolderName;
  }

  public String toString() {
    return String.format(
      "Account: %s, Holder: %s, Balance: $%.2f",
      accountNumber,
      accountHolderName,
      balance
    );
  }
}