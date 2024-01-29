package com.frs.application.mapper;

import com.frs.application.domain.SaveRecipe;
import com.frs.application.dto.SaveRecipeDTO;
import org.springframework.stereotype.Service;

@Service
public class SaveRecipeMapper {

    public SaveRecipeDTO toDto(SaveRecipe entity) {
        if (entity == null) return null;
        return SaveRecipeDTO.builder()
                .id(entity.getId())
                .recipeId(entity.getRecipeId())
                .accountId(entity.getAccountId())
                .isDeleted(entity.isDeleted())
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }
    public SaveRecipe toEntity(SaveRecipeDTO dto) {
        if (dto == null) return null;
        SaveRecipe entity = new SaveRecipe();
        entity.setId(dto.getId());
        entity.setRecipeId(dto.getRecipeId());
        entity.setAccountId(dto.getAccountId());
        entity.setDeleted(dto.isDeleted());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        return entity;
    }
}