import bag.Owner;
import bag.Pet;
import custom.Item;
import custom.ShoppingBasket;
import custom.ShoppingItem;
import for_an_attribute.Car;
import ordered.Tutorial;
import subset.Group;
import subset.Student;
import unique.BankAccount;
import xor.PRDepartment;
import xor.Role;
import xor.Soldier;
import xor.SpecialForces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        testForAttr();
//        testUnique();
//        testSubset();
//        testOrdered();
//        testBag();
//        testXOR();
        testCustom();
    }


    private static void testForAttr() {
        Car car = new Car("Jacob", "Mazda", "3", 2016);

        car.addInspection(LocalDate.of(2016, Month.MAY, 11));
        car.addInspection(LocalDate.of(2017, Month.MARCH, 23));
        car.addInspection(LocalDate.of(2018, Month.MARCH, 15));
        car.addInspection(LocalDate.of(2019, Month.MARCH, 15));


        System.out.println(car);

//        car.addInspection(null);
        car.addInspection();

    }

    private static void testUnique() {
        BankAccount account = new BankAccount("Jacob", 15000, LocalDate.now(), 1234567890L);
        BankAccount account2 = new BankAccount("Sophia", 9876, LocalDate.now(), 9999999999L);
//        BankAccount accountTest = new BankAccount("AAA", 9876, LocalDate.now(), 9999999999L);

        account.addCreditCard(12345L);
        account.addCreditCard(11111L);
        account2.addCreditCard(2222L);
//        account.removeCreditCard(2222L);

        System.out.println(BankAccount.getMapOfUnique());

    }

    private static void testSubset() {
        Student stud1 = new Student("AAA", "aaa");
        Student stud2 = new Student("bbb", "bbb");
        Student stud3 = new Student("ccc", "ccc");
        Student stud4 = new Student("ddd", "ddd");
        Student stud5 = new Student("eee", "eee");
        Student stud6 = new Student("fff", "fff");

        Group gr1 = new Group(123, "coolName");
        Group gr2 = new Group(133, "asdfas");
        Group gr3 = new Group(444, "erwqerw");

        stud1.addGroup(gr1);
        stud2.addGroup(gr1);
        stud3.addGroup(gr1);
        stud4.addGroup(gr1);
        stud5.addGroup(gr1);
        stud6.addGroup(gr1);

        System.out.println("GROUP 1 students:");
        System.out.println(gr1.getStudents());

        gr2.addStudent(stud1);
        gr2.addStudent(stud2);
        gr2.addStudent(stud3);
        gr2.addStudent(stud4);

        System.out.println(stud1.getGroups());
        stud1.addPresidentGroup(gr1);
        gr3.addStudent(stud6);
        gr3.setPresident(stud6);

//        gr3.setPresident(stud5);
        System.out.println("Map of presidents");
        Group.getGroupMap().forEach((student, group) -> System.out.println("President: " + student.getLastName() + " Group: " + group.getName()));

    }

    private static void testOrdered() {

        ordered.Student student1 = new ordered.Student("ZZZ", "asdf");
        ordered.Student student2 = new ordered.Student("DDD", "asdf");
        ordered.Student student3 = new ordered.Student("CCC", "asdf");
        ordered.Student student4 = new ordered.Student("PPP", "asdf");

        Tutorial tutorial = new Tutorial("MAS", LocalDateTime.now());

        tutorial.addAttendee(student1);
        tutorial.addAttendee(student2);
        tutorial.addAttendee(student3);
        tutorial.addAttendee(student4);
        System.out.println(tutorial.getAttendees());


    }

    private static void testBag() {

        Pet pet1 = new Pet(1, "Skrr", LocalDate.of(2016, Month.AUGUST, 11));
        Pet pet2 = new Pet(2, "Brrr", LocalDate.of(2013, Month.AUGUST, 11));
        Pet pet3 = new Pet(3, "Krrr", LocalDate.of(2020, Month.MARCH, 11));


        Owner owner1 = new Owner(1, "Andrzej", LocalDate.of(1973, Month.MAY, 15), "123123123");
        Owner owner2 = new Owner(2, "Jarosław", LocalDate.of(2000, Month.MAY, 15), "123987456");

        owner1.addPet(pet1.getId());
        owner2.addPet(pet2.getId());
        owner2.addPet(pet3.getId());

        System.out.println(Pet.getExtent());
        owner2.removePet(pet3.getId());
        System.out.println(Pet.getExtent());
    }

    private static void testXOR() {

        Soldier soldier1 = new Soldier("AAA", LocalDate.now(), 12345);
        Soldier soldier2 = new Soldier("BBB", LocalDate.now(), 12345);
        Soldier soldier3 = new Soldier("CCC", LocalDate.now(), 12345);
        Soldier soldier4 = new Soldier("DDD", LocalDate.now(), 12345);
        Soldier soldier5 = new Soldier("EEE", LocalDate.now(), 12345);
        SpecialForces delta = new SpecialForces("Delta Force", "USA");
        PRDepartment department = new PRDepartment(123123, soldier5);

        soldier1.setRole(Role.SECRET);
        soldier2.setRole(Role.PUBLIC);
        soldier3.setRole(Role.SECRET);
        soldier4.setRole(Role.PUBLIC);
        soldier5.setRole(Role.PUBLIC);

        delta.addSoldier(soldier1);
        department.addWorkers(soldier2);
        delta.addSoldier(soldier3);
        department.addWorkers(soldier4);

        System.out.println("PUBLIC:");
        System.out.println(Soldier.getPublicSoldiers());
        System.out.println("SECRET:");
        System.out.println(Soldier.getSecretSoldiers());

        soldier1.setRole(Role.PUBLIC);
        System.out.println("SECRET v2: ");
        System.out.println(Soldier.getSecretSoldiers());

    }

    private static void testCustom() {
        Item item1 = new Item("AS12", "Banana", "BananaRepublic", 3.99);
        Item item2 = new Item("AC51", "Apple", "Apple", 5.99);
        Item item3 = new Item("GP40", "Toilet Paper", "Velvet", 4.99);

        ShoppingItem bananas = new ShoppingItem(4, item1);
        ShoppingItem apples = new ShoppingItem(2, item2);
//        ShoppingItem tp = new ShoppingItem(15, item3);
        ShoppingItem tp = new ShoppingItem(5, item3);

        ShoppingBasket basket = new ShoppingBasket(LocalDate.now());
        basket.addItem(bananas);
        basket.addItem(apples);
        basket.addItem(tp);
        System.out.println(basket);
    }

}
