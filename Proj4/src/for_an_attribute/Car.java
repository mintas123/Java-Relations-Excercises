package for_an_attribute;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static utils.Utils.checkIfNull;

public class Car {
    private String owner;
    private String brand;
    private String model;
    // cannot be before 1990
    private int yearOfProduction;

    // free periodic inspection when done every year (else paid)
    private List<LocalDate> inspectionList = new ArrayList<>();

    public Car(String owner, String brand, String model, int yearOfProduction) {
        setOwner(owner);
        setBrand(brand);
        setModel(model);
        setYearOfProduction(yearOfProduction);
    }

    public void addInspection(LocalDate inspection) {
        checkIfNull(inspection);
        validateInspection(inspection);
    }

    public void addInspection() {
        LocalDate now = LocalDate.now();
        validateInspection(now);
    }


    private void validateInspection(LocalDate inspection) {
        if (inspectionList.isEmpty()) {
            inspectionList.add(inspection);
        }
        LocalDate last = inspectionList.stream()
                .max(Comparator.comparing(LocalDate::toEpochDay))
                .get();

        Duration between = Duration.between(last.atStartOfDay(), inspection.atStartOfDay());
        long days = between.toDays();

        if (days > 365) {
            throw new IllegalArgumentException(String.format("Your last inspection was at %s, You are %s days(s) late!", last.toString(), days - 365));
        } else {
            inspectionList.add(inspection);
        }
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        checkIfNull(owner);
        this.owner = owner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        checkIfNull(brand);
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        checkIfNull(model);
        this.model = model;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        if (yearOfProduction < 1990) {
            throw new IllegalArgumentException("Cars before 1990 cannot be maintained here!");
        }
        this.yearOfProduction = yearOfProduction;
    }

    public List<LocalDate> getInspectionList() {
        return Collections.unmodifiableList(inspectionList);
    }

    @Override
    public String toString() {
        return "Car{" +
                "owner='" + owner + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                ", inspectionList=" + inspectionList +
                '}';
    }
}
