/**
 * Created by timothy on 1/7/17.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Ownership {
    private int customerId;
    private CreditCardCollection cards;
    private boolean current = true;

    public Ownership(int customerId) {
        this.customerId = customerId;
        this.cards = new CreditCardCollection();
    }

    public int getCustomerId() {
        return customerId;
    }

    public CreditCardCollection getCards() {
        return cards;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCards(CreditCardCollection cards) {
        this.cards = cards;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
