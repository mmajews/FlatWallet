package com.flat.wallet.repositories;

import com.flat.wallet.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
	Group findById(Long id);
}


