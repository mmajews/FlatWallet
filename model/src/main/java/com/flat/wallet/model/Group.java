package com.flat.wallet.model;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group extends EntityWithId {

	private static final Logger logger = LoggerFactory.getLogger(Group.class);

	@NotNull
	@OneToOne
	private User groupFounder;

	private String name;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "SHOPPING_LIST_ID", unique = true, nullable = false)
	@NotNull
	private ShoppingList groupShoppingList;

	@ManyToMany
	private List<User> groupParticipants = new ArrayList<>();

	public Group() {
	}

	public Group(User user) {
		groupFounder = user;
		groupShoppingList = new ShoppingList(this);
	}

	public void addParticipant(User user) {
		Preconditions.checkNotNull(user, "User cannot be null while adding to group");
		if (groupParticipants.contains(user)) {
			logger.warn("User with id: {} already is added to this group, adding aborted", user.getId());
		}
		groupParticipants.add(user);
	}

	public boolean ifParticipant(User user) {
		if (groupParticipants.contains(user) || groupFounder == user) {
			return true;
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getGroupFounder() {
		return groupFounder;
	}

	public List<User> getGroupParticipants() {
		return groupParticipants;
	}

	public ShoppingList getGroupShoppingList() {
		return groupShoppingList;
	}

	public void setGroupParticipants(List<User> groupParticipants) {
		this.groupParticipants = groupParticipants;
	}

	public void setGroupShoppingList(ShoppingList groupShoppingList) {
		this.groupShoppingList = groupShoppingList;
	}

	public void setGroupFounder(User groupFounder) {
		this.groupFounder = groupFounder;
	}

	public void addItemToList(String item) {
		this.groupShoppingList.addItem(item);
	}
}

