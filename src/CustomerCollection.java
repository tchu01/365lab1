import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by timothy on 1/7/17.
 */
public class CustomerCollection {
    private HashMap<Integer, Customer> collById;
    private HashMap<Integer, Customer> collBySsn;
    private HashSet<Integer> ssns;

    public CustomerCollection() {
        this.collById = new HashMap<>();
        this.collBySsn = new HashMap<>();
        this.ssns = new HashSet<>();
    }

    public void addCustomer(Customer cust) {
        if(!this.ssns.contains(cust.getSsn())) {
            this.ssns.add(cust.getSsn());
            if(!this.collById.containsKey(cust.getId())) {
                this.collById.put(cust.getId(), cust);
                this.collBySsn.put(cust.getSsn(), cust);
            } else {
                System.out.println("ERROR: Collection already contains customer with that id.");
            }
        } else {
            System.out.println("ERROR: Collection already contains customer with that SSN.");
        }
    }

    public HashMap<Integer, Customer> getCollById() {
        return collById;
    }

    public HashMap<Integer, Customer> getCollBySsn() {
        return collBySsn;
    }
}
