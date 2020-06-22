package pl.edu.pjatk.s16604.mas_FP;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.edu.pjatk.s16604.mas_FP.model.Doctor;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class MasFpApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasFpApplication.class, args);
	}


	@Bean
	public CommandLineRunner init() {
		return (args) -> {

			Doctor doctor = new Doctor("a","b","a","a","12345678910", LocalDate.of(19800, Month.MAY,26),false,5000,false);
			System.out.println(doctor.getSalary());

		};
	}
}
