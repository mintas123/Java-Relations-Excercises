package multi_aspect_inheritance;

import java.time.LocalDate;

public class OfficeWorker extends Employee {

    private boolean privateOffice;
    private boolean hasChildren;

    public OfficeWorker(String name, LocalDate startDate, String phone, int salary, ContractType contractType, boolean privateOffice, boolean hasChildren) {
        super(name, startDate, phone, salary, contractType);
        setHasChildren(hasChildren);
        setPrivateOffice(privateOffice);
    }

    public boolean isPrivateOffice() {
        return privateOffice;
    }

    public void setPrivateOffice(boolean privateOffice) {
        this.privateOffice = privateOffice;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean canGetMaternity() {
        return (this.getContractType().equals(ContractType.EMPLOYMENT) && hasChildren);
    }

    @Override
    public String toString() {
        return "OfficeWorker{ " +
                super.toString() +
                ", privateOffice=" + privateOffice +
                ", hasChildren=" + hasChildren +
                '}';
    }
}
