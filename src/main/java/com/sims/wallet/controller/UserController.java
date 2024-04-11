package com.sims.wallet.controller;


import com.sims.wallet.model.request.UpdateUserRequest;
import com.sims.wallet.model.response.CustomResponse;
import com.sims.wallet.model.response.GetBalances;
import com.sims.wallet.model.response.GetUser;
import com.sims.wallet.service.UserService;
import com.sims.wallet.util.constant.ApiPathConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(ApiPathConstant.API + ApiPathConstant.VERSION)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private static final String UPLOAD_FOLDER = "src/main/resources/static/api/v1/photo/";

    @GetMapping(ApiPathConstant.PROFILE)
    public ResponseEntity<CustomResponse<?>> getProfile(Authentication authentication) {
        GetUser user = userService.getUser(authentication);


        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<>(200,"Success get balance", user));
    }
    @GetMapping(ApiPathConstant.BALANCE)
    public ResponseEntity<CustomResponse<GetBalances>> getBalance(Authentication authentication) {
        GetBalances getBalances = userService.getBalance(authentication);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<>(200,"Success get balance", getBalances));
    }

    @PutMapping(ApiPathConstant.PROFILE + ApiPathConstant.UPDATE)
    public ResponseEntity<CustomResponse<?>> updateUser(
            @RequestBody
            UpdateUserRequest updateUserRequest,
             Authentication authentication
    ) {
        try {
            userService.updateUser(updateUserRequest, authentication);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>(200, "success", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>(400, e.getMessage(), null));
        }

    }

    @PutMapping(ApiPathConstant.PROFILE + ApiPathConstant.IMAGE)
    public ResponseEntity<CustomResponse<?>> updateImage(@RequestParam("file") MultipartFile file, Authentication authentication){
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>(400,"Please select a file to upload",null));
        }
        try {
            File directory = new File(UPLOAD_FOLDER);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            userService.updateUserImage(file, authentication);

            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>(200,"yesss",null));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage() + "Error");
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>(400,"Failed to upload file",null));
        }

    }
}
