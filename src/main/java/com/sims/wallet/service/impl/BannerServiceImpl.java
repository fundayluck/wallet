package com.sims.wallet.service.impl;

import com.sims.wallet.model.entity.Banner;
import com.sims.wallet.repository.BannerRepository;
import com.sims.wallet.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    @Override
    public List<Banner> getBanner() {
        return bannerRepository.findAll();
    }
}
