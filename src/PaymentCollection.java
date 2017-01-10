import java.util.ArrayList;

/**
 * Created by timothy on 1/7/17.
 */
public class PaymentCollection {
    private ArrayList<Payment> coll;

    public PaymentCollection() {
        this.coll = new ArrayList<>();
    }

    public void addPayment(Payment payment) {
        this.coll.add(payment);
    }
}
