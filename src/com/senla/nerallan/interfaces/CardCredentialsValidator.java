package com.senla.nerallan.interfaces;

import java.io.IOException;

public interface CardCredentialsValidator {
    boolean isCardNumValid(String cardNum) throws IOException;

    boolean isCardPinCorrect(String inputCardNum, String inputPin);
}
