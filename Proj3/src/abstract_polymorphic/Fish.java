package abstract_polymorphic;

import abstract_polymorphic.Enums.FishType;
import abstract_polymorphic.Enums.FoodIntakeType;

public class Fish extends Animal {

    private FishType waterType;
    private int incubationLength;


    public Fish(String name, String latinName, double weight, FoodIntakeType foodIntakeType, FishType waterType, int incubationLength) {
        super(name, latinName, weight, foodIntakeType);
        this.waterType = waterType;
        this.incubationLength = incubationLength;

    }

    @Override
    public int getIncubationTime() {
        return getIncubationLength();
    }

    public FishType getWaterType() {
        return waterType;
    }

    public void setWaterType(FishType waterType) {
        this.waterType = waterType;
    }

    public int getIncubationLength() {
        return incubationLength;
    }

    public void setIncubationLength(int incubationLength) {
        this.incubationLength = incubationLength;
    }

    @Override
    public String toString() {
        return "Fish{" +
                super.toString() +
                "waterType=" + waterType.toString() +
                ", incubationLength=" + incubationLength +
                '}';
    }
}
