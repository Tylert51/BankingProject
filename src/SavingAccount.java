public class SavingAccount extends BankAccount {
    private static double interestRate = 2.5;

    public SavingAccount(int accNum, String accType, double bal) {
        super(accNum, accType, bal);
    }

    // adds that interest to the balance
    public void applyInterest() {
        setBalance(getBalance() + calculateInterest());
    }

    // calculates the interest based on the interest rate
    public double calculateInterest() {
        return getBalance() * (interestRate / 100);
    }
}
