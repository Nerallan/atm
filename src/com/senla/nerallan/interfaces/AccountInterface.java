package com.senla.nerallan.interfaces;

import com.senla.nerallan.exceptions.DepositAmountExceedException;
import com.senla.nerallan.exceptions.InsufficientFundsException;

public interface AccountInterface {
    double deposit(double amount) throws DepositAmountExceedException;

    double withdraw(double amount) throws InsufficientFundsException;

    void getCurrentBalance();
}
