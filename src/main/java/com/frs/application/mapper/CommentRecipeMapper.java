package com.frs.application.mapper;

import com.frs.application.domain.CommentRecipe;
import com.frs.application.dto.CommentRecipeDTO;
import org.springframework.stereotype.Service;

@Service
public class CommentRecipeMapper {
    public CommentRecipeDTO toDto(CommentRecipe entity) {
        if (entity == null) return null;
        return CommentRecipeDTO.builder()
                .id(entity.getId())
                .recipeId(entity.getRecipeId())
                .accountId(entity.getAccountId())
                .parentId(entity.getParentId())
                .description(entity.getDescription())
                .isDeleted(entity.isDeleted())
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }
    public CommentRecipe toEntity(CommentRecipeDTO dto) {
        if (dto == null) return null;
        CommentRecipe entity = new CommentRecipe();
        entity.setId(dto.getId());
        entity.setRecipeId(dto.getRecipeId());
        entity.setAccountId(dto.getAccountId());
        entity.setParentId(dto.getParentId());
        entity.setDescription(dto.getDescription());
        entity.setDeleted(dto.isDeleted());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        return entity;
    }
}
