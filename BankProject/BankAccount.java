public class BankAccount {
  private String accountNumber;
  private double balance;
  private String accountHolderName;

  public BankAccount(
      String accountNumber,
      String accountHolderName,
      double initialBalance) {

    if (accountNumber.length() != 8) {
      throw new InvalidAccountException("Account number is not exactly 8 digits");
    }

    if (accountHolderName.isEmpty() || accountHolderName.equals(null)) {
      throw new IllegalArgumentException("Account name is empty or null");
    }

    if (initialBalance < 0) {
      throw new IllegalArgumentException("Account balance is negative");
    }

    this.accountNumber = accountNumber;
    this.accountHolderName = accountHolderName;
    this.balance = initialBalance;
  }

  public void deposit(double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount that you are trying to deposit is negative");
    }
    balance += amount;
  }

  public void withdraw(double amount) throws InsufficientFundsException {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount you are trying to withdraw is negative");
    } else if (amount > balance) {
      throw new InsufficientFundsException("Amount you are trying to withdraw is more than you have in your account.");
    }

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
        balance);
  }
}