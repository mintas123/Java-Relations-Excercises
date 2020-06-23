package pl.edu.pjatk.s16604.mas_FP.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjatk.s16604.mas_FP.model.Appointment;
import pl.edu.pjatk.s16604.mas_FP.model.Doctor;
import pl.edu.pjatk.s16604.mas_FP.model.Patient;
import pl.edu.pjatk.s16604.mas_FP.model.Person;
import pl.edu.pjatk.s16604.mas_FP.model.Referral;
import pl.edu.pjatk.s16604.mas_FP.service.DoctorService;
import pl.edu.pjatk.s16604.mas_FP.service.PatientService;

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

    @GetMapping({"/appointment/{patient}"})
    public List<Appointment> searchPatientAppHistory(@PathVariable long patient) {
        return patientService.searchPatientAppHistory(patient);
    }

    @GetMapping({"/referrals/{patient}"})
    public List<Referral> searchPatientRefHistory(@PathVariable long patient) {
        return patientService.searchPatientRefHistory(patient);
    }

    @GetMapping({"/appointment/{doctor}/{dateFrom}/{dateTo}"})
    public List<LocalDateTime> getSpots(@PathVariable String doctor, @PathVariable LocalDate dateFrom, @PathVariable LocalDate dateTo) {
        List<Doctor> doctor1 = doctorService.searchDoctorByIdOrName(doctor);
        if (doctor1.isEmpty()) {
            return null;
        }
        return doctorService.searchByCriteria(doctor1.get(0), dateFrom, dateTo);
    }

    @GetMapping({"/patient"})
    public List<Patient> getAllPatients() {
        return patientService.findAllPatient();
    }

    @PostMapping({"/patient/new"})
    public void addPatient(@RequestBody Patient patient) {
        patientService.searchAndCreatePatient(patient);
    }
}
