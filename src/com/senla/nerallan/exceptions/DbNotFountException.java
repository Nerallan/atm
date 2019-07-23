package com.senla.nerallan.exceptions;

public class DbNotFountException extends Exception {
    public DbNotFountException() {
        super("DB file not found!");
    }
}
