package pl.edu.pjatk.s16604.mas_FP.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas_FP.entity.Division;
import pl.edu.pjatk.s16604.mas_FP.entity.Doctor;
import pl.edu.pjatk.s16604.mas_FP.repository.DivisionRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.DoctorRepository;

@Service
@NoArgsConstructor
public class DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public void saveDivision(Division division) {
        divisionRepository.save(division);
    }


    public void addHead(Long divisionId, Long doctorId) {
        Doctor doctor = doctorRepository.getByPersonId(doctorId);
        Division division = divisionRepository.getAllByDivisionId(divisionId);
        if (doctor != null && division != null) {
            if (!doctor.isHead() && division.getHead() == null) {
                doctor.setHead(true);
                division.setHead(doctor);
                divisionRepository.save(division);
                doctorRepository.save(doctor);
            }

        }

    }

    public void removeHead(Long divisionId, Long doctorId) {
        Doctor doctor = doctorRepository.getByPersonId(doctorId);
        Division division = divisionRepository.getAllByDivisionId(divisionId);
        if (doctor != null && division != null) {
            if (doctor.isHead() && division.getHead().equals(doctor)) {
                doctor.setHead(false);
                division.setHead(null);
                divisionRepository.save(division);
                doctorRepository.save(doctor);
            }

        }

    }

    public void addToStaff(Long divisionId, Long doctorId) {
        Doctor doctor = doctorRepository.getByPersonId(doctorId);
        Division division = divisionRepository.getAllByDivisionId(divisionId);
        if (doctor != null && division != null) {
            if (division.getStaff().isEmpty() && doctor.getDivision() == null) {
                doctor.setDivision(division);
                division.addToStaff(doctor);
                divisionRepository.save(division);
                doctorRepository.save(doctor);
            } else if (!division.getStaff().contains(doctor) && !doctor.getDivision().equals(division)) {
                doctor.setDivision(division);
                division.addToStaff(doctor);
                divisionRepository.save(division);
                doctorRepository.save(doctor);
            }
        }
    }

    public void removeFromStaff(Long divisionId, Long doctorId) {
        Doctor doctor = doctorRepository.getByPersonId(doctorId);
        Division division = divisionRepository.getAllByDivisionId(divisionId);
        if (doctor != null && division != null) {
            if (!division.getStaff().isEmpty() && division.getStaff().contains(doctor)
                    && doctor.getDivision() != null && doctor.getDivision().equals(division)) {
                doctor.setDivision(null);
                division.removeFromStaff(doctor);
                divisionRepository.save(division);
                doctorRepository.save(doctor);
            }
        }
    }

    public Double getDivMean(Long divisionId) {
        Division division = divisionRepository.getAllByDivisionId(divisionId);
        if (division != null && !division.getStaff().isEmpty()) {
            int empCount = division.getStaff().size();
            double sum = division.getStaff().stream().mapToDouble(Doctor::getSalary).sum();
            return sum / empCount;
        }
        return 0.0;
    }


}
