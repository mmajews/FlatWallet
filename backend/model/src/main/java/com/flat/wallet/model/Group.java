package com.flat.wallet.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group extends EntityWithId {

	@NotNull
	@OneToOne
	private User groupFounder;

	@OneToMany
	private List<User> groupParticipants;
}
