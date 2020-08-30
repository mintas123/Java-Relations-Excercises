package pl.edu.pjatk.s16604.mas_FP.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class MeetingDTO {
    private long doctorId;
    private long patientId;
    private LocalDateTime date;
}
