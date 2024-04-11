package com.sims.wallet.controller;

import com.sims.wallet.model.entity.Services;
import com.sims.wallet.model.entity.Transaction;
import com.sims.wallet.model.request.PageResponseWrap;
import com.sims.wallet.model.request.RequestTopup;
import com.sims.wallet.model.response.CustomResponse;
import com.sims.wallet.service.ServicesService;
import com.sims.wallet.service.TransactionService;
import com.sims.wallet.util.constant.ApiPathConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPathConstant.API + ApiPathConstant.VERSION)
@RequiredArgsConstructor
public class ServicesController {
    private final ServicesService servicesService;
    private final TransactionService transactionService;

    @GetMapping(ApiPathConstant.SERVICES)
    public ResponseEntity<CustomResponse<?>> getBanner() {
        System.out.println("get banner");
        List<Services> banners = servicesService.getServices();


        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<>(200,"Success get services", banners));
    }

    @GetMapping(ApiPathConstant.TRANSACTION + ApiPathConstant.HISTORY)
    public ResponseEntity<CustomResponse<?>> getAllTransaction(@RequestParam(name = "limit", defaultValue = "10") int limit, Authentication authentication) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Transaction> result = transactionService.getAllTransaction(pageable, authentication);

        PageResponseWrap<Transaction> response = PageResponseWrap.<Transaction>builder()
                .records(result)
                .offset(0) // You can set the offset if needed
                .limit(limit) // Convert the limit to string as required by PageResponseWrap
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>(200,"Success get transaction", response));
    }

    @PostMapping(ApiPathConstant.TOPUP)
    public ResponseEntity<CustomResponse<?>> doTopup(
            @RequestBody
            RequestTopup requestTopup,
            Authentication authentication
    ) {
        System.out.println(requestTopup.getTop_up_amount());
        System.out.println(authentication.getName());

        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>());
    }
}
