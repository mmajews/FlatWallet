package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableOAuth2Sso
@ComponentScan(basePackages = { "com.flat.wallet.user", "app" })
public class Application extends WebSecurityConfigurerAdapter {

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private OAuth2ClientContext oauth2ClientContext;

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
				.authenticated()
				.and().formLogin().defaultSuccessUrl("/", true).loginPage("/login").permitAll()
				.and().logout().logoutSuccessUrl("/").permitAll()
				.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	}

	@Bean
	@ConfigurationProperties("facebook")
	ClientResources facebook() {
		return new ClientResources();
	}

	private Filter ssoFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		filters.add(ssoFilter(facebook(), "/login/facebook"));
		filter.setFilters(filters);
		return filter;
	}

	private Filter ssoFilter(ClientResources client, String path) {
		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
		OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(facebookTemplate);
		filter.setTokenServices(
				new UserInfoTokenServices(client.getResource().getUserInfoUri(), client.getClient().getClientId()));
		return filter;
	}

	class ClientResources {
		private OAuth2ProtectedResourceDetails client = new AuthorizationCodeResourceDetails();
		private ResourceServerProperties resource = new ResourceServerProperties();

		public OAuth2ProtectedResourceDetails getClient() {
			return client;
		}

		public ResourceServerProperties getResource() {
			return resource;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
