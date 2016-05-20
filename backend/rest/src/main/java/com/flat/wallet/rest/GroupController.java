package com.flat.wallet.rest;

import com.flat.wallet.exceptions.EntityNotFound;
import com.flat.wallet.model.Group;
import com.flat.wallet.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group/")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/user/{userId}/create", method = RequestMethod.POST)
	public Group createGroup(@PathVariable("userId") Long userId) throws EntityNotFound {
		return groupService.createNewGroup(userId);
	}

	@PreAuthorize("isAnonymous()")
	@RequestMapping(value = "{groupId}", method = RequestMethod.GET)
	public Group getGroupById(@PathVariable("groupId") Long groupId) throws EntityNotFound {
		return groupService.getGroupById(groupId);
	}
}
