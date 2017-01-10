import java.util.HashMap;

/**
 * Created by timothy on 1/7/17.
 */
public class VenderCollection {
    private HashMap<Integer, Vender> coll;

    public VenderCollection() {
        this.coll = new HashMap<>();
    }

    public void addVender(Vender vender) {
        if(!this.coll.containsKey(vender.getId())) {
            this.coll.put(vender.getId(), vender);
        } else {
            System.out.println("ERROR: This collection already contains vender with that id.");
        }
    }
}
