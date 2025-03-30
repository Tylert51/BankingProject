public class SavingAccount extends BankAccount {
    private static double interestRate = 2.5;

    public SavingAccount(int accNum, String accType, double bal) {
        super(accNum, accType, bal);
    }

    public void applyInterest() {
        setBalance(getBalance() + calculateInterest());
    }

    public double calculateInterest() {
        return getBalance() * (interestRate / 100);
    }
}
