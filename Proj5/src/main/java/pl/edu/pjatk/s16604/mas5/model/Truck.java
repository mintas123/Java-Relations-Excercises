package pl.edu.pjatk.s16604.mas5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "carId")

@Getter
@Setter
@NoArgsConstructor
public class Truck extends Car {

    @NotBlank
    private String suspensionType;

    @Min(0)
    private double towingCapacity;

    @Column(name = "is_AWD")
    private boolean isAWD;

    public Truck(@NotBlank String brand, @NotBlank String model, @Min(0) double power, double price, @Min(1995) int productionYear,
                 @NotBlank String suspensionType, @Min(0) double towingCapacity, boolean isAWD) {
        super(brand, model, power, price, productionYear);
        this.suspensionType = suspensionType;
        this.towingCapacity = towingCapacity;
        this.isAWD = isAWD;
    }

    @Override
    public String toString() {
        return super.toString() +
                "   truck info{" +
                "suspensionType='" + suspensionType + '\'' +
                ", towingCapacity=" + towingCapacity +
                ", isAWD=" + isAWD +
                '}';
    }
}
