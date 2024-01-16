package com.frs.application.mapper;

import com.frs.application.domain.Step;
import com.frs.application.dto.StepDTO;
import org.springframework.stereotype.Service;

@Service
public class StepMapper {
    public StepDTO toDto(Step step) {
        if(step == null) {
            return null;
        }
        return StepDTO.builder()
                .id(step.getId())
                .recipeId(step.getRecipeId())
                .orderValue(step.getOrderValue())
                .description(step.getDescription())
                .createdDate(step.getCreatedDate())
                .lastModifiedDate(step.getLastModifiedDate())
                .isDeleted(step.isDeleted())
                .build();
    }
    public Step toEntity(StepDTO stepDTO) {
        if(stepDTO == null) {
            return null;
        }
        Step step = new Step();
        step.setId(stepDTO.getId());
        step.setRecipeId(stepDTO.getRecipeId());
        step.setOrderValue(stepDTO.getOrderValue());
        step.setDescription(stepDTO.getDescription());
        step.setCreatedDate(stepDTO.getCreatedDate());
        step.setLastModifiedDate(stepDTO.getLastModifiedDate());
        step.setDeleted(stepDTO.isDeleted());
        return step;
    }
}
