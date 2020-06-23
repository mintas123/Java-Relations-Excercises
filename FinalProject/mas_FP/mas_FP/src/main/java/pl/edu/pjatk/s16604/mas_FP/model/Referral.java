package pl.edu.pjatk.s16604.mas_FP.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Referral {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long referralId;

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

    @FutureOrPresent
    private LocalDate dateFrom;

    @FutureOrPresent
    private LocalDate dateTo;

    public Referral(@FutureOrPresent LocalDate dateFrom, @FutureOrPresent LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
