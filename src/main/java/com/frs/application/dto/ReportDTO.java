package com.frs.application.dto;

import com.frs.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class ReportDTO extends BaseDTO {
    private Long accountId;
    private Long recipeId;
    private String description;
    private String reportStatus;
    private String adminResponse;
    private LocalDateTime adminResponseDate;
}
