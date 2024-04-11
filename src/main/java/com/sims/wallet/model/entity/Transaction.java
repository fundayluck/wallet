package com.sims.wallet.model.entity;

import com.sims.wallet.util.constant.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "t_transaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Transaction {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "invoice_number", unique = true, nullable = false, columnDefinition = "VARCHAR(255)")
    private String invoice_number;
    @Enumerated(EnumType.STRING)
    private TransactionType transaction_type;
    private String description;
    private Double total_amount;
    private Date created_on;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
