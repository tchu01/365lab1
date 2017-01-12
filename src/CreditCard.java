/**
 * Created by timothy on 1/7/17.
 */
public class CreditCard {
    public enum CardType {
        VISA, MC, AMEX, DISCOVER
    }

    private String number;
    private CardType type;
    private double limit;
    private double balance;
    private boolean active = false;

    public CreditCard(String number, CardType type, double limit, double balance) {
        this.number = number;
        this.type = type;
        this.limit = limit;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public CardType getType() {
        return type;
    }

    public double getLimit() {
        return limit;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void addBalance(double amount) { this.balance += amount; }

    public void subtractBalance(double amount) { this.balance -= amount; }
}
