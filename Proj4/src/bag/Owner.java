package bag;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static utils.Utils.checkIfNull;


public class Owner {

    private int id;
    private String name;
    private LocalDate birthday;
    private String phone;

    private List<Integer> pets = new ArrayList<>();

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
    }


    public List<Integer> getPets() {
        return pets;
    }

    public void addPet(int petId) {
        if (!pets.contains(petId)) {
            pets.add(petId);

            Pet pet = Pet.findPet(petId);
            pet.addOwner(this.id);
        }
    }


    public void removePet(int petId) {
        if (pets.contains(petId)) {
            pets.remove(Integer.valueOf(petId));

            Pet pet = Pet.findPet(petId);
            pet.removeOwnerReference(this.id);
        }
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
