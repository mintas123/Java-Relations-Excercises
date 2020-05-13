package ordered;

import static utils.Utils.checkIfNull;

public class Student {

    private static int counter = 1;
    private int id;
    private String name;
    private String email;


    public Student(String name, String email) {
        setName(name);
        setEmail(email);
        this.id = counter;

        counter++;
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\nStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
