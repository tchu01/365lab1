import java.util.HashMap;

/**
 * Created by timothy on 1/7/17.
 */
public class CustomerCollection {
    private HashMap<Long, Customer> coll;

    public CustomerCollection() {
        this.coll = new HashMap<>();
    }

    public void addCustomer(Customer cust) {
        this.coll.put(cust.getId(), cust);
    }

}
