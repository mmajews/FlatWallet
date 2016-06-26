package com.flat.wallet.rest.group;

import com.flat.wallet.model.Group;
import com.flat.wallet.model.User;
import com.flat.wallet.model.auth.UserRole;
import com.flat.wallet.repositories.GroupRepository;
import com.flat.wallet.rest.DefaultSpringRestTest;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class GroupIntegrationControllerTest extends DefaultSpringRestTest {

	@Autowired
	private GroupRepository groupRepository;

	private static String userToken = "";
	private Group group;

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

		group = new Group(user);
		group.addParticipant(user);
		group.setName("group");
		groupRepository.save(group);

		userToken = tokenAuthenticationService.getTokenForUser(user);
	}

	private void cleanDatabase() {
		userRepository.deleteAll();
		groupRepository.deleteAll();
		RestAssured.port = port;
	}

	@Test
	public void shouldCreateGroup() throws Exception {
		final String testUrl = String.format("/api/group/create?groupName=%s", "groupName");
		Group returnedGroup = given()
				.header("X-AUTH-TOKEN", userToken)
				.when()
				.post(testUrl)
				.as(Group.class);

		Group databaseGroup = groupRepository.getOne(returnedGroup.getId());
		assertThat(returnedGroup).isEqualToComparingFieldByField(returnedGroup);
	}

	@Test
	public void shouldReturnAllGroups() throws Exception {
		final String testUrl = "/api/group/all";
		List<Group> returnedGroups = Arrays.asList(given()
				.header("X-AUTH-TOKEN", userToken)
				.when()
				.get(testUrl)
				.as(Group[].class));

		assertThat(returnedGroups).hasSize(1).extracting("id").contains(group.getId());
	}

	@Test
	public void shouldGetGroupById() throws Exception {
		final String testUrl = String.format("/api/group/%d", group.getId());
		Group returnedGroup = given()
				.header("X-AUTH-TOKEN", userToken)
				.when()
				.get(testUrl)
				.as(Group.class);

		assertThat(returnedGroup).isEqualToComparingFieldByField(returnedGroup);
	}

	@Test
	public void shouldReturnNotFound() throws Exception {
		final String testUrl = String.format("/api/group/%d", 1000);
		given()
				.header("X-AUTH-TOKEN", userToken)
				.when()
				.get(testUrl)
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}
}