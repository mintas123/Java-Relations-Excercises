package pl.edu.pjatk.s16604.mas_FP.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pjatk.s16604.mas_FP.enums.ContractType;
import pl.edu.pjatk.s16604.mas_FP.enums.Gender;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "personId")

@Getter
@Setter
@NoArgsConstructor
public class Receptionist extends Person {

    @Min(2000)
    private double salary;

    @NotNull
    private ContractType contractType;


    public Receptionist(@NotBlank String name, @NotBlank String lastName, @Email(message = "Invalid email") String email, @NotBlank String phone, @Size(min = 11, max = 11, message = "PESEL is 11 digits long!") @NotNull String pesel, @Past LocalDate birthday, Gender gender, @Min(2000) double salary, @NotNull ContractType contractType) {
        super(name, lastName, email, phone, pesel, birthday, gender);
        this.salary = salary;
        this.contractType = contractType;
    }
}
