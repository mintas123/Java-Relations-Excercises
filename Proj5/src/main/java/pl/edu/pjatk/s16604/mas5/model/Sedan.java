package pl.edu.pjatk.s16604.mas5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "carId")

@Getter
@Setter
@NoArgsConstructor
public class Sedan extends Car {

    @Min(0)
    private int range;
    @Min(0)
    private float zeroToHundred;

    private boolean isAutonomous;

    private boolean hasAndroidAuto;

    public Sedan(@NotBlank String brand, @NotBlank String model, @Min(0) double power, double price, @Min(1995) int productionYear,
                 @Min(0) int range, @Min(0) float zeroToHundred, boolean isAutonomous, boolean hasAndroidAuto) {
        super(brand, model, power, price, productionYear);
        this.range = range;
        this.zeroToHundred = zeroToHundred;
        this.isAutonomous = isAutonomous;
        this.hasAndroidAuto = hasAndroidAuto;
    }

    @Override
    public String toString() {
        return
                super.toString() +
                        "   sedan info{" +
                        "range=" + range +
                        ", zeroToHundred=" + zeroToHundred +
                        ", isAutonomous=" + isAutonomous +
                        ", hasAndroidAuto=" + hasAndroidAuto +
                        '}';
    }
}
