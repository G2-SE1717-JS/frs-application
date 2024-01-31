package com.frs.application.dto;

import com.frs.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
public class UserProfileDTO extends BaseDTO {
    private Long accountId;
    private String fullName;
    private String biography;
    private String origin;
    private String profilePicture;
}
