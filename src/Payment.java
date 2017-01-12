/**
 * Created by timothy on 1/7/17.
 */
public class Payment {
    private String date;
    private String creditCardNumber;
    private double amount;

    public Payment(String date, String creditCardNumber, double amount) {
        this.date = date;
        this.creditCardNumber = creditCardNumber;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
