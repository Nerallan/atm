package com.senla.nerallan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestAtm {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Atm atm = new Atm();
        while (true){
            atm.displayAtmMenu();
            int operation = Integer.parseInt(bufferedReader.readLine());
            atm.selectOperation(operation);
        }
    }
}
