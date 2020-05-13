package multi_inheritance;

import multi_inheritance.Enums.RacingType;
import multi_inheritance.Enums.RacingWorldType;

import java.time.LocalDate;

public class GTAGame extends Shooter implements IRacing {

    private RacingType racingType;
    private RacingWorldType racingWorldType;


    public GTAGame(String name, LocalDate releaseDate, double budget, int weaponCount, boolean isGore, RacingType racingType, RacingWorldType racingWorldType) {
        super(name, releaseDate, budget, weaponCount, isGore);
        setRacingType(racingType);
        setRacingWorldType(racingWorldType);

    }

    @Override
    public RacingType getRacingType() {
        return racingType;
    }

    @Override
    public void setRacingType(RacingType racingType) {
        if (racingType == null) {
            throw new IllegalArgumentException("racingType cannot be null");
        }
        this.racingType = racingType;
    }

    @Override
    public RacingWorldType getRacingWorldType() {
        return racingWorldType;
    }

    @Override
    public void setRacingWorldType(RacingWorldType racingWorldType) {
        if (racingWorldType == null) {
            throw new IllegalArgumentException("worldType cannot be null");
        }
        this.racingWorldType = racingWorldType;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", racingType=" + racingType +
                ", racingWorldType=" + racingWorldType;
    }
}
