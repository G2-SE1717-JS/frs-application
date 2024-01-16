package com.frs.application.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.frs.core.constants.enums.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {
    private Long id;
    private String email;
    private String username;
    private String password;
    private UserRole role;
    private boolean verified;
    private boolean status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
