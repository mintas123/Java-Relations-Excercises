package pl.edu.pjatk.s16604.mas_FP.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pjatk.s16604.mas_FP.enums.DrugType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long drugId;

    private DrugType type;
    @NotBlank
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "drug_presc",
            joinColumns = @JoinColumn(name = "drug_id"),
            inverseJoinColumns = @JoinColumn(name = "presc_id")
    )
    private Set<Prescription> useHistory = new HashSet<>();

    public Drug(DrugType type, @NotBlank String name) {
        this.type = type;
        this.name = name;
    }
}
