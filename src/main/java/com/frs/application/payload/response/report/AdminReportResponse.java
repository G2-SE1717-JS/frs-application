package com.frs.application.payload.response.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.frs.application.constants.enums.ReportStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminReportResponse {
    private Long id;
    private Long accountId;
    private Long recipeId;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private ReportStatus status;
    private String adminResponse;
    private LocalDateTime adminResponseDate;
}
