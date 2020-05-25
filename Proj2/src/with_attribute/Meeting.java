package with_attribute;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Meeting {

    private static int ID_GEN = 0;
    private static Set<Meeting> extent = new HashSet<>();

    private int id;
    private Course course;
    private Set<Integer> attendees = new HashSet<>();
    private LocalDateTime time;

    public Meeting(Course course, LocalDateTime time) {
        setCourse(course);
        setTime(time);

        extent.add(this);
        id = ID_GEN;
        ID_GEN++;
    }

    public void addAttendee(int attendeeId) {
        if (!attendees.contains(attendeeId)) {
            attendees.add(attendeeId);
        }
    }

    public void removeAttendee(int attendeeId) {
        if (attendees.contains(attendeeId)) {
            attendees.remove(attendeeId);
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("null course");
        }
        this.course = course;
    }

    public Set<Integer> getAttendees() {
        return Collections.unmodifiableSet(attendees);
    }


    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException("null time!");
        }
        this.time = time;
    }

    public static Meeting findById(int id) {
        return extent.stream().filter(meeting -> meeting.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException("Not found!"));

    }

    public static Set<Meeting> getExtent() {
        return Collections.unmodifiableSet(extent);
    }
}
