/**
 * Created by timothy on 1/7/17.
 */
public class Main {
    public static void main(String[] args) {
        CustomerCollection cc = new CustomerCollection();
        OwnershipCollection oc = new OwnershipCollection();
        VenderCollection vc = new VenderCollection()
        TransactionCollection tc = new TransactionCollection();
        PaymentCollection pc = new PaymentCollection();

        //#1 Create a new customer
        Customer customer1 = new Customer(123456789, "Timothy Chu","1 Grand Ave", 1234567890);
        cc.addCustomer(customer1);

        //#2 Create a new credit card for an existing customer
        CreditCard card1 = new CreditCard(1234123412341234, CreditCard.CardType.AMEX, 3000.00, 0.0);
        Ownership ownership1 = new Ownership(customer1, new CreditCardCollection());
        oc.addOwnership(customer1, );




    }
}
