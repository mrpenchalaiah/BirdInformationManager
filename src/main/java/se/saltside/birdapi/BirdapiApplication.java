package se.saltside.birdapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages="se.saltside")
@EnableMongoRepositories(basePackages="se.saltside")
public class BirdapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BirdapiApplication.class, args);
	}
}
