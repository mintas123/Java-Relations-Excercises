package custom;

import static utils.Utils.checkIfNull;

public class Item {
    private String id;
    private String name;
    private String producer;
    private double cost;

    public Item(String id, String name, String producer, double cost) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        checkIfNull(producer);
        this.producer = producer;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", cost=" + cost +
                '}';
    }
}
