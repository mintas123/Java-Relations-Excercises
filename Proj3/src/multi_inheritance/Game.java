package multi_inheritance;

import java.time.LocalDate;

import static Utils.Utils.checkIfNull;

public class Game {
    private String name;
    private LocalDate releaseDate;
    private double budget;

    public Game(String name, LocalDate releaseDate, double budget) {
        checkIfNull(name);
        checkIfNull(releaseDate);

        this.name = name;
        this.releaseDate = releaseDate;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        checkIfNull(releaseDate);
        this.releaseDate = releaseDate;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", budget=" + budget;
    }
}
