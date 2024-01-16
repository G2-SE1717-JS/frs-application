package com.frs.application.mapper;

import com.frs.application.domain.Ingredients;
import com.frs.application.dto.IngredientsDTO;
import org.springframework.stereotype.Service;

@Service
public class IngredientsMapper {

    public IngredientsDTO toDto(Ingredients entity) {
        if (entity == null) return null;
        return IngredientsDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .isDeleted(entity.isDeleted())
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }
    public Ingredients toEntity(IngredientsDTO dto) {
        if (dto == null) return null;
        Ingredients entity = new Ingredients();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setDeleted(dto.isDeleted());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        return entity;
    }
}
