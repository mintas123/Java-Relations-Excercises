package pl.edu.pjatk.s16604.mas_FP.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas_FP.model.Appointment;
import pl.edu.pjatk.s16604.mas_FP.model.Doctor;
import pl.edu.pjatk.s16604.mas_FP.model.Patient;
import pl.edu.pjatk.s16604.mas_FP.model.Prescription;
import pl.edu.pjatk.s16604.mas_FP.model.Referral;
import pl.edu.pjatk.s16604.mas_FP.repository.AppointmentRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.DoctorRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.PatientRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.PrescriptionRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.ReferralRepository;

import java.util.Optional;


@Service
@NoArgsConstructor
public class VisitService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Autowired
    private ReferralRepository referralRepository;


    @Autowired
    private PrescriptionRepository prescriptionRepository;


    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public void addDataToAppointment(Long appointmentId, Long patientId, Long doctorId, Long referralId, Long prescId) {
        Appointment appointment = appointmentRepository.getByAppointmentId(appointmentId);
        Doctor doctor = doctorRepository.getByPersonId(doctorId);
        Optional<Patient> optionalPatient = patientRepository.getByPersonId(patientId);
        Prescription prescription = prescriptionRepository.getAllByPrescriptionId(prescId);
        Referral referral = referralRepository.getAllByReferralId(referralId);

        if (appointment != null && doctor != null && optionalPatient.isPresent() && prescription != null && referral != null) {
            appointment.setDoctor(doctor);
            Patient patient = optionalPatient.get();
            appointment.setPatient(patient);
            appointment.setPrescription(prescription);
            appointment.setReferral(referral);
            patient.addVisit(appointment);
            doctor.addVisit(appointment);
            prescription.setAppointment(appointment);
            prescription.setDoctor(doctor);
            prescription.setPatient(patient);
            referral.setAppointment(appointment);
            referral.setDoctor(doctor);
            referral.setPatient(patient);
            doctorRepository.save(doctor);
            appointmentRepository.save(appointment);
            patientRepository.save(patient);
            referralRepository.save(referral);
            prescriptionRepository.save(prescription);
        }
    }

    public void cancelAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
