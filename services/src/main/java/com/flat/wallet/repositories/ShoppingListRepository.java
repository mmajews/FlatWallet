package com.flat.wallet.repositories;

import com.flat.wallet.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    ShoppingList findById(Long id);

    //// FIXME: 15.06.2016 actually it is not group in ShoppingList, names matter
    //    ShoppingList findByGroup(Group group);

    //// FIXME: It is not possible i believe, to query in this matter
    //    ShoppingList findByGroupId(Long id);
}

