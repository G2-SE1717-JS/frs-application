package com.frs.application.mapper;

import com.frs.application.domain.StepImg;
import com.frs.application.dto.StepImgDTO;
import org.springframework.stereotype.Service;

@Service
public class StepImgMapper {
    public StepImgDTO toDto(StepImg stepImg) {
        if(stepImg == null) {
            return null;
        }
        return StepImgDTO.builder()
                .id(stepImg.getId())
                .stepId(stepImg.getStepId())
                .image(stepImg.getImage())
                .createdDate(stepImg.getCreatedDate())
                .lastModifiedDate(stepImg.getLastModifiedDate())
                .isDeleted(stepImg.isDeleted())
                .build();
    }
    public StepImg toEntity(StepImgDTO stepImgDTO) {
        if(stepImgDTO == null) {
            return null;
        }
        StepImg stepImg = new StepImg();
        stepImg.setId(stepImgDTO.getId());
        stepImg.setStepId(stepImgDTO.getStepId());
        stepImg.setImage(stepImgDTO.getImage());
        stepImg.setCreatedDate(stepImgDTO.getCreatedDate());
        stepImg.setLastModifiedDate(stepImgDTO.getLastModifiedDate());
        stepImg.setDeleted(stepImgDTO.isDeleted());
        return stepImg;
    }
}
