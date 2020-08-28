package pl.edu.pjatk.s16604.mas_FP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjatk.s16604.mas_FP.DTO.PatientDTO;
import pl.edu.pjatk.s16604.mas_FP.DTO.VisitDTO;
import pl.edu.pjatk.s16604.mas_FP.entity.Appointment;
import pl.edu.pjatk.s16604.mas_FP.entity.Doctor;
import pl.edu.pjatk.s16604.mas_FP.entity.Patient;
import pl.edu.pjatk.s16604.mas_FP.entity.Person;
import pl.edu.pjatk.s16604.mas_FP.entity.Referral;
import pl.edu.pjatk.s16604.mas_FP.service.DivisionService;
import pl.edu.pjatk.s16604.mas_FP.service.DoctorService;
import pl.edu.pjatk.s16604.mas_FP.service.PatientService;
import pl.edu.pjatk.s16604.mas_FP.service.ReferralService;
import pl.edu.pjatk.s16604.mas_FP.service.VisitService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    DivisionService divisionService;

    @Autowired
    VisitService visitService;

    @Autowired
    ReferralService referralService;

    // to search all patients
    @GetMapping({"/patient"})
    public List<Patient> getAllPatients() {
        return patientService.findAllPatient();
    }

    @GetMapping({"/patient/{string}"})
    public List<Patient> searchPatientByString(@PathVariable String string) {
        return patientService.searchPatientByString(string);
    }

    // patient details
    @GetMapping({"/patient/history/{patientId}"})
    public List<VisitDTO> searchPatientAppHistory(@PathVariable long patientId) {
        return patientService.searchPatientAppHistory(patientId);
    }

    @GetMapping({"/patient/referrals/{patientId}"})
    public List<Referral> searchPatientRefHistory(@PathVariable long patientId) {
        return patientService.searchPatientRefHistory(patientId);
    }

    //patient edit and create
    @PutMapping({"/patient/{patientId}/edit"})
    public ResponseEntity<Void> updatePatient(@PathVariable long patientId, @RequestBody PatientDTO patientDTO) {
        Patient patientFound = patientService.searchPatientById(patientId);
        if (patientFound != null) {
            patientService.updatePatient(patientId, patientDTO);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/patient")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createPatient(@RequestBody PatientDTO patientDTO) {
        patientService.addPatient(patientDTO);
    }









    @GetMapping({"/patient/pesel/{pesel}"})
    public List<Patient> searchPatientByPesel(@PathVariable String pesel) {
        return patientService.searchPatientByPesel(pesel);
    }

    @GetMapping({"/person/pesel/{pesel}"})
    public List<Person> searchByPesel(@PathVariable String pesel) {
        return patientService.searchPersonByPesel(pesel);
    }

    @GetMapping({"/doctor/{string}"})
    public List<Doctor> searchDoctorByIdOrName(@PathVariable String string) {
        return doctorService.searchDoctorByIdOrName(string);
    }


    @GetMapping({"/appointment/{doctor}/{dateFrom}/{dateTo}"})
    public List<LocalDateTime> getSpots(@PathVariable String doctor, @PathVariable LocalDate dateFrom, @PathVariable LocalDate dateTo) {
        List<Doctor> doctor1 = doctorService.searchDoctorByIdOrName(doctor);
        if (doctor1.isEmpty()) {
            return null;
        }
        return doctorService.searchByCriteria(doctor1.get(0), dateFrom, dateTo);
    }


    @PostMapping({"/patient/new"})
    public void addPatient(@RequestBody Patient patient) {
        patientService.searchAndCreatePatient(patient);
    }
}
