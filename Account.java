
package quizcs;
import java.text.DecimalFormat;

class Account {
    DecimalFormat money = new DecimalFormat("###,###,###.##");
    private String accountName;
    private String gender;
    private String address;
    private String accountType;
    private int accountNumber;
    private double balance;

    public Account(String accountName, String gender, String address, String accountType, int accountNumber, double balance) {
        this.accountName = accountName;
        this.gender = gender;
        this.address = address;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
    public void setNewAccountName(String updatedName) {
        this.accountName = updatedName;
    }
    
    public void setNewGender(String updatedGender) {
        this.gender = updatedGender;
    }
    
    public void setNewAddress(String updatedAddress) {
        this.address = updatedAddress;
    }
    
    public String getAccountName() {
        return accountName;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getAccountType() {
        return accountType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    public void setBalance(double updatedBalance) {
        balance = updatedBalance;
        }
    public double getBalance() {
        return balance;
    }
    public void displayAccountDetails() {
        System.out.println("\n----- Account Details -----");
        System.out.println("Account Name: " + getAccountName());
        System.out.println("Gender: " + getGender());
        System.out.println("Address: " + getAddress());
        System.out.println("Account Type: " + getAccountType());
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Balance: PHP " + money.format(getBalance()));
        System.out.println("---------------------------");
    }
}