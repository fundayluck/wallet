package com.sims.wallet.controller;

import com.sims.wallet.model.entity.User;
import com.sims.wallet.model.request.RegisterRequest;
import com.sims.wallet.model.response.CustomResponse;
import com.sims.wallet.model.response.GetUser;
import com.sims.wallet.model.response.RegisterResponse;
import com.sims.wallet.service.UserService;
import com.sims.wallet.util.constant.ApiPathConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiPathConstant.API + ApiPathConstant.VERSION)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(ApiPathConstant.PROFILE)
    public ResponseEntity<CustomResponse<?>> getProfile(Authentication authentication) {
        GetUser user = userService.getUser(authentication);


        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<>(200,"Success get balance", user));
    }
    @GetMapping(ApiPathConstant.BALANCE)
    public ResponseEntity<CustomResponse<?>> getBalance() {
        System.out.println("afan");
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<>(200,"Success get balance", 1000000));
    }
}
