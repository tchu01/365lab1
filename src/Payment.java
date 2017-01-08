/**
 * Created by timothy on 1/7/17.
 */
public class Payment {
    private String date;
    private long creditCardNumber;
    private double amount;

    public Payment(String date, long creditCardNumber, double amount) {
        this.date = date;
        this.creditCardNumber = creditCardNumber;
        this.amount = amount;
    }
}
