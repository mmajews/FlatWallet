package com.flat.wallet.services;

import com.flat.wallet.exceptions.EntityNotFound;
import com.flat.wallet.model.Group;
import com.flat.wallet.model.User;
import com.flat.wallet.repositories.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupService {

	private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

	@Autowired
	@Qualifier("userService")
	private SocialUserService userService;

	@Autowired
	private GroupRepository groupRepository;

	@Transactional(readOnly = true)
	public Group getGroupById(Long id) {
		return groupRepository.findById(id);
	}

	public void createNewGroup(Group group) {
		groupRepository.save(group);
	}

	public void addParticipantToGroup(Long groupId, Long userId) throws EntityNotFound {
		Group group = getGroupById(groupId);
		if (group == null) {
			throw new EntityNotFound(Group.class, groupId);
		}

		User user = userService.loadUserByUserId(userId.toString());
		if (user == null) {
			throw new EntityNotFound(User.class, userId);
		}
		group.addParticipant(user);
	}

}
