package com.sims.wallet.repository;

import com.sims.wallet.model.entity.Transaction;
import com.sims.wallet.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByUser(User user);

    List<Transaction> findAllByUser(Pageable pageable, User user);
}
