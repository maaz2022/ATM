package ATM2;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Account {
    private int accountNumber;
    private int pinNumber;
    private double checkingBalance;
    private double savingBalance;

    public Account(){}

    public Account(int accountNumber,int pinNumber){
        this.accountNumber = accountNumber;
        this.pinNumber = pinNumber;
    }
    public Account(int accountNumber,int pinNumber,double savingBalance,double checkingBalance){
     this.accountNumber = accountNumber;
     this.pinNumber = pinNumber;
     this.savingBalance=savingBalance;
     this.checkingBalance = checkingBalance;  
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }
    public double calcCheckingWithdraw(double checkingBalance2){
        checkingBalance = (checkingBalance - checkingBalance2);
        return checkingBalance;
    }
    public double calcSavingWithdraw(double amount){
        savingBalance = (savingBalance - amount);
        return savingBalance;
    }
    public double calcCheckingDeposit(double amount){
        checkingBalance = (checkingBalance + amount);
        return checkingBalance;
    }
    public double calcSavingDeposit(double amount){
        savingBalance = (savingBalance + amount);
        return savingBalance;
    }
    public void calcCheckTransfer(double amount){
        checkingBalance = checkingBalance - amount;
        savingBalance = savingBalance + amount;
    }
    public void calcSavingTransfer(double amount){
        savingBalance = savingBalance - amount;
        checkingBalance = checkingBalance + amount;
    }

    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    Scanner input = new Scanner(System.in);

    public void getCheckingWidthrawInput(){
        boolean loop = false;
            while(!loop){
            try{
            System.out.println("\n Current Account Balance " + moneyFormat.format(checkingBalance));
            System.out.println("Amount you want to widthraw from checking account OR type 0 for more options ");
            Double amount = input.nextDouble();
            if(checkingBalance - amount >=0 && amount >= 0){
                calcCheckingWithdraw(amount);
                System.out.println("\n Current Account Balance of Checking Account: " + moneyFormat.format(checkingBalance));
                loop = true;
            }
            else{
                System.out.println("Balance can't be negitive");
            }
        }
        catch(InputMismatchException e){
            System.out.println("\n invalid choice");
            input.next();
        }
    }
    }
    public void getSavingWidthrawInput(){
        boolean loop = false;
        while(!loop){
            try{
                System.out.println("\n Saving Account Balance = " + moneyFormat.format(savingBalance));
                System.out.println("\n Amount you want to widthraw from saving Account OR type 0 for more options ");
                double amount = input.nextDouble();
                if(savingBalance - amount >= 0 && amount >= 0){
                    calcSavingWithdraw(amount);
                    System.out.println("\n Current saving Account amount = " + moneyFormat.format(savingBalance));
                    loop = true;
                }
                else{
                    System.out.println("Balance can't be negative");
                }
            }
                catch(InputMismatchException e){
                    System.out.println("\n invalid input");
                    input.next();
                }
            }
    }
    public void getSavingDepositInput(){
        boolean loop = false;
        while(!loop){
            try{
                System.out.println("\n Saving Account Balance = " + moneyFormat.format(savingBalance));
                System.out.println("\n Amount you want to deposit to saving Account OR type 0 for more options ");
                double amount = input.nextDouble();
                if(savingBalance + amount >= 0 && amount >= 0){
                    calcSavingDeposit(savingBalance);
                    System.out.println("\n Current saving Account amount = " + moneyFormat.format(savingBalance));
                    loop = true;
                }
                else{
                    System.out.println("Balance can't be negative");
                }
            }
                catch(InputMismatchException e){
                    System.out.println("\n invalid input");
                }
            }
    }
    public void getCheckingDepositInput(){
        boolean loop = false;
        while(!loop){
            try{
                System.out.println("\n checking Account Balance = " + moneyFormat.format(checkingBalance));
                System.out.println("\n Amount you want to deposit to checking Account OR type 0 for more options ");
                double amount = input.nextDouble();
                if(checkingBalance + amount >= 0 && amount >= 0){
                    calcCheckingDeposit(amount);
                    System.out.println("\n Current checking Account amount = " + moneyFormat.format(checkingBalance));
                    loop = true;
                }
                else{
                    System.out.println("Balance can't be negative");
                }
            }
                catch(InputMismatchException e){
                    System.out.println("\n invalid input");
                    input.next();
                }
            }
    }
   public void getTransferInput(String accType) {
    boolean loop = false;
    while (!loop) {
        try {
            if (accType.equals("checking")) {
                System.out.println("\nSelect account to transfer to:");
                System.out.println("\n1. Savings");
                System.out.println("\n2. Exit");
                System.out.print("Choice: ");
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("\nChecking account balance: " + moneyFormat.format(checkingBalance));
                        System.out.print("Amount you want to transfer to Savings: ");
                        double amount = input.nextDouble();
						if ((savingBalance + amount) >= 0 && (checkingBalance - amount) >= 0 && amount >= 0) {
                            calcCheckTransfer(amount);
                            System.out.println("\nChecking balance after transfer: " + moneyFormat.format(checkingBalance));
                            System.out.println("\nSavings balance after transfer: " + moneyFormat.format(savingBalance));
                        } else {
                            System.out.println("\nInvalid amount or insufficient funds.");
                        }
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("\nInvalid choice");
                        break;
                }
            } else if (accType.equals("Saving")) {
                System.out.println("\nSelect account to transfer to:");
                System.out.println("\n1. Checking");
                System.out.println("\n2. Exit");
                System.out.print("Choice: ");
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("\nSavings account balance: " + moneyFormat.format(savingBalance));
                        System.out.print("Amount you want to transfer to Checking: ");
                        double amount = input.nextDouble();
                        if (amount >= 0 && savingBalance >= amount) {
                            calcSavingTransfer(amount);
                            System.out.println("\nSavings balance after transfer: " + moneyFormat.format(savingBalance));
                            System.out.println("\nChecking balance after transfer: " + moneyFormat.format(checkingBalance));
                        } else {
                            System.out.println("\nInvalid amount or insufficient funds.");
                        }
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("\nInvalid choice");
                        break;
                }
            } else {
                System.out.println("\nAccount type is not recognized.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid choice");
            input.next();
        }
    }
   }}