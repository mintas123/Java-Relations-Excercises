package basic_and_qualified;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static utils.ProjUtils.checkIfNull;

public class Owner {

    private static Map<Integer, Owner> extent = new TreeMap<>();

    private int id;
    private String name;
    private LocalDate birthday;
    private String phone;

    private List<Pet> pets = new ArrayList<>();
    private Map<String, Pet> petsQualif = new TreeMap<>();


    public Owner(int id, String name, LocalDate birthday, String phone) {

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
        extent.put(id, this);
    }


    public List<Pet> getPets() {
        return pets;
    }

    public String getPetsNames(boolean isQualified) {
        StringBuilder sb = new StringBuilder();
        if (isQualified) {
            petsQualif.forEach((k, v) -> sb.append(k + " "));

        } else {
            pets.stream().forEach(pet -> sb.append(pet.getName() + " "));
        }

        return sb.toString();
    }

    //basic
    public void addPet(Pet pet) {
        if (!pets.contains(pet)) {
            pets.add(pet);
            pet.addOwner(this);
        }
    }

    public void removePet(Pet pet) {
        if (pets.contains(pet)) {
            pets.remove(pet);
            pet.removeOwner(this);
        }
    }

    //qualified
    public void addPetQual(Pet pet) {
        if (!petsQualif.containsKey(pet.getName())) {
            petsQualif.put(pet.getName(), pet);
            pet.addOwnerQual(this);
        }
    }

    public void removePetQual(Pet pet) {
        if (petsQualif.containsKey(pet.getName())) {
            petsQualif.remove(pet.getName(), pet);
            pet.removeOwnerQual(this);
        }
    }

    public Pet findPetQual(String name) {
        if (!petsQualif.containsKey(name)) {
            throw new IllegalArgumentException("Unable to find a pet:" + name);
        }
        return petsQualif.get(name);
    }

    public static Map<Integer, Owner> getExtent() {
        return extent;
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

    public static Owner findOwner(int id) {
        return extent.get(id);
    }


}
