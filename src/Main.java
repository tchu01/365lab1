/**
 * Created by timothy on 1/7/17.
 */
public class Main {
    public static void main(String[] args) {
        CustomerCollection cc = new CustomerCollection();
        OwnershipCollection oc = new OwnershipCollection();
        VenderCollection vc = new VenderCollection();
        TransactionCollection tc = new TransactionCollection();
        PaymentCollection pc = new PaymentCollection();

        //#1 Create a new customer
        Customer customer1 = new Customer(123456789, "Timothy Chu","1 Grand Ave", 1234567890);
        cc.addCustomer(customer1);

        //#2 Create a new credit card for an existing customer
        CreditCard card1 = new CreditCard("1234123412341234", CreditCard.CardType.AMEX, 3000.00, 0.0);
        Ownership ownership1 = new Ownership(customer1.getId());
        ownership1.addCard(card1);
        oc.addOwnership(customer1.getId(), ownership1);

        //#3 Issue a card duplicate for additional customer
        Customer customer2 = new Customer(234567890, "Helen Phan", "2 Grand Ave", 1234567890);
        cc.addCustomer(customer2);
        Ownership ownership2 = new Ownership(customer2.getId());
        ownership1.addCard(card1);
        oc.addOwnership(customer2.getId(), ownership2);

        //#4 Cancel a credit card





    }
}
