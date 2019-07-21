package com.senla.nerallan;

public class Customer {
    private String name;
    private Account account;

    Customer (String name, Account account){
        this.name = name;
        this.account = account;
    }

    public void display(){
        System.out.println("Name: " + name + ", \nAccount Number:" + account.getAccountNumber() + ", \nAccount Balance: " + account.getCurrentBalance());
    }

    public String getName(){
        return name;
    }

    public Account getAccount(){
        return account;
    }
}
