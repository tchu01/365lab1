import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by timothy on 1/7/17.
 */
public class TransactionCollection {
    private HashMap<String, ArrayList<Transaction>> coll;

    public TransactionCollection() {
        this.coll = new HashMap<>();
    }

    public void addTransaction(String number,Transaction transaction) {
        if(this.coll.containsKey(number)) {
            this.coll.get(number).add(transaction);
        } else {
            this.coll.put(number, new ArrayList<>());
            this.coll.get(number).add(transaction);
        }
    }

    public HashMap<String, ArrayList<Transaction>> getColl() {
        return coll;
    }
}
