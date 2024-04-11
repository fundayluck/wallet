package com.sims.wallet.service.impl;

import com.sims.wallet.model.entity.Transaction;
import com.sims.wallet.model.entity.User;
import com.sims.wallet.model.request.RequestPayment;
import com.sims.wallet.model.request.RequestTopup;
import com.sims.wallet.repository.TransactionRepository;
import com.sims.wallet.repository.UserRepository;
import com.sims.wallet.service.TransactionService;
import com.sims.wallet.util.constant.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    @Override
    public Transaction createTransaction(Transaction transaction, Authentication authentication) {
        System.out.println("save records transaction");
        Optional<User> findUser = userRepository.findByEmail(authentication.getName());
            User result = User.builder()
                    .id(findUser.get().getId())
                    .email(findUser.get().getEmail())
                    .password(findUser.get().getPassword())
                    .firstName(findUser.get().getFirstName())
                    .lastName(findUser.get().getLastName())
                    .profilePicture(findUser.get().getProfilePicture())
                    .balance(findUser.get().getBalance() + transaction.getTotal_amount())
                    .build();
            transaction.setUser(result);
            transactionRepository.save(transaction);
            userRepository.saveAndFlush(result);
        return transaction;
    }

    @Override
    public List<Transaction> getAllTransaction(Pageable pageable, Authentication authentication) {
        Optional<User> findUser = userRepository.findByEmail(authentication.getName());
        if (findUser.isPresent()) {
            User getUser = new User();
            getUser.setLastName(findUser.get().getLastName());
            getUser.setId(findUser.get().getId());
            getUser.setFirstName(findUser.get().getFirstName());
            getUser.setEmail(findUser.get().getEmail());
            getUser.setProfilePicture(findUser.get().getProfilePicture());
            getUser.setPassword(findUser.get().getPassword());
            getUser.setBalance(findUser.get().getBalance());
            List<Transaction> transactions = transactionRepository.findAllByUser(pageable,getUser);
            return transactions;
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public void doTopup(RequestTopup requestTopup,Authentication authentication) {
        try {
            Transaction transaction = Transaction.builder()
                    .transaction_type(TransactionType.TOPUP)
                    .description("Top Up Balance")
                    .total_amount(requestTopup.getTop_up_amount())
                    .created_on(Date.valueOf(LocalDate.now()))
                    .build();
            createTransaction(transaction, authentication);
            System.out.println("do topup");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void doPayment(RequestPayment requestPayment,Authentication authentication) {
        System.out.println("do payment");
    }
}
