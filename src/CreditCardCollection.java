import java.util.HashMap;

/**
 * Created by timothy on 1/7/17.
 */
public class CreditCardCollection {
    private HashMap<String, CreditCard> coll;

    public CreditCardCollection() {
        this.coll = new HashMap<>();
    }

    public void addCard(CreditCard card) {
        if(!coll.containsKey(card.getNumber())) {
            coll.put(card.getNumber(), card);
        } else {
            System.out.println("ERROR: Collection already contains card with that number.");
        }
    }
}
