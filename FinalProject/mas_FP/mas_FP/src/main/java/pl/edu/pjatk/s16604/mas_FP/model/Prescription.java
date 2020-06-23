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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prescriptionId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private boolean isUsed;

    @Future
    private LocalDate deadline;

    @ManyToMany(mappedBy = "useHistory",
            fetch = FetchType.EAGER)
    private Set<Drug> drugs = new HashSet<>();
    @NotBlank
    private String eReceptaCode;

    private String instructions;

    public void addDrugg(Drug drug) {
        this.drugs.add(drug);
    }

    public void removeDrug(Drug drug) {
        if (this.drugs.contains(drug)) {
            this.drugs.remove(drug);
        }
    }

    public Prescription(boolean isUsed, @Future LocalDate deadline, @NotBlank String eReceptaCode, String instructions) {
        this.isUsed = isUsed;
        this.deadline = deadline;
        this.eReceptaCode = eReceptaCode;
        this.instructions = instructions;
    }
}
