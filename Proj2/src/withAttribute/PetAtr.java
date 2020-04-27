package withAttribute;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static utils.ProjUtils.checkIfNull;

public class PetAtr {

    private static Map<Integer, PetAtr> extent = new TreeMap<>();

    private int id;
    private String name;
    private LocalDate birthday;

    private List<Integer> owners = new ArrayList<>();


    public PetAtr(int id, String name, LocalDate birthday, List<Integer> ownersId) {
        checkIfNull(name);
        checkIfNull(birthday);
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.owners = ownersId;
        extent.put(id, this);

    }

    public void addOwner(int newOwnerId) {
        if (!owners.contains(newOwnerId)) {

            owners.add(newOwnerId);

            OwnerAtr owner = OwnerAtr.findOwner(newOwnerId);
            owner.addPet(this.id);
        }

    }


    public void removeOwner(int ownerId) {
        if (owners.contains(ownerId)) {
            owners.remove(Integer.valueOf(ownerId));
            OwnerAtr owner = OwnerAtr.findOwner(ownerId);
            owner.removePetReference(this.id);
        }
    }

    public void removeOwnerReference(int ownerId) {
        owners.remove((Integer.valueOf(ownerId)));
    }

    public static Map<Integer, PetAtr> getExtent() {
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


    public static PetAtr findPet(int id) {
        return extent.get(id);
    }
}
