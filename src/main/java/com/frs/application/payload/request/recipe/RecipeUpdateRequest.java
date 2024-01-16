package com.frs.application.payload.request.recipe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class RecipeUpdateRequest {
    private String title;
    private String description;
    private Long ration;
    @JsonProperty("cooking_time")
    private LocalTime cookingTime;
}
