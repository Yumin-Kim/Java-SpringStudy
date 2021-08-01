package tutorial.study.servlet_nolecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ServletNolectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletNolectureApplication.class, args);
	}

}
