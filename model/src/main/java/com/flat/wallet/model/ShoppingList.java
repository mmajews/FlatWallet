package com.flat.wallet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "SHOPPING_LIST_ITEM_LIST_ID", nullable = false)
    @NotNull
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

    public void deleteItem(ListItem item) {
        itemsList.remove(item);
    }

    public void setItemAsBoughtById(Long Id){
        for (ListItem item : itemsList){
            if (item.getId() == Id) {
                item.itemBought();
                return;
            }
        }
    }

    public Group getListOwningGroup() {
        return listOwningGroup;
    }

    public void setListOwningGroup(Group listOwningGroup) {
        this.listOwningGroup = listOwningGroup;
    }

    public List<ListItem> getItemsToBeBought(){

        List<ListItem> list = new ArrayList<>();

        for(ListItem item : itemsList) if (!item.isBought()) list.add(item);

        return list;
    }

    public List<ListItem> getItemsAlreadyBought(){
        return itemsList.stream().filter(item -> item.isBought()).collect(Collectors.toList());
    }
}
