package com.flat.wallet.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.flat.wallet.model")
@ComponentScan(basePackages = { "com.flat.wallet.facebook", "com.flat.wallet.rest", "com.flat.wallet.app" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
