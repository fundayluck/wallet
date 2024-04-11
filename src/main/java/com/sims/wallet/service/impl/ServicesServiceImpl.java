package com.sims.wallet.service.impl;

import com.sims.wallet.model.entity.Services;
import com.sims.wallet.repository.ServicesRepository;
import com.sims.wallet.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {
    private final ServicesRepository servicesRepository;
    @Override
    public List<Services> getServices() {
        return servicesRepository.findAll();
    }

    @Override
    public Services getServiceByCode(String code) {
        return servicesRepository.findByServiceCode(code);
    }
}
