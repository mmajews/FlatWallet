package com.flat.wallet.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.flat.wallet.model")
@EnableJpaRepositories(basePackages = "com.flat.wallet.repositories")
@ComponentScan(basePackages = { "com.flat.wallet.facebook", "com.flat.wallet.rest", "com.flat.wallet.app",
		"com.flat.wallet.repositories", "com.flat.wallet.services" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
