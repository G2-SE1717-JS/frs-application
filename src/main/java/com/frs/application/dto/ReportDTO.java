package com.frs.application.dto;

import com.frs.application.constants.enums.ReportStatus;
import com.frs.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
public class ReportDTO extends BaseDTO {
    private Long accountId;
    private Long recipeId;
    private String description;
    private ReportStatus status;
    private String adminResponse;
    private LocalDateTime adminResponseDate;
}
