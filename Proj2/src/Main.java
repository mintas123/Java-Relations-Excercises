import basic_and_qualified.Owner;
import basic_and_qualified.Pet;
import composition.File;
import composition.Folder;
import withAttribute.OwnerAtr;
import withAttribute.PetAtr;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
//            testBasic();
//            testQualified();
//            testWithAttribute();
            testComposition();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testBasic() {
        Pet pet1 = new Pet(1, "Burek", LocalDate.of(2016, Month.AUGUST, 11));
        Pet pet2 = new Pet(2, "Mruczek", LocalDate.of(2013, Month.AUGUST, 11));
        Pet pet3 = new Pet(3, "Ćwirek", LocalDate.of(2020, Month.MARCH, 11));


        Owner owner1 = new Owner(1, "Piotr", LocalDate.of(1973, Month.MAY, 15), "123123123");
        Owner owner2 = new Owner(2, "Anna", LocalDate.of(2000, Month.MAY, 15), "123987456");

        owner1.addPet(pet1);
        pet2.addOwner(owner1);
        pet3.addOwner(owner2);

        pet2.addOwner(owner2);
        pet2.removeOwner(owner2);
        owner2.addPet(pet1);
        owner2.removePet(pet1);

        System.out.println("Pets:");
        Pet.getExtent().forEach((k, v) -> {
            System.out.println(v.getName() + ":");
            System.out.println(v.getOwnersNames(false));
            System.out.println("___");
        });

        System.out.println("\nOwners:\n");
        Owner.getExtent().forEach((k, v) -> {
            System.out.println(v.getName() + ":");
            System.out.println(v.getPetsNames(false));
            System.out.println("___");

        });
    }

    public static void testQualified() {
        Pet pet4 = new Pet(4, "Woof", LocalDate.of(2016, Month.AUGUST, 11));
        Pet pet5 = new Pet(5, "Purr", LocalDate.of(2013, Month.AUGUST, 11));
        Pet pet6 = new Pet(6, "Chirp", LocalDate.of(2020, Month.MARCH, 11));


        Owner owner3 = new Owner(3, "Jakub", LocalDate.of(1973, Month.MAY, 15), "123123123");
        Owner owner4 = new Owner(4, "Zofia", LocalDate.of(2000, Month.MAY, 15), "123987456");

        owner3.addPetQual(pet4);
        owner3.addPetQual(pet5);
        owner3.addPetQual(pet6);
        owner4.addPetQual(pet6);

        pet4.addOwnerQual(owner4);
        pet4.removeOwnerQual(owner4);


        Pet foundPet = owner3.findPetQual("Woof");
        System.out.println(foundPet.getName() + ":");
        System.out.println(foundPet.getOwnersNames(true));

        Owner foundOwner = pet6.findOwnerQual("Jakub");
        System.out.println(foundOwner.getName() + ":");
        System.out.println(foundOwner.getPetsNames(true));

    }

    public static void testWithAttribute() {

        List<Integer> list1 = new ArrayList<>(List.of(5, 6));
        List<Integer> list2 = new ArrayList<>(List.of(5));
        List<Integer> list3 = new ArrayList<>(List.of(7, 8));
        List<Integer> list4 = new ArrayList<>(List.of(8));


        PetAtr pet7 = new PetAtr(7, "Skrr", LocalDate.of(2016, Month.AUGUST, 11), list1);
        PetAtr pet8 = new PetAtr(8, "Brrr", LocalDate.of(2013, Month.AUGUST, 11), list2);
        PetAtr pet9 = new PetAtr(9, "Krrr", LocalDate.of(2020, Month.MARCH, 11), list2);


        OwnerAtr owner5 = new OwnerAtr(5, "Andrzej", LocalDate.of(1973, Month.MAY, 15), "123123123", list3);
        OwnerAtr owner6 = new OwnerAtr(6, "Jarosław", LocalDate.of(2000, Month.MAY, 15), "123987456", list4);


        owner5.addPet(9);
        pet9.addOwner(5);
//        owner5.removePet(5);
//        owner5.removePet(8);
//        pet7.removeOwner(1);
//        pet7.removeOwner(5);


        System.out.println(pet7.getOwners());
        System.out.println(pet8.getOwners());
        System.out.println(pet9.getOwners());
        System.out.println(owner5.getPets());
        System.out.println(owner6.getPets());

    }

    public static void testComposition() {
        Folder folder1 = new Folder("Projects", "School");
        Folder folder2 = new Folder("Recipies", "Cooking");

        File.createFile(folder1, "Proj2_MAS", LocalDate.of(2020, 04, 27));
        File.createFile(folder1, "Proj1_PRM", LocalDate.now());

        File pizza = File.createFile(folder2, "Best pizza", LocalDate.now());
        File brownie = File.createFile(folder2, "Amazing brownie", LocalDate.now());
        File mistake = File.createFile(folder2, "Important project", LocalDate.now());


        System.out.println(folder1);
        System.out.println(folder2);

//        folder1.addFile(brownie);
        folder2.removeFile(pizza);
        mistake.changeFolder(folder1.getName());

        System.out.println();
        System.out.println(folder1);
        System.out.println(folder2);

    }
}
