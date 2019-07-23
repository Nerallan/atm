package com.senla.nerallan.exceptions;

import static com.senla.nerallan.AtmCashManager.MAX_DEPOSIT_PER_TRANSACTION;

public class DepositAmountExceedException extends Exception {

    public DepositAmountExceedException() {
        super("Exceeded the allowable limit of deposit cash (" + MAX_DEPOSIT_PER_TRANSACTION + ") per transaction");
    }
}
