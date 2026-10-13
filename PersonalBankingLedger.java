import java.util.*;

class BankAccount {
    private String accountHolderName;
    private String accountNumber;
    private double balance;
    private double totalDeposits;
    private double totalWithdrawals;

    // Default constructor
    public BankAccount() {
        this("UNKNOWN", "0000000000", 0.0);
    }

    // Parameterized constructor using String manipulation (formatting name to uppercase)
    public BankAccount(String accountHolderName, String accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName.trim().toUpperCase();
        this.accountNumber = accountNumber.trim();
        this.balance = initialBalance;
        this.totalDeposits = 0.0;
        this.totalWithdrawals = 0.0;
    }

    // Overloaded deposit 1: amount only
    public void deposit(double amount) {
        deposit(amount, "General Deposit");
    }

    // Overloaded deposit 2: amount with description
    public void deposit(double amount, String description) {
        System.out.printf("Deposit Amount   : Rs. %.2f\n", amount);
        if (amount > 0) {
            this.balance += amount;
            this.totalDeposits += amount;
            System.out.println("Transaction Status : Deposit Successful");
            System.out.printf("Updated Balance    : Rs. %.2f\n\n", this.balance);
        } else {
            System.out.println("Transaction Status : Invalid Deposit Amount");
            System.out.printf("Available Balance  : Rs. %.2f\n\n", this.balance);
        }
    }

    // Overloaded withdraw 1: amount only
    public void withdraw(double amount) {
        withdraw(amount, "General Withdrawal");
    }

    // Overloaded withdraw 2: amount with description
    public void withdraw(double amount, String description) {
        System.out.printf("Withdrawal Amount  : Rs. %.2f\n", amount);
        if (amount <= 0) {
            System.out.println("Transaction Status : Invalid Withdrawal Amount");
            System.out.printf("Available Balance  : Rs. %.2f\n\n", this.balance);
        } else if (amount > this.balance) {
            System.out.println("Transaction Status : Insufficient Balance");
            System.out.printf("Available Balance  : Rs. %.2f\n\n", this.balance);
        } else {
            this.balance -= amount;
            this.totalWithdrawals += amount;
            System.out.println("Transaction Status : Withdrawal Successful");
            System.out.printf("Updated Balance    : Rs. %.2f\n\n", this.balance);
        }
    }

    // String manipulation to verify account holder name
    public boolean verifyAccountHolder(String inputName) {
        return this.accountHolderName.equalsIgnoreCase(inputName.trim());
    }

    public void displayAccountDetails() {
        System.out.println("========== PERSONAL BANKING LEDGER ==========\n");
        System.out.println("Account Holder Name : " + this.accountHolderName);
        System.out.println("Account Number      : " + this.accountNumber);
        System.out.printf("Initial Balance     : Rs. %.2f\n\n", this.balance);
    }

    public void displaySummary() {
        System.out.println("--------- ACCOUNT SUMMARY ---------");
        System.out.println("Account Holder      : " + this.accountHolderName);
        System.out.println("Account Number      : " + this.accountNumber);
        System.out.printf("Total Deposits      : Rs. %.2f\n", this.totalDeposits);
        System.out.printf("Total Withdrawals   : Rs. %.2f\n", this.totalWithdrawals);
        System.out.printf("Final Balance       : Rs. %.2f\n\n", this.balance);
    }
}

public class PersonalBankingLedger {
    public static void main(String[] args) {
        // Instantiate using constructor
        BankAccount account = new BankAccount("Ravi Kumar", "1234567890", 10000.00);

        // Display initial header details
        account.displayAccountDetails();

        System.out.println("--------- TRANSACTION DETAILS ---------");

        // 1. Successful deposit (Method overloading: with description)
        account.deposit(5000.00, "Salary credit");

        // 2. Successful withdrawal (Method overloading: amount only)
        account.withdraw(2000.00);

        // 3. Attempted withdrawal exceeding balance
        account.withdraw(20000.00, "ATM cash withdrawal");

        // Display final summary
        account.displaySummary();

        // Account holder verification using String comparison
        String verificationName = "ravi kumar";
        boolean isMatch = account.verifyAccountHolder(verificationName);
        System.out.println("Account Holder Verification: " + (isMatch ? "Names Match" : "Names Do Not Match"));
        System.out.println("=============================================");
    }
}