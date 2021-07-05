package springcore.fastcamp.fastcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FastcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastcampApplication.class, args);
	}

}
