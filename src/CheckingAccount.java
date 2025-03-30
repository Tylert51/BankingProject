public class CheckingAccount extends BankAccount {

    private double overDraftLimit;

    public CheckingAccount(int accNum, String accType, double bal, double overDraft) {
        super(accNum, accType, bal);
        overDraftLimit = overDraft;
    }
}
