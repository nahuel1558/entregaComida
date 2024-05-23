package com.isft194.entregaComida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

@EnableJpaRepositories(basePackages = "com.isft194.entregaComida.repository")
@ComponentScan(basePackages = "com.isft194.entregaComida.model")
@SpringBootApplication
public class EntregaComidaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntregaComidaApplication.class, args);
	}

}