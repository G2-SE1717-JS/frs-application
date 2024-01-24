package com.frs.application.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportResponse implements Serializable {
    private Long id;
    private Long accountId;
    private Long recipeId;
    private String description;
    private String reportStatus;
    private String adminResponse;
    private LocalDateTime adminResponseDate;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
