package pl.edu.pjatk.s16604.mas_FP.DTO;

import lombok.Builder;
import lombok.Data;
import pl.edu.pjatk.s16604.mas_FP.enums.Gender;

import java.time.LocalDate;

@Builder
@Data
public class PatientDTO {
    private long personId;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String pesel;
    private LocalDate birthday;
    private Gender gender;
    private LocalDate clientSince;
    private String insuranceProvider;
    private boolean isVIP;


}
