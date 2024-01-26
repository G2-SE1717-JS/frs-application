package com.frs.application.dto;

import com.frs.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class FollowAccountDTO extends BaseDTO {
    private Long accountId;
    private Long followedAccountId;
}
