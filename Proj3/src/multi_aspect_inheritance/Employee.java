package multi_aspect_inheritance;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Utils.Utils.checkIfNull;

public class Employee {
    private static List<Employee> extent = new ArrayList<>();
    private static int idCounter = 0;
    private int id;
    private String name;
    private LocalDate startDate;
    private String phone;
    private double salary;

    private ContractType contractType;

    // attributes of UoP :
    private int paidHolidayLeft;
    // attributes of UoZ:
    private boolean isStudent;

    public Employee(String name, LocalDate startDate, String phone, int salary, ContractType contractType) {
        checkIfNull(name);
        checkIfNull(startDate);
        checkIfNull(phone);
        this.id = idCounter;
        this.name = name;
        this.startDate = startDate;
        this.phone = phone;
        this.salary = salary;
        if (contractType == null) {
            throw new IllegalArgumentException("Employee must be on some kind of contract!");
        }
        this.contractType = contractType;
        idCounter++;
        extent.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        checkIfNull(startDate);
        this.startDate = startDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        checkIfNull(phone);
        this.phone = phone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public static List<Employee> getExtent() {
        return Collections.unmodifiableList(extent);
    }

    // only employment:
    public void addHolidays(LocalDate startDate, LocalDate endDate) {
        if (this.getContractType().equals(ContractType.EMPLOYMENT)) {
            int period = Period.between(startDate, endDate).getDays();
            if (getPaidHolidayLeft() - period < 0) {
                int missing = Math.abs(getPaidHolidayLeft() - period);
                throw new IllegalArgumentException("Not enough free days! Missing " + missing + "days of holiday");
            }
            setPaidHolidayLeft(getPaidHolidayLeft() - period);
        } else {
            throw new IllegalArgumentException("Only pernament workers can have holidays!");
        }
    }

    public int getPaidHolidayLeft() {
        if (!this.contractType.equals(ContractType.EMPLOYMENT)) {
            throw new IllegalArgumentException("Not a permanent worker!");
        }
        return paidHolidayLeft;
    }

    public void setPaidHolidayLeft(int paidHolidayLeft) {
        if (!this.contractType.equals(ContractType.EMPLOYMENT)) {
            throw new IllegalArgumentException("Not a permanent worker!");
        }
        this.paidHolidayLeft = paidHolidayLeft;
    }

    //only mandate:
    public boolean isStudent() {
        if (!contractType.equals(ContractType.MANDATE)) {
            throw new IllegalArgumentException("Not a mandate worker!");
        }
        return isStudent;
    }

    public void setStudent(boolean isStudentNow) {
        if (!contractType.equals(ContractType.MANDATE)) {
            throw new IllegalArgumentException("Not a mandate worker!");
        }
        if (!this.isStudent && isStudentNow) {
            setSalary(getSalary() * 1.1);
        }
        if (this.isStudent && !isStudentNow) {
            setSalary(getSalary() * 0.9);
        }
        isStudent = isStudentNow;
    }


    @Override
    public String toString() {
        if (this.getContractType().equals(ContractType.EMPLOYMENT)) {
            return "id=" + id +
                    ", name='" + name + '\'' +
                    ", startDate=" + startDate +
                    ", phone='" + phone + '\'' +
                    ", salary=" + salary +
                    ", contractType=" + contractType +
                    ", paidHolidayLeft=" + paidHolidayLeft;
        }
        return "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", contractType=" + contractType +
                ", isStudent=" + isStudent;
    }


}
