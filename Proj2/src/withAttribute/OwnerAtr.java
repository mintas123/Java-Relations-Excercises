package withAttribute;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static utils.ProjUtils.checkIfNull;

public class OwnerAtr {

    private static Map<Integer, OwnerAtr> extent = new TreeMap<>();

    private int id;
    private String name;
    private LocalDate birthday;
    private String phone;

    private List<Integer> pets = new ArrayList<>();

    public OwnerAtr(int id, String name, LocalDate birthday, String phone, List<Integer> petsId) {

        checkIfNull(name);
        checkIfNull(birthday);
        this.id = id;
        this.name = name;

        LocalDate today = LocalDate.now();
        if (birthday.isAfter(today)) {
            throw new IllegalArgumentException("Cant be birth day in the future");
        }
        this.birthday = birthday;
        this.phone = phone;
        this.pets = petsId;
        extent.put(id, this);
    }


    public List<Integer> getPets() {
        return pets;
    }

    public void addPet(int petId) {
        if (!pets.contains(petId)) {
            pets.add(petId);

            PetAtr pet = PetAtr.findPet(petId);
            pet.addOwner(this.id);
        }
    }


    public void removePet(int petId) {
        if (pets.contains(petId)) {
            pets.remove(Integer.valueOf(petId));

            PetAtr pet = PetAtr.findPet(petId);
            pet.removeOwnerReference(this.id);
        }
    }

    public void removePetReference(int petId) {
        pets.remove((Integer.valueOf(petId)));
    }

    public static Map<Integer, OwnerAtr> getExtent() {
        return extent;
    }

    public static OwnerAtr findOwner(int id) {
        return extent.get(id);
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
