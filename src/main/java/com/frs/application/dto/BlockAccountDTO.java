package com.frs.application.dto;

import com.frs.core.base.BaseDTO;
import com.frs.core.constants.enums.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class BlockAccountDTO extends BaseDTO {
    private Long blockAccountId;
    private Long accountId;
}
