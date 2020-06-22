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
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long divisionId;
    @NotBlank
    private String name;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "head_id")
    private Doctor head;
    @NotBlank
    private String description;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "doctor")
    private Set<Doctor> staff;


    public Division(@NotBlank String name, @NotBlank String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Division{" +
                "divisionId=" + divisionId +
                ", name='" + name + '\'' +
                ", head=" + head +
                ", description='" + description + '\'' +
//                ", staff=" + staff.forEach(doctor -> "d"); +
                '}';
    }
}
