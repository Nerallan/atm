package com.senla.nerallan;

import java.util.Date;

public class Account {
    private static final int MAX_BALANCE = 1000000;

    private String accountNumber;
    private int balance;
    private Date operationDate;
    private boolean isActive = true;


    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public Account(String accountNumber, int balance, Date operationDate, boolean isActive) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.operationDate = operationDate;
        this.isActive = isActive;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public int getCurrentBalance() {
        return balance;
    }
}
