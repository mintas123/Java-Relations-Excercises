package ordered;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import static utils.Utils.checkIfNull;

public class Tutorial {

    class NameComparator implements Comparator<Student> {

        @Override
        public int compare(Student e1, Student e2) {
            return e1.getName().compareTo(e2.getName());
        }
    }

    private String name;
    private LocalDateTime time;
    private TreeSet<Student> attendees = new TreeSet<>(new NameComparator());

    public Tutorial(String name, LocalDateTime time) {
        setName(name);
        setTime(time);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Set<Student> getAttendees() {
        return Collections.unmodifiableSet(attendees);
    }

    public void addAttendee(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Null student");
        }
        if (getAttendees().contains(student)) {
            throw new IllegalArgumentException("Already here");
        }
        attendees.add(student);
    }

    public void removeAttendee(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Null student");
        }
        if (!getAttendees().contains(student)) {
            throw new IllegalArgumentException("Not here");
        }
        attendees.remove(student);

    }
}
