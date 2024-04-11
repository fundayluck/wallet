package com.sims.wallet.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "t_banner")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Banner {
    @Id
    private String banner_image;
    private String banner_name;
    private String description;
}
