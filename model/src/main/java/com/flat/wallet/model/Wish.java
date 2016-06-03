package com.flat.wallet.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wish")
public class Wish extends EntityWithId {

	@ManyToOne
	private Wishlist parentWishlist;

	@OneToOne
	private User userThatCompleted;

	@OneToOne
	private User userThatRequested;

	@OneToOne
	private User userThatWantedToComplete;

	@OneToOne
	private Price price;

	private String description;

	private boolean isCompleted;
}
