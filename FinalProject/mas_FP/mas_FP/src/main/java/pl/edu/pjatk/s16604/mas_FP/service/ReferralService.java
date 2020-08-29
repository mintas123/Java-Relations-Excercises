package pl.edu.pjatk.s16604.mas_FP.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas_FP.entity.Doctor;
import pl.edu.pjatk.s16604.mas_FP.entity.Patient;
import pl.edu.pjatk.s16604.mas_FP.entity.Referral;
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

    public void markAsUsed(Long referralId) {
        Optional<Referral> optionalReferral = referralRepository.getAllByReferralId(referralId);
        if (optionalReferral.isPresent() && !optionalReferral.get().isUsed()) {
            optionalReferral.get().setUsed(true);
            saveReferral(optionalReferral.get());
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
