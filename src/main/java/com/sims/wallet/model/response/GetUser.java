package com.sims.wallet.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class GetUser {
    private String email;
    private String firstName;
    private String lastName;
    private String profile_image;
}
