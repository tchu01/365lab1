import java.util.HashSet;

/**
 * Created by timothy on 1/7/17.
 */
public class Main {
    public static void main(String[] args) {
        CustomerCollection cc = new CustomerCollection();
        OwnershipCollection oc = new OwnershipCollection();
        //VenderCollection vc = new VenderCollection();
        //TransactionCollection tc = new TransactionCollection();
        //PaymentCollection pc = new PaymentCollection();

        //#1 Create a new customer
        Customer customer1 = createAndAddCustomer(cc, 123456789, "Timothy Chu", "1 Grand Ave",
                "1234567890");

        //#2 Create a new credit card for an existing customer
        CreditCard card1 = addCreditCardToCustomer(oc,"1234123412341234", CreditCard.CardType.AMEX,
                3000.00, 0.0, customer1.getId());

        //#3 Issue a card duplicate for additional customer
        Customer customer2 = createAndAddCustomer(cc, 234567890, "Helen Phan", "2 Grand Ave",
                "1234567890");
        CreditCard card2 = addCreditCardToCustomer(oc, card1.getNumber(), card1.getType(), card1.getLimit(),
                card1.getBalance(), customer2.getId());

        //#4 Cancel a credit card
        Customer customer3 = createAndAddCustomer(cc, 345678901, "John Doe", "3 Grand Ave",
                "9876543210");
        CreditCard card3 = addCreditCardToCustomer(oc, "4321432143214321", CreditCard.CardType.DISCOVER,
                4000.00, 0.0, customer3.getId());
        cancelCreditCard(oc, card3.getNumber());

        //#5 Activate a credit card
        activateCreditCard(oc, "1234123412341234");

        //#6 Add a new vender
        System.out.println("DEBUG");

        //#7 Create a new transaction
        System.out.println("HERE");
    }

    public static Customer createAndAddCustomer(CustomerCollection cc, int ssn, String name, String address,
                                                String phone) {
        Customer customer = new Customer(ssn, name, address, phone);
        cc.addCustomer(customer);
        return customer;
    }

    public static CreditCard addCreditCardToCustomer(OwnershipCollection oc, String number, CreditCard.CardType type,
                                                     double limit, double balance, int customerId) {
        CreditCard card = new CreditCard(number, type, limit, balance);
        Ownership ownership = null;
        if(!oc.getCollById().containsKey(customerId)) {
            ownership = new Ownership(customerId);
            ownership.addCreditCard(card);
            oc.addOwnership(customerId, ownership);
        } else {
            ownership = oc.getCollById().get(customerId);
            ownership.addCreditCard(card);
        }

        oc.addReverseOwnership(number, customerId);

        return card;
    }

    public static void cancelCreditCard(OwnershipCollection oc, String number) {
        HashSet<Integer> ids = oc.getCollByNumber().get(number);
        for(Integer i : ids) {
            oc.getCollById().get(i).setCurrent(false);
        }
    }

    public static void activateCreditCard(OwnershipCollection oc, String number) {
        HashSet<Integer> ids = oc.getCollByNumber().get(number);
        for(Integer i : ids) {
            oc.getCollById().get(i).getCards().getColl().get(number).setActive(true);
        }
    }
}
