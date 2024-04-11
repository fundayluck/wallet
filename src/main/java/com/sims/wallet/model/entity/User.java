package com.sims.wallet.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mst_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    private String profilePicture;
    @Column(nullable = false)
    private Double balance;

}
