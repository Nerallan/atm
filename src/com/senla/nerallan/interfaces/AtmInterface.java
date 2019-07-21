package com.senla.nerallan.interfaces;

import com.senla.nerallan.exceptions.InvalidAccountException;
import com.senla.nerallan.exceptions.InvalidPinException;

import java.io.IOException;

public interface AtmInterface {
    void login(String cardNumber, String password) throws InvalidAccountException, InvalidPinException;

    void displayAtmMenu();

    void selectOperation(int operation) throws IOException;

    void exit();
}
