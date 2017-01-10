/**
 * Created by timothy on 1/7/17.
 */
public class Customer {
    private static int uid = 0;

    private int ssn;
    private int id;
    private String name;
    private String address;
    private long phone;

    public Customer(int ssn, String name, String address, long phone) {
        this.ssn = ssn;
        this.id = uid++;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getSsn() {
        return ssn;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public long getPhone() {
        return phone;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
