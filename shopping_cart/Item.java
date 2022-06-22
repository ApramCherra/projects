

import java.text.*;

public class Item {
    
    private double price;
    private double bulkPrice;
    private NumberFormat formatter;
    private String name;
    private int bulkQuantity;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
        this.bulkPrice = price;
        this.bulkQuantity = 1;

    }

    public Item(String name, double price, int bulkQuantity, double bulkPrice) {
        // this(/*WHAT SHOULD YOU PUT HERE?*/);
        this.name = name;
        this.price = price;
        this.bulkPrice = bulkPrice;
        this.bulkQuantity = bulkQuantity;
    }

    public double priceFor(int quantity) {
        // This does NOT produce the correct behavior. It's only here to keep the
        // compiler happy until you replace it with working code
        int q = quantity/this.bulkQuantity;
        int r = quantity%this.bulkQuantity;

        return q * this.bulkPrice + r * this.price;
    }

    public String toString() {
        // This does NOT produce the correct behavior. It's only here to keep the
        // compiler happy until you replace it with working code
        return this.name + " Price: " + this.price + " Bulk Price: " + this.bulkPrice + " Bulk Quantity: " + this.bulkQuantity;
    }
}
