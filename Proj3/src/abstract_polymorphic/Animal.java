package abstract_polymorphic;

import abstract_polymorphic.Enums.FoodIntakeType;

import static Utils.Utils.checkIfNull;

public abstract class Animal {

    private String name;
    private String latinName;
    private double avgWeight;
    private FoodIntakeType foodIntakeType;

    public Animal(String name, String latinName, double avgWeight, FoodIntakeType foodIntakeType) {
        super();
        checkIfNull(name);
        checkIfNull(latinName);

        this.name = name;
        this.latinName = latinName;
        this.avgWeight = avgWeight;
        this.foodIntakeType = foodIntakeType;
    }

    public abstract int getIncubationTime();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        checkIfNull(latinName);
        this.latinName = latinName;
    }

    public double getAvgWeight() {
        return avgWeight;
    }

    public void setAvgWeight(double avgWeight) {
        this.avgWeight = avgWeight;
    }

    public FoodIntakeType getFoodIntakeType() {
        return foodIntakeType;
    }

    public void setFoodIntakeType(FoodIntakeType foodIntakeType) {
        this.foodIntakeType = foodIntakeType;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", latinName='" + latinName + '\'' +
                ", avgWeight=" + avgWeight +
                ", foodIntakeType=" + foodIntakeType.toString() +
                '}';
    }
}
