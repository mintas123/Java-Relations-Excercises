package pl.edu.pjatk.s16604.mas_FP.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pjatk.s16604.mas_FP.enums.CommuteType;
import pl.edu.pjatk.s16604.mas_FP.enums.Gender;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "personId")

@Getter
@Setter
@NoArgsConstructor
public class Doctor extends Person {


    @Min(2500)
    private double salary;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "division_id")
    private Division division;

    private boolean isHead;

    @JsonIgnore
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "doctor")
    private Set<Appointment> visits = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "doctor")
    private Set<Referral> referrals = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "commute_type",
            joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "doctor_commute")
    private Set<CommuteType> commuteTypeSet = new HashSet<>();

    private String carRegistrationPlate;

    private boolean hasBikeStand;


    public Doctor(@NotBlank String name, @NotBlank String lastName, @Email(message = "Invalid email") String email,
                  @NotBlank String phone, @Size(min = 11, max = 11, message = "PESEL is 11 digits long!") @NotNull String pesel,
                  @Past LocalDate birthday, Gender gender, @Min(2500) double salary) {
        super(name, lastName, email, phone, pesel, birthday, gender);
        this.salary = salary;
    }

    public void addVisit(Appointment appointment) {
        this.visits.add(appointment);
    }

    public void removeVisit(Appointment appointment) {
        if (this.visits.contains(appointment)) {
            this.visits.remove(appointment);
        }
    }

    public void addPublicTransportCommute() {
        if (!this.commuteTypeSet.contains(CommuteType.P_TRANSPORT)) {
            this.commuteTypeSet.add(CommuteType.P_TRANSPORT);
            // company health policy - paying for Karta Miejska
            this.setSalary(this.getSalary() + 150);
        }
    }

    public void removePublicTransportCommute() {
        if (this.commuteTypeSet.contains(CommuteType.P_TRANSPORT)) {
            this.commuteTypeSet.remove(CommuteType.P_TRANSPORT);
            this.setSalary(this.getSalary() - 150);
        }
    }

    public void addBikeCommute() {
        if (!this.commuteTypeSet.contains(CommuteType.BIKE)) {
            this.commuteTypeSet.add(CommuteType.BIKE);
            // company health policy - if you are willing to ride a bike you get your own stand
            this.setHasBikeStand(true);
        }
    }

    public void removeBikeCommute() {
        if (this.commuteTypeSet.contains(CommuteType.BIKE)) {
            this.commuteTypeSet.remove(CommuteType.BIKE);
            this.setHasBikeStand(false);
        }
    }

    public void addCarCommute(@NotBlank String licencePlate) {
        if (!this.commuteTypeSet.contains(CommuteType.CAR)) {
            this.commuteTypeSet.add(CommuteType.CAR);
            // for the parking you need to have registered your car
            this.setCarRegistrationPlate(licencePlate);

        }
    }

    public void removeCarCommute() {
        if (this.commuteTypeSet.contains(CommuteType.CAR)) {
            this.commuteTypeSet.remove(CommuteType.CAR);
            this.setCarRegistrationPlate(null);

        }
    }


}
