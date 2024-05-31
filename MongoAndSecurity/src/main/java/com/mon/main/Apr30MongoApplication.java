package com.mon.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("com.mon")
@EntityScan("com.mon.entity")
@EnableMongoRepositories("com.mon.repository")
public class Apr30MongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Apr30MongoApplication.class, args);
	}

}
