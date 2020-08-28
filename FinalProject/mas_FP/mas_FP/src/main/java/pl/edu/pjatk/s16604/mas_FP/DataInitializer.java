package pl.edu.pjatk.s16604.mas_FP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.s16604.mas_FP.enums.ContractType;
import pl.edu.pjatk.s16604.mas_FP.enums.DrugType;
import pl.edu.pjatk.s16604.mas_FP.enums.Gender;
import pl.edu.pjatk.s16604.mas_FP.entity.Building;
import pl.edu.pjatk.s16604.mas_FP.entity.Division;
import pl.edu.pjatk.s16604.mas_FP.entity.Doctor;
import pl.edu.pjatk.s16604.mas_FP.entity.Drug;
import pl.edu.pjatk.s16604.mas_FP.entity.Meeting;
import pl.edu.pjatk.s16604.mas_FP.entity.Patient;
import pl.edu.pjatk.s16604.mas_FP.entity.Prescription;
import pl.edu.pjatk.s16604.mas_FP.entity.Receptionist;
import pl.edu.pjatk.s16604.mas_FP.entity.Referral;
import pl.edu.pjatk.s16604.mas_FP.entity.Room;
import pl.edu.pjatk.s16604.mas_FP.entity.TeleMedicine;
import pl.edu.pjatk.s16604.mas_FP.repository.BuildingRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.DrugRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.ReceptionistRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.RoomRepository;
import pl.edu.pjatk.s16604.mas_FP.service.DivisionService;
import pl.edu.pjatk.s16604.mas_FP.service.DoctorService;
import pl.edu.pjatk.s16604.mas_FP.service.PatientService;
import pl.edu.pjatk.s16604.mas_FP.service.PrescriptionService;
import pl.edu.pjatk.s16604.mas_FP.service.ReferralService;
import pl.edu.pjatk.s16604.mas_FP.service.VisitService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Component
public class DataInitializer {

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;
    @Autowired
    PrescriptionService prescriptionService;
    @Autowired
    ReferralService referralService;
    @Autowired
    VisitService visitService;

    @Autowired
    private DivisionService divisionService;

    @Autowired
    private DrugRepository drugRepository;


    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RoomRepository roomRepository;


    public void initData() {

        Doctor doctor = new Doctor("Andrzej", "Bartecki", "ab@gmail.com", "789456123", "80052655998", LocalDate.of(1980, Month.MAY, 26), Gender.MALE, 6600);
        Doctor doctor2 = new Doctor("Daniel", "Dalski", "dada@gmail.com", "604535696", "93052235778", LocalDate.of(1993, Month.MAY, 22), Gender.MALE, 4800);
        doctorService.saveDoctor(doctor);
        doctorService.saveDoctor(doctor2);


        Division div1 = new Division("Dermatology", "some desc");
        divisionService.saveDivision(div1);
        divisionService.addToStaff(div1.getDivisionId(), doctor.getPersonId());
        divisionService.addHead(div1.getDivisionId(), doctor.getPersonId());
        divisionService.addToStaff(div1.getDivisionId(), doctor2.getPersonId());

        Patient patient1 = new Patient("xxx", "bbb", "aaa@gmail.com", "12344432", "11122233309", LocalDate.of(1966, Month.MAY, 22), Gender.MALE, LocalDate.now(), "AXA", false);
        Patient patient2 = new Patient("aaa", "bbb", "bbb@gmail.com", "34243244", "44455565492", LocalDate.of(1986, Month.MAY, 21), Gender.MALE, LocalDate.now(), "X", true);
        patientService.searchAndCreatePatient(patient1);
        patientService.searchAndCreatePatient(patient2);

        Receptionist receptionist1 = new Receptionist("anna", "baaab", "annabaab@gmail.com", "333444222", "43253497603", LocalDate.of(1976, Month.MAY, 22), Gender.FEMALE, 3000, ContractType.MANDATE);
        Receptionist receptionist2 = new Receptionist("daniel", "baaab", "faniel@gmail.com", "123421344", "56786497603", LocalDate.of(1986, Month.MAY, 22), Gender.MALE, 4000, ContractType.EMPLOYMENT);
        receptionistRepository.save(receptionist1);
        receptionistRepository.save(receptionist2);


        Building b1 = new Building("AAA 12/3 Warsxzawa");
        Building b2 = new Building("AAA 12/4 Warsxzawa");
        Building b3 = new Building("AAA 12/5 Warsxzawa");
        buildingRepository.save(b1);
        buildingRepository.save(b2);
        buildingRepository.save(b3);

        Room room = new Room(12, b1, "East");
        Room room2 = new Room(13, b1, "East");
        Room room3 = new Room(14, b1, "East");
        Room room4 = new Room(1, b1, "East");
        Room room5 = new Room(1, b1, "East");
        Room room6 = new Room(2, b1, "East");
        roomRepository.save(room);
        roomRepository.save(room2);
        roomRepository.save(room3);
        roomRepository.save(room4);
        roomRepository.save(room5);
        roomRepository.save(room6);

        Prescription pres1 = new Prescription(false, LocalDate.of(2021, Month.JULY, 23), "SDF234", "blablalballbab");
        Prescription pres2 = new Prescription(false, LocalDate.of(2021, Month.JULY, 25), "SDF234", "blablalballbab");
        prescriptionService.savePresc(pres1);
        prescriptionService.savePresc(pres2);

        Drug drug1 = new Drug(DrugType.PAINKILER, "AAA");
        Drug drug2 = new Drug(DrugType.SKIN_CARE, "DDD");
        Drug drug3 = new Drug(DrugType.SKIN_CARE, "EEE");
        Drug drug4 = new Drug(DrugType.STOMACH, "FFF");
        drugRepository.save(drug1);
        drugRepository.save(drug2);
        drugRepository.save(drug3);
        drugRepository.save(drug4);

        prescriptionService.addDrug(pres1.getPrescriptionId(), drug1.getDrugId());
        prescriptionService.addDrug(pres1.getPrescriptionId(), drug2.getDrugId());
        prescriptionService.addDrug(pres1.getPrescriptionId(), drug3.getDrugId());
        prescriptionService.addDrug(pres2.getPrescriptionId(), drug4.getDrugId());

        Referral referral = new Referral(LocalDate.now(), LocalDate.of(2020, Month.SEPTEMBER, 23));
        Referral referral2 = new Referral(LocalDate.now(), LocalDate.of(2020, Month.SEPTEMBER, 21));
        referralService.saveReferral(referral);
        referralService.saveReferral(referral2);

        Meeting meeting = new Meeting(LocalDateTime.now(), room);
        TeleMedicine teleMedicine = new TeleMedicine(LocalDateTime.now(), true);

        visitService.saveAppointment(meeting);
        visitService.saveAppointment(teleMedicine);

        visitService.addDataToAppointment(meeting.getAppointmentId(), patient1.getPersonId(), doctor.getPersonId()
                , referral.getReferralId(), pres1.getPrescriptionId());
        visitService.addDataToAppointment(teleMedicine.getAppointmentId(), patient2.getPersonId(), doctor2.getPersonId()
                , referral2.getReferralId(), pres2.getPrescriptionId());


        System.out.println(patientService.findAllPatient());
        System.out.println(patientService.searchPatientByPesel("1"));
        System.out.println(prescriptionService.getAllByDoctor(doctor.getPersonId()));


    }


}
