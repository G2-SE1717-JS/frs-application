package com.frs.application.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.frs.application.constants.enums.ReportStatus;
import lombok.Builder;
import lombok.Data;

import java.io.IOException;
import java.time.LocalDateTime;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportResponse {
    private Long id;
    private Long accountId;
    private Long recipeId;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private ReportStatus reportStatus;
    private String adminResponse;
    private LocalDateTime adminResponseDate;
}
