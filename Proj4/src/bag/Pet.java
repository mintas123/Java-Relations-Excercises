package bag;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Utils.checkIfNull;


public class Pet {

    private static Map<Integer, Pet> extent = new HashMap<>();

    private int id;
    private String name;
    private LocalDate birthday;

    private List<Integer> owners = new ArrayList<>();


    public Pet(int id, String name, LocalDate birthday) {
        checkIfNull(name);
        checkIfNull(birthday);
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        extent.put(id, this);

    }

    public void addOwner(int newOwnerId) {
        if (!owners.contains(newOwnerId)) {
            owners.add(newOwnerId);
        }

    }

    public void removeOwner(int ownerId) {
        if (owners.contains(ownerId)) {
            owners.remove(Integer.valueOf(ownerId));
        }
    }

    public void removeOwnerReference(int ownerId) {
        owners.remove((Integer.valueOf(ownerId)));
        extent.remove((Integer.valueOf(ownerId)));
    }

    public static Map<Integer, Pet> getExtent() {
        return extent;
    }


    public List<Integer> getOwners() {
        return owners;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }


    public static Pet findPet(int id) {
        return extent.get(id);
    }
}
