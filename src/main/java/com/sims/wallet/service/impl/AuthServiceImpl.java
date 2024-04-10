package com.sims.wallet.service.impl;

import com.sims.wallet.model.entity.AppUser;
import com.sims.wallet.model.entity.User;
import com.sims.wallet.model.request.LoginRequest;
import com.sims.wallet.model.request.RegisterRequest;
import com.sims.wallet.model.response.LoginResponse;
import com.sims.wallet.model.response.RegisterResponse;
import com.sims.wallet.repository.UserRepository;
import com.sims.wallet.security.JwtUtil;
import com.sims.wallet.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        try {
            String emailRegex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
            if (!registerRequest.getEmail().matches(emailRegex)) {
                throw new RuntimeException("Invalid Email format!");
            }
            if(
                    registerRequest.getEmail() == "" ||
                    registerRequest.getFirstName() == "" ||
                    registerRequest.getLastName() == "" ||
                    registerRequest.getPassword() == ""
            ) {
                throw new RuntimeException("fill all parameters!");
            }
            User user = User.builder()
                    .email(registerRequest.getEmail())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .firstName(registerRequest.getFirstName())
                    .lastName(registerRequest.getLastName())
                    .balance(0.0)
                    .build();
            userRepository.save(user);
            return RegisterResponse.builder()
                    .email(user.getEmail())
                    .build();
        }catch (DataIntegrityViolationException e) {
            throw new RuntimeException("User Already Exist!");
        }
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            ));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            AppUser appUser = (AppUser) authentication.getPrincipal();
            String token = jwtUtil.generateToken(appUser);

            return LoginResponse.builder()
                    .email(loginRequest.getEmail())
                    .token(token)
                    .build();
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password", e);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during login", e);
        }
    }
}
