package com.senla.nerallan.exceptions;

public class DepositAmountExceedException extends Exception {
    private static final int MAX_AMOUNT_PER_TRANSACTION = 1000000;

    public DepositAmountExceedException() {
        super("Exceeded the allowable limit of deposit funds " + MAX_AMOUNT_PER_TRANSACTION + " for the transaction");
    }
}
