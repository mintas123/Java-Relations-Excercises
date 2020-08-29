package pl.edu.pjatk.s16604.mas_FP.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "appointmentId")

@Getter
@Setter
@NoArgsConstructor
public class TeleMedicine extends Appointment {

    private boolean canGivePrescription;

    public TeleMedicine(LocalDateTime date, boolean canGivePrescription) {
        super(date);
        this.canGivePrescription = canGivePrescription;
    }
}
