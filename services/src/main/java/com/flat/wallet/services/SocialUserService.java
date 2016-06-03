package com.flat.wallet.services;

import com.flat.wallet.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.List;

public interface SocialUserService extends SocialUserDetailsService, UserDetailsService {
    void saveAndFlush(User user);

    List<User> findAll();
    User loadUserByConnectionKey(ConnectionKey connectionKey);
    User loadUserByUserId(String userId);
    User loadUserByUsername(String username);
    void updateUserDetails(User user);

    User getCurrentUser();
}
