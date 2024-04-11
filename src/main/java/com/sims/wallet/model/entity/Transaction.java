package com.sims.wallet.model.entity;

import com.sims.wallet.util.constant.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "t_transaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    @Column(unique = true)
    private String invoice_number;
    @Enumerated(EnumType.STRING)
    private TransactionType transaction_type;
    private String description;
    private Double total_amount;
    private Timestamp created_on;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
