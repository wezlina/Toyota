package com.productservice.serhathar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SerhatharApplication {

	public static void main(String[] args) {//eureka, cloud, openfeign eksik.

		SpringApplication.run(SerhatharApplication.class, args);
	}

}
