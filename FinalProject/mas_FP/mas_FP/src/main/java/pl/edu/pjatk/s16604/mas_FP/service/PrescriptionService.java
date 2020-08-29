package pl.edu.pjatk.s16604.mas_FP.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas_FP.entity.Doctor;
import pl.edu.pjatk.s16604.mas_FP.entity.Drug;
import pl.edu.pjatk.s16604.mas_FP.entity.Patient;
import pl.edu.pjatk.s16604.mas_FP.entity.Prescription;
import pl.edu.pjatk.s16604.mas_FP.repository.DoctorRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.DrugRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.PatientRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.PrescriptionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class PrescriptionService {


    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DrugRepository drugRepository;


    public void savePresc(Prescription referral) {
        prescriptionRepository.save(referral);
    }

    public void addDrug(Long prescId, Long drugId) {
        Drug drug = drugRepository.getAllByDrugId(drugId);
        Optional<Prescription> prescriptionOptional = prescriptionRepository.getAllByPrescriptionId(prescId);
        if (drug != null && prescriptionOptional.isPresent()) {
            prescriptionOptional.get().addDrugg(drug);
            prescriptionRepository.save(prescriptionOptional.get());
        }
    }

    public void markAsUsed(Long prescId) {
        Optional<Prescription> prescriptionOptional = prescriptionRepository.getAllByPrescriptionId(prescId);
        if (prescriptionOptional.isPresent() && !prescriptionOptional.get().isUsed()) {
            prescriptionOptional.get().setUsed(true);
            savePresc(prescriptionOptional.get());
        }
    }

    public List<Prescription> getAllByDoctor(Long docId) {
        Doctor doctor = doctorRepository.getByPersonId(docId);
        if (doctor != null) {
            return prescriptionRepository.getAllByDoctor(doctor);
        }
        return new ArrayList<>();
    }

    public List<Prescription> getAllByPatient(Long patientId) {
        Optional<Patient> patientOptional = patientRepository.getByPersonId(patientId);
        if (patientOptional.isPresent()) {
            return prescriptionRepository.getAllByPatient(patientOptional.get());
        }
        return new ArrayList<>();
    }
}
