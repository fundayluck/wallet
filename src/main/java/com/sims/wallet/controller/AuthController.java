package com.sims.wallet.controller;

import com.sims.wallet.model.request.LoginRequest;
import com.sims.wallet.model.request.RegisterRequest;
import com.sims.wallet.model.response.CustomResponse;
import com.sims.wallet.model.response.LoginResponse;
import com.sims.wallet.model.response.RegisterResponse;
import com.sims.wallet.service.AuthService;
import com.sims.wallet.util.constant.ApiPathConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPathConstant.API + ApiPathConstant.VERSION + ApiPathConstant.AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(ApiPathConstant.REGISTER)
    public ResponseEntity<CustomResponse<?>> Register(@RequestBody RegisterRequest registerRequest) {
        try {
            RegisterResponse registerResponse = authService.register(registerRequest);
            System.out.println(registerResponse + "response");
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomResponse<>(200,"Success Created User!", registerResponse));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @PostMapping(ApiPathConstant.LOGIN)
    public ResponseEntity<CustomResponse<?>> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = authService.login(loginRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomResponse<>(200, "success login!", loginResponse));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomResponse<>(401, e.getMessage(), null));
        }
    }

}
