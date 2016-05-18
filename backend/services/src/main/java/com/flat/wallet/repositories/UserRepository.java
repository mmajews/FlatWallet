package com.flat.wallet.repositories;

import com.flat.wallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findById(Long id);

	User findByProviderIdAndProviderUserId(String providerId, String providerUserId);
}
