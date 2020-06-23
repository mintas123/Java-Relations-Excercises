package pl.edu.pjatk.s16604.mas_FP.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    private int roomNumber;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "buildingId")
    private Building building;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "appointmentId")
    private Set<Meeting> meetings;

    @NotBlank
    private String wing;

    public Room(int roomNumber, Building building, @NotBlank String wing) {
        this.roomNumber = roomNumber;
        this.building = building;
        this.wing = wing;
    }
}
