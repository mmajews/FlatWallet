package com.flat.wallet.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "listitem")
public class ListItem extends EntityWithId{

    private String name;

    private boolean bought = false;

    @ManyToOne
    @JoinColumn(name = "SHOPPING_LIST_ID", unique = true, nullable = false)
    private ShoppingList itemOwningShoppingList;

    public ListItem(String name, ShoppingList shoppingList) {
        this.name = name;
        this.itemOwningShoppingList = shoppingList;
    }

    public void itemBought(){
        this.bought = true;
    }

    public boolean isBought(){
        return bought;
    }
}
