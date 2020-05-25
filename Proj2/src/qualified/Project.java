package qualified;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static utils.ProjUtils.checkIfNull;

public class Project {
    private static Set<Project> extent = new HashSet<>();
    private static int ID_GENERATOR = 0;

    private String identifier;
    private String name;
    private LocalDate deadline;
    private List<Employee> workers = new ArrayList<>();

    public Project(String name, LocalDate deadline) {
        setName(name);
        setDeadline(deadline);
        identifier = ID_GENERATOR + "_" + name.toUpperCase();

        ID_GENERATOR++;
        extent.add(this);
    }

    public void addWorker(Employee newWorker) {
        if (!workers.contains(newWorker)) {
            workers.add(newWorker);
        }
    }

    public void removeWorker(Employee removedWorker) {
        if (workers.contains(removedWorker)) {
            workers.remove(removedWorker);
        }
    }


    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        checkIfNull(deadline);
        this.deadline = deadline;
    }

    public List<Employee> getWorkers() {
        return Collections.unmodifiableList(workers);
    }

    public static Set<Project> getExtent() {
        return Collections.unmodifiableSet(extent);
    }

    @Override
    public String toString() {
        return "Project{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", deadline=" + deadline +
                "}\n";
    }
}
