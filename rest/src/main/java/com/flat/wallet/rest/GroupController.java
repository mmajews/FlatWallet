package com.flat.wallet.rest;

import com.flat.wallet.model.Group;
import com.flat.wallet.model.ListItem;
import com.flat.wallet.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@Autowired

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

	@RequestMapping(value = "{groupId}/shoppinglist", method = RequestMethod.GET)
    public List<ListItem> getGroupShoppingList(@PathVariable("groupId") Long groupId) throws Exception {
        return groupService.getGroupShoppingList(groupId);
    }

	@RequestMapping(value = "{groupId}/shoppinglist/toBeBought", method = RequestMethod.GET)
	public List<ListItem> getGroupShoppingListToBeBought(@PathVariable("groupId") Long groupId) throws Exception {
		return groupService.getGroupShoppingListToBeBought(groupId);
	}

	@RequestMapping(value = "{groupId}/shoppinglist/alreadyBought", method = RequestMethod.GET)
	public List<ListItem> getGroupShoppingListAlreadyBought(@PathVariable("groupId") Long groupId) throws Exception {
		return groupService.getGroupShoppingListAlreadyBought(groupId);
	}

    @RequestMapping(value = "{groupId}/addItemToList", method = RequestMethod.POST)
    public void addItemToShoppingList(@PathVariable("groupId") Long groupId, @RequestParam(name = "item") String item) throws Exception {
        groupService.addItemToGroupShoppingList(groupId, item);
    }

	@RequestMapping(value = "/setItemAsBought", method = RequestMethod.POST)
	public void setItemAsBought(@RequestParam(name = "itemId") Long itemId) throws Exception {
		groupService.setItemAsBought(itemId);
	}


}
