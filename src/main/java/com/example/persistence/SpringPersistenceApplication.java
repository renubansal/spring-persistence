package com.example.persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringPersistenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPersistenceApplication.class, args);
	}

}
