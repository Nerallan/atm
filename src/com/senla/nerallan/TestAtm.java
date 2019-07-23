package com.senla.nerallan;

import com.senla.nerallan.db.CardFileDB;
import com.senla.nerallan.exceptions.DbNotFountException;
import com.senla.nerallan.interfaces.AtmServerInterface;
import com.senla.nerallan.operations.Transaction;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class TestAtm {
    private static final String CREDENTALS_DB_NAME = "cards_credentials";
    private static HashMap<String, String> credentials;

    private static ConsoleManager consoleManager;
    private static AtmServerInterface atmServerInterface;
    private static AtmServer atmServer;

    private static String inputCardNum = null;

    public static void main(String[] args) throws ParseException, DbNotFountException {

        consoleManager = new ConsoleManager();
        consoleManager.displayWelcomeScreen();
        consoleManager.requestCardCredentials();

        CardFileDB cardFileDB = new CardFileDB();
        try {
            credentials = cardFileDB.readTextFile(CREDENTALS_DB_NAME);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        if (isLogin()) {
            while (true) {
                consoleManager.displayAtmMenu();
                int operation = consoleManager.inputDigits();
                if (operation == 1 || operation == 2 || operation == 3 || operation == 0){
                    Account account = new Account(inputCardNum);
                    Transaction currentTransaction = Transaction.setTransaction(account, operation);
                    if (currentTransaction != null){
                        try {
                            atmServerInterface = new AtmServer();
                            currentTransaction.performTransaction(account, atmServerInterface, operation);
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                    System.out.println("No such operation! Select from 0 to 4");
                }
            }
        }
    }




    private static boolean isLogin() {
        try {
            atmServer = new AtmServer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException | DbNotFountException exeption) {
            exeption.printStackTrace();
            System.out.println(exeption.getMessage());
        }
        consoleManager.showCardNumRequest();

        do {
            inputCardNum = consoleManager.inputMessage();
        } while (!atmServer.isCardNumValid(inputCardNum));

        String pinCode = null;
        int invalidPinCounter = 0;
        consoleManager.showPinRequest();
        do {
            pinCode = consoleManager.inputMessage();
            if (pinCode.equals(credentials.get(inputCardNum))) {
                return true;
            } else if (invalidPinCounter == 3) {
                return false;
            }
            invalidPinCounter++;
        } while (!atmServer.isCardPinCorrect(inputCardNum, pinCode));
        return true;
    }

}
