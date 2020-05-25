package with_attribute;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static utils.ProjUtils.checkIfNull;

public class Attendee {

    private static Set<Attendee> extent = new HashSet<>();
    private static int ID_GEN = 0;

    private int id;
    private String name;
    private Set<String> courses = new HashSet<>();
    private Set<Integer> meetings = new HashSet<>();

    public Attendee(String name) {
        setName(name);
        this.id = ID_GEN;
        ID_GEN++;
        extent.add(this);
    }

    public void addCourse(String courseName) {
        if (courses.contains(courseName)) {
            throw new IllegalArgumentException("Attendee already signed for this course");
        }
        if (courseName == null || courseName.isEmpty()) {
            throw new IllegalArgumentException("Null course");
        }
        courses.add(courseName);
    }

    public void removeCourse(String courseName) {
        if (!courses.contains(courseName)) {
            throw new IllegalArgumentException("Attendee not signed for this course");
        }
        if (courseName == null) {
            throw new IllegalArgumentException("Null course");
        }

        Meeting.getExtent().stream()
                .filter(meeting ->
                        meeting.getCourse().getName().equals(courseName)
                                && meeting.getAttendees().contains(this.id))
                .forEach(meeting -> this.removeMeeting(meeting.getId()));
        courses.remove(courseName);
    }

    public void addMeeting(Integer meetingId) {
        if (meetings.contains(meetingId)) {
            throw new IllegalArgumentException("Attendee already signed for this meeting");
        }
        if (meetingId == null) {
            throw new IllegalArgumentException("Null meeting");
        }

        Meeting meeting = Meeting.findById(meetingId);
        if (!courses.contains(meeting.getCourse().getName())) {
            throw new IllegalArgumentException("Attendee not signed for this course - cant sign for a meeting");
        }
        meeting.addAttendee(this.id);
        meetings.add(meetingId);
    }

    public void removeMeeting(Integer meetingId) {
        if (!meetings.contains(meetingId)) {
            throw new IllegalArgumentException("Attendee not signed for this meeting");
        }
        if (meetingId == null) {
            throw new IllegalArgumentException("Null meeting");
        }
        Meeting meeting = Meeting.findById(meetingId);
        if (!courses.contains(meeting.getCourse().getName())) {
            throw new IllegalArgumentException("Attendee not signed for this course - cant resign from a meeting");
        }
        meeting.removeAttendee(this.id);
        meetings.remove(meetingId);
    }

    public static Set<Attendee> getExtent() {
        return Collections.unmodifiableSet(extent);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    public Set<String> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    public Set<Integer> getMeetings() {
        return Collections.unmodifiableSet(meetings);
    }

}
