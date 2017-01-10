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

    public String getDate() {
        return date;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
