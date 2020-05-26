package pl.edu.pjatk.s16604.mas5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dealerId;

    @NotBlank
    @Size(max = 10, min = 10, message = "NIP is 10 chars long!")
    private String nip;

    @NotBlank
    private String name;

    @NotBlank
    private String street;

    @NotBlank
    private String phone;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "dealer")
    private Set<Car> fleet = new HashSet<>();

    public Dealer(@NotBlank @Size(max = 10, min = 10, message = "NIP is 10 chars long!") String nip,
                  @NotBlank String name, @NotBlank String street, @NotBlank String phone) {
        this.nip = nip;
        this.name = name;
        this.street = street;
        this.phone = phone;
    }
}
