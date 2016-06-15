package com.flat.wallet.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 13.06.2016.
 */
@Entity
@Table(name = "shoppinglists")
public class ShoppingList extends EntityWithId {

    @NotNull
    @OneToOne(mappedBy="groupShoppingList")
    private Group listOwningGroup;

    private List<String> itemsList = new ArrayList<>();

    public ShoppingList(Group listOwningGroup) {
        this.listOwningGroup = listOwningGroup;
    }

    public ShoppingList(){

    }

    public List<String> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<String> itemsList) {
        this.itemsList = itemsList;
    }

    public void addItem(String item){
        itemsList.add(item);
    }

    public boolean deleteItem(String item){
        return itemsList.remove(item);
    }

    public Group getListOwningGroup() {
        return listOwningGroup;
    }

    public void setListOwningGroup(Group listOwningGroup) {
        this.listOwningGroup = listOwningGroup;
    }
}
