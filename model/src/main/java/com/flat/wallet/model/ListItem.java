package com.flat.wallet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "listitem")
public class ListItem extends EntityWithId {

	private String name;

	private boolean bought = false;

	@ManyToOne(optional = false)
	@NotNull
	@JsonIgnore
	private ShoppingList itemOwningShoppingList;

	public ListItem(String name, ShoppingList shoppingList) {
		this.name = name;
		this.itemOwningShoppingList = shoppingList;
	}

	public void itemBought() {
		this.bought = true;
	}

	public boolean isBought() {
		return bought;
	}

	public ListItem() {
	}

	public ShoppingList getItemOwningShoppingList() {
		return itemOwningShoppingList;
	}

	public void setItemOwningShoppingList(ShoppingList itemOwningShoppingList) {
		this.itemOwningShoppingList = itemOwningShoppingList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBought(boolean bought) {
		this.bought = bought;
	}
}
