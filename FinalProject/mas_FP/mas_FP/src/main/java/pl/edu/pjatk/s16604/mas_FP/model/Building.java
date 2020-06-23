package pl.edu.pjatk.s16604.mas_FP.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "building")
    private Set<Room> rooms;

    public Building(@NotBlank String address) {
        this.address = address;
    }
}
