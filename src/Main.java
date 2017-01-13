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
        Vender vender1 = createAndAddVender(vc, "Costco", "4 Grand Ave");

        //#7 Create a new transaction
        makeTransaction(oc, tc, "01/19/2017", customer1.getId(), card1.getNumber(), vender1.getId(),
                500.00);

        //#8 Allow a customer to pay off credit card
        makePayment(oc, pc, "01/20/2017", card1.getNumber(), 400.00);
        System.out.println("HERE");

        //CLI for interacting with database
        Scanner scan = new Scanner(System.in);
        printOptions();
        while(true) {
            String next = scan.nextLine();
            String split[] = next.split(" ");
            switch (split[0]) {
                case "q":
                    System.out.println("Goodbye");
                    return;
                case "newCustomer":
                    newCustomer(cc, split);
                    break;
                case "addCreditCardToCustomer":
                    newCardForCustomer(oc, split);
                    break;
                case "cancelCreditCard":
                    cancelCard(oc, split);
                    break;
                case "activateCreditCard":
                    activateCard(oc, split);
                    break;
                case "newVender":
                    break;
                case "newTransaction":
                    break;
                case "newPayment":
                    break;
                case "customerId":
                    queryCustomerById(cc, Integer.parseInt(split[1]));
                    break;
                case "customerSsn":
                    queryCustomerBySsn(cc, Integer.parseInt(split[1]));
                    break;
                case "cardById":
                    queryCardsById(oc, Integer.parseInt(split[1]));
                    break;
                case "cardBySsn":
                    queryCardsBySsn(cc, oc, Integer.parseInt(split[1]));
                    break;
                case "cardByNumber":
                    queryCardByNumber(cc, oc, split[1]);
                    break;
                case "transaction":
                    queryTransactions(tc, split[1], split[2]);
                    break;
            }
        }
    }

    public static void printOptions() {
        System.out.println("Options for customizing database:");
        System.out.println("1) newCustomer <first name> <last name> <ssn> <phone> <address>");
        System.out.println("2) addCreditCardToCustomer <card number> <card type> <limit> <balance> <customer id>");
        System.out.println("3) cancelCreditCard <card number>");
        System.out.println("4) activateCreditCard <card number>");
        System.out.println("5) newVender <name> <location>");
        System.out.println("6) newTransaction <date> <customer id> <card number> <vender id> <amount>");
        System.out.println("7) newPayment <date> <card number> <amount>");

        System.out.println("Options for querying database:");
        System.out.println("1) customerId <customer id>");
        System.out.println("2) customerSsn <ssn>");
        System.out.println("3) cardById <customer id>");
        System.out.println("4) cardBySsn <ssn>");
        System.out.println("5) cardByNumber <card number>");
        System.out.println("6) transaction <start date range> <stop date range>");
    }

    public static void newCustomer(CustomerCollection cc, String[] split) {
        String name = split[1] + " " + split[2];
        int ssn = Integer.parseInt(split[3]);
        String phone = split[4];
        String address = getAddress(split);
        Customer c = createAndAddCustomer(cc, ssn, name, address, phone);
        System.out.println("Successfully added new customer with id: " + c.getId());
    }

    public static String getAddress(String[] split) {
        String result = "";
        for(int i = 5; i < split.length; i++) {
            result += split[i] + " ";
        }

        return result.trim();
    }

    public static Customer createAndAddCustomer(CustomerCollection cc, int ssn, String name, String address,
                                                String phone) {
        Customer customer = new Customer(ssn, name, address, phone);
        cc.addCustomer(customer);
        return customer;
    }

    public static void newCardForCustomer(OwnershipCollection oc, String[] split) {
        if(split.length == 5) {
            addCreditCardToCustomer(oc, split[1], getType(split[2]), Double.parseDouble(split[3]),
                    Double.parseDouble(split[4]), Integer.parseInt(split[5]));
            System.out.println("Successfully added credit card to customer");
        } else {
            System.out.println("Incorrect number of arguments");
        }
    }

    public static CreditCard.CardType getType(String type) {
        if(type.equals("VISA")) {
            return CreditCard.CardType.VISA;
        } else if(type.equals("MC")) {
            return CreditCard.CardType.MC;
        } else if(type.equals("AMEX")) {
            return CreditCard.CardType.AMEX;
        } else {
            return CreditCard.CardType.DISCOVER;
        }
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

    public static void cancelCard(OwnershipCollection oc, String[] split) {
        if(split.length == 2) {
            cancelCreditCard(oc, split[1]);
            System.out.println("Successfully canceled credit card");
        } else {
            System.out.println("Incorrect number of arguments");
        }
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

    public static void activateCard(OwnershipCollection oc, String[] split) {
        if(split.length == 2) {
            activateCreditCard(oc, split[1]);
            System.out.println("Successfully activated crdit card");
        } else {
            System.out.println("Incorrect number of arguments");
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

    public static void queryTransactions(TransactionCollection tc, String start, String end) {
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
}
