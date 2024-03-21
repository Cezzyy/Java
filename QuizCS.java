package quizcs;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class QuizCS {
    static Scanner read = new Scanner(System.in);
    static ArrayList<Account> accountList = new ArrayList<>();
    static DecimalFormat money = new DecimalFormat("###,###,###.##");
    static boolean input = false;
    public static void main(String[] args) {
        int menuchoice;
        while (!input) {
        try {
            accountMenu();
            menuchoice = read.nextInt();
                switch(menuchoice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        existingAccount();
                        break;
                    case 3:
                        accountList();
                        break;
                    case 4:
                        updateInformation();
                        break;
                    case 5:
                        System.out.println("Thanks for using the program");
                        System.exit(0);
                    default:
                        System.out.println("Invalid input, please choice from only 1-5");
                }
            } catch (Exception e) {
                System.out.println("You have entered an invalid input, please try again!");
                read.nextLine();
            }
        }
    }

    private static void accountMenu() {
        System.out.println("--- Account Menu ---");
        System.out.println("1) Create Account");
        System.out.println("2) Use Existing Account");
        System.out.println("3) Show Account list");
        System.out.println("4) Update Account Information");
        System.out.println("5) Exit");
        System.out.println("========================");
        System.out.print("Enter choices (1-5): ");
    }
    private static void createAccount() {
        read.nextLine();
        System.out.print("Enter your Account Name: ");
        String AccountName = read.nextLine();
        System.out.print("Enter your Gender: ");
        String Gender = read.nextLine();
        System.out.print("Enter your Address: ");
        String Address = read.nextLine();
        String AccountType = "Savings";
        Random random = new Random();
        int AccountNumber = random.nextInt(999999);
        double balance = 0;
        
        Account newAccount = new Account(AccountName, Gender, Address, AccountType, AccountNumber, balance);
        accountList.add(newAccount);
        
        System.out.println("============================");
        System.out.println("--- Created Account Information ---");
        System.out.println("Account Name: " + AccountName);
        System.out.println("Gender: " + Gender);
        System.out.println("Address: " + Address);
        System.out.println("Account Type: " + AccountType);
        System.out.println("Account Number: " + String.valueOf(AccountNumber));
        System.out.println("Balance: PHP " + money.format(balance));
        
    }
    private static void existingAccount() {
    boolean existingAccount = false;

    while (!existingAccount) {
        try {
            System.out.print("Enter your Account Number: ");
            int inputAccountNumber = read.nextInt();

            for (Account account : accountList) {
                if (account.getAccountNumber() == inputAccountNumber) {
                    existingAccount = true;
                    System.out.println("Logging in into your account...");
                    TransactionMenu(account);
                    break;
                }
            }
            if (!existingAccount) {
                System.out.println("Account cannot be found. Please enter the correct account number or \ncreate a new account if you have not yet created one.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numbers only for the account number.");
            read.nextLine();
        }
    }
}

private static void TransactionMenu(Account account) {
    boolean transactionLoop = true;
    
    while (transactionLoop) {
    System.out.println("--- Transaction Menu ---");
    System.out.println("Hi! " + account.getAccountName());
    System.out.println("1) Deposit");
    System.out.println("2) Withdraw");
    System.out.println("3) Check Account full details");
    System.out.println("4) Logout Account");
    System.out.print("Enter your choices (1-4): ");
    int menuchoice = read.nextInt();
    
    try {
        switch (menuchoice) {
    case 1:
        double depositAmount;
        boolean depositLoop = false;
        while (!depositLoop) {
            try {
                System.out.print("Enter deposit amount: ");
                depositAmount = read.nextDouble();
                if (depositAmount >= 1000) {
                    account.setBalance(account.getBalance() + depositAmount);
                    System.out.println("Deposit process successful. Your new balance: PHP " + money.format(account.getBalance()));
                    depositLoop = true;
                } else {
                    System.out.println("Allowed Money deposit is at PHP 1,000 above, please input a positive number");
                }
            } catch (InputMismatchException e) {
                System.out.println("You have entered an invalid input. Please enter positive numbers only.");
                read.nextLine();
            }
        }
        break;
    case 2:
        double withdrawAmount;
        boolean withdrawLoop = false;
        while (!withdrawLoop) {
            try {
                System.out.print("Enter withdraw amount: ");
                withdrawAmount = read.nextDouble();

                if (withdrawAmount > 0 && withdrawAmount <= account.getBalance()) {
                    account.setBalance(account.getBalance() - withdrawAmount);
                    System.out.println("Withdraw process successful. Your new balance: PHP " + money.format(account.getBalance()));
                    withdrawLoop = true;
                } else {
                    System.out.println("You do not have enough money to be withdrawn, please input a positive number");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You have entered an invalid input. Please enter positive numbers only.");
                read.nextLine();
            }
        }
        break;
    case 3:
        account.displayAccountDetails();
        break;
    case 4:
        transactionLoop = false;
        break;
    default:
        System.out.println("Invalid choice. Please try again.");
        break;
}
    } catch (Exception e) {
        System.out.println("You have entered an invalid input, please try again");
        }
    }
}
    private static void updateInformation() {
        boolean updateInfo = false;
        
        while (!updateInfo) {
            try {
            System.out.print("Enter your Account Number: ");
            int inputAccNumber = read.nextInt();

            for (Account account : accountList) {
                if (account.getAccountNumber() == inputAccNumber) {
                    updateInfo = true;
                    System.out.println("Logging in into your account...");
                    UpdateInfoMenu(account);
                    break;
                }
            }
            if (!updateInfo) {
                System.out.println("Account cannot be found. Please enter the correct account number or \ncreate a new account if you have not yet created one.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numbers only for the account number.");
            read.nextLine();
        }
    }
}
    private static void accountList() {
        System.out.println("--- Account List ---");
        System.out.println("Accounts currently active: " + accountList.size());
        System.out.println("-- All account information --");
        for (Account account : accountList) {
            System.out.println("Account Name: " + account.getAccountName());
            System.out.println("Gender: " + account.getGender());
            System.out.println("Address: " + account.getAddress());
            System.out.println("Account Type: " + account.getAccountType());
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Balance: PHP " + money.format(account.getBalance()));
            System.out.println("========================");
        }
    }

    private static void UpdateInfoMenu(Account account) {
        boolean UpdateMenu = true;
        
        while (UpdateMenu) {
            System.out.println("--- Update Information Menu ---");
                System.out.println("Hi! " + account.getAccountName());
                System.out.println("1) Update Account Name");
                System.out.println("2) Update Gender");
                System.out.println("3) Update Address");
                System.out.println("4) Display Account Details");
                System.out.println("5) Logout Account");
                System.out.print("Enter your choices (1-5): ");
                int Infochoice = read.nextInt();
            try {
                switch (Infochoice) {
                    case 1:
                        System.out.println("Your old Account name: " + account.getAccountName());
                        System.out.print("Enter your updated Account name: ");
                        read.nextLine();
                        String updatedName = read.nextLine();
                        account.setNewAccountName(updatedName);
                        System.out.println("Updating Name successful. Your new account name: " + account.getAccountName());
                        break;
                    case 2:
                        System.out.println("Your old Gender: " + account.getGender());
                        System.out.print("Enter your updated gender: ");
                        read.nextLine();
                        String updatedGender = read.nextLine();
                        account.setNewGender(updatedGender);
                        System.out.println("Updating Gender successful. Your new gender: " + account.getGender());
                        break;
                    case 3:
                        System.out.println("Your old address: " + account.getAddress());
                        System.out.print("Enter your updated address: ");
                        read.nextLine();
                        String updatedAddress = read.nextLine();
                        account.setNewAddress(updatedAddress);
                        System.out.println("Updating Address successful. Your new address: " + account.getAddress());
                        break;
                    case 4:
                        System.out.println("============================");
                        System.out.println("--- Account Information ---");
                        System.out.println("Account Name: " + account.getAccountName());
                        System.out.println("Gender: " + account.getGender());
                        System.out.println("Address: " + account.getAddress());
                        System.out.println("Account Type: " + account.getAccountType());
                        System.out.println("Account Number: " + String.valueOf(account.getAccountNumber()));
                        System.out.println("Balance: PHP " + money.format(account.getBalance()));
                        System.out.println("============================");
                        break;
                    case 5:
                        UpdateMenu = false;
                        break;
                    default:
                        System.out.println("Invalid input, please enter from choices (1-4) only!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a valid input.");
            }
        }
    }
}