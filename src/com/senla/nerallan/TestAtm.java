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
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }

        try {
            atmServer = new AtmServer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException | DbNotFountException exeption) {
            exeption.printStackTrace();
            System.out.println(exeption.getMessage());
        }

        if (isLogin()) {
            Account account = new Account(inputCardNum);
            try {
                atmServerInterface = new AtmServer();
            } catch (IOException exception) {
                exception.printStackTrace();
                System.out.println(exception.getMessage());
            }
            if (atmServer.isCardActive(inputCardNum)){
                while (true) {
                    consoleManager.displayAtmMenu();
                    int operation = consoleManager.inputDigits();
                    if (operation == 1 || operation == 2 || operation == 3 || operation == 0){
                        Transaction currentTransaction = Transaction.setTransaction(account, operation);
                        if (currentTransaction != null){
                            try {
                                currentTransaction.performTransaction(account, atmServerInterface, operation);
                            } catch (IOException exception) {
                                exception.printStackTrace();
                                System.out.println(exception.getMessage());
                            }
                        }
                    } else {
                        System.out.println("No such operation! Select from 0 to 4");
                    }
                }
            } else {
                System.out.println("Your card is blocked! ");
            }
        }
    }




    private static boolean isLogin() {
        consoleManager.showCardNumRequest();

        do {
            inputCardNum = consoleManager.inputMessage();
        } while (!atmServer.isCardNumValid(inputCardNum));

        String pinCode = null;
        int invalidPinCounter = 1;
        consoleManager.showPinRequest();
        do {
            pinCode = consoleManager.inputMessage();
            if (pinCode.equals(credentials.get(inputCardNum))) {
                return true;
            } else if (invalidPinCounter == 3) {
                System.out.println("CARD BLOCKED");
                atmServer.blockingCard(inputCardNum);
                return false;
            }
            System.out.println((3-invalidPinCounter) + " attempts left");
            invalidPinCounter++;
        } while (!atmServer.isCardPinCorrect(inputCardNum, pinCode));
        return true;
    }

}
