public class CheckingAccount extends BankAccount {

    private double overDraftLimit;

    public CheckingAccount(int accNum, String accType, double bal, double overDraft) {
        super(accNum, accType, bal);
        overDraftLimit = overDraft;
    }

    public double getOverDraftLimit() {
        return overDraftLimit;
    }

    @Override
    public boolean withdraw (double wd) {
        double newBal = getBalance() - wd;
        if(getBalance() < 0 || Math.abs(newBal) > overDraftLimit) {
            return false;
        }

        setBalance(newBal);
        return true;
    }
}
