package pl.edu.pjatk.s16604.mas_FP.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "appointmentId")

@Getter
@Setter
@NoArgsConstructor
public class Meeting extends Appointment {

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "roomId")
    private Room room;

    public Meeting(LocalDateTime date, Room room) {
        super(date);
        this.room = room;
    }
}
