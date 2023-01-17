package net.transnet.lds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringJobSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJobSchedulerApplication.class, args);
	}

}
