package qualified;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static utils.ProjUtils.checkIfNull;

public class Employee {
    private static Set<Employee> extent = new HashSet<>();
    private static int ID_GENERATOR = 0;

    private String identifier;
    private String name;
    private String email;

    private Map<String, Project> projectMap = new TreeMap<>();

    public Employee(String name, String email) {
        this.identifier = name.substring(0, 2).toUpperCase() + ID_GENERATOR;
        setName(name);
        setEmail(email);

        ID_GENERATOR++;
        extent.add(this);
    }

    public void addProjectQualif(Project project) {
        if (!projectMap.containsKey(project.getIdentifier())) {
            projectMap.put(project.getIdentifier(), project);
            project.addWorker(this);
        }
    }

    public void removeProjectQualif(Project project) {
        if (projectMap.containsKey(project.getIdentifier())) {
            projectMap.remove(project.getIdentifier(), project);
            project.removeWorker(this);
        }
    }

    public Project findProject(String identifier) {
        if (!projectMap.containsKey(identifier)) {
            throw new IllegalArgumentException("Employee not involved in project " + identifier);
        }
        return projectMap.get(identifier);
    }

    public String getProjects() {
        StringBuilder sb = new StringBuilder();
        projectMap.forEach((k, v) -> sb.append(k + " "));
        return sb.toString();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        checkIfNull(email);
        this.email = email;
    }

    public static Set<Employee> getExtent() {
        return Collections.unmodifiableSet(extent);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                "}\n";
    }
}
