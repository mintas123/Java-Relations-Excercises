package pl.edu.pjatk.s16604.mas_FP.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DoctorDTO {
    private long personId;
    private String name;
    private String lastName;
    private String divisionName;
}
