import java.text.DecimalFormat;
import java.util.*;

public class Main {

    public static final int maxLev = 2;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Create account Class to track account info
        Account acc = new Account(50000, 5000, 2500, 2500);
        acc.showInfo();

        TradeForm trade = new TradeForm(acc);

        // Make Menu for interactive program
        boolean quit = false;
        while(quit != true) {
            pt(" ");
            pt("Welcome to Trading Tools");
            pt("Choose an option to continue");
            pt("1: Max Shares bought \n2: Average Entry Calculator \n3: Percent Risk");

            switch(in.nextInt()) {
                case(1): 
                    trade.maxShares();
                    break;
                case(2):
                    double x = trade.avgPriceCalc(0);
                    pt("Your average price per share is $" + x);
                    break;
                case(3):
                    trade.optimalPosSize(acc);
                    break;
                default:
                    pt("Goodbye!");
                    quit = true;
                    break;
            }
        }
    }

    // Println method quick
    public static void pt(String input) {
        System.out.println(input);
    } // Method end 

    // Println method quick
    public static void pt(double input) {
        System.out.println(input);
    } // Method end 

    // Max Shares Method
    public static void maxShares(double balance, double price) {
        pt("You can buy at most " + (int)(balance / price) * maxLev + " Shares");
    }

    // Average Price Calculation
    public static double avgPriceCalc(int lots) {
        Scanner in = new Scanner(System.in);

        int lotNum;
        if(lots == 0) {
        // Enter Ammount of lots for for loop
        pt("Enter ammount of lots");
        lotNum = in.nextInt();
        }

        ArrayList<Double> lotNumber = new ArrayList<Double>();
        lotNum = lots;

        // For loop to check average price
        for (int i = 0; i < lotNum; i++) {
            pt("Enter Price of Lots to average input");
            lotNumber.add(in.nextDouble());
        }

        // Final Avg Calc
        double finalSum = 0;
        for (Double double1 : lotNumber) {
            finalSum = finalSum + double1;
        }

        //Return average price per share
        return finalSum / lotNumber.size();

    } // Method End

    // Calcualte optimial Postion size
    public static void optimalPosSize(Account acc) {
        DecimalFormat df = new DecimalFormat("#.##");
        Scanner in = new Scanner(System.in);

        pt("Long or Short (L/S)");
        String longShort = in.nextLine();


        pt("Enter ammount of lots");
        int lots = in.nextInt();        

        double avgPrice = avgPriceCalc(lots);

        pt("Enter risk ammount in percent");
        double percentRisk = in.nextDouble() / 100.00;

        double totalLoss = (avgPrice * percentRisk) * lots;
        double stopLevel = ((avgPrice * lots) - totalLoss) / lots;

        if(longShort.equals("S")) {
            stopLevel = ((avgPrice * lots) + totalLoss) / lots;
        }

        pt("\nYou will risk " + (percentRisk*100)+ "%" + "\nYour Entry Price is $"+ avgPrice + "\nYour stop level will be $" + stopLevel + "\nRisking a total of $" + totalLoss);
        pt("Total risk to the account is " + df.format((totalLoss / (acc.balance))*100) + "%");
    } // End Method


    // Method for calculating profit
    public static void profitCalc(Account acc) {
        optimalPosSize(acc);
    }
    
    
}