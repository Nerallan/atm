package com.senla.nerallan.db;

import com.senla.nerallan.Account;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtmFileDB {

    private String fileName;
    private String dateFormatTemplate = "yyyyMMddHHmm";

    public AtmFileDB() {
    }

    public List<Account> readTextFile(String fileName) throws IOException, ParseException {
        this.fileName = fileName;
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

    private Account parseAccount(String[] accountData) {
        String cardNum = accountData[0];
        int balance = Integer.parseInt(accountData[1]);
        Date date = null;
        try {
            date = new SimpleDateFormat(dateFormatTemplate).parse(accountData[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean isActive = Boolean.parseBoolean(accountData[3]);
        return new Account(cardNum, balance, date, isActive);
    }


    public boolean updateDb(List<Account> accountList) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Account account : accountList) {
            stringBuilder.append(account.getAccountNumber());
            stringBuilder.append(" ");
            stringBuilder.append(String.valueOf(account.getCurrentBalance()));
            stringBuilder.append(" ");
            Date date = account.getOperationDate();
            DateFormat dateFormat = new SimpleDateFormat(dateFormatTemplate);
            stringBuilder.append(dateFormat.format(date));
            stringBuilder.append(" ");
            stringBuilder.append(String.valueOf(account.isActive()));
            stringBuilder.append("\n");
        }
        if (writer != null) {
            try {
                writer.write(String.valueOf(stringBuilder));
                writer.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
