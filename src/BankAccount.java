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

    public String getAccountNumber() {
        return "AC" + accountNumber;
    }

    public boolean deposit(double dep) {
        if(dep < 0) {
            return false;
        }
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
