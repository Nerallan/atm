package com.senla.nerallan.db;

import com.senla.nerallan.Account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtmFileDB {

    public AtmFileDB() {
    }

    public List<Account> readTextFile(String fileName) throws IOException, ParseException {
        List<Account> accountList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] accountDbData = line.split(" ");
                Account account = parseAccount(accountDbData);
                accountList.add(account);
            }
        } finally {
            bufferedReader.close();
        }
        return accountList;
    }

    private Account parseAccount(String[] accountData){
        String cardNum = accountData[0];
        int balance = Integer.parseInt(accountData[1]);
        Date date = null;
        try {
             date = new SimpleDateFormat("yyyyMMddHHmm").parse(accountData[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean isActive = Boolean.parseBoolean(accountData[3]);
        return new Account(cardNum, balance, date, isActive);
    }
}
