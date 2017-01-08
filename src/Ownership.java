/**
 * Created by timothy on 1/7/17.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Ownership {
    private Customer customer;
    private CreditCardCollection cards;
    private boolean current = true;

    public Ownership(Customer customer, CreditCardCollection cards) {
        this.customer = customer;
        this.cards = cards;
    }

    public Customer getCust() {
        return customer;
    }

    public CreditCardCollection getCards() {
        return cards;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCust(Customer customer) {
        this.customer = customer;
    }

    public void setCards(CreditCardCollection cards) {
        this.cards = cards;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
