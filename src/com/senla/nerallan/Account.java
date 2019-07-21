package com.senla.nerallan;

public class Account {
    private static final int MAX_BALANCE = 1000000;

    private String accountNumber;
    private double balance = 0;
    private boolean firstTime = true;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Account(double balance, String accountNumber) {
        this.accountNumber = accountNumber;
        if (balance <= MAX_BALANCE) {
            this.balance = balance;
        }
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " was successfully deposited in your account, \n The new balance of your account is " + balance);
        } else {
            System.err.println("Deposit cash amount is negative!");
        }
    }

    void withdraw(double amount) {
        if (amount > 0) {
            double tempBalance = balance;
            if (firstTime) {
                tempBalance -= amount;
                if (tempBalance >= 0){
                    balance -= amount;
                    System.out.println("FIRST TIME You withdraw " + amount + " from your account.\nThe new balance is " + balance );
                } else {
                    System.err.println("Insufficient balance in the account to withdraw " + amount);
                    System.err.println("Current balance " + balance);
                }
                firstTime = false;
            } else {
                Atm atm = new Atm();
                tempBalance = tempBalance - amount - atm.getTransactionFees();
                if (tempBalance >= 0){
                    balance -= amount - atm.getTransactionFees();
                    System.out.println("You withdraw " + amount + " from your account.\nThe new balance is " + balance + " Transaction fees " + atm.getTransactionFees());
                } else {
                    System.err.println("Insufficient balance in the account to withdraw! " + amount);
                }
            }
        } else {
            System.err.println("Withdraw cash amount is negative!");
        }
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public double getCurrentBalance() {
        return balance;
    }
}
