package com.sims.wallet.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "t_services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Services {
    @Id
    private String service_code;
    private String service_icon;
    private String service_name;
    private Double service_amount;
}
