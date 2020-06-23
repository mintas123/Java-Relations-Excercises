package pl.edu.pjatk.s16604.mas_FP.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas_FP.model.Appointment;
import pl.edu.pjatk.s16604.mas_FP.model.Patient;
import pl.edu.pjatk.s16604.mas_FP.model.Person;
import pl.edu.pjatk.s16604.mas_FP.model.Referral;
import pl.edu.pjatk.s16604.mas_FP.repository.AppointmentRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.PatientRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.PersonRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.ReferralRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ReferralRepository referralRepository;
    @Autowired
    private PersonRepository personRepository;


    public List<Patient> searchPatientByPesel(String pesel) {
        return patientRepository.getAllByPeselStartingWith(pesel);
    }

    public List<Person> searchPersonByPesel(String pesel) {
        return personRepository.getAllByPeselStartingWith(pesel);
    }

    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
    }


    public List<Appointment> searchPatientAppHistory(long patientId) {
        Optional<Patient> optionalPatient = patientRepository.getByPersonId(patientId);
        if (optionalPatient.isPresent()) {
            return appointmentRepository.getAllByPatient(optionalPatient.get());
        } else {
            return new ArrayList<>();
        }
    }

    public List<Referral> searchPatientRefHistory(long patientId) {
        Optional<Patient> optionalPatient = patientRepository.getByPersonId(patientId);
        if (optionalPatient.isPresent()) {
            return referralRepository.getAllByPatient(optionalPatient.get());
        } else {
            return new ArrayList<>();
        }
    }

    public void searchAndCreatePatient(Patient patient) {
        Optional<Patient> optionalPatient = patientRepository.getByPersonId(patient.getPersonId());
        if (optionalPatient.isEmpty()) {
            patientRepository.save(patient);
        }
    }


}
