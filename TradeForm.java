import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class TradeForm {
public final int maxLev = 2;

public Account acc;

    public TradeForm(Account account) {
        account = acc;
    }

    ArrayList<Double> lotNumber = new ArrayList<Double>();

    // Println method quick
    public static void pt(String input) {
        System.out.println(input);
    } // Method end 

    // Println method quick
    public static void pt(double input) {
        System.out.println(input);
    } // Method end 

    // Max Shares Method
    public void maxShares() {
        Scanner in = new Scanner(System.in);
        double price = in.nextDouble();
        
        pt("You can buy at most " + (int)(acc.balance / price) * maxLev + " Shares");
    }

    // Average Price Calculation
    public double avgPriceCalc(int lots) {
        Scanner in = new Scanner(System.in);

        int lotNum;
        if(lots == 0) {
        // Enter Ammount of lots for for loop
        pt("Enter ammount of lots");
        lotNum = in.nextInt();
        }

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
    public void optimalPosSize(Account acc) {
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
    public void profitCalc(Account acc) {
        optimalPosSize(acc);
    }
}
