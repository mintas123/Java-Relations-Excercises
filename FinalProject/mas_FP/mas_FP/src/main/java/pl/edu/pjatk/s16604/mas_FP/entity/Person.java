package pl.edu.pjatk.s16604.mas_FP.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pjatk.s16604.mas_FP.enums.Gender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)

@Getter
@Setter
@NoArgsConstructor
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long personId;

    @NotBlank
    private String name;

    @NotBlank
    @Column(name = "last_name")

    private String lastName;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank
    private String phone;

    @Size(min = 11, max = 11, message = "PESEL is 11 digits long!")
    @NotNull
    @Column(unique = true)
    private String pesel;

    @Past
    private LocalDate birthday;

    private Gender gender;


    public Person(@NotBlank String name, @NotBlank String lastName, @Email(message = "Invalid email") String email, @NotBlank String phone, @Size(min = 11, max = 11, message = "PESEL is 11 digits long!") @NotNull String pesel, @Past LocalDate birthday, Gender gender) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.pesel = pesel;
        this.birthday = birthday;
        this.gender = gender;
    }

    public int getAge() {
        LocalDate now = LocalDate.now();
        return Period.between(this.birthday, now).getYears();
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", pesel='" + pesel + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                '}';
    }
}
