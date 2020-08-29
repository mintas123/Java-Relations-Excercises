package pl.edu.pjatk.s16604.mas_FP.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas_FP.DTO.PatientDTO;
import pl.edu.pjatk.s16604.mas_FP.DTO.ReferralDTO;
import pl.edu.pjatk.s16604.mas_FP.DTO.VisitDTO;
import pl.edu.pjatk.s16604.mas_FP.entity.Appointment;
import pl.edu.pjatk.s16604.mas_FP.entity.Patient;
import pl.edu.pjatk.s16604.mas_FP.entity.Person;
import pl.edu.pjatk.s16604.mas_FP.entity.Referral;
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

    public List<Patient> searchPatientByString(String string) {
        return patientRepository.getAllByNameContainsOrLastNameContainsOrPeselContains(string, string, string);
    }

    public Patient searchPatientById(long patientId) {
        Optional<Patient> optionalPatient = patientRepository.getByPersonId(patientId);
        if (optionalPatient.isPresent()) {
            return optionalPatient.get();
        } else {
            throw new RuntimeException("No such patient");
        }
    }

    public void updatePatient(long id, PatientDTO dto) {
        Patient patient = patientRepository.getByPersonId(id).get();
        patient.setName(dto.getName());
        patient.setLastName(dto.getLastName());
        patient.setPesel(dto.getPesel());
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());
        patient.setBirthday(dto.getBirthday());
        patient.setGender(dto.getGender());
        patient.setClientSince(dto.getClientSince());
        patient.setInsuranceProvider(dto.getInsuranceProvider());
        patient.setVIP(dto.isVIP());


        patientRepository.save(patient);

    }

    public void addPatient(PatientDTO patient) {
        Patient newPatient = new Patient(patient.getName(),
                patient.getLastName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getPesel(),
                patient.getBirthday(),
                patient.getGender(),
                patient.getClientSince(),
                patient.getInsuranceProvider(),
                patient.isVIP()
        );
        patientRepository.save(newPatient);

    }

    public List<Person> searchPersonByPesel(String pesel) {
        return personRepository.getAllByPeselStartingWith(pesel);
    }

    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
    }


    public List<VisitDTO> searchPatientAppHistory(long patientId) {
        Optional<Patient> optionalPatient = patientRepository.getByPersonId(patientId);
        if (optionalPatient.isPresent()) {
            List<Appointment> appointments = appointmentRepository.getAllByPatient(optionalPatient.get());
            List<VisitDTO> visitDTOList = new ArrayList<>();
            appointments.forEach(appointment ->
                    visitDTOList.add(VisitDTO.builder()
                            .visitDate(appointment.getDate())
                            .doctorName(appointment.getDoctor().getName())
                            .doctorLastName(appointment.getDoctor().getLastName())
                            .divisionName(appointment.getDoctor().getDivision().getName())
                            .build())
            );
            return visitDTOList;
        } else {
            return new ArrayList<>();
        }
    }

    public List<ReferralDTO> searchPatientRefHistory(long patientId) {
        Optional<Patient> optionalPatient = patientRepository.getByPersonId(patientId);
        if (optionalPatient.isPresent()) {
            List<Referral> referrals = referralRepository.getAllByPatient(optionalPatient.get());
            List<ReferralDTO> referralDTOList = new ArrayList<>();
            referrals.forEach(referral ->
                    referralDTOList.add(ReferralDTO.builder()
                            .doctorName(referral.getDoctor().getName())
                            .doctorLastName(referral.getDoctor().getLastName())
                            .divisionName(referral.getDoctor().getDivision().getName())
                            .isUsed(referral.isUsed())
                            .dateFrom(referral.getDateFrom())
                            .dateTo(referral.getDateTo()).build())
            );
            return referralDTOList;
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
