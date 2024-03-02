package com.frs.application.dto;

import com.frs.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class VerificationDTO extends BaseDTO {
    private Long id;
    private String token;
    private String email;
    private LocalDateTime expiredTime;
}
