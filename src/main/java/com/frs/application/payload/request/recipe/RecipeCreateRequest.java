package com.frs.application.payload.request.recipe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.frs.application.constants.enums.RecipeStatus;
import com.frs.application.domain.Step;
import com.frs.application.domain.StepImg;
import com.frs.application.payload.request.step.StepCreateRequest;
import com.google.api.client.util.DateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class RecipeCreateRequest {
    private String title;
    private String description;
    private Long ration;
    @JsonProperty("cooking_time")
    private LocalTime cookingTime;
    private List<StepCreateRequest> steps;


}
