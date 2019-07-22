package com.senla.nerallan;

import com.senla.nerallan.db.CardFileDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class TestAtm {
    private static final String CREDENTALS_DB_NAME = "cards_credentials";
    private static HashMap<String, String> credentials;

    private static ConsoleManager consoleManager;

    private static Atm atm;

    public static void main(String[] args) throws IOException {

        consoleManager = new ConsoleManager();
        consoleManager.displayWelcomeScreen();
        consoleManager.requestCardCredentials();

        isLogin();
    }

    private static boolean isLogin() throws IOException {
        CardFileDB cardFileDB = new CardFileDB();
        credentials = cardFileDB.readTextFile(CREDENTALS_DB_NAME);

        atm = new Atm();
        consoleManager.showCardNumRequest();
        String cardNum = null;
        do {
            cardNum = consoleManager.inputMessage();
        } while (!atm.isCardNumValid(cardNum));

        String pinCode = null;
        int invalidPinCounter = 0;
        consoleManager.showPinRequest();
        do {
            pinCode = consoleManager.inputMessage();
            if (pinCode.equals(credentials.get(cardNum))){
                return true;
            } else if(invalidPinCounter == 3) {
                return false;
            }
            invalidPinCounter++;
        } while(!atm.isCardPinCorrect(cardNum, pinCode));
        return true;
    }


//    public String verifyPIN(String cardNumber, int pin) {
//        if (!bankDB.isEmpty() && cardNumber != null) {
//            System.out.println(bankDB.get(cardNumber)[1] + "  " + pinVal);
//            if (bankDB.get(cardNumber)[0].equals(String.valueOf(pinVal))) {
//                return bankDB.get(cardNumber)[1];
//            }
//        }
//        return null;
//    }


}
