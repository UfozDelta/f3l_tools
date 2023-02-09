public class Account {
    double balance;
    double maxDD;
    double DailyDD;
    double target;


    public Account(double i, double mDD, double dDD, double t) {
        balance = i;
        maxDD = mDD;
        DailyDD = dDD;
        target = t;
    }

    public void showInfo() {
        System.out.println("$" + balance + " Max Drawdown $" + maxDD + " Daily Drawdown $" + DailyDD + " Target $" + target);
    }
}
