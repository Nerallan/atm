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


    public String getAccountNumber() {
        return accountNumber;
    }

    public double getCurrentBalance() {
        return balance;
    }
}
