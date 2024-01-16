package com.frs.application.payload.request;

import com.frs.core.constants.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountUpdateRequest {
private String username;
private String password;
private UserRole role;
private boolean verified;
private boolean status;
}
