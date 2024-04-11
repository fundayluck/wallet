package com.sims.wallet.service;

import com.sims.wallet.model.entity.Transaction;
import com.sims.wallet.model.request.RequestPayment;
import com.sims.wallet.model.request.RequestTopup;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;


import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction, Authentication authentication);
    List<Transaction> getAllTransaction(Pageable pageable, Authentication authentication);
    void doTopup(RequestTopup requestTopup,Authentication authentication);
    void doPayment(RequestPayment requestPayment,Authentication authentication);
}
