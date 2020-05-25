import basic.Owner;
import basic.Pet;
import composition.File;
import composition.ZipFolder;
import qualified.Employee;
import qualified.Project;
import with_attribute.Attendee;
import with_attribute.Course;
import with_attribute.Meeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    public static void main(String[] args) {

//            testBasic();
        testQualified();
        testWithAttribute();
//            testComposition();

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

        Project proj1 = new Project("Hydra", LocalDate.of(2021, Month.SEPTEMBER, 11));
        Project proj2 = new Project("Archangel", LocalDate.of(2022, Month.SEPTEMBER, 11));
        Project proj3 = new Project("Predator", LocalDate.of(2023, Month.SEPTEMBER, 11));
        Employee employee1 = new Employee("Daniel", "dan@gmail.com");
        Employee employee2 = new Employee("Robert", "rob@gmail.com");
        Employee employee3 = new Employee("Todd", "todd@gmail.com");

        employee1.addProjectQualif(proj1);

        employee1.addProjectQualif(proj2);
        employee2.addProjectQualif(proj2);

        employee3.addProjectQualif(proj3);
        employee1.addProjectQualif(proj3);
        employee2.addProjectQualif(proj3);

        System.out.println("Proj 2 members:");
        System.out.println(proj2.getWorkers());

        System.out.println("Extent:");
        System.out.println(Employee.getExtent());

        System.out.println(employee1.getProjects());
        System.out.println(employee1.findProject("0_HYDRA"));


    }

    public static void testWithAttribute() {

        Attendee andrzej = new Attendee("Andrzej");
        Attendee maciej = new Attendee("Maciej");
        Attendee daniel = new Attendee("Daniel");

        Course java = new Course("REST services in Java", "usage of blablabla in blablabla", 90);
        Course cSharp = new Course("Unity with C#", "blablablab", 90);
        Course cobol = new Course("COBOL for beginners", "sdfsa", 45);

        Meeting meeting1 = new Meeting(java, LocalDateTime.of(2020, Month.MAY, 26, 14, 0));
        Meeting meeting2 = new Meeting(java, LocalDateTime.of(2020, Month.MAY, 27, 14, 0));
        Meeting meeting3 = new Meeting(java, LocalDateTime.of(2020, Month.MAY, 28, 14, 0));


        Meeting meeting4 = new Meeting(cSharp, LocalDateTime.of(2020, Month.APRIL, 26, 15, 0));
        Meeting meeting5 = new Meeting(cSharp, LocalDateTime.of(2020, Month.APRIL, 27, 15, 0));
        Meeting meeting6 = new Meeting(cSharp, LocalDateTime.of(2020, Month.APRIL, 28, 15, 0));

        Meeting meeting7 = new Meeting(cobol, LocalDateTime.of(2020, Month.JUNE, 26, 16, 0));
        Meeting meeting8 = new Meeting(cobol, LocalDateTime.of(2020, Month.JUNE, 27, 16, 0));
        Meeting meeting9 = new Meeting(cobol, LocalDateTime.of(2020, Month.JUNE, 28, 16, 0));

        andrzej.addCourse(java.getName());

        maciej.addCourse(java.getName());
        maciej.addCourse(cSharp.getName());

        daniel.addCourse(java.getName());
        daniel.addCourse(cSharp.getName());
        daniel.addCourse(cobol.getName());

        andrzej.addMeeting(meeting1.getId());
        andrzej.addMeeting(meeting2.getId());
        andrzej.addMeeting(meeting3.getId());
//        andrzej.addMeeting(meeting4.getId());


        maciej.addMeeting(meeting3.getId());
        maciej.addMeeting(meeting4.getId());
        maciej.addMeeting(meeting5.getId());
        maciej.addMeeting(meeting6.getId());

        daniel.addMeeting(meeting2.getId());
        daniel.addMeeting(meeting4.getId());
        daniel.addMeeting(meeting7.getId());
        daniel.addMeeting(meeting8.getId());
        daniel.addMeeting(meeting9.getId());

        System.out.println("Daniel courses:");
        daniel.getCourses().forEach(s -> System.out.println(s));
        System.out.println("Daniel meetings:");
        daniel.getMeetings().forEach(integer -> System.out.println(Meeting.findById(integer).getTime()));
        daniel.removeCourse(cobol.getName());
        System.out.println("Daniel meetings after resign from 1 course:");
        daniel.getMeetings().forEach(integer -> System.out.println(Meeting.findById(integer).getTime()));


    }

    public static void testComposition() {
        ZipFolder zipFolder1 = new ZipFolder("Projects", "School");
        ZipFolder zipFolder2 = new ZipFolder("Recipies", "Cooking");

        File.createFile(zipFolder1, "Proj2_MAS", LocalDate.of(2020, 04, 27));
        File.createFile(zipFolder1, "Proj1_PRM", LocalDate.now());

        File pizza = File.createFile(zipFolder2, "Best pizza", LocalDate.now());


        System.out.println(zipFolder1);
        System.out.println(zipFolder2);

//        zipFolder1.addFile(brownie);
        zipFolder2.removeFile(pizza);

        System.out.println();
        System.out.println(zipFolder1);
        System.out.println(zipFolder2);

    }

}
