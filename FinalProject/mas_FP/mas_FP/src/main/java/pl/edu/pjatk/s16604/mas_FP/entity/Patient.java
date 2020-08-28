package pl.edu.pjatk.s16604.mas_FP.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pjatk.s16604.mas_FP.enums.Gender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "personId")

@Getter
@Setter
@NoArgsConstructor
public class Patient extends Person {

    @PastOrPresent
    private LocalDate clientSince;

    @NotBlank
    private String insuranceProvider;

    @Column(name = "is_vip")
    private boolean isVIP;

    @JsonIgnore
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "patient")
    private Set<Appointment> appointments;

    @JsonIgnore
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "patient")
    private Set<Referral> referrals;


    public void addVisit(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public void removeVisit(Appointment appointment) {
        if (this.appointments.contains(appointment)) {
            this.appointments.remove(appointment);
        }
    }


    public Patient(@NotBlank String name, @NotBlank String lastName, @Email(message = "Invalid email") String email, @NotBlank String phone, @Size(min = 11, max = 11, message = "PESEL is 11 digits long!") @NotNull String pesel, @Past LocalDate birthday, Gender gender, @PastOrPresent LocalDate clientSince, @NotBlank String insuranceProvider, boolean isVIP) {
        super(name, lastName, email, phone, pesel, birthday, gender);
        this.clientSince = clientSince;
        this.insuranceProvider = insuranceProvider;
        this.isVIP = isVIP;
    }
}
