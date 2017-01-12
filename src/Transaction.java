/**
 * Created by timothy on 1/7/17.
 */
public class Transaction {
    private String date;
    private int customerId;
    private String creditCardNumber;
    private int venderId;
    private double amount;

    public Transaction(String date, int customerId, String creditCardNumber, int venderId, double amount) {
        this.date = date;
        this.customerId = customerId;
        this.creditCardNumber = creditCardNumber;
        this.venderId = venderId;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public int getVenderId() {
        return venderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setVenderId(int venderId) {
        this.venderId = venderId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
