import java.util.HashMap;

/**
 * Created by timothy on 1/7/17.
 */
public class OwnershipCollection {
    private HashMap<Integer, Ownership> coll;

    public OwnershipCollection() {
        coll = new HashMap<>();
    }

    public void addOwnership(int customerId, Ownership ownership) {
        if(!coll.containsKey(customerId)) {
            coll.put(customerId, ownership);
        } else {
            System.out.println("ERROR: Collection already contains ownership with that id.");
        }
    }
}
