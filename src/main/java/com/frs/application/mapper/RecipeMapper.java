package com.frs.application.mapper;

import com.frs.application.domain.Recipe;
import com.frs.application.dto.RecipeDTO;
import lombok.Data;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service

public class RecipeMapper {
    public RecipeDTO toDto(Recipe recipe) {
        if(recipe == null) return null;
        return RecipeDTO.builder()
                .id(recipe.getId())
                .accountId(recipe.getAccountId())
                .title(recipe.getTitle())
                .description(recipe.getDescription())
                .ration(recipe.getRation())
                .cookingTime(recipe.getCookingTime())
                .isDeleted(recipe.isDeleted())
                .createdDate(recipe.getCreatedDate())
                .lastModifiedDate(recipe.getLastModifiedDate())
                .build();

    }
    public Recipe toEntity(RecipeDTO recipeDTO) {
        if(recipeDTO == null) return null;
        Recipe recipe = new Recipe();
        recipe.setId(recipeDTO.getId());
        recipe.setAccountId(recipeDTO.getAccountId());
        recipe.setTitle(recipeDTO.getTitle());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setRation(recipeDTO.getRation());
        recipe.setCookingTime(recipeDTO.getCookingTime());
        recipe.setDeleted(recipeDTO.isDeleted());
        recipe.setCreatedDate(recipeDTO.getCreatedDate());
        recipe.setLastModifiedDate(recipeDTO.getLastModifiedDate());
        return recipe;
    }
}
