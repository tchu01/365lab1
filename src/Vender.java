/**
 * Created by timothy on 1/7/17.
 */
public class Vender {
    private static long uid = 0;

    private long id;
    private String name;
    private String location;

    public Vender(String name, String location) {
        this.id = uid++;
        this.name = name;
        this.location = location;
    }

    public long getId() {
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
