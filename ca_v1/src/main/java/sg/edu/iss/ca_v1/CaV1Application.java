package sg.edu.iss.ca_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.r2dbc.core.DatabaseClient;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;

@SpringBootApplication
public class CaV1Application {

	public static void main(String[] args) {
		SpringApplication.run(CaV1Application.class, args);
		

	}

}
