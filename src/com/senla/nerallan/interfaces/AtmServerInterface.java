package com.senla.nerallan.interfaces;

import java.util.Map;

public interface AtmServerInterface {
    boolean withdraw(String cardNum, int amount);
    boolean deposit(String cardNum, int amount);
    int getBalance(String cardNum);
    Map<String, Integer> operationPerform(String cardNum, int cashAmount, int operation);
}
