package com.sims.wallet.controller;

import com.sims.wallet.model.entity.Banner;
import com.sims.wallet.model.entity.Services;
import com.sims.wallet.model.response.CustomResponse;
import com.sims.wallet.model.response.GetUser;
import com.sims.wallet.service.BannerService;
import com.sims.wallet.service.ServicesService;
import com.sims.wallet.util.constant.ApiPathConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPathConstant.API + ApiPathConstant.VERSION)
@RequiredArgsConstructor
public class BannerController {
    private final BannerService bannerService;
    private final ServicesService servicesService;

    @GetMapping(ApiPathConstant.BANNER)
    public ResponseEntity<CustomResponse<?>> getBannerImages() {

        List<Banner> banners = bannerService.getBanner();


        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<>(200,"Success get Banner images", banners));
    }

    @GetMapping(ApiPathConstant.SERVICES)
    public ResponseEntity<CustomResponse<?>> getBanner() {

        List<Services> banners = servicesService.getServices();


        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<>(200,"Success get services", banners));
    }
}
