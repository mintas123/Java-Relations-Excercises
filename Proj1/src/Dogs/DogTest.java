package Dogs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DogTest {
    public static void main(String[] args) {

        List<LocalDate> dates1 = Arrays.asList(
                LocalDate.of(2016, Month.DECEMBER, 13),
                LocalDate.of(2019, Month.AUGUST, 22),
                LocalDate.of(2020, Month.MARCH, 12)
        );

        List<LocalDate> dates2 = Arrays.asList(
                LocalDate.of(2015, Month.DECEMBER, 15),
                LocalDate.of(2018, Month.AUGUST, 21),
                LocalDate.of(2019, Month.MARCH, 2)
        );

        Dog d1 = new Dog("Karbon", LocalDate.of(2015, Month.AUGUST, 12), Arrays.asList("Cockier Spaniel"));
        Dog d2 = new Dog("Luna", LocalDate.of(2015, Month.APRIL, 25), Arrays.asList("German Shepherd"), dates1);
        Dog d3 = new Dog("Scooby", LocalDate.of(2005, Month.JULY, 10), Arrays.asList("Dachshund", "Pitbull"), dates2, false);
        Dog d4 = new Dog("Krawat", LocalDate.of(2001, Month.JUNE, 9), Arrays.asList("Mutt"), new ArrayList<>());


        System.out.println("Extent in the beginning:");
        System.out.println(Dog.getExtent());
        System.out.println(Dog.getRaceSet());

        d1.addCheckup(LocalDate.now());
        d2.removeCheckup(dates1.get(2));
        d2.setSterilised(true);
        d3.removeRace("Dachshund");
        d3.setName("Scooby-Doo");


        System.out.println("\nDog2 details:");

        System.out.print(d2.toString());
        System.out.println("Age:" + d2.getAge());
        d2.bark();

        System.out.println("\nDog3 details:");
        System.out.println(d3.getName());
        System.out.println(d3.getBirthday());
        System.out.println(d3.getRaces());
        System.out.println(d3.getVetCheckups());
        System.out.println(d3.isSterilised());

        System.out.println("\nFind methods:");

        System.out.println("age 4 :\n" + Dog.findByAge(4));
        System.out.println("age 1 :\n" + Dog.findByAge(1));
        System.out.println("Race Cockier Spaniel:\n" + Dog.findByRace("Cockier Spaniel"));
        System.out.println("Race sde:\n" + Dog.findByRace("AAAA"));


//         incorrect dogs
//        Dog d5 = new Dog("Karbon", LocalDate.of(2015, Month.AUGUST, 12),null);
//        Dog d6 = new Dog("Karbon", LocalDate.of(2015, Month.AUGUST, 12),new ArrayList<>());
//        Dog d7 = new Dog("Luna", LocalDate.of(2015, Month.APRIL, 25), Arrays.asList("German Shepherd"),null);
//         illegal operations
//        d1.removeCheckup(dates2.get(1));
//        d2.removeRace("German Shepherd");
//        d3.removeRace("dsafasd");
//        d2.addRace(null);
//        d3.addCheckup(null);

        System.out.println("\nExtent in the end:");
        System.out.println(Dog.getExtent());
        System.out.println(Dog.getRaceSet());


        // serialization
        try {
            ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream("dog_data.dat")));
            Dog.writeExtent(oos);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            ObjectInputStream ois = new ObjectInputStream((new FileInputStream("dog_data.dat")));
            Dog.readExtent(ois);
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Dog.getExtent());


    }
}