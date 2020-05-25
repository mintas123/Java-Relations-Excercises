package with_attribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static utils.ProjUtils.checkIfNull;

public class Course {
    private static int ID_GEN = 0;
    private int id;
    private String name;
    private String topic;
    private int duration;

    private List<Integer> meetings = new ArrayList<>();

    public Course(String name, String topic, int duration) {
        setName(name);
        setTopic(topic);
        this.id = ID_GEN;
        this.duration = duration;
        ID_GEN++;
    }

    public void addMeeting(int meeting) {
        if (meetings.contains(meeting)) {
            throw new IllegalArgumentException("Meeting already in the system");
        }
        meetings.add(meeting);
    }

    public void removeMeeting(int meeting) {
        if (!meetings.contains(meeting)) {
            throw new IllegalArgumentException("Meeting not in the system");
        }
        meetings.remove(meeting);
    }

    public List<Integer> getMeetings() {
        return Collections.unmodifiableList(meetings);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        checkIfNull(topic);
        this.topic = topic;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
