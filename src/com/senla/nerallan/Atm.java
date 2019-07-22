package com.senla.nerallan;

import com.senla.nerallan.db.CardFileDB;
import com.senla.nerallan.interfaces.CardCredentialsValidator;

import java.io.IOException;
import java.util.HashMap;

public class Atm implements CardCredentialsValidator {

    private static final String CREDENTALS_DB_NAME = "cards_credentials";
    private static HashMap<String, String> credentials;
    private CardFileDB cardFileDB;

    public Atm() throws IOException {
        cardFileDB = new CardFileDB();
        credentials = cardFileDB.readTextFile(CREDENTALS_DB_NAME);
    }


    @Override
    public boolean isCardNumValid(String cardNum) throws IOException {
        String cardMatcher = "[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}";
//        String validNumber = "1111-2222-3333-4444";
        if (cardNum.length() == 19) {
            if (cardNum.matches(cardMatcher)) {
                if(isSuchCardInDB(cardNum)){
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
            if (inputPin.equals(credentials.get(inputCardNum))){
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
        for (String num : credentials.keySet()){
            if (num.equals(inputCardNum)){
                return true;
            }
        }
    return false;
    }
}
