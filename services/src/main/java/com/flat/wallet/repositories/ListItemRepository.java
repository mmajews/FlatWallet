package com.flat.wallet.repositories;

import com.flat.wallet.model.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {

    ListItem findById(Long id);
}
