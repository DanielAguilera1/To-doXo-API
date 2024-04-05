package com.dnxo.todoxo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ToDoxoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoxoApplication.class, args);
	}

}