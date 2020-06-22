package pl.edu.pjatk.s16604.mas_FP.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "divisionId")
    private Division division;

    private  boolean isHead;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "doctor")
    private Set<Appointment> visits;

    public Doctor(@NotBlank String name, @NotBlank String lastName, @Email(message = "Invalid email") String email,
                  @NotBlank String phone, @Size(min = 11, max = 11, message = "PESEL is 11 digits long!") @NotNull String pesel,
                  @Past LocalDate birthday, @NotBlank boolean sex, @Min(2500) double salary, boolean isHead) {
        super(name, lastName, email, phone, pesel, birthday, sex);
        this.salary = salary;
        this.isHead = isHead;
    }
}
