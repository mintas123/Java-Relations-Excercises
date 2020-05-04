package dynamic_inheritance;

import overlapping_inheritance.HolidayPeriod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static Utils.Utils.checkIfNull;

public class Employee implements IRegularEmp, IManager {

    public enum EmpRole {
        BASIC,
        MANAGER
    }

    private int id;
    private String name;
    private LocalDate startDate;
    private double salary;
    private Enum<EmpRole> role;

    private LocalDate promotionDate;

    private Set<HolidayPeriod> holidays = new HashSet<>();
    private List<LocalDate> reports = new ArrayList<>();


    private static int idCounter = 0;
    private static List<Employee> extent = new ArrayList<>();

    public Employee(String name, LocalDate startDate, double salary, Enum<EmpRole> role) {
        checkIfNull(name);
        checkIfNull(startDate);
        this.name = name;
        this.startDate = startDate;
        this.salary = salary;
        if (role == null) {
            throw new IllegalArgumentException("role cannot be null");
        }
        this.role = role;
        this.id = idCounter;

        idCounter++;
        extent.add(this);
    }

    public void setManagerRole(LocalDate date) {
        if (!getRole().equals(EmpRole.MANAGER)) {
            setRole(EmpRole.MANAGER);
            setPromotionDate(date);
        } else {
            throw new IllegalArgumentException("Role already set!");
        }
    }

    public void setRegularRole() {
        if (!getRole().equals(EmpRole.BASIC)) {
            setRole(EmpRole.BASIC);
            setPromotionDate(null);
        } else {
            throw new IllegalArgumentException("Role already set!");
        }
    }

    @Override
    public void giveHoliday(int empId, LocalDate from, LocalDate to) {
        if (role.equals(EmpRole.MANAGER)) {
            Employee employee = findEmployee(empId);
            employee.addHoliday(from, to);
        } else {
            throw new IllegalArgumentException("Access denied. Must be a manager!");
        }

    }

    @Override
    public void reportManager(int managerId) {
        if (role.equals(EmpRole.BASIC)) {
            Employee manager = findEmployee(managerId);
            if (manager.getRole().equals(EmpRole.MANAGER)) {
                manager.addReport();
            } else {
                throw new IllegalArgumentException("Cant report non manager person!");
            }
        } else {
            throw new IllegalArgumentException("Access denied. Must be a regular worker!");
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public double getSalary() {
        return salary;
    }

    public Enum<EmpRole> getRole() {
        return role;
    }

    public LocalDate getPromotionDate() {
        return promotionDate;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    private void setPromotionDate(LocalDate promotionDate) {
        this.promotionDate = promotionDate;
    }

    public void setSalary(double salary) {
        this.salary = salary;

    }

    private void setRole(Enum<EmpRole> role) {
        if (role == null) {
            throw new IllegalArgumentException("role cannot be null");
        }
        this.role = role;
    }

    public List<LocalDate> getReports() {
        return Collections.unmodifiableList(reports);
    }

    private void addReport() {
        reports.add(LocalDate.now());
    }

    public Set<HolidayPeriod> getHolidays() {
        return Collections.unmodifiableSet(holidays);
    }

    private void addHoliday(LocalDate start, LocalDate end) {
        HolidayPeriod holidayPeriod = new HolidayPeriod(start, end);
        holidays.add(holidayPeriod);
    }

    public static List<Employee> getExtent() {
        return Collections.unmodifiableList(extent);
    }

    private Employee findEmployee(int empId) {
        return getExtent().stream().filter(user -> user.id == empId).findFirst().get();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", salary=" + salary +
                ", role=" + role +
                ", promotionDate=" + promotionDate +
                ", holidays=" + holidays +
                ", reports=" + reports +
                '}';
    }
}
