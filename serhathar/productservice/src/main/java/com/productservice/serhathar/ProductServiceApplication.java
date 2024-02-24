package com.productservice.serhathar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
public class ProductServiceApplication {

	public static void main(String[] args) {//eureka, cloud, openfeign eksik.

		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
