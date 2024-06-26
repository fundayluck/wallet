package com.sims.wallet.repository;

import com.sims.wallet.model.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services, String> {
    Services findByServiceCode(String serviceCode);
}
