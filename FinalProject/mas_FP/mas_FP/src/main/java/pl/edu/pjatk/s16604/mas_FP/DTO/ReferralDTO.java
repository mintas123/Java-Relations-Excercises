package pl.edu.pjatk.s16604.mas_FP.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ReferralDTO {
    private String doctorName;
    private String doctorLastName;
    private String divisionName;
    private boolean isUsed;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
