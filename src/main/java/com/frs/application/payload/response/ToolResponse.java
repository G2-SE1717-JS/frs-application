package com.frs.application.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToolResponse {
    private Long id;
    private String name;
    private String image;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
