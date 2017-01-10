/**
 * Created by timothy on 1/7/17.
 */
public class Vender {
    private static int uid = 0;

    private int id;
    private String name;
    private String location;

    public Vender(String name, String location) {
        this.id = uid++;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
