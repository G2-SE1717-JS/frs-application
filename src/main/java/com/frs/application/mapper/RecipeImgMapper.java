package com.frs.application.mapper;

import com.frs.application.domain.RecipeImg;
import com.frs.application.dto.RecipeImgDTO;
import org.springframework.stereotype.Service;

@Service
public class RecipeImgMapper {
    public RecipeImgDTO toDto(RecipeImg recipeImg) {
        if(recipeImg == null) {
            return null;
        }
        return RecipeImgDTO.builder()
                .id(recipeImg.getId())
                .recipeId(recipeImg.getRecipeId())
                .image(recipeImg.getImage())
                .createdDate(recipeImg.getCreatedDate())
                .lastModifiedDate(recipeImg.getLastModifiedDate())
                .isDeleted(recipeImg.isDeleted())
                .build();
    }

    public RecipeImg toEntity(RecipeImgDTO recipeImgDTO) {
        if(recipeImgDTO == null) {
            return null;
        }
        RecipeImg recipeImg = new RecipeImg();
        recipeImg.setId(recipeImgDTO.getId());
        recipeImg.setRecipeId(recipeImgDTO.getRecipeId());
        recipeImg.setImage(recipeImgDTO.getImage());
        recipeImg.setCreatedDate(recipeImgDTO.getCreatedDate());
        recipeImg.setLastModifiedDate(recipeImgDTO.getLastModifiedDate());
        recipeImg.setDeleted(recipeImgDTO.isDeleted());
        return recipeImg;
    }
}
