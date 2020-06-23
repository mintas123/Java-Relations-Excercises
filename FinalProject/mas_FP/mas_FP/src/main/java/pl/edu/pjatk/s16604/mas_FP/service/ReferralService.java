package pl.edu.pjatk.s16604.mas_FP.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas_FP.model.Doctor;
import pl.edu.pjatk.s16604.mas_FP.model.Patient;
import pl.edu.pjatk.s16604.mas_FP.model.Referral;
import pl.edu.pjatk.s16604.mas_FP.repository.DoctorRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.PatientRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.ReferralRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ReferralService {


    @Autowired
    private ReferralRepository referralRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;


    public void saveReferral(Referral referral) {
        referralRepository.save(referral);
    }

    public void markAsSaved(Long referralId) {
        Referral referral = referralRepository.getAllByReferralId(referralId);
        if (referral != null && !referral.isUsed()) {
            referral.setUsed(true);
            saveReferral(referral);
        }
    }

    public List<Referral> getAllByDoctor(Long docId) {
        Doctor doctor = doctorRepository.getByPersonId(docId);
        if (doctor != null) {
            return referralRepository.getAllByDoctor(doctor);
        }
        return new ArrayList<>();
    }

    public List<Referral> getAllByPatient(Long patientId) {
        Optional<Patient> patientOptional = patientRepository.getByPersonId(patientId);
        if (patientOptional.isPresent()) {
            return referralRepository.getAllByPatient(patientOptional.get());
        }
        return new ArrayList<>();
    }
}
