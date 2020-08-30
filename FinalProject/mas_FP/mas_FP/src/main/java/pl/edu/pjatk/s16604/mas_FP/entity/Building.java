package pl.edu.pjatk.s16604.mas_FP.entity;


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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table

@Getter
@Setter
@NoArgsConstructor
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long buildingId;

    @NotBlank
    private String address;


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "div_build",
            joinColumns = @JoinColumn(name = "building_id"),
            inverseJoinColumns = @JoinColumn(name = "div_id")
    )
    private Set<Division> divisions = new HashSet<>();


    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "building",
            orphanRemoval = true)
    private Set<Room> rooms;

    public Building(@NotBlank String address) {
        this.address = address;
    }

    public void addDivision(Division division) {
        if (!divisions.contains(division)) {
            divisions.add(division);
        }
    }

    public void removeDivision(Division division) {
        if (divisions.contains(division)) {
            divisions.remove(division);
        }
    }
}
