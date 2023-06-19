package com.SchoolManagement.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SchoolManagementApplication{


	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementApplication.class, args);
	}

}
