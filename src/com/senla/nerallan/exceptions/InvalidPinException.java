package com.senla.nerallan.exceptions;

public class InvalidPinException extends Exception {
    public InvalidPinException(int attemptNum) {
        super("Invalid pin! to card lock left " + attemptNum + " attemp(s)");
    }
}
