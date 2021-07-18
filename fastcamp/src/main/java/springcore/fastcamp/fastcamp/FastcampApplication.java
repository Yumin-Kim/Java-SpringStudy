package springcore.fastcamp.fastcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 시간  자동 변경이 가능하도록
@ServletComponentScan
public class FastcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastcampApplication.class, args);
	}

}
