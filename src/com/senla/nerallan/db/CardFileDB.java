package com.senla.nerallan.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CardFileDB {

    private static HashMap<String, String> cardDB = new HashMap<>();

    public CardFileDB(){
    }

    public HashMap<String, String> readTextFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        try {
            String credentialsLine;
            while ((credentialsLine = bufferedReader.readLine()) != null) {
                String[] credentials = credentialsLine.split(" ");
                cardDB.put(credentials[0], credentials[1]);
            }
        } finally {
            bufferedReader.close();
        }
        return cardDB;
    }
}
