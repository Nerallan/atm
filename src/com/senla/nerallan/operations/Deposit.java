package com.senla.nerallan.operations;

import com.senla.nerallan.Account;
import com.senla.nerallan.ConsoleManager;
import com.senla.nerallan.exceptions.InsufficientFundsException;

import java.util.Map;

public class Deposit extends Transaction {
    ConsoleManager consoleManager;

    public Deposit(Account account) {
        super(account);
        consoleManager = new ConsoleManager();
    }

    @Override
    public int getCustomerRequest(){
        int depositAmount = consoleManager.setScreen("Please, enter the amount you want to deposit ", true);
        return depositAmount;
    }

    @Override
    public boolean displayMessage(Map<String, Integer> message, int transactionId) {
        if (message.get("Success") == 1){
            consoleManager.setScreen("Cash successfully deposit ", false);
            return true;
        } else {
            consoleManager.setScreen("Some error occurred while performint operation ", false);
            return false;
        }
    }
}
