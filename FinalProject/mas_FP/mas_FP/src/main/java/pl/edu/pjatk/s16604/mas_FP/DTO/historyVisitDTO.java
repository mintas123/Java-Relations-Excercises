package pl.edu.pjatk.s16604.mas_FP.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class historyVisitDTO {
    private LocalDateTime visitDate;
    private String doctorName;
    private String doctorLastName;
    private String divisionName;
}
