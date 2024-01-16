package com.frs.application.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.frs.application.domain.Step;
import com.frs.application.domain.StepImg;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeResponse implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String ration;
    private String cookingTime;
    private List<Step> steps;
    private List<StepImg> stepImgs;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
