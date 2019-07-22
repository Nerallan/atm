package com.senla.nerallan.operations;

import com.senla.nerallan.Account;
import com.senla.nerallan.exceptions.InsufficientFundsException;

import java.util.Map;

public abstract class Transaction {
    private static int transactionId = 1;
    private int id;

    public Transaction(Account account){
        id = transactionId++;
    }

    public static Transaction setTransaction(Account account, int option) {
        switch (option) {
            case 1:
                return new Withdraw(account);
            case 2:
                return new Deposit(account);
            case 3:
                return new BalanceRequest(account);
            default:
                return null;
        }
    }


    public abstract int getCustomerRequest();
    public abstract boolean displayMessage(Map<String, Integer> message, int transactionId);
}
