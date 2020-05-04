package abstract_polymorphic;

import abstract_polymorphic.Enums.FoodIntakeType;

public class Reptile extends Animal {
    private int hatchTime;
    private boolean isVenomous;

    public Reptile(String name, String latinName, double weight, FoodIntakeType foodIntakeType, int hatchTime, boolean isVenomous) {
        super(name, latinName, weight, foodIntakeType);
        this.hatchTime = hatchTime;
        this.isVenomous = isVenomous;
    }

    @Override
    public int getIncubationTime() {
        return getHatchTime();
    }

    public int getHatchTime() {
        return hatchTime;
    }

    public void setHatchTime(int hatchTime) {
        this.hatchTime = hatchTime;
    }

    @Override
    public String toString() {
        return "Reptile{" +
                super.toString() +
                "hatchTime=" + hatchTime +
                ", isVenomous=" + isVenomous +
                '}';
    }
}
