public class CheckingAccount extends BankAccount {

    private double overDraftLimit;

    public CheckingAccount(int accNum, String accType, double bal, double overDraft) {
        super(accNum, accType, bal);
        overDraftLimit = overDraft;
    }

    public double getOverDraftLimit() {
        return overDraftLimit;
    }

    // overridden method that carries out a withdrawal
    @Override
    public boolean withdraw (double wd) {
        double newBal = getBalance() - wd;
        if(getBalance() < 0) {
            System.out.println("Failed Withdrawal, Insufficient " + getAccountType() + " balance, Account Balance: " + getBalance());
            return false;  // return false if balance is already negative and print an error msg
        }
        if(Math.abs(newBal) > overDraftLimit) {
            System.out.println("Failed Withdrawal, Exceeded overdraft limit, Account Balance: " + getBalance());
            return false;   // returns false if they are trying to withdrawal more than their overdraft limit and prints an error msg
        }

        setBalance(newBal);
        System.out.println("Successful Withdrawal, Account Balance: " + getBalance());
        return true;    // returns true if withdrawal successful and prints validation
    }
}
