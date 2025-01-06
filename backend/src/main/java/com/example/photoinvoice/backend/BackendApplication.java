package com.example.photoinvoice.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
//public class BackendApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(BackendApplication.class, args);
//	}
//
//}
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.example.photoinvoice.backend.repositories")
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
