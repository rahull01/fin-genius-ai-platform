package com.finginuis.Ai_Advisory_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AiAdvisoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiAdvisoryServiceApplication.class, args);
	}

}
