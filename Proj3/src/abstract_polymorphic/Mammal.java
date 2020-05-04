package abstract_polymorphic;

import abstract_polymorphic.Enums.FoodIntakeType;

public class Mammal extends Animal {
    private boolean isDomesticate;
    private int pregnancyTime;

    public Mammal(String name, String latinName, double weight, FoodIntakeType foodIntakeType, boolean isDomesticate, int pregnancyTime) {
        super(name, latinName, weight, foodIntakeType);
        this.pregnancyTime = pregnancyTime;
        this.isDomesticate = isDomesticate;
    }

    @Override
    public int getIncubationTime() {
        return getPregnancyTime();
    }


    public boolean isDomesticate() {
        return isDomesticate;
    }

    public void setDomesticate(boolean domesticate) {
        isDomesticate = domesticate;
    }

    public int getPregnancyTime() {
        return pregnancyTime;
    }

    public void setPregnancyTime(int pregnancyTime) {
        this.pregnancyTime = pregnancyTime;
    }

    @Override
    public String toString() {
        return "Mammal{" +
                super.toString() +
                "isDomesticate=" + isDomesticate +
                ", pregnancyTime=" + pregnancyTime +
                '}';
    }
}
