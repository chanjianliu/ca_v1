package sg.edu.iss.ca_v1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CaV1Application {

	public static void main(String[] args) {
		SpringApplication.run(CaV1Application.class, args);
	}
	@Bean
	CommandLineRunner runner() {
		return args -> { 
			     
		};
	}
} 
