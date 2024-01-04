package com.frs.application.dto;

import com.frs.core.base.BaseDTO;
import com.frs.core.constants.enums.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class AccountDTO extends BaseDTO {
    private String email;
    private String username;
    private String password;
    private UserRole role;
    private boolean verified;
    private boolean status;
}
