package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
@ComponentScan("com.flat.wallet.user")
public class Application extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/",
						"/login**",
						"/webjars/**",
						"/bower_components/**",
						"/assets/**",
						"/app/**",
						"/api/auth/isAuthenticated")
				.permitAll()
				.anyRequest()
				.authenticated();

		http.formLogin().defaultSuccessUrl("/", true);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
