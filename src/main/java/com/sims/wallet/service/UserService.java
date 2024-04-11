package com.sims.wallet.service;

import com.sims.wallet.model.entity.AppUser;
import com.sims.wallet.model.entity.User;
import com.sims.wallet.model.request.UpdateUserRequest;
import com.sims.wallet.model.response.GetBalances;
import com.sims.wallet.model.response.GetUser;
import com.sims.wallet.model.response.GetUserById;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
    GetUserById getUserById(String id);
    GetUser getUser(Authentication authentication);
    GetBalances getBalance(Authentication authentication);
    User updateUser(UpdateUserRequest updateUserRequest,Authentication authentication);
}
