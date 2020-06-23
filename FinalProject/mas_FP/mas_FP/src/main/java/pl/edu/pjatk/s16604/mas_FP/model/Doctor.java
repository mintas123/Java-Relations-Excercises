package pl.edu.pjatk.s16604.mas_FP.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pjatk.s16604.mas_FP.enums.Gender;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "doctor")
    private Set<Appointment> visits;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "doctor")
    private Set<Referral> referrals;

    public void addVisit(Appointment appointment) {
        this.visits.add(appointment);
    }

    public void removeVisit(Appointment appointment) {
        if (this.visits.contains(appointment)) {
            this.visits.remove(appointment);
        }
    }

    public Doctor(@NotBlank String name, @NotBlank String lastName, @Email(message = "Invalid email") String email,
                  @NotBlank String phone, @Size(min = 11, max = 11, message = "PESEL is 11 digits long!") @NotNull String pesel,
                  @Past LocalDate birthday, Gender gender, @Min(2500) double salary) {
        super(name, lastName, email, phone, pesel, birthday, gender);
        this.salary = salary;
    }

}
