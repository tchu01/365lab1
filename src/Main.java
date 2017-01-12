import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

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
        Customer customer1 = createAndAddCustomer(cc, 123456789, "Timothy Chu", "1 Grand Ave",
                "1234567890");

        //#2 Create a new credit card for an existing customer
        CreditCard card1 = addCreditCardToCustomer(oc,"1234123412341234", CreditCard.CardType.AMEX,
                3000.00, 0.0, customer1);

        //#3 Issue a card duplicate for additional customer
        Customer customer2 = createAndAddCustomer(cc, 234567890, "Helen Phan", "2 Grand Ave",
                "1234567890");
        CreditCard card2 = addCreditCardToCustomer(oc, card1.getNumber(), card1.getType(), card1.getLimit(),
                card1.getBalance(), customer2);

        //#4 Cancel a credit card
        Customer customer3 = createAndAddCustomer(cc, 345678901, "John Doe", "3 Grand Ave",
                "9876543210");
        CreditCard card3 = addCreditCardToCustomer(oc, "4321432143214321", CreditCard.CardType.DISCOVER,
                4000.00, 0.0, customer3);
        cancelCreditCard(oc, card3.getNumber());

        //#5 Activate a credit card
        activateCreditCard(oc, "1234123412341234");

        //#6 Add a new vender
        Vender vender1 = createAndAddVender(vc, "Costco", "4 Grand Ave");

        //#7 Create a new transaction
        makeTransaction(oc, tc, "01/19/2017", customer1.getId(), card1.getNumber(), vender1.getId(),
                500.00);

        //#8 Allow a customer to pay off credit card
        makePayment(oc, pc, "01/20/2017", card1.getNumber(), 400.00);
        System.out.println("HERE");

        //CLI for querying
        Scanner scan = new Scanner(System.in);
        System.out.println("Query options: q - quit | customerId <id> | customerSsn <ssn>");
        System.out.println("cardById <id> | cardBySsn <ssn> | cardByNumber <number>");
        System.out.println("transaction <start> <stop>");
        boolean cont = true;
        while(cont) {
            String next = scan.nextLine();
            if(next.equals("q")) {
                cont = false;
            }
            String split[] = next.split(" ");
            if(split[0].equals("customerId")) {
                queryCustomerById(cc, Integer.parseInt(split[1]));
            } else if(split[0].equals("customerSsn")) {
                queryCustomerBySsn(cc, Integer.parseInt(split[1]));
            } else if(split[0].equals("cardById")) {
                queryCardsById(oc, Integer.parseInt(split[1]));
            } else if(split[0].equals("cardBySsn")) {
                queryCardsBySsn(cc, oc, Integer.parseInt(split[1]));
            } else if(split[0].equals("cardByNumber")) {
                queryCardByNumber(cc, oc, split[1]);
            } else if(split[0].equals("transaction")) {
                queryTranscations(tc, split[1], split[2]);
            }

        }
    }

    public static Customer createAndAddCustomer(CustomerCollection cc, int ssn, String name, String address,
                                                String phone) {
        Customer customer = new Customer(ssn, name, address, phone);
        cc.addCustomer(customer);
        return customer;
    }

    public static CreditCard addCreditCardToCustomer(OwnershipCollection oc, String number, CreditCard.CardType type,
                                                     double limit, double balance, Customer customer) {
        CreditCard card = new CreditCard(number, type, limit, balance);
        Ownership ownership = null;
        if(!oc.getCollById().containsKey(customer.getId())) {
            ownership = new Ownership(customer.getId());
            ownership.addCreditCard(card);
            oc.addOwnership(customer.getId(), ownership);
        } else {
            ownership = oc.getCollById().get(customer.getId());
            ownership.addCreditCard(card);
        }

        oc.addReverseOwnership(number, customer.getId());

        return card;
    }

    public static void cancelCreditCard(OwnershipCollection oc, String number) {
        if(oc.getCollByNumber().containsKey(number)) {
            HashSet<Integer> ids = oc.getCollByNumber().get(number);
            for (Integer i : ids) {
                oc.getCollById().get(i).setCurrent(false);
            }
        } else {
            System.out.println("Cannot cancel credit card, no credit card with that number");
        }
    }

    public static void activateCreditCard(OwnershipCollection oc, String number) {
        if(oc.getCollByNumber().containsKey(number)) {
            HashSet<Integer> ids = oc.getCollByNumber().get(number);
            for (Integer i : ids) {
                oc.getCollById().get(i).getCards().getColl().get(number).setActive(true);
            }
        } else {
            System.out.println("Cannot activate credit card, no credit card with that number.");
        }
    }

    public static Vender createAndAddVender(VenderCollection vc, String name, String location) {
        Vender vender = new Vender(name, location);
        vc.addVender(vender);
        return vender;
    }

    public static void makeTransaction(OwnershipCollection oc, TransactionCollection tc, String date, int customerId,
                                       String cardNumber, int venderId, double amount) {
        if(oc.getCollById().containsKey(customerId)) {
            if(oc.getCollByNumber().containsKey(cardNumber)) {
                Transaction transaction = new Transaction(date, customerId, cardNumber, venderId, amount);
                tc.addTransaction(transaction);

                HashSet<Integer> ids = oc.getCollByNumber().get(cardNumber);
                for(Integer i : ids) {
                    oc.getCollById().get(i).getCards().getColl().get(cardNumber).addBalance(amount);
                }
            } else {
                System.out.println("Cannot make transaction, no credit card for that customer");
            }
        } else {
            System.out.println("Cannot make transaction, no customer with that id.");
        }
    }

    public static void makePayment(OwnershipCollection oc, PaymentCollection pc, String date, String cardNumber,
                                   double amount) {
        if(oc.getCollByNumber().containsKey(cardNumber)) {
            Payment payment = new Payment(date, cardNumber, amount);
            pc.addPayment(payment);

            HashSet<Integer> ids = oc.getCollByNumber().get(cardNumber);
            for(Integer i : ids) {
                oc.getCollById().get(i).getCards().getColl().get(cardNumber).subtractBalance(amount);
            }
        } else {
            System.out.println("Cannot make payment, no card with that number.");
        }
    }

    public static void queryCustomerById(CustomerCollection cc, int id) {
        Customer c = cc.getCollById().get(id);
        System.out.println("Customer: " + c.getName());
    }

    public static void queryCustomerBySsn(CustomerCollection cc, int ssn) {
        Customer c = cc.getCollBySsn().get(ssn);
        System.out.println("Customer: " + c.getName());
    }

    public static void queryCardsById(OwnershipCollection oc, int id) {
        Ownership o = oc.getCollById().get(id);
        HashMap<String, CreditCard> cards = o.getCards().getColl();

        for(CreditCard card : cards.values()) {
            System.out.println("Card #: " + card.getNumber() + " card balance: " + card.getBalance() +
                    " card limit: " + card.getLimit() + " card type: " + card.getType() + " card active: " +
                    card.isActive());
        }
    }

    public static void queryTranscations(TransactionCollection tc, String start, String end) {
        for(Transaction t : tc.getColl()) {
            String date = t.getDate();
            int i = date.compareTo(start);
            i = date.compareTo(end);
            if(date.compareTo(start) >= 1 && date.compareTo(end) <= -1) {
                System.out.println("Transaction date: " + t.getDate() + " card number: " + t.getCreditCardNumber() +
                        " amount: " + t.getAmount() + " customer id: " + t.getCustomerId() +
                        " venderid: " + t.getVenderId());
            }
        }
    }

    public static void queryCardsBySsn(CustomerCollection cc, OwnershipCollection oc, int ssn) {
        Customer c = cc.getCollBySsn().get(ssn);
        Ownership o = oc.getCollById().get(c.getId());
        HashMap<String, CreditCard> cards = o.getCards().getColl();

        for(CreditCard card : cards.values()) {
            System.out.println("Card #: " + card.getNumber() + " card balance: " + card.getBalance() +
                    " card limit: " + card.getLimit() + " card type: " + card.getType() + " card active: " +
                    card.isActive());
        }
    }

    public static void queryCardByNumber(CustomerCollection cc, OwnershipCollection oc, String number) {
        HashSet<Integer> ids = oc.getCollByNumber().get(number);
        ArrayList<String> names = new ArrayList<>();
        boolean first = true;
        for(Integer i : ids) {
            if(first) {
                first = false;
                CreditCard card = oc.getCollById().get(i).getCards().getColl().get(number);
                System.out.println("Card #: " + card.getNumber() + " card balance: " + card.getBalance() +
                        " card limit: " + card.getLimit() + " card type: " + card.getType() + " card active: " +
                        card.isActive());
            }
            names.add(cc.getCollById().get(i).getName());
        }
        System.out.println("Cardholders:");
        for(String name : names) {
            System.out.println(name);
        }
    }
}
