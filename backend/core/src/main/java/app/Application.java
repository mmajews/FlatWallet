package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
		"com.flat.wallet.configuration",
		"com.flat.wallet.user" })
@EnableOAuth2Sso
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

}
