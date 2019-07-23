package com.senla.nerallan;

import com.senla.nerallan.exceptions.DepositAmountExceedException;
import com.senla.nerallan.exceptions.InsufficientFundsException;

public class AtmCashManager {
    public static final int MAX_DEPOSIT_PER_TRANSACTION = 1000000;
    private static final int CURRENT_ATM_BALANCE = 10000;

    private int availableCash;

    public AtmCashManager(){
        availableCash = CURRENT_ATM_BALANCE;
    }


    public boolean isCashAmountAvailable(int amount){
        if(amount <= availableCash) {
            return true;
        } else {
            try {
                throw new InsufficientFundsException();
            } catch (InsufficientFundsException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public boolean isPossibleToDeposit(int amount) throws DepositAmountExceedException{
        if (amount <= MAX_DEPOSIT_PER_TRANSACTION) {
            return true;
        } else {
            throw new DepositAmountExceedException();
        }
    }

}
