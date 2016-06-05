package com.flat.wallet.rest;

import com.flat.wallet.model.Group;
import com.flat.wallet.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Group createGroup(@RequestParam(name = "groupName") String groupName) throws Exception {
		return groupService.createNewGroup(groupName);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Group> createGroup() throws Exception {
		return groupService.getGroups();
	}

	@PreAuthorize("isAnonymous()")
	@RequestMapping(value = "{groupId}", method = RequestMethod.GET)
	public Group getGroupById(@PathVariable("groupId") Long groupId) throws Exception {
		return groupService.getGroupById(groupId);
	}

}
