import abstract_polymorphic.Animal;
import abstract_polymorphic.Enums.FishType;
import abstract_polymorphic.Enums.FoodIntakeType;
import abstract_polymorphic.Fish;
import abstract_polymorphic.Mammal;
import abstract_polymorphic.Reptile;
import dynamic_inheritance.Employee;
import multi_inheritance.Enums.RacingType;
import multi_inheritance.Enums.RacingWorldType;
import multi_inheritance.GTAGame;
import multi_inheritance.Racing;
import multi_inheritance.Shooter;
import overlapping_inheritance.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) {
//        testAbstract();
//        testOverlapping();
//        testDynamicInheritance();
        testMultliInheritance();
    }

    public static void testAbstract() {

        Animal blub = new Fish("Common name", "Comoenae Namus", 2.0, FoodIntakeType.SCAVENGER, FishType.SALTWATER, 30);
        Animal panda = new Mammal("Giant Panda", "Ailuropoda melanoleuca", 90.0, FoodIntakeType.HERBIVORE, false, 130);
        Animal snek = new Reptile("Indian Python", "Python molurus", 35.0, FoodIntakeType.CARNIVORE, 80, false);

        System.out.println(snek);
        System.out.println(snek.getIncubationTime());

        System.out.println(panda);
        System.out.println(panda.getIncubationTime());

        System.out.println(blub);
        System.out.println(blub.getIncubationTime());


    }

    public static void testOverlapping() {
        EnumSet<User.UserRole> basicRoles = EnumSet.noneOf(User.UserRole.class);
        basicRoles.add(User.UserRole.BASIC);
        User basic = new User("boom420", LocalDate.now(), basicRoles);

        EnumSet<User.UserRole> modRoles = EnumSet.noneOf(User.UserRole.class);
        modRoles.add(User.UserRole.BASIC);
        modRoles.add(User.UserRole.MODERATOR);
        User mod = new User("guy", LocalDate.now(), modRoles);

        EnumSet<User.UserRole> adminRoles = EnumSet.noneOf(User.UserRole.class);
        adminRoles.add(User.UserRole.BASIC);
        adminRoles.add(User.UserRole.ADMIN);
        User admin = new User("admin", LocalDate.now(), adminRoles);
        System.out.println("Initial state:");
        System.out.println(basic);
        System.out.println(mod);
        System.out.println(admin);

        System.out.println("\nAfter changes:");

//        basic.giveWarningToUser(mod.getId());
        basic.reportUser(mod.getId());
        admin.promoteUser(basic.getId(), User.UserRole.MODERATOR);
        mod.giveWarningToUser(basic.getId());
        admin.banUser(basic.getId());

//        basic.banUser(mod.getId());
//        admin.promoteUser(mod.getId(), User.UserRole.MODERATOR);


        System.out.println(basic);
        System.out.println(mod);
        System.out.println(admin);

    }

    public static void testDynamicInheritance() {

        Employee worker1 = new Employee("Adam", LocalDate.of(2019, Month.MAY, 4), 6999.0, Employee.EmpRole.BASIC);
        Employee worker2manager = new Employee("Hilary", LocalDate.of(2020, Month.MAY, 1), 9999L, Employee.EmpRole.BASIC);
        Employee manager2worker = new Employee("Karen", LocalDate.of(2017, Month.MAY, 14), 15000L, Employee.EmpRole.MANAGER);

        System.out.println("Initial:");
        System.out.println(worker1);
        System.out.println(worker2manager);
        System.out.println(manager2worker);

//        worker1.giveHoliday(worker2manager.getId(), LocalDate.now(), LocalDate.of(2020, Month.MAY, 25));
        worker1.reportManager(manager2worker.getId());
        worker2manager.setManagerRole(LocalDate.now());
        manager2worker.setRegularRole();
//        worker2manager.reportManager(manager2worker.getId());

        System.out.println("\nAfter:");
        System.out.println(worker1);
        System.out.println(worker2manager);
        System.out.println(manager2worker);
    }

    public static void testMultliInheritance() {
        Shooter battlefieldV = new Shooter("Battlefield V", LocalDate.of(2018, Month.OCTOBER, 9), 3000000.0, 60, false);
        Racing shift2 = new Racing("NFS: Shift 2", LocalDate.of(2011, Month.MARCH, 29), 1000000.0, RacingType.SIMULATION, RacingWorldType.TRACKS);

        GTAGame gtaV = new GTAGame("GTA V", LocalDate.of(2013, Month.SEPTEMBER, 17), 265000000.0, 70, true, RacingType.ARCADE, RacingWorldType.SANDBOX);

        System.out.println(battlefieldV);
        System.out.println(shift2);
        System.out.println(gtaV);

        System.out.println(gtaV.getRacingType());
        System.out.println(gtaV.getWeaponCount());
    }
}
