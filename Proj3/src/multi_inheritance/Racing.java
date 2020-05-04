package multi_inheritance;

import multi_inheritance.Enums.RacingType;
import multi_inheritance.Enums.RacingWorldType;

import java.time.LocalDate;

public class Racing extends Game implements IRacing {


    private RacingType racingType;
    private RacingWorldType racingWorldType;


    public Racing(String name, LocalDate releaseDate, double budget, RacingType racingType, RacingWorldType worldType) {
        super(name, releaseDate, budget);
        this.racingType = racingType;
        this.racingWorldType = worldType;
    }

    @Override
    public RacingType getRacingType() {
        return racingType;
    }

    @Override
    public void setRacingType(RacingType racingType) {
        this.racingType = racingType;
    }

    @Override
    public RacingWorldType getRacingWorldType() {
        return racingWorldType;
    }

    @Override
    public void setRacingWorldType(RacingWorldType racingWorldType) {
        this.racingWorldType = racingWorldType;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", racingType=" + racingType +
                ", racingWorldType=" + racingWorldType;
    }
}
