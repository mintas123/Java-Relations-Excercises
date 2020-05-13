package custom;

public class ShoppingItem {

    // due to COVID - only 5 pieces of same item per visit
    private int quantity;
    private Item item;

    public ShoppingItem(int quantity, Item item) {
        setItem(item);
        setQuantity(quantity);

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 5) {
            throw new IllegalArgumentException("Only 5 allowed per visit!");
        }
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null item!");
        }
        this.item = item;
    }

    @Override
    public String toString() {
        return quantity + "x " + item + "\n";
    }
}
