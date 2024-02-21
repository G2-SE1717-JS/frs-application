package com.frs.application.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.frs.application.constants.enums.RecipeStatus;
import com.frs.application.domain.Step;
import com.frs.application.domain.StepImg;
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
public class RecipeResponse implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Long ration;
    private LocalTime cookingTime;
    private List<StepResponse> steps;
    private RecipeStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
