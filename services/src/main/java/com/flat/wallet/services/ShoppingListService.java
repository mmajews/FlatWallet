package com.flat.wallet.services;

import com.flat.wallet.model.ShoppingList;
import com.flat.wallet.repositories.GroupRepository;
import com.flat.wallet.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Marcin on 13.06.2016.
 */
@Service
@Transactional
public class ShoppingListService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    public ShoppingList createNewShoppingList() throws Exception {
        ShoppingList shoppingList = new ShoppingList();
        shoppingListRepository.save(shoppingList);

        return shoppingList;
    }

}
