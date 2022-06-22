

import java.util.*;
import java.util.Map.Entry;

public class ShoppingCart {

    Map<String, ItemOrder> orderedItems;
    boolean discount = false;
    public static final double DISCOUNT_PERCENT = 0.9;

    public ShoppingCart() {
        this.orderedItems = new HashMap<>();

    }

    public void add(ItemOrder next) {

        if(this.orderedItems.containsKey(next.getItem().toString()))
            this.orderedItems.remove(next.getItem().toString());
            this.orderedItems.put(next.getItem().toString(), next);

    }

    public void setDiscount(boolean discount) {
        this.discount = discount;

    }

    public double getTotal() {
        // This does NOT produce the correct behavior. It's only here to keep the
        // compiler happy until you replace it with working code
        double total = 0;
            for(Entry<String, ItemOrder> order : this.orderedItems.entrySet())
            {
                total += order.getValue().getPrice();
            }
            if(!this.discount)
            return total;

        return total * DISCOUNT_PERCENT ;
    }
}
