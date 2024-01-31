package com.frs.application.payload.request;

import com.frs.application.payload.request.userprofile.ProfileCreateRequest;
import com.frs.core.constants.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AccountCreateRequest {
    private String email;
    private String username;
    private String password;
    private UserRole role;
    private boolean verified;
    private boolean status;
    private ProfileCreateRequest profileCreateRequest;
}
