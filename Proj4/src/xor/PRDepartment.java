package xor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PRDepartment {
    private double budget;
    private Soldier head;
    private Set<Soldier> workers = new HashSet<>();

    public PRDepartment(double budget, Soldier head) {
        this.budget = budget;
        this.head = head;
    }

    public void addWorkers(Soldier soldier) {
        if (soldier == null) {
            throw new IllegalArgumentException("Null soldier");
        }
        if (getWorkers().contains(soldier)) {
            throw new IllegalArgumentException("Already here");
        }

        if (!soldier.getRole().equals(Role.PUBLIC)) {
            throw new IllegalArgumentException("Soldier is not public - ready!");
        }
        workers.add(soldier);
        Soldier.addPublicSoldier(soldier);
    }

    public void removeSoldier(Soldier soldier) {
        if (soldier == null) {
            throw new IllegalArgumentException("Null soldier");
        }
        if (!getWorkers().contains(soldier)) {
            throw new IllegalArgumentException("Not here");
        }
        soldier.setRole(Role.PUBLIC);
        Soldier.removePublicSoldier(soldier);
        workers.remove(soldier);

    }

    public Set<Soldier> getWorkers() {
        return Collections.unmodifiableSet(workers);
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Soldier getHead() {
        return head;
    }

    public void setHead(Soldier head) {
        if (head == null) {
            throw new IllegalArgumentException("Null head");
        }
        this.head = head;
    }

    public void addExpense(double cost) {
        if (cost > budget) {
            System.out.println("You dont have money!");
        } else {
            budget -= cost;
        }

    }
}
