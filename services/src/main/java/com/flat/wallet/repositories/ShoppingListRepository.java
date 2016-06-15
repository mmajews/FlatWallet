package com.flat.wallet.repositories;

import com.flat.wallet.model.Group;
import com.flat.wallet.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Marcin on 13.06.2016.
 */
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    ShoppingList findById(Long id);

    ShoppingList findByGroup(Group group);

    ShoppingList fingByGroupId(Long id);
}

