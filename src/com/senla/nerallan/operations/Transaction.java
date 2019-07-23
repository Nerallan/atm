package com.senla.nerallan.operations;

import com.senla.nerallan.Account;
import com.senla.nerallan.AtmServer;
import com.senla.nerallan.exceptions.InsufficientFundsException;
import com.senla.nerallan.interfaces.AtmServerInterface;

import java.io.IOException;
import java.util.Map;

public abstract class Transaction {
    private Account account;
    private static int transactionId = 1;
    private int id;

    public Transaction(Account account) {
        this.account = account;
        id = transactionId++;
    }

    public static Transaction setTransaction(Account account, int option) {
        switch (option) {
            case 1:
                return new Deposit(account);
            case 2:
                return new Withdraw(account);
            case 3:
                return new BalanceRequest(account);
            case 0:
                System.exit(1);
            default:
                return null;
        }
    }


    public boolean performTransaction(Account account, AtmServerInterface atmServerInterface, int operation) throws IOException {
        int cashAmount = getCustomerRequest();
        Map<String, Integer> serverReply = null;
        if (cashAmount != -1) {
            serverReply = atmServerInterface.operationPerform(account.getAccountNumber(), cashAmount, operation);
        }
        return displayMessage(serverReply, transactionId);
    }

    public abstract int getCustomerRequest();

    public abstract boolean displayMessage(Map<String, Integer> message, int transactionId);
}
