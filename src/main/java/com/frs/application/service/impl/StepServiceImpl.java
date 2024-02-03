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

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<StepResponse> getAllByRecipeId(Long id) {
        List<StepDTO> stepDTOS = stepLogic.findAllByRecipeId(id);
        List<StepImgDTO> stepImgDTOS = stepDTOS.stream().map(stepDTO ->
                        stepImgLogic.findAllByStepId(stepDTO.getId())).flatMap(List::stream)
                .collect(Collectors.toList());
        return stepDTOS.stream().map(stepDTO -> {
            List<String> imgs = stepImgDTOS.stream().filter(stepImgDTO ->
                    stepImgDTO.getStepId().equals(stepDTO.getId()))
                    .map(StepImgDTO::getImage).collect(Collectors.toList());
            return StepResponse.builder()
                    .orderValue(stepDTO.getOrderValue())
                    .description(stepDTO.getDescription())
                    .stepImgs(imgs)
                    .build();
        }).collect(Collectors.toList());
    }


}
