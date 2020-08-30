package pl.edu.pjatk.s16604.mas_FP.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas_FP.DTO.MeetingDTO;
import pl.edu.pjatk.s16604.mas_FP.entity.Appointment;
import pl.edu.pjatk.s16604.mas_FP.entity.Doctor;
import pl.edu.pjatk.s16604.mas_FP.entity.Meeting;
import pl.edu.pjatk.s16604.mas_FP.entity.Patient;
import pl.edu.pjatk.s16604.mas_FP.entity.Prescription;
import pl.edu.pjatk.s16604.mas_FP.entity.Referral;
import pl.edu.pjatk.s16604.mas_FP.entity.TeleMedicine;
import pl.edu.pjatk.s16604.mas_FP.repository.AppointmentRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.DoctorRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.MeetingRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.PatientRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.PrescriptionRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.ReferralRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.TeleMedicineRepository;

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
    private TeleMedicineRepository teleMedicineRepository;
    @Autowired
    private MeetingRepository meetingRepository;


    @Autowired
    private ReferralRepository referralRepository;


    @Autowired
    private PrescriptionRepository prescriptionRepository;


    // checking if there is already entry in the database (only to happen when manually creating POST)
    public void addMeeting(MeetingDTO meetingDTO) {
        Doctor doctor = doctorRepository.getByPersonId(meetingDTO.getDoctorId());
        Patient patient = patientRepository.getByPersonId(meetingDTO.getPatientId()).get();
        Optional<Appointment> selectedSpot = appointmentRepository.checkIfTaken(doctor,patient,meetingDTO.getDate());

        if (selectedSpot.isEmpty()){
            Meeting meeting = new Meeting(meetingDTO.getDate());
            saveAppointment(meeting);
            addDataToAppointment(
                    meeting.getAppointmentId(),
                    meetingDTO.getPatientId(),
                    meetingDTO.getDoctorId()
            );
        }

    }

    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }


    public void addDataToAppointment(Long appointmentId, Long patientId, Long doctorId, Long referralId, Long prescId) {
        Appointment appointment = appointmentRepository.getByAppointmentId(appointmentId);
        Doctor doctor = doctorRepository.getByPersonId(doctorId);
        Optional<Patient> optionalPatient = patientRepository.getByPersonId(patientId);
        Optional<Prescription> optionalPrescription = prescriptionRepository.getAllByPrescriptionId(prescId);
        Optional<Referral> optionalReferral = referralRepository.getAllByReferralId(referralId);

        if (appointment != null && doctor != null && optionalPatient.isPresent()) {
            appointment.setDoctor(doctor);
            Patient patient = optionalPatient.get();
            appointment.setPatient(patient);
            if (appointment instanceof TeleMedicine && optionalPrescription.isPresent() &&
                    ((TeleMedicine) appointment).isCanGivePrescription() ||
                    appointment instanceof Meeting) {
                appointment.setPrescription(optionalPrescription.get());
                optionalPrescription.get().setAppointment(appointment);
                optionalPrescription.get().setDoctor(doctor);
                optionalPrescription.get().setPatient(patient);
                prescriptionRepository.save(optionalPrescription.get());

            }
            if (optionalReferral.isPresent()) {
                appointment.setReferral(optionalReferral.get());
                optionalReferral.get().setAppointment(appointment);
                optionalReferral.get().setDoctor(doctor);
                optionalReferral.get().setPatient(patient);
                referralRepository.save(optionalReferral.get());
            }

            patient.addVisit(appointment);
            doctor.addVisit(appointment);


            doctorRepository.save(doctor);
            appointmentRepository.save(appointment);
            patientRepository.save(patient);
        }
    }

    public void addDataToAppointment(Long appointmentId, Long patientId, Long doctorId, Long prescId) {
        Appointment appointment = appointmentRepository.getByAppointmentId(appointmentId);
        Doctor doctor = doctorRepository.getByPersonId(doctorId);
        Optional<Patient> optionalPatient = patientRepository.getByPersonId(patientId);
        Optional<Prescription> optionalPrescription = prescriptionRepository.getAllByPrescriptionId(prescId);

        if (appointment != null && doctor != null && optionalPatient.isPresent()) {
            appointment.setDoctor(doctor);
            Patient patient = optionalPatient.get();
            appointment.setPatient(patient);
            Optional<TeleMedicine> teleMedicine = teleMedicineRepository.getAllByAppointmentId(appointmentId);
            Optional<Meeting> meeting = meetingRepository.getAllByAppointmentId(appointmentId);

            if (((teleMedicine.isPresent() && teleMedicine.get().isCanGivePrescription()) || meeting.isPresent())
                    && optionalPrescription.isPresent()) {
                appointment.setPrescription(optionalPrescription.get());
                optionalPrescription.get().setAppointment(appointment);
                optionalPrescription.get().setDoctor(doctor);
                optionalPrescription.get().setPatient(patient);
                prescriptionRepository.save(optionalPrescription.get());

            }
            patient.addVisit(appointment);
            doctor.addVisit(appointment);


            doctorRepository.save(doctor);
            appointmentRepository.save(appointment);
            patientRepository.save(patient);
        }
    }

    public void addDataToAppointment(Long appointmentId, Long patientId, Long doctorId) {
        Appointment appointment = appointmentRepository.getByAppointmentId(appointmentId);
        Doctor doctor = doctorRepository.getByPersonId(doctorId);
        Optional<Patient> optionalPatient = patientRepository.getByPersonId(patientId);


        if (appointment != null && doctor != null && optionalPatient.isPresent()) {
            appointment.setDoctor(doctor);
            Patient patient = optionalPatient.get();
            appointment.setPatient(patient);
            patient.addVisit(appointment);
            doctor.addVisit(appointment);

            doctorRepository.save(doctor);
            appointmentRepository.save(appointment);
            patientRepository.save(patient);
        }
    }

    public void cancelAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
