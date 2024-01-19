package com.frs.application.service.impl;

import com.frs.application.dto.StepDTO;
import com.frs.application.dto.StepImgDTO;
import com.frs.application.logic.IStepImgLogic;
import com.frs.application.logic.IStepLogic;
import com.frs.application.payload.request.step.StepCreateRequest;
import com.frs.application.payload.response.StepResponse;
import com.frs.application.service.IStepService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StepServiceImpl implements IStepService {
    private final IStepLogic stepLogic;
    private final IStepImgLogic stepImgLogic;

    @Override
    public StepResponse update(Long id, StepCreateRequest request) {
        StepDTO stepDTO = stepLogic.getById(id);
        stepImgLogic.deleteImageByStepId(stepDTO.getId());

        stepDTO = StepDTO.builder()
                .id(stepDTO.getId())
                .recipeId(stepDTO.getRecipeId())
                .description(request.getDescription())
                .orderValue(request.getOrderValue())
                .build();
        stepDTO = stepLogic.save(stepDTO);
        for(String img : request.getImages()){
            StepImgDTO stepImgDTO = StepImgDTO.builder()
                    .stepId(stepDTO.getId())
                    .image(img)
                    .build();
            stepImgLogic.save(stepImgDTO);
        }
        return StepResponse.builder()
                .description(stepDTO.getDescription())
                .orderValue(stepDTO.getOrderValue())
                .createdDate(stepDTO.getCreatedDate())
                .lastModifiedDate(stepDTO.getLastModifiedDate())
                .build();
    }
}
