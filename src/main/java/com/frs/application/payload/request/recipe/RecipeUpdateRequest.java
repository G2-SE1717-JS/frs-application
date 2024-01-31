package com.frs.application.payload.request.recipe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.frs.application.constants.enums.RecipeStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class RecipeUpdateRequest {
    private String title;
    private String description;
    private Long ration;
    private RecipeStatus status;
    @JsonProperty("cooking_time")
    private LocalTime cookingTime;
}
