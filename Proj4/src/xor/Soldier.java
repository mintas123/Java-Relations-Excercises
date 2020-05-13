package xor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static utils.Utils.checkIfNull;

public class Soldier {
    private static Set<Soldier> secretSoldiers = new HashSet<>();
    private static Set<Soldier> publicSoldiers = new HashSet<>();

    private static int counter = 1;
    private int id;
    private String name;
    private LocalDate startDate;
    private double salary;
    private Role role = Role.REGULAR;

    public Soldier(String name, LocalDate startDate, double salary) {
        setName(name);
        setSalary(salary);
        setStartDate(startDate);
        this.id = counter;

        counter++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        checkIfNull(startDate);
        this.startDate = startDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("null role");
        }
        if (role.equals(Role.PUBLIC) && getSecretSoldiers().contains(this)) {
            removeSecretSoldier(this);
        }
        if (role.equals(Role.SECRET) && getPublicSoldiers().contains(this)) {
            removePublicSoldier(this);
        }
        this.role = role;
    }

    public static Set<Soldier> getSecretSoldiers() {
        return Collections.unmodifiableSet(secretSoldiers);
    }

    public static Set<Soldier> getPublicSoldiers() {
        return Collections.unmodifiableSet(publicSoldiers);
    }

    public static void addSecretSoldier(Soldier soldier) {
        if (soldier.getRole().equals(Role.SECRET)) {
            if (secretSoldiers.contains(soldier)) {
                throw new IllegalArgumentException("Already here!");
            }
            secretSoldiers.add(soldier);
        } else {
            throw new IllegalArgumentException("NOT Secret!");
        }
    }

    public static void addPublicSoldier(Soldier soldier) {
        if (soldier.getRole().equals(Role.PUBLIC)) {
            if (publicSoldiers.contains(soldier)) {
                throw new IllegalArgumentException("Already here!");
            }
            publicSoldiers.add(soldier);
        } else {
            throw new IllegalArgumentException("NOT Public!");
        }
    }

    public static void removeSecretSoldier(Soldier soldier) {
        if (soldier.getRole().equals(Role.SECRET)) {
            if (!secretSoldiers.contains(soldier)) {
                throw new IllegalArgumentException("Not here!");
            }
            secretSoldiers.remove(soldier);
        } else {
            throw new IllegalArgumentException("NOT Secret!");
        }
    }

    public static void removePublicSoldier(Soldier soldier) {
        if (soldier.getRole().equals(Role.PUBLIC)) {
            if (!publicSoldiers.contains(soldier)) {
                throw new IllegalArgumentException("Not here!");
            }
            publicSoldiers.remove(soldier);
        } else {
            throw new IllegalArgumentException("NOT Public!");
        }
    }

    @Override
    public String toString() {
        return "\nSoldier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", salary=" + salary +
                ", role=" + role +
                '}';
    }
}
