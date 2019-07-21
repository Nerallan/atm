package com.senla.nerallan;

import com.senla.nerallan.exceptions.InvalidAccountException;
import com.senla.nerallan.exceptions.InvalidPinException;
import com.senla.nerallan.interfaces.AccountInterface;
import com.senla.nerallan.interfaces.AtmInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Atm implements AtmInterface {

    private double interestRate = 2;
    private double transactionFees = 10;
    private List<Customer> customerList = new ArrayList<>();

    public void calculateInterest(Customer customer) {
        Account account = customer.getAccount();
        double balance = account.getCurrentBalance();
        double interestAmount = balance * interestRate / 100;
        double totalBalance = balance + interestAmount;
        System.out.println("Interest amount: " + interestAmount + "\nTotal money after adding interests: " + totalBalance);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getTransactionFees() {
        return transactionFees;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    @Override
    public void displayAtmMenu() {
        System.out.println("========================================");
        System.out.println("Please enter desired operation:");
        System.out.println("[1] Add Customer");
        System.out.println("[2] Deposit Money");
        System.out.println("[3] Withdraw Money");
        System.out.println("[4] Check Balance");
        System.out.println("[5] Calculate Interests");
        System.out.println("[0] Exit");
        System.out.println("========================================");
    }

    @Override
    public void login(String cardNumber, String password) throws InvalidAccountException, InvalidPinException {

    }

    @Override
    public void selectOperation(int operation) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        customerList = getCustomerList();
        switch (operation) {
            case 1:
                System.out.println("Creating account for a new customer: ");
                System.out.println("Please enter the initial amount in your account: ");
                double balance = Double.parseDouble(bufferedReader.readLine());
                System.out.println("Please enter your account number: ");
                String accountNum = bufferedReader.readLine();
                Account account = new Account(balance, accountNum);
                System.out.println("Please enter your name: ");
                String name = bufferedReader.readLine();
                Customer customer = new Customer(name, account);
                customerList.add(customer);
                System.out.println("Number of customers: " + customerList.size());
                for (Customer c : customerList) {
                    System.out.println(c.getName() + " name");
                }
                break;
            case 2:
                System.out.println("Enter account number ");
                accountNum = bufferedReader.readLine();
                if (customerList.isEmpty()) {
                    System.out.println("Account number not found!");
                } else {
                    boolean accountFound = false;
                    for (int index = 0; index < customerList.size(); index++) {
                        Account accountTemp = customerList.get(index).getAccount();
                        String accountNumTemp = accountTemp.getAccountNumber();
                        if (accountNum.equals(accountNumTemp)) {
                            System.out.println("Please enter the amount to deposit ");
                            double moneyAmount = Double.parseDouble(bufferedReader.readLine());
                            accountTemp.deposit(moneyAmount);
                            accountFound = true;
                        }
                    }
                    if (!accountFound) {
                        System.out.println("Account number not found!");
                    }
                }
                break;
            case 3:
                System.out.println("Enter account number ");
                accountNum = bufferedReader.readLine();
                if (customerList.isEmpty()) {
                    System.out.println("Account number not found!");
                } else {
                    boolean accountFound = false;
                    for (int index = 0; index < customerList.size(); index++) {
                        Account accountTemp = customerList.get(index).getAccount();
                        String accountNumTemp = accountTemp.getAccountNumber();
                        if (accountNum.equals(accountNumTemp)) {
                            System.out.println("Please enter the amount to withdraw ");
                            double moneyAmount = Double.parseDouble(bufferedReader.readLine());
                            accountTemp.withdraw(moneyAmount);
                            accountFound = true;
                        }
                    }
                    if (!accountFound) {
                        System.out.println("Account number not found!");
                    }
                }
                break;
            case 4:
                System.out.println("Enter account number ");
                accountNum = bufferedReader.readLine();
                if (customerList.isEmpty()) {
                    System.out.println("Account number not found!");
                } else {
                    boolean accountFound = false;
                    for (int index = 0; index < customerList.size(); index++) {
                        Account accountTemp = customerList.get(index).getAccount();
                        String accountNumTemp = accountTemp.getAccountNumber();
                        if (accountNum.equals(accountNumTemp)) {
                            System.out.println("Balance is: " + accountTemp.getCurrentBalance());
                            accountFound = true;
                        }
                    }
                    if (!accountFound) {
                        System.out.println("Account number not found!");
                    }
                }
                break;
            case 5:
                System.out.println("Enter account number ");
                accountNum = bufferedReader.readLine();
                if (customerList.isEmpty()) {
                    System.out.println("Account number not found!");
                } else {
                    boolean accountFound = false;
                    for (int index = 0; index < customerList.size(); index++) {
                        Account accountTemp = customerList.get(index).getAccount();
                        String accountNumTemp = accountTemp.getAccountNumber();
                        if (accountNum.equals(accountNumTemp)) {
                            calculateInterest(customerList.get(index));
                            accountFound = true;
                        }
                    }
                    if (!accountFound) {
                        System.out.println("Account number not found!");
                    }
                }
                break;
            case 0:
                exit();
                break;
            default:
                break;

        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
