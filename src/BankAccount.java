public abstract class BankAccount {

    private int accountNumber;
    private String accountType;
    private double balance;

    public BankAccount(int accNum, String accType, double bal) {
        accountNumber = accNum;
        accountType = accType;
        balance = bal;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double bal) {
        balance = bal;
    }

    // given an int, will return the correctly formatted account number
    public String getAccountNumber() {
        return "AC" + accountNumber;
    }

    // method that carries out a deposit
    public boolean deposit(double dep) {
        if(dep < 0) {
            System.out.println("Failed Deposit - Must deposit more than $0, Account Balance: " + balance);
            return false;   // return false if unsuccessful and print an error msg
        }
        balance += dep;
        System.out.println("Successful Deposit, Account Balance: " + balance);
        return true;    // returns true if successful and prints validation to user
    }

    // method that carries out a withdrawal
    public boolean withdraw(double wd) {
        if(wd > balance) {  // if trying to withdrawal more than what they have
            System.out.println("Failed Withdrawal, Insufficient " + accountType + " balance, Account Balance: " + balance);
            return false;   // return false if unsuccessful and print an error msg
        }
        balance -= wd;
        System.out.println("Successful Withdrawal, Account Balance: " + balance);
        return true;    // returns true if successful and prints validation to user
    }

    public String getAccountType() {
        return accountType;
    }


}
