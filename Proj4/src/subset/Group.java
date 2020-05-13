package subset;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.isNull;
import static utils.Utils.checkIfNull;

public class Group {

    public static Set<Group> extent = new HashSet<>();

    static Map<Student, Group> groupMap = new HashMap<>();
    private int id;
    private String name;
    //consists of
    private Set<Student> students = new HashSet<>();
    // is lead by
    private Student president;


    public Group(int id, String name) {
        setId(id);
        setName(name);
        extent.add(this);
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            this.students.add(student);
            student.addGroup(this);
        }
    }

    public void removeStudent(Student student) {
        if (students.contains(student)) {
            this.students.remove(student);
            student.removeGroup(this);
        }
    }

    public Student getPresident() {
        return president;
    }

    public void setPresident(Student president) {
        if (isNull(this.getPresident())) {
            if (president == null) {
                throw new IllegalArgumentException("Null president!");
            }
            if (!this.getStudents().contains(president)) {
                throw new IllegalArgumentException("Not a member of the group!");
            }
            this.president = president;
            president.addPresidentGroup(this);

            groupMap.put(president, this);
            Student.groupMap.put(president, this);
        }
    }

    public void removePresident() {
        getPresident().removePresidentGroup(this);
        president = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public static Map<Student, Group> getGroupMap() {
        return Collections.unmodifiableMap(groupMap);
    }

//    @Override
//    public String toString() {
//        return "\nGroup{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", president=" + president.getLastName() +
//                '}';
//    }
}
