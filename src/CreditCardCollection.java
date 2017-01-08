import java.util.HashMap;

/**
 * Created by timothy on 1/7/17.
 */
public class CreditCardCollection {
    private HashMap<Long, CreditCard> coll;

    public CreditCardCollection() {
        this.coll = new HashMap<>();
    }

    public void addCustomer(CreditCard card) {
        if(coll.containsKey(card.getNumber())) {
            System.out.println("Collection already contains card with that number.");
        } else {
            coll.put(card.getNumber(), card);
        }
    }
}
