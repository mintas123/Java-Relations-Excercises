package multi_aspect_inheritance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Utils.Utils.checkIfNull;

public class PhysicalWorker extends Employee {
    private static List<PhysicalWorker> extentPhysical = new ArrayList<>();

    private String warehouseLoc;
    private boolean isForkliftCertified;

    public PhysicalWorker(String name, LocalDate startDate, String phone, int salary, ContractType contractType, String warehouseLoc, boolean isForkliftCertified) {
        super(name, startDate, phone, salary, contractType);
        setForkliftCertified(isForkliftCertified);
        setWarehouseLoc(warehouseLoc);
        extentPhysical.add(this);
    }

    public String getWarehouseLoc() {
        return warehouseLoc;
    }

    public void setWarehouseLoc(String warehouseLoc) {
        checkIfNull(warehouseLoc);
        this.warehouseLoc = warehouseLoc;
    }

    public boolean isForkliftCertified() {
        return isForkliftCertified;
    }

    public void setForkliftCertified(boolean forkliftCertified) {
        isForkliftCertified = forkliftCertified;
    }

    public static List<PhysicalWorker> findByLocation(String warehouseLoc) {
        checkIfNull(warehouseLoc);
        return extentPhysical.stream()
                .filter(physicalWorker -> physicalWorker.getWarehouseLoc().equals(warehouseLoc))
                .collect(Collectors.toList());
    }

    public static List<PhysicalWorker> findAllCertifiedStudents() {
        return extentPhysical.stream()
                .filter(physicalWorker -> physicalWorker.getContractType().equals(ContractType.MANDATE))
                .filter(PhysicalWorker::isForkliftCertified)
                .filter(Employee::isStudent)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "PhysicalWorker{ " +
                super.toString() +
                ", warehouseLoc='" + warehouseLoc + '\'' +
                ", isForkliftCertified=" + isForkliftCertified +
                '}';
    }
}
