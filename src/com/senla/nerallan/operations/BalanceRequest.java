package com.senla.nerallan.operations;

import com.senla.nerallan.Account;
import com.senla.nerallan.ConsoleManager;

import java.util.Map;

public class BalanceRequest extends Transaction {
    ConsoleManager consoleManager;

    public BalanceRequest(Account account) {
        super(account);
        consoleManager = new ConsoleManager();
    }

    @Override
    public int getCustomerRequest(){
        return 0;
    }

    @Override
    public boolean displayMessage(Map<String, Integer> message, int transactionId) {
        if(message.get("Success") == 1) {
            consoleManager.setScreen("Your Account Balance is: " + String.valueOf(message.get("balance")), false);
            return true;
        } else if (message.get("wrong") == 0){
            consoleManager.setScreen("Something goes wrong ", false);
            return false;
        } else {
            consoleManager.setScreen("Couldn't retrieve the balance", false);
            return false;
        }
    }
}
