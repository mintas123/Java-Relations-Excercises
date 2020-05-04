package multi_inheritance;

import java.time.LocalDate;

public class Shooter extends Game implements IShooter {

    private int weaponCount;
    private boolean isGore;

    public Shooter(String name, LocalDate releaseDate, double budget, int weaponCount, boolean isGore) {
        super(name, releaseDate, budget);
        this.isGore = isGore;
        this.weaponCount = weaponCount;
    }

    @Override
    public int getWeaponCount() {
        return weaponCount;
    }

    @Override
    public void setWeaponCount(int weaponCount) {
        this.weaponCount = weaponCount;
    }

    @Override
    public boolean isGore() {
        return isGore;
    }

    @Override
    public void setGore(boolean gore) {
        isGore = gore;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", weaponCount=" + weaponCount +
                ", isGore=" + isGore;
    }
}
