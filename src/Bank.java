public class Bank {
    private int nextAccountNumber;
    private BankAccount[] accounts;
    private final int MAX_ACCOUNTS = 10;

    public Bank() {
        nextAccountNumber = 1;
        accounts = new BankAccount[10];
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        BankAccount[] accounts = bank.getAccounts();


    }

    private BankAccount[] getAccounts() {
        return accounts;
    }


}