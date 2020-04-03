package Dogs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Dog implements Serializable {

    private static List<Dog> extent = new ArrayList<>();
    private static Set<String> raceSet = new HashSet<>();

    private String name;
    private LocalDate birthday;
    private List<String> races = new ArrayList<>();
    private List<LocalDate> vetCheckups = new ArrayList<>();
    private boolean sterilised;


    // Serializable persistence

    public static void writeExtent(ObjectOutputStream oos) {
        try {
            oos.write(extent.size());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Whoops, extent size writing error");
        }
        extent.stream().forEach(dog -> dog.write(oos));
    }

    public static void readExtent(ObjectInputStream ois) {
        extent.clear();
        int count = 4;
//        try {
//            count = ois.readInt();
//        } catch (IOException io) {
//            io.printStackTrace();
//        }
        Dog dog = null;
        for (int i = 0; i < count; i++) {
            dog = new Dog();
            dog.read(ois);
        }
    }

    private void write(ObjectOutputStream objectOutputStream) {
        try {
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing object: \n" + this.toString());
        }
    }

    private void read(ObjectInputStream objectInputStream) {

        try {
            Dog serialized = (Dog) objectInputStream.readObject();
            this.name = serialized.name;
            this.birthday = serialized.birthday;
            this.races = serialized.races;
            this.vetCheckups = serialized.vetCheckups;
            this.sterilised = serialized.sterilised;
        } catch (IOException io) {
            io.printStackTrace();
            System.out.println("Error reading object");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
            System.out.println("Error parsing class");
        }

    }
    ///////////


    public Dog() {
    }

    public Dog(String name, LocalDate birthday, List<String> races) {
        super();
        this.name = name;
        this.birthday = birthday;
        if (races == null) {
            throw new IllegalArgumentException(" Races cannot be null");
        }
        if (races.size() < 1) {
            throw new IllegalArgumentException(" Race list must contain at least one entry");
        }
        for (String race : races) {
            addRace(race);
        }
        extent.add(this);
    }

    public Dog(String name, LocalDate birthday, List<String> races, List<LocalDate> vetCheckups) {
        this(name, birthday, races);
        if (vetCheckups == null) {
            extent.remove(this);
            throw new IllegalArgumentException("vetCheckups cannot be null");
        }
        for (LocalDate checkup : vetCheckups) {
            addCheckup(checkup);
        }
    }

    public Dog(String name, LocalDate birthday, List<String> races, List<LocalDate> vetCheckups, boolean sterilised) {
        this(name, birthday, races, vetCheckups);
        this.sterilised = sterilised;
    }


    public static List<Dog> getExtent() {
        return extent;
    }

    public static Set<String> getRaceSet() {
        return raceSet;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }


    public LocalDate getBirthday() {
        return birthday;
    }

    public List<String> getRaces() {
        return Collections.unmodifiableList(races);
    }

    public void addRace(String race) {
        checkIfNull(race);
        this.races.add(race);

        if (!raceSet.contains(race)) {
            raceSet.add(race);
        }
    }

    public void removeRace(String race) {
        if (this.races.size() <= 1) {
            throw new IllegalArgumentException("At least one race needed");
        }
        if (!this.races.contains(race)) {
            throw new IllegalArgumentException("Race not in the collection");
        }
        races.remove(race);

        List<Dog> dogs = findByRace(race);
        if (dogs.size() <= 1) {
            raceSet.remove(race);
        }
    }

    public List<LocalDate> getVetCheckups() {
        return Collections.unmodifiableList(vetCheckups);
    }

    public void addCheckup(LocalDate checkup) {
        checkIfNull(checkup);
        this.vetCheckups.add((checkup));
    }

    public void removeCheckup(LocalDate checkup) {
        if (!this.vetCheckups.contains(checkup)) {
            throw new IllegalArgumentException("Checkup not in the collection");
        }
        this.vetCheckups.remove(checkup);
    }


    public boolean isSterilised() {
        return sterilised;
    }

    public void setSterilised(boolean sterilised) {
        this.sterilised = sterilised;
    }


    public int getAge() {
        return calcAge(this.birthday);
    }


    private void checkIfNull(String param) {
        if (param == null) {
            throw new IllegalArgumentException("not null string required");
        }
    }

    private void checkIfNull(LocalDate param) {
        if (param == null) {
            throw new IllegalArgumentException("not null date required");
        }
    }

    public void bark() {
        System.out.println(this.getName() + ": woof!");
    }

    private static int calcAge(LocalDate date) {
        LocalDate today = LocalDate.now();
        return Period.between(date, today).getYears();
    }

    public static List<Dog> findByRace(String race) {
        return extent.stream().filter(
                dog -> dog.races.stream().anyMatch(
                        r -> r.equals(race))).collect(Collectors.toList());
    }


    public static List<Dog> findByAge(int age) {
        return extent.stream().filter(dog -> calcAge(dog.birthday) == age).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "{" +
                "NAME='" + name + '\'' +
                ", BDATE=" + birthday +
                ", RACE=" + races +
                ", CHECKUPS=" + vetCheckups +
                ", STERILE=" + sterilised +
                "}\n";
    }


}
