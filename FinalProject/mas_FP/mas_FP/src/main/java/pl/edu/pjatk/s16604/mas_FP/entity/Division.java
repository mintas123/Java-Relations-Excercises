package pl.edu.pjatk.s16604.mas_FP.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@JsonIgnoreProperties(value = {"head"})
@Getter
@Setter
@NoArgsConstructor
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long divisionId;
    @NotBlank
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
            mappedBy = "divisions"
    )
    private Set<Building> buildings = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "head_id")
    private Doctor head;
    @NotBlank
    private String description;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "personId")
    private Set<Doctor> staff;


    public Division(@NotBlank String name, @NotBlank String description) {
        this.name = name;
        this.description = description;
    }

    public void addToStaff(Doctor doctor) {
        if (!staff.contains(doctor)) {
            staff.add(doctor);
        }
    }

    public void removeFromStaff(Doctor doctor) {
        if (staff.contains(doctor)) {
            staff.remove(doctor);
        }
    }

    public void addBuilding(Building building) {
        if (!buildings.contains(building)) {
            buildings.add(building);
        }
    }

    public void removeBuilding(Building building) {
        if (buildings.contains(building)) {
            buildings.remove(building);
        }
    }


    @Override
    public String toString() {
        return "Division{" +
                "divisionId=" + divisionId +
                ", name='" + name + '\'' +
                ", head=" + head +
                ", description='" + description + '\'' +
                '}';
    }
}
