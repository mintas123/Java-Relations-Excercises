package pl.edu.pjatk.s16604.mas5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)

@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @Min(0)
    private double power;

    private double price;

    @Column(name = "production_year")
    @Min(1995)
    private int productionYear;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;


    public Car(@NotBlank String brand, @NotBlank String model, @Min(0) double power, double price, @Min(1995) int productionYear) {
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.price = price;
        this.productionYear = productionYear;
    }

    @Override
    public String toString() {
        return "\nCar{" +
                "carId=" + carId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", power=" + power +
                ", price=" + price +
                ", productionYear=" + productionYear +
                "}\n";
    }
}
