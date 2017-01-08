/**
 * Created by timothy on 1/7/17.
 */
public class Transaction {
    private String date;
    private long customerId;
    private long creditCardNumber;
    private long venderId;

    public Transaction(String date, long customerId, long creditCardNumber, long venderId) {
        this.date = date;
        this.customerId = customerId;
        this.creditCardNumber = creditCardNumber;
        this.venderId = venderId;
    }
}
