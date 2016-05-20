package com.flat.wallet.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "wishlists")
public class Wishlist extends EntityWithId {

	@OneToMany
	private List<Wish> wishes;

	@OneToOne
	@NotNull
	private User createdBy;
}
