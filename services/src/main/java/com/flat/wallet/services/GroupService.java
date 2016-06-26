package com.flat.wallet.services;

import com.flat.wallet.exceptions.EntityNotFoundException;
import com.flat.wallet.model.Group;
import com.flat.wallet.model.User;
import com.flat.wallet.repositories.GroupRepository;
import com.flat.wallet.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupService {

	private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

	@Autowired
	@Qualifier("userService")
	private SocialUserService userService;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShoppingListService shoppingListService;

	@Transactional(readOnly = true)
	public Group getGroupById(Long id) throws Exception {
		final Group group = groupRepository.findById(id);
		if (group == null) {
			throw new EntityNotFoundException(Group.class, id);
		}
		User user = userService.getCurrentUser();
		if (group.ifParticipant(user)) {
			return group;
		}
		throw new BadCredentialsException("You are not authorized to get this group");
	}

	public List<Group> getGroups() throws Exception {
		return getCurrentUser().getGroups();
	}

	public Group createNewGroup(String newGroupName) throws Exception {
		User currentUser = getCurrentUser();
		currentUser = userRepository.findById(currentUser.getId());
		Group group = new Group(currentUser);
		group.setName(newGroupName);
		group.addParticipant(currentUser);
		groupRepository.save(group);
		currentUser.getGroups().add(group);
		userService.updateUserDetails(currentUser);
		return group;
	}

	private User getCurrentUser() throws Exception {
		User currentUser = userService.getCurrentUser();
		if (currentUser == null) {
			logger.error("No user logged, aborting.");
			//// FIXME: 05.06.16 do something about exceptions
			throw new Exception("You are not permitted to view this site");
		}
		return userRepository.findById(currentUser.getId());
	}

	public void addParticipantToGroup(Long groupId, Long userId) throws Exception {
		Group group = getGroupById(groupId);
		if (group == null) {
			throw new EntityNotFoundException(Group.class, groupId);
		}

		User user = userService.loadUserByUserId(userId.toString());
		if (user == null) {
			throw new EntityNotFoundException(User.class, userId);
		}
		group.addParticipant(user);
	}

	public List<String> getGroupShoppingList(Long groupID) throws Exception {
		Group group = getGroupById(groupID);
		if (group == null) {
			throw new EntityNotFoundException(Group.class, groupID);
		}
		return group.getGroupShoppingList().getItemsList();

	}

	public void addItemToGroupShoppingList(Long groupId, String item) throws Exception {
		Group group = getGroupById(groupId);

		if (group == null) {
			throw new EntityNotFoundException(Group.class, groupId);
		}

		group.addItemToList(item);
	}

}
