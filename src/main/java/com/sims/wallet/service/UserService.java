package com.sims.wallet.service;

import com.sims.wallet.model.entity.AppUser;
import com.sims.wallet.model.response.GetUserById;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
    GetUserById getUserById(String id);
}
