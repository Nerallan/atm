package com.senla.nerallan;

import com.senla.nerallan.db.AtmFileDB;
import com.senla.nerallan.db.CardFileDB;
import com.senla.nerallan.exceptions.DbNotFountException;
import com.senla.nerallan.exceptions.DepositAmountExceedException;
import com.senla.nerallan.interfaces.AtmServerInterface;
import com.senla.nerallan.interfaces.CardCredentialsValidator;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtmServer implements CardCredentialsValidator, AtmServerInterface {

    private static final String CREDENTALS_DB_NAME = "cards_credentials";
    private static final String ATM_DB_NAME = "atm_db";
    private static HashMap<String, String> credentials;

    private Map<String, Integer> serverReply = new HashMap<>();
    private List<Account> accountList;
    private CardFileDB cardFileDB;
    private AtmFileDB atmFileDB;

    public AtmServer() throws ParseException, DbNotFountException, IOException {
        cardFileDB = new CardFileDB();
        atmFileDB = new AtmFileDB();
        File checkFile = new File(CREDENTALS_DB_NAME);

        if (checkFile.exists()){
            credentials = cardFileDB.readTextFile(CREDENTALS_DB_NAME);
        } else {
            throw new DbNotFountException();
        }

        checkFile = new File(ATM_DB_NAME);
        if(checkFile.exists()){
            accountList = atmFileDB.readTextFile(ATM_DB_NAME);
        } else {
            throw new DbNotFountException();
        }
    }



    private boolean withdraw(String cardNum, int amount) {
        boolean operationPerform = false;
        for (int index = 0; index < accountList.size(); index++) {
            if (cardNum.equals(accountList.get(index).getAccountNumber())) {
                int balance = accountList.get(index).getCurrentBalance();
                AtmCashManager cashManager = new AtmCashManager();
                if (balance >= amount && cashManager.isCashAmountAvailable(amount)) {
                    balance -= amount;
                    accountList.get(index).setBalance(balance);
                    operationPerform = true;
                }
            }
        }
        return operationPerform;
    }

    private boolean deposit(String cardNum, int amount){
        boolean operationPerform = false;
        for (int index = 0; index < accountList.size(); index++) {
            if (cardNum.equals(accountList.get(index).getAccountNumber())) {
                int balance = accountList.get(index).getCurrentBalance();
                AtmCashManager cashManager = new AtmCashManager();
                try {
                    if (cashManager.isPossibleToDeposit(amount)) {
                        balance += amount;
                        accountList.get(index).setBalance(balance);
                        operationPerform = true;
                    }
                } catch (DepositAmountExceedException e) {
                    e.printStackTrace();
                }
            }
        }
        return operationPerform;
    }

    private int getBalance(String cardNum){
        int balance = -1;
        for (int index = 0; index < accountList.size(); index++){
            if (cardNum.equals(accountList.get(index).getAccountNumber())){
                balance = accountList.get(index).getCurrentBalance();
            }
        }
        return balance;
    }


    @Override
    public Map<String, Integer> operationPerform(String cardNumber, int amount, int operation){
        serverReply.clear();
        switch (operation) {
            case 1:
                if (deposit(cardNumber, amount)) {
                    serverReply.put("Success", 1);
                } else {
                    serverReply.put("Success", 0);
                }
                return serverReply;
            case 2:
                if (withdraw(cardNumber, amount)) {
                    serverReply.put("Success", 1);
                } else {
                    serverReply.put("Success", 0);
                }
                return serverReply;
            case 3:
                int balance = getBalance(cardNumber);
                if (balance != -1.0) {
                    serverReply.put("Success", 1);
                    serverReply.put("balance", balance);
                } else {
                    serverReply.put("Success", 0);
                    serverReply.put("balance", -1);
                }
                return serverReply;
            default:
                return serverReply;
        }
    }


    @Override
    public boolean isCardNumValid(String cardNum) {
        String cardMatcher = "[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}";
//        String validNumber = "1111-2222-3333-4444";
        if (cardNum.length() == 19) {
            if (cardNum.matches(cardMatcher)) {
                if (isSuchCardInDB(cardNum)) {
                    return true;
                } else {
                    System.out.print("Card with such number doesn't exist. Try again!\nCARD NUMBER: ");
                    return false;
                }
            } else {
                System.out.print("Card number should contain only digits and -\nNumber template XXXX-XXXX-XXXX-XXXX. Try again!\nCARD NUMBER: ");
                return false;
            }
        } else {
            System.out.print("The length of the card number does not match the specified template XXXX-XXXX-XXXX-XXXX. Try again!\nCARD NUMBER: ");
            return false;
        }
    }

    @Override
    public boolean isCardPinCorrect(String inputCardNum, String inputPin) {
        String passwordMatcher = "[0-9]{4,8}";
        if (!inputPin.isEmpty() && inputPin.matches(passwordMatcher)) {
            if (inputPin.equals(credentials.get(inputCardNum))) {
                return true;
            } else {
                System.out.print("Incorrect PIN. Try again!\nPIN CODE: ");
                return false;
            }
        } else {
            System.out.print("Error! Password should contain only digits in quantity from 4 to 8. Try again!\nPIN CODE: ");
            return false;
        }
    }

    private boolean isSuchCardInDB(String inputCardNum) {
        for (String num : credentials.keySet()) {
            if (num.equals(inputCardNum)) {
                return true;
            }
        }
        return false;
    }
}
