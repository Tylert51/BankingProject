import java.util.Scanner;

public class Bank {

    private int nextAccountNumber;
    private BankAccount[] accounts;
    private boolean canCreateAccount;

    public Bank() {
        nextAccountNumber = 1;
        accounts = new BankAccount[10];
        canCreateAccount = true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Bank bank = new Bank();
        BankAccount[] accounts = bank.getAccounts();
        String choice;

        do {

            System.out.print("""
                    ++++++++++++++++++++++++++++++++++++++++++++
                    1. Create Account
                    2. Deposit
                    3. Withdraw
                    4. Display All Accounts
                    5. Apply Interest Rate to Accounts
                    6. Exit
                    Choose an option:\s""");
            choice = sc.nextLine().trim();

            switch (choice) {
                case "1":   // create account

                    if(bank.canCreateAccount()) {   // if there is enough space for another account
                        System.out.print("Enter 's' for Saving or 'c' for Checking: ");
                        String accType = sc.nextLine().trim().toLowerCase();

                        if(!accType.equals("s") && !accType.equals("c")) {

                            System.out.println("Invalid choice. Enter 's' or 'c'. Resetting to menu...");   // if invalid choice

                        } else {
                            System.out.print("Enter initial balance: ");
                            String bal = sc.nextLine().trim();
                            double balance;
                            double overdraftLim;
                            int accNum = bank.getNextAccountNumber();

                            try {   // tries to parse the double but if can't will handle exception
                                balance = Double.parseDouble(bal);
                                if(balance < 0) {
                                    throw new NumberFormatException();
                                }

                                if(accType.equals("c")) {   // if checking

                                    System.out.print("Enter overdraft limit: ");

                                    try {   // tries to parse double but if can't will handle exception

                                        overdraftLim = Double.parseDouble(sc.nextLine().trim());

                                        if(overdraftLim < 0) {
                                            throw new NumberFormatException();
                                        }

                                        accounts[accNum - 1] = new CheckingAccount(accNum, "Checking", balance, overdraftLim);  //creates the checking acc
                                        System.out.println("Account created. Account Number: " + accounts[accNum-1].getAccountNumber() + ". Account Balance: " + accounts[accNum-1].getBalance());
                                        bank.incrementAccNum(); //increments the nextAccountNumber

                                    } catch (NumberFormatException e) {
                                        System.out.println("Please enter a positive number for the overdraft limit\nResetting to menu...");
                                    }

                                } else {    // if savings
                                    accounts[accNum - 1] = new SavingAccount(accNum, "Savings", balance);   //creates the savings acc
                                    System.out.println("Account created. Account Number: " + accounts[accNum-1].getAccountNumber() + ". Account Balance: " + accounts[accNum-1].getBalance());
                                    bank.incrementAccNum(); //increments the nextAccountNumber
                                }

                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a positive number for balance\nResetting to menu...");
                            }
                        }

                    } else {    // if all 10 accounts have been created
                        System.out.println("Can't create more accounts.");
                    }

                    break;

                case "2":   // deposit

                    System.out.print("Enter account number (case insensitive): ");
                    String accNum = sc.nextLine().toLowerCase().trim();
                    BankAccount acc = bank.findAccount(accNum);
                    double deposit;

                    if(acc != null) {   // if acc is found

                        System.out.print("Enter deposit amount: ");
                        String dep = sc.nextLine();

                        try {

                            deposit = Double.parseDouble(dep);
                            acc.deposit(deposit);   // has its own error msg if double is correctly parsed but is negative

                        } catch (NumberFormatException e) {
                            System.out.println("Invalid format. Please enter a positive number for deposit amount. Resetting to menu...");
                        }

                    } else {    // if acc isn't found
                        System.out.println("Account not found. Resetting to menu...");
                    }

                    break;

                case "3":   // withdraw

                    System.out.print("Enter account number (case insensitive): ");
                    accNum = sc.nextLine().toLowerCase().trim();
                    acc = bank.findAccount(accNum);
                    double withdrawal;

                    if(acc != null) {   // if acc is found

                        System.out.print("Enter withdraw amount: ");
                        String withdraw = sc.nextLine();

                        try {

                            withdrawal = Double.parseDouble(withdraw);
                            acc.withdraw(withdrawal);   // has its own error msg regarding the withdrawal amounts and balances

                        } catch (NumberFormatException e) {     // only need to worry about number format exceptions
                            System.out.println("Invalid format. Please enter a positive number for withdrawal amount. Resetting to menu...");
                        }

                    } else {    // if acc isn't found
                        System.out.println("Account not found. Resetting to menu...");
                    }

                    break;

                case "4":   // print out accounts
                    System.out.println(bank.displayAccounts());
                    break;

                case "5":   // apply interest rate to all accounts

                    bank.applyInterestToAccounts();   // will call function that applies interest to all the savings accounts
                    System.out.println(bank.displayAccounts());     // prints out updated accounts

                    break;

                case "6":   // exit
                    System.out.println("Exiting...");
                    break;

                default:    // if they didn't select one of the options
                    System.out.println("Invalid Choice. Please enter an option listed above.");
            }

        } while (!choice.equals("6"));
    }

    private BankAccount[] getAccounts() {
        return accounts;
    }

    private String displayAccounts() {
        String display = "";

        if(nextAccountNumber == 1) {    // If no accounts have been created
            return "No accounts to display";
        }

        for(int i = 0; i < nextAccountNumber - 1; i++) { // for every created account
            BankAccount acc = accounts[i];
            //prints necessary info for that BankAccount acc
            display += "Account number: " + acc.getAccountNumber() + ", Type: " + acc.getAccountType() + ", Balance: " + acc.getBalance() + "\n";
        }

        return display;
    }

    private boolean canCreateAccount() {
        return canCreateAccount;
    }

    public int getNextAccountNumber() {
        return nextAccountNumber;
    }

    public void incrementAccNum() {
        nextAccountNumber++;

        if(nextAccountNumber == 11) { // if all ten accounts already created
            canCreateAccount = false;
        }
    }

    public BankAccount findAccount(String accNum) {
        for(int i = 0; i < nextAccountNumber - 1; i++) { // for every created account
            BankAccount acc = accounts[i];
            if(acc.getAccountNumber().toLowerCase().equals(accNum)) {
                return acc;
            }
        }
        return null;
    }

    public void applyInterestToAccounts() {
        for(int i = 0; i < nextAccountNumber - 1; i++) {    // for every created account
            BankAccount acc = accounts[i];

            if(acc.getClass().equals(SavingAccount.class)) {    // if it is a Savings account
                SavingAccount savingsAcc = (SavingAccount) acc;
                savingsAcc.applyInterest();     // will apply the interest to that saving account
            }
        }
    }


}