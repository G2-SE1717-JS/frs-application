package com.frs.application.mapper;


import com.frs.application.domain.Tool;
import com.frs.application.dto.IngredientsDTO;
import com.frs.application.dto.ToolDTO;
import org.springframework.stereotype.Service;


@Service
public class ToolMapper {
    public ToolDTO toDto(Tool entity) {
        if (entity == null) return null;
        return ToolDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .isDeleted(entity.isDeleted())
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }
    public Tool toEntity(ToolDTO dto) {
        if (dto == null) return null;
        Tool entity = new Tool();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setDeleted(dto.isDeleted());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        return entity;
    }
}
