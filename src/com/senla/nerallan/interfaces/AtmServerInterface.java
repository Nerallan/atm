package com.senla.nerallan.interfaces;

import java.util.Map;

public interface AtmServerInterface {
    Map<String, Integer> operationPerform(String cardNum, int cashAmount, int operation);
}
