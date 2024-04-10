package com.sims.wallet.service;

import com.sims.wallet.model.request.LoginRequest;
import com.sims.wallet.model.request.RegisterRequest;
import com.sims.wallet.model.response.LoginResponse;
import com.sims.wallet.model.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);

}
