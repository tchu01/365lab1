/**
 * Created by timothy on 1/7/17.
 */
public class Transaction {
    private String date;
    private int customerId;
    private long creditCardNumber;
    private int venderId;

    public Transaction(String date, int customerId, long creditCardNumber, int venderId) {
        this.date = date;
        this.customerId = customerId;
        this.creditCardNumber = creditCardNumber;
        this.venderId = venderId;
    }

    public String getDate() {
        return date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public int getVenderId() {
        return venderId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setVenderId(int venderId) {
        this.venderId = venderId;
    }


}
