package com.senla.nerallan.operations;

import com.senla.nerallan.Account;
import com.senla.nerallan.AtmCashManager;
import com.senla.nerallan.ConsoleManager;
import com.senla.nerallan.exceptions.InsufficientFundsException;

import java.util.Map;

public class Withdraw extends Transaction {
   ConsoleManager consoleManager;

    public Withdraw(Account account) {
        super(account);
        consoleManager = new ConsoleManager();
    }


    @Override
    public int getCustomerRequest(){
        int withdrawMoney = consoleManager.setScreen("Please, enter the amount to withdraw ", true);
        AtmCashManager cashManager = new AtmCashManager();
        if (cashManager.isCashAmountAvailable(withdrawMoney)){
            return withdrawMoney;
        } else {
            return -1;
        }
    }

    @Override
    public boolean displayMessage(Map<String, Integer> message, int transactionId) {
        if (message.get("Success") == 1){
            consoleManager.setScreen("Please, take your cash ", false);
            return true;
        } else {
            consoleManager.setScreen("Your account doesn't have enough money to withdraw ", false);
            return false;
        }
    }
}
