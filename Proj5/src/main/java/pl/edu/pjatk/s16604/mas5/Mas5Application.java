package pl.edu.pjatk.s16604.mas5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.edu.pjatk.s16604.mas5.model.Car;
import pl.edu.pjatk.s16604.mas5.model.Dealer;
import pl.edu.pjatk.s16604.mas5.model.Sedan;
import pl.edu.pjatk.s16604.mas5.model.Truck;
import pl.edu.pjatk.s16604.mas5.service.CarsService;
import pl.edu.pjatk.s16604.mas5.service.DealerService;
import pl.edu.pjatk.s16604.mas5.service.SedanService;
import pl.edu.pjatk.s16604.mas5.service.TruckService;

import java.util.List;

@SpringBootApplication
public class Mas5Application {

    public static void main(String[] args) {
        SpringApplication.run(Mas5Application.class, args);
    }

    @Bean
    public CommandLineRunner init(CarsService carsService, DealerService dealerService,
                                  SedanService sedanService, TruckService truckService) {
        return (args) -> {
            Sedan mazda3 = new Sedan("Mazda", "3", 180.0, 80000.0, 2017,
                    800, 7f, false, true);
            Sedan auris = new Sedan("Toyota", "Auris", 120.0, 70000.0, 2020,
                    700, 8f, false, false);

            Sedan bmw = new Sedan("BMW", "330", 240.0, 200000.0, 2019,
                    700, 5.4f, false, true);
            Truck hilux = new Truck("Toyota", "hilux", 200.0, 125000.0, 2020,
                    "Suspension X", 3500, false);
            Truck ram = new Truck("Dodge", "RAM", 350.0, 180000.0, 2018,
                    "Suspension Y", 5500, true);

            sedanService.persistSedan(mazda3);
            sedanService.persistSedan(auris);
            sedanService.persistSedan(bmw);
            truckService.persistTruck(hilux);
            truckService.persistTruck(ram);

            Dealer usedCars = new Dealer("1231231230", "Used Cars Co.", "Street 123/12", "171-181-123");
            Dealer toyotaDealer = new Dealer("1110001112", "Toyota Warsaw", "St 98/11c", "999-432-123");

            dealerService.persist(usedCars);
            dealerService.persist(toyotaDealer);

            auris.setDealer(toyotaDealer);
            hilux.setDealer(toyotaDealer);
            mazda3.setDealer(usedCars);
            ram.setDealer(usedCars);
            bmw.setDealer(usedCars);

            sedanService.persistSedan(bmw);
            sedanService.persistSedan(mazda3);
            sedanService.persistSedan(auris);
            truckService.persistTruck(hilux);
            truckService.persistTruck(ram);

            System.out.println("\n\n------CARS REPO------");
            List<Car> allByBrand = carsService.findAllByBrand("Toyota");
            System.out.println("\nOPERATION: Cars by brand:");
            allByBrand.forEach(System.out::println);

            System.out.println("\nOPERATION: Car by id:");
            Car car = carsService.getCarById(3L);
            System.out.println(car);

            System.out.println("\nOPERATION: All cars:");
            List<Car> cars = carsService.getCars();
            cars.forEach(System.out::println);

            System.out.println("\n\n------SEDAN REPO------");

            System.out.println("\nOPERATION: All sedans:");
            sedanService.getSedans().forEach(System.out::println);


            System.out.println("\nOPERATION: All sedans by Android:");
            System.out.println(sedanService.getSedansWithAndroidAuto());


            System.out.println("\n\n------DEALER REPO------");

            System.out.println("\nOPERATION: Dealer by name:");
            System.out.println(dealerService.getByName("Used Cars Co.").get(0));


            System.out.println("\nOPERATION: Dealer with id = 7:");
            Dealer dealerById = dealerService.getDealerById(7L);
            System.out.println(dealerById);


            System.out.println("\nOPERATION: Dealers fleet after delete bmw:");
            carsService.delete(bmw.getCarId());
            dealerService.getByName("Used Cars Co.").get(0).getFleet().forEach(System.out::println);

        };
    }

}
