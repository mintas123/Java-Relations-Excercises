package subset;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static utils.Utils.checkIfNull;

public class Student {

    // student belongs to a class, class consists of students
    // class president represents a class (only those who are in the same class)

    private static Set<Student> extent = new HashSet<>();

    // map with president - group association
    static Map<Student, Group> groupMap = new HashMap<>();


    private static int counter = 1000;
    private String id;
    private String name;
    private String lastName;

    //belongs to
    private Set<Group> groups = new HashSet<>();
    //leads
    private Set<Group> presidentOfGroupSet = new HashSet<>();


    public Student(String name, String lastName) {
        this.id = "S" + counter;
        checkIfNull(name);
        checkIfNull(lastName);

        counter++;
        extent.add(this);
    }

    public void addPresidentGroup(Group group) {
        if (!getPresidentOfGroupSet().contains(group) && getGroups().contains(group)) {
            presidentOfGroupSet.add(group);
            group.setPresident(this);
            Group.groupMap.put(this, group);
            groupMap.put(this, group);

        }
    }

    public void removePresidentGroup(Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Group doesnt exist!");
        }
        if (getPresidentOfGroupSet().contains(group)) {
            group.removePresident();
            this.presidentOfGroupSet.remove(group);
        }
    }

    public void addGroup(Group group) {
        if (!getGroups().contains(group)) {
            this.groups.add(group);
            group.addStudent(this);

        }
    }

    public void removeGroup(Group group) {
        if (getGroups().contains(group)) {
            if (group.getPresident().getId().equals(this.getId())) {
                removePresidentGroup(group);
            }

            this.groups.remove(group);
            group.removeStudent(this);

        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Set<Group> getGroups() {
        return Collections.unmodifiableSet(groups);
    }


    public Set<Group> getPresidentOfGroupSet() {
        return Collections.unmodifiableSet(presidentOfGroupSet);
    }

    public static Set<Student> getExtent() {
        return Collections.unmodifiableSet(extent);
    }

    public static Map<Student, Group> getGroupMap() {
        return Collections.unmodifiableMap(groupMap);
    }

//    @Override
//    public String toString() {
//        return "\nStudent{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", lastName='" + lastName + '\'' +
////                ", groups=" + groups +
//                ", presidentOfGroupSet=" + presidentOfGroupSet +
//                '}';
//    }
}
