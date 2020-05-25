package basic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static utils.ProjUtils.checkIfNull;

public class Pet {

    private static Map<Integer, Pet> extent = new TreeMap<>();

    private int id;
    private String name;
    private LocalDate birthday;

    private List<Owner> owners = new ArrayList<>();
    private Map<String, Owner> ownersQualif = new TreeMap<>();


    public Pet(int id, String name, LocalDate birthday) {
        checkIfNull(name);
        checkIfNull(birthday);
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        extent.put(id, this);

    }

    //basic
    public void addOwner(Owner newOwner) {
        if (!owners.contains(newOwner)) {
            owners.add(newOwner);
            newOwner.addPet(this);
        }
    }

    public void removeOwner(Owner owner) {
        if (owners.contains(owner)) {
            owners.remove(owner);
            owner.removePet(this);
        }
    }

    //qualified
    public void addOwnerQual(Owner newOwner) {
        if (!ownersQualif.containsKey(newOwner.getName())) {
            ownersQualif.put(newOwner.getName(), newOwner);
            newOwner.addPetQual(this);
        }
    }

    public void removeOwnerQual(Owner owner) {
        if (ownersQualif.containsKey(owner.getName())) {
            ownersQualif.remove(owner.getName(), owner);
            owner.removePetQual(this);
        }
    }

    public Owner findOwnerQual(String name) {
        if (!ownersQualif.containsKey(name)) {
            throw new IllegalArgumentException("Unable to find an owner:" + name);
        }
        return ownersQualif.get(name);
    }

    public static Map<Integer, Pet> getExtent() {
        return extent;
    }


    public String getOwnersNames(boolean isQualified) {
        StringBuilder sb = new StringBuilder();

        if (isQualified) {
            ownersQualif.forEach((k, v) -> sb.append(k + " "));
        } else {
            owners.stream().forEach(owner -> sb.append(owner.getName() + " "));
        }

        return sb.toString();
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
