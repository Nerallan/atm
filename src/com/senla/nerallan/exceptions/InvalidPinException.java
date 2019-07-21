package com.senla.nerallan.exceptions;

public class InvalidPinException extends Exception {
    public InvalidPinException(int attemptNum) {
        super("Invalid pin for this card, to lock left " + attemptNum + " attemp(s)");
    }
}
