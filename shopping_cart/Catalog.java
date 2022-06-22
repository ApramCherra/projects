import java.util.*;

public class Catalog {

    private String name = "";
    private ArrayList<Item> items;

    public Catalog(String name) {
    this.name = name;
    this.items = new ArrayList<>();
    }

    public void add(Item next) {
        this.items.add(next);
    }

    public int size() {
        // This does NOT produce the correct behavior. It's only here to keep the
        // compiler happy until you replace it with working code
        return this.items.size();
    }

    public Item get(int index) {
        // This does NOT produce the correct behavior. It's only here to keep the
        // compiler happy until you replace it with working code

        return this.items.get(index);
    }

    public String getName() {
        // This does NOT produce the correct behavior. It's only here to keep the
        // compiler happy until you replace it with working code
        return this.name;
    }
}
