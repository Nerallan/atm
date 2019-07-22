package com.senla.nerallan;

import com.senla.nerallan.interfaces.ConsoleManagerInterface;

import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleManager implements ConsoleManagerInterface{
    private Scanner scanner;

    public ConsoleManager(){
        scanner = new Scanner(new InputStreamReader(System.in));
    }

    public int setScreen(String message, boolean isInputNeeded){
        System.out.println(message);
        if(isInputNeeded){
            return inputDigits();
        } else {
            return -1;
        }
    }

    public void showCardNumRequest(){
        System.out.print("CARD NUMBER: ");
    }

    public void showPinRequest(){
        System.out.print("PIN CODE: ");
    }


    @Override
    public String inputMessage(){
        String text = scanner.nextLine();
        return text;
    }

    @Override
    public int inputDigits(){
        int digits = 0;
        while(scanner.hasNextInt()){
            digits = scanner.nextInt();
        }
        return digits;
    }


    @Override
    public void displayWelcomeScreen() {
        setScreen("****************************************", false);
        setScreen("             Welcome to ATM!", false);
        setScreen("****************************************", false);
    }

    @Override
    public void requestCardCredentials() {
        setScreen("Please, input card credentials", false);
    }

    @Override
    public void displayAtmMenu() {
        setScreen("========================================", false);
        setScreen("                ATM menu!", false);
        setScreen("----------------------------------------", false);
        setScreen("Please enter desired operation:", false);
        setScreen("[1] Deposit Money", false);
        setScreen("[2] Withdraw Money", false);
        setScreen("[3] Check Balance", false);
        setScreen("[0] Exit", false);
        setScreen("========================================", false);
    }
}
