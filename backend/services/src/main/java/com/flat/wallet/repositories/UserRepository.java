package com.flat.wallet.repositories;

import com.flat.wallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAll();

	User findByUsername(String username);

	User findById(Long id);

	User findByProviderIdAndProviderUserId(String providerId, String providerUserId);
}
