package pl.edu.pjatk.s16604.mas_FP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
import pl.edu.pjatk.s16604.mas_FP.enums.ContractType;
import pl.edu.pjatk.s16604.mas_FP.enums.DrugType;
import pl.edu.pjatk.s16604.mas_FP.enums.Gender;
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
        Doctor doctor3 = new Doctor("Anna", "Bąk", "abak@gmail.com", "432534456", "93232235778", LocalDate.of(1993, Month.SEPTEMBER, 22), Gender.FEMALE, 4800);
        Doctor doctor4 = new Doctor("Zbigniew", "Jamroz", "zbijam@gmail.com", "345654567", "12341234123", LocalDate.of(1993, Month.MAY, 22), Gender.MALE, 5234);
        Doctor doctor5 = new Doctor("Adrian", "Dalik", "addal@gmail.com", "543567654", "65101255857", LocalDate.of(1993, Month.MAY, 22), Gender.MALE, 5344);
        Doctor doctor6 = new Doctor("Miłosz", "Bliski", "bliskiOrazMily@gmail.com", "345654678", "66042675885", LocalDate.of(1993, Month.MAY, 22), Gender.MALE, 9000);
        doctorService.saveDoctor(doctor);
        doctorService.saveDoctor(doctor2);
        doctorService.saveDoctor(doctor3);
        doctorService.saveDoctor(doctor4);
        doctorService.saveDoctor(doctor5);
        doctorService.saveDoctor(doctor6);


        Building b1 = new Building("Wołoska 22 Warszawa");
        Building b2 = new Building("Wilanowska 363 Warszawa");
        Building b3 = new Building("Puławska 322/12 Warszawa");
        buildingRepository.save(b1);
        buildingRepository.save(b2);
        buildingRepository.save(b3);

        Room room = new Room(12, b1, "East");
        Room room2 = new Room(13, b1, "East");
        Room room3 = new Room(42, b2, "West");
        Room room4 = new Room(1, b3, "East");
        Room room5 = new Room(1, b3, "North");
        Room room6 = new Room(2, b3, "East");
        roomRepository.save(room);
        roomRepository.save(room2);
        roomRepository.save(room3);
        roomRepository.save(room4);
        roomRepository.save(room5);
        roomRepository.save(room6);


        Division div1 = new Division("Dermatology", "skin goes itchy ");
        Division div2 = new Division("Cardiology", "heart goes bum bum");
        Division div3 = new Division("Podology", "tupot małych stóp");
        divisionService.saveDivision(div1);
        divisionService.saveDivision(div2);
        divisionService.saveDivision(div3);
        divisionService.addToStaff(div1.getDivisionId(), doctor.getPersonId());
        divisionService.addHead(div1.getDivisionId(), doctor2.getPersonId());
        divisionService.addToStaff(div1.getDivisionId(), doctor2.getPersonId());
        divisionService.addToStaff(div1.getDivisionId(), doctor3.getPersonId());
        divisionService.addToStaff(div2.getDivisionId(), doctor4.getPersonId());
        divisionService.addToStaff(div3.getDivisionId(), doctor5.getPersonId());
        divisionService.addToStaff(div3.getDivisionId(), doctor6.getPersonId());


        Patient patient1 = new Patient("Anna", "Małowiecka", "anama@gmail.com", "432523634", "10301608556", LocalDate.of(2010, Month.OCTOBER, 16), Gender.FEMALE, LocalDate.now(), "AXA", false);
        Patient patient2 = new Patient("Zofia", "Śliwińska", "zsliwinska@gmail.com", "798369528", "10200158966", LocalDate.of(2000, Month.JANUARY, 1), Gender.FEMALE, LocalDate.now(), "X", true);
        Patient patient3 = new Patient("Radosław", "Biedrzycki", "radbied@gmail.com", "779582195", "98053259964", LocalDate.of(1998, Month.MAY, 23), Gender.MALE, LocalDate.now(), "X", true);
        Patient patient4 = new Patient("Jakub", "Mróz", "kumroz@gmail.com", "787312931", "99042608556", LocalDate.of(1999, Month.APRIL, 26), Gender.MALE, LocalDate.now(), "X", true);
        Patient patient5 = new Patient("Piotr", "Mróz", "piotmrz@gmail.com", "777999828", "69100148775", LocalDate.of(1969, Month.OCTOBER, 1), Gender.MALE, LocalDate.now(), "X", true);
        Patient patient6 = new Patient("Teresa", "Tarnaś", "teretarnas@gmail.com", "999654199", "66051080778", LocalDate.of(1966, Month.MAY, 10), Gender.MALE, LocalDate.now(), "X", true);
        Patient patient7 = new Patient("Adrianna", "Urbaniak", "a.z.urbaniak@gmail.com", "604318955", "90090254880", LocalDate.of(1990, Month.SEPTEMBER, 2), Gender.FEMALE, LocalDate.now(), "X", true);
        Patient patient8 = new Patient("Krzysztof", "Zieliński", "krzysiuziel@gmail.com", "787656989", "76112566604", LocalDate.of(1976, Month.NOVEMBER, 12), Gender.MALE, LocalDate.now(), "X", true);
        patientService.searchAndCreatePatient(patient1);
        patientService.searchAndCreatePatient(patient2);
        patientService.searchAndCreatePatient(patient3);
        patientService.searchAndCreatePatient(patient4);
        patientService.searchAndCreatePatient(patient5);
        patientService.searchAndCreatePatient(patient6);
        patientService.searchAndCreatePatient(patient7);
        patientService.searchAndCreatePatient(patient8);

        Receptionist receptionist1 = new Receptionist("anna", "baaab", "annabaab@gmail.com", "333444222", "43253497603", LocalDate.of(1976, Month.MAY, 22), Gender.FEMALE, 3000, ContractType.MANDATE);
        Receptionist receptionist2 = new Receptionist("daniel", "baaab", "faniel@gmail.com", "123421344", "56786497603", LocalDate.of(1986, Month.MAY, 22), Gender.MALE, 4000, ContractType.EMPLOYMENT);
        receptionistRepository.save(receptionist1);
        receptionistRepository.save(receptionist2);

        Prescription pres1 = new Prescription(false, LocalDate.of(2021, Month.JULY, 23), "SDF234", "blablalballbab");
        Prescription pres2 = new Prescription(false, LocalDate.of(2021, Month.JULY, 25), "DSF324", "werwerwer");
        Prescription pres3 = new Prescription(false, LocalDate.of(2021, Month.APRIL, 25), "AS23DF", "asdfsdafsdaf");
        prescriptionService.savePresc(pres1);
        prescriptionService.savePresc(pres2);
        prescriptionService.savePresc(pres3);

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
        prescriptionService.addDrug(pres3.getPrescriptionId(), drug1.getDrugId());

        Referral referral = new Referral(LocalDate.now(), LocalDate.of(2021, Month.APRIL, 26));
        Referral referral2 = new Referral(LocalDate.now(), LocalDate.of(2020, Month.NOVEMBER, 2));
        Referral referral3 = new Referral(LocalDate.now(), LocalDate.of(2020, Month.SEPTEMBER, 21));
        Referral referral4 = new Referral(LocalDate.now(), LocalDate.of(2020, Month.SEPTEMBER, 15));
        Referral referral5 = new Referral(LocalDate.now(), LocalDate.of(2020, Month.SEPTEMBER, 25));
        Referral referral6 = new Referral(LocalDate.now(), LocalDate.of(2020, Month.DECEMBER, 1));
        Referral referral7 = new Referral(LocalDate.now(), LocalDate.of(2020, Month.SEPTEMBER, 23));

        referralService.saveReferral(referral);
        referralService.saveReferral(referral2);
        referralService.saveReferral(referral3);
        referralService.saveReferral(referral4);
        referralService.saveReferral(referral5);
        referralService.saveReferral(referral6);
        referralService.saveReferral(referral7);


        Meeting meeting = new Meeting(LocalDateTime.now());
        Meeting meeting2 = new Meeting(LocalDateTime.of(2020, Month.AUGUST, 12, 14, 15));
        Meeting meeting3 = new Meeting(LocalDateTime.of(2020, Month.SEPTEMBER, 7, 8, 15));
        Meeting meeting4 = new Meeting(LocalDateTime.of(2020, Month.SEPTEMBER, 7, 8, 30));
        Meeting meeting5 = new Meeting(LocalDateTime.of(2020, Month.SEPTEMBER, 7, 9, 0));
        TeleMedicine teleMedicine = new TeleMedicine(LocalDateTime.of(2020, Month.SEPTEMBER, 7, 9, 30), true);
        TeleMedicine teleMedicine2 = new TeleMedicine(LocalDateTime.of(2020, Month.SEPTEMBER, 8, 9, 45), false);
        TeleMedicine teleMedicine3 = new TeleMedicine(LocalDateTime.of(2020, Month.SEPTEMBER, 8, 9, 15), false);

        visitService.saveAppointment(meeting);
        visitService.saveAppointment(meeting2);
        visitService.saveAppointment(meeting3);
        visitService.saveAppointment(meeting4);
        visitService.saveAppointment(meeting5);
        visitService.saveAppointment(teleMedicine);
        visitService.saveAppointment(teleMedicine2);
        visitService.saveAppointment(teleMedicine3);

        visitService.addDataToAppointment(meeting.getAppointmentId(), patient1.getPersonId(), doctor4.getPersonId()
                , referral.getReferralId(), pres1.getPrescriptionId());
        visitService.addDataToAppointment(meeting2.getAppointmentId(), patient4.getPersonId(), doctor.getPersonId()
                , referral2.getReferralId(), pres1.getPrescriptionId());
        visitService.addDataToAppointment(meeting3.getAppointmentId(), patient4.getPersonId(), doctor.getPersonId()
                , referral3.getReferralId(), pres1.getPrescriptionId());
        visitService.addDataToAppointment(meeting4.getAppointmentId(), patient2.getPersonId(), doctor.getPersonId());
        visitService.addDataToAppointment(meeting5.getAppointmentId(), patient5.getPersonId(), doctor.getPersonId());

        visitService.addDataToAppointment(teleMedicine.getAppointmentId(), patient4.getPersonId(), doctor2.getPersonId(), pres3.getPrescriptionId());
        visitService.addDataToAppointment(teleMedicine2.getAppointmentId(), patient7.getPersonId(), doctor5.getPersonId());
        visitService.addDataToAppointment(teleMedicine3.getAppointmentId(), patient6.getPersonId(), doctor5.getPersonId());

//        divisionService.addToBuildings(div1.getDivisionId(),b1.getBuildingId());
//        divisionService.addToBuildings(div2.getDivisionId(),b2.getBuildingId());
//        divisionService.addToBuildings(div3.getDivisionId(),b3.getBuildingId());

    }


}
