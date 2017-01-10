import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by timothy on 1/7/17.
 */
public class CustomerCollection {
    private HashMap<Integer, Customer> coll;
    private HashSet<Integer> ssns;

    public CustomerCollection() {
        this.coll = new HashMap<>();
        this.ssns = new HashSet<>();
    }

    public void addCustomer(Customer cust) {
        if(!this.ssns.contains(cust.getSsn())) {
            this.ssns.add(cust.getSsn());
            if(!this.coll.containsKey(cust.getId())) {
                this.coll.put(cust.getId(), cust);
            } else {
                System.out.println("ERROR: Collection already contains customer with that id.");
            }
        } else {
            System.out.println("ERROR: Collection already contains customer with that SSN.");
        }
    }

}
