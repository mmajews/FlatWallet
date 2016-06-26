package com.flat.wallet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "shoppinglists")
public class ShoppingList extends EntityWithId {

    @OneToOne(mappedBy = "groupShoppingList", optional = false)
    @NotNull
    @JsonIgnore
    private Group listOwningGroup;

    @OneToMany(mappedBy = "itemOwningShoppingList")
    @JsonIgnore
    private List<ListItem> itemsList = new ArrayList<>();

    public ShoppingList(Group listOwningGroup) {
        this.listOwningGroup = listOwningGroup;
    }

    public ShoppingList() {

    }

    public List<ListItem> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<ListItem> itemsList) {
        this.itemsList = itemsList;
    }

    public void addItem(ListItem item){
        itemsList.add(item);
    }

//    public boolean deleteItem(String item){
//        return itemsList.remove(item);
//    }

    public Group getListOwningGroup() {
        return listOwningGroup;
    }

    public void setListOwningGroup(Group listOwningGroup) {
        this.listOwningGroup = listOwningGroup;
    }

    public List<ListItem> getItemsToBeBought(){

        List<ListItem> toBeBought = itemsList.stream().filter(item -> item.isBought()).collect(Collectors.toList());

        return toBeBought;
    }

    public List<ListItem> getItemsAlreadyBought(){

        List<ListItem> alreadyBought = itemsList.stream().filter(item -> !item.isBought()).collect(Collectors.toList());

        return alreadyBought;
    }
}
