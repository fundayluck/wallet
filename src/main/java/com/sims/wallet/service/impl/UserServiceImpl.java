package com.sims.wallet.service.impl;

import com.sims.wallet.model.entity.AppUser;
import com.sims.wallet.model.entity.User;
import com.sims.wallet.model.response.GetUser;
import com.sims.wallet.model.response.GetUserById;
import com.sims.wallet.repository.UserRepository;
import com.sims.wallet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    @Override
    public AppUser loadUserByUserId(String id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("invalid credential user"));
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    @Override
    public GetUserById getUserById(String id) {
        List<User> user = repository.findAll();
        List<GetUserById> result = new ArrayList<>();
        for (User us : user) {
            if(us.getId().equals(id)) {
               result.add(
                        GetUserById.builder()
                                .email(us.getEmail())
                                .build()
                );
            }
        }
        return result.get(0);
    }

    @Override
    public GetUser getUser(Authentication authentication) {
        Optional<User> user = repository.findByEmail(authentication.getName());

        if (user.isPresent()) {
            User actualUser = user.get();
            GetUser getUser = GetUser.builder()
                    .firstName(actualUser.getFirstName())
                    .lastName(actualUser.getLastName())
                    .profile_image(actualUser.getProfilePicture())
                    .email(actualUser.getEmail())
                    .build();
            return getUser;
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("invalid credential user"));
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
