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

    public void setAccountNumber(int num) {
        accountNumber = num;
    }

    public boolean deposit(double dep) {
        balance += dep;
        return true;
    }

    public boolean withdraw(double wd) {
        if(wd > balance) {
            return false;
        }
        balance -= wd;
        return true;
    }

    public String getAccountType() {
        return accountType;
    }


}
