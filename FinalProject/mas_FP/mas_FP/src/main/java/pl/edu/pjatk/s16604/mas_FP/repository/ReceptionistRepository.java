package pl.edu.pjatk.s16604.mas_FP.repository;

import pl.edu.pjatk.s16604.mas_FP.entity.Receptionist;
import pl.edu.pjatk.s16604.mas_FP.enums.ContractType;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ReceptionistRepository extends PersonBaseRepository<Receptionist> {

    Receptionist getAllByPersonId(long id);

    List<Receptionist> getAllByContractType(ContractType type);
}
