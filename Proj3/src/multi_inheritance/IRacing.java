package multi_inheritance;

import multi_inheritance.Enums.RacingType;
import multi_inheritance.Enums.RacingWorldType;

public interface IRacing {

    RacingType getRacingType();


    void setRacingType(RacingType racingType);


    RacingWorldType getRacingWorldType();


    void setRacingWorldType(RacingWorldType racingWorldType);


}
