package com.isft194.entregaComida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EnableJpaRepositories(basePackages = "com.isft194.entregaComida.repository")
@ComponentScan(basePackages = "com.isft194.entregaComida")
@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.isft194.entregaComida.model"})
public class EntregaComidaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntregaComidaApplication.class, args);
	}

}