package com.frs.application.payload.request.saveRecipe;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveRecipeCreateRequest {
    private Long recipeId;
    private Long accountId;
}
