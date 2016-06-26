package com.flat.wallet.rest.group;

import com.flat.wallet.app.token.TokenAuthenticationService;
import com.flat.wallet.model.Group;
import com.flat.wallet.model.User;
import com.flat.wallet.model.auth.UserRole;
import com.flat.wallet.repositories.GroupRepository;
import com.flat.wallet.repositories.UserRepository;
import com.flat.wallet.rest.DefaultSpringRestTest;
import com.flat.wallet.rest.GroupController;
import com.flat.wallet.services.GroupService;
import com.flat.wallet.services.SocialUserService;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GroupIntegrationControllerTest extends DefaultSpringRestTest {

	@Autowired
	private GroupService groupService;

	@Autowired
	private SocialUserService socialUserService;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupController groupController;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	@Value("${local.server.port}")
	private int port;

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

		Group group = new Group(user);
		group.addParticipant(user);
		group.setName("group");
		groupRepository.save(group);

		userToken = tokenAuthenticationService.getTokenForUser(user);
		System.out.println();
	}

	private void cleanDatabase() {
		groupRepository.deleteAll();
		userRepository.deleteAll();
		RestAssured.port = port;
	}

	@Test
	public void shouldCreateGroup() throws Exception {
		final String testUrl = String.format("/api/group/create?groupName=%s", "groupName");
		Group returnedGroup = given().
				header("X-AUTH-TOKEN", userToken).
				when().
				post(testUrl).
				as(Group.class);

		Group databaseGroup = groupRepository.getOne(returnedGroup.getId());
		assertThat(returnedGroup).isEqualToComparingFieldByField(returnedGroup);
	}
}