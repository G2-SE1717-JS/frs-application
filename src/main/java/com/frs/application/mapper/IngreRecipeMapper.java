package com.frs.application.mapper;

import com.frs.application.domain.IngreRecipe;
import com.frs.application.dto.IngreRecipeDTO;
import org.springframework.stereotype.Service;

@Service
public class IngreRecipeMapper {
    public IngreRecipeDTO toDto(IngreRecipe ingreRecipe) {
        return IngreRecipeDTO.builder()
                .recipeId(ingreRecipe.getRecipe_id())
                .ingredientsId(ingreRecipe.getIngre_id())
                .build();
    }

}
