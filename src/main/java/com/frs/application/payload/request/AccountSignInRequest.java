package com.frs.application.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountSignInRequest {
    @NotBlank
    private String identifier;
    @NotBlank
    private String password;
}
