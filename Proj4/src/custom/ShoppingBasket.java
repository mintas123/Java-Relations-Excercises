package custom;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static utils.Utils.checkIfNull;

public class ShoppingBasket {
    private LocalDate date;
    private Set<ShoppingItem> itemSet = new HashSet<>();


    public ShoppingBasket(LocalDate date) {
        setDate(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public Set<ShoppingItem> getItemSet() {
        return itemSet;
    }

    public void addItem(ShoppingItem shoppingItem) {
        if (shoppingItem == null) {
            throw new IllegalArgumentException("Null shoppingItem!");
        }
        if (itemSet.contains(shoppingItem)) {
            throw new IllegalArgumentException("Item in the basket!");
        }
        itemSet.add(shoppingItem);
    }

    public void removeItem(ShoppingItem shoppingItem) {
        if (shoppingItem == null) {
            throw new IllegalArgumentException("Null shoppingItem!");
        }
        if (!itemSet.contains(shoppingItem)) {
            throw new IllegalArgumentException("Item not in the basket!");
        }
        itemSet.remove(shoppingItem);
    }

    public void setDate(LocalDate date) {
        checkIfNull(date);
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShoppingBasket{\n" +
                "date=" + date +
                ",\nitems:\n" + itemSet +
                '}';
    }
}
