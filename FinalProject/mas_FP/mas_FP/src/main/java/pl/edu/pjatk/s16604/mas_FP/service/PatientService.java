package pl.edu.pjatk.s16604.mas_FP.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas_FP.DTO.PatientDTO;
import pl.edu.pjatk.s16604.mas_FP.DTO.ReferralDTO;
import pl.edu.pjatk.s16604.mas_FP.DTO.historyVisitDTO;
import pl.edu.pjatk.s16604.mas_FP.entity.Appointment;
import pl.edu.pjatk.s16604.mas_FP.entity.Patient;
import pl.edu.pjatk.s16604.mas_FP.entity.Person;
import pl.edu.pjatk.s16604.mas_FP.entity.Referral;
import pl.edu.pjatk.s16604.mas_FP.repository.AppointmentRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.PatientRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.PersonRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.ReferralRepository;

import java.util.ArrayList;
import java.util.Comparator;
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


    // Update patient from the JSON
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
    // Create patient from the JSON
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
        searchAndCreatePatient(newPatient);

    }

    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
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

    public void searchAndCreatePatient(Patient patient) {
        Optional<Patient> optionalPatient = patientRepository.getByPersonId(patient.getPersonId());
        if (optionalPatient.isEmpty()) {
            patientRepository.save(patient);
        }
    }


    //return visits the patient has in the system - both past and future
    public List<historyVisitDTO> searchPatientAppHistory(long patientId) {
        Optional<Patient> optionalPatient = patientRepository.getByPersonId(patientId);
        if (optionalPatient.isPresent()) {
            List<Appointment> appointments = appointmentRepository.getAllByPatientOrderByDate(optionalPatient.get());
            List<historyVisitDTO> historyDTOList = new ArrayList<>();
            appointments.forEach(appointment ->
                    historyDTOList.add(historyVisitDTO.builder()
                            .visitDate(appointment.getDate())
                            .doctorName(appointment.getDoctor().getName())
                            .doctorLastName(appointment.getDoctor().getLastName())
                            .divisionName(appointment.getDoctor().getDivision().getName())
                            .build())
            );
            historyDTOList.sort(Comparator.comparing(historyVisitDTO::getVisitDate).reversed());
            return historyDTOList;
        } else {
            return new ArrayList<>();
        }
    }
    //return referrals the patient has in the system - both past and future
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
            referralDTOList.sort(Comparator.comparing(ReferralDTO::getDateTo).reversed());
            return referralDTOList;
        } else {
            return new ArrayList<>();
        }
    }


}
