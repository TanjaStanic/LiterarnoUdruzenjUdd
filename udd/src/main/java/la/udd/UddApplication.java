package la.udd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@SpringBootApplication
public class UddApplication {

	public static void main(String[] args) {
		SpringApplication.run(UddApplication.class, args);
		System.out.println("Started!");
	}

}
