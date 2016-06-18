package com.flat.wallet.rest.user;

import com.flat.wallet.model.Group;
import com.flat.wallet.model.ShoppingList;
import com.flat.wallet.model.User;
import com.flat.wallet.rest.GroupController;
import com.flat.wallet.services.GroupService;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class GroupControllerTest {
	@Mock
	private GroupService groupService;

	@InjectMocks
	private static GroupController groupController = new GroupController();

	@BeforeClass
	public static void initialSetUp() throws Exception {
		RestAssuredMockMvc.standaloneSetup(groupController);
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldCreateGroup() throws Exception {

		//FIXME to be implemented
	}

	@Test
	public void shouldGetGroupById() throws Exception {
		User user = new User();
		Group expectedGroup = new Group(user);
		ShoppingList shoppingList = new ShoppingList(expectedGroup);
		when(groupService.getGroupById(10L)).thenReturn(expectedGroup);

		//FIXME to be implemented
	}

}