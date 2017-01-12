import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by timothy on 1/7/17.
 */
public class OwnershipCollection {
    private HashMap<Integer, Ownership> collById;
    private static HashMap<String, HashSet<Integer>> collByNumber = new HashMap<>();

    public OwnershipCollection() {
        collById = new HashMap<>();
    }

    public void addOwnership(int customerId, Ownership ownership) {
        if(!collById.containsKey(customerId)) {
            collById.put(customerId, ownership);
        } else {
            System.out.println("ERROR: Collection already contains ownership with that id.");
        }
    }

    public void addReverseOwnership(String number, int customerId) {
        if(!collByNumber.containsKey(number)) {
            collByNumber.put(number, new HashSet<>());
            collByNumber.get(number).add(customerId);
        } else {
            collByNumber.get(number).add(customerId);
        }
    }

    public HashMap<Integer, Ownership> getCollById() {
        return collById;
    }

    public static HashMap<String, HashSet<Integer>> getCollByNumber() {
        return collByNumber;
    }
}
