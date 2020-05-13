package xor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static utils.Utils.checkIfNull;

public class SpecialForces {
    private String name;
    private String country;
    private Set<Soldier> soldiers = new HashSet<>();

    public SpecialForces(String name, String country) {
        setName(name);
        setCountry(country);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        checkIfNull(country);
        this.country = country;
    }

    public Set<Soldier> getSoldiers() {
        return Collections.unmodifiableSet(soldiers);
    }

    public void addSoldier(Soldier soldier) {
        if (soldier == null) {
            throw new IllegalArgumentException("Null soldier");
        }
        if (getSoldiers().contains(soldier)) {
            throw new IllegalArgumentException("Already here");
        }

        if (!soldier.getRole().equals(Role.SECRET)) {
            throw new IllegalArgumentException("Soldier is not secret - ready!");
        }
        soldiers.add(soldier);
        Soldier.addSecretSoldier(soldier);
    }

    public void removeSoldier(Soldier soldier) {
        if (soldier == null) {
            throw new IllegalArgumentException("Null soldier");
        }
        if (!getSoldiers().contains(soldier)) {
            throw new IllegalArgumentException("Not here");
        }
        soldier.setRole(Role.PUBLIC);
        Soldier.removeSecretSoldier(soldier);
        soldiers.remove(soldier);

    }
}
