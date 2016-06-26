package com.flat.wallet.rest.group;

import com.flat.wallet.model.User;
import com.flat.wallet.model.auth.UserRole;
import com.flat.wallet.rest.DefaultSpringRestTest;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserIntegrationControllerTest extends DefaultSpringRestTest {

	private static String userToken = "";

	@Before
	public void setUp() throws Exception {
		cleanDatabase();

		User user = new User();
		user.setAccessToken("token");
		user.setExpires(999999999999999L);
		user.setProviderId("123");
		user.setProviderUserId("123");
		user.setUsername("user");
		user.grantRole(UserRole.ADMIN);
		user.grantRole(UserRole.USER);
		userRepository.save(user);

		userToken = tokenAuthenticationService.getTokenForUser(user);
	}

	private void cleanDatabase() {
		userRepository.deleteAll();
		RestAssured.port = port;
	}

	@Test
	public void shouldReturnCurrentUser() throws Exception {
		final String testUrl = "api/user/current";
		User returnedUser = given()
				.header("X-AUTH-TOKEN", userToken)
				.when()
				.get(testUrl)
				.as(User.class);

		assertThat(returnedUser).isEqualToComparingFieldByField(returnedUser);
	}

}
