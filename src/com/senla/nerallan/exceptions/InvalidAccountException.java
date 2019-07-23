package com.senla.nerallan.exceptions;

public class InvalidAccountException extends Exception {
    public InvalidAccountException(String accountNum) {
        super("Account with number " + accountNum + " doesn't exist");
    }
}
