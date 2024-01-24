package com.frs.application.service.impl;

import com.frs.application.dto.*;
import com.frs.application.logic.IAccountLogic;
import com.frs.application.logic.IRecipeLogic;
import com.frs.application.logic.IStepImgLogic;
import com.frs.application.logic.IStepLogic;
import com.frs.application.payload.request.recipe.RecipeCreateRequest;
import com.frs.application.payload.request.recipe.RecipeUpdateRequest;
import com.frs.application.payload.request.step.StepCreateRequest;
import com.frs.application.payload.response.RecipeResponse;
import com.frs.application.payload.response.StepResponse;
import com.frs.application.service.IRecipeService;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeServiceImpl implements IRecipeService {
    private final IRecipeLogic recipeLogic;
    private final IStepLogic stepLogic;
    private final IStepImgLogic stepImgLogic;
    private final IAccountLogic accountLogic;

    @Override
    public List<RecipeResponse> create(RecipeCreateRequest request, String remoteUser) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        RecipeDTO recipeDTO = RecipeDTO.builder()
                .accountId(accountDTO.getId())
                .title(request.getTitle())
                .ration(request.getRation())
                .cookingTime(request.getCookingTime())
                .description(request.getDescription())
                .build();
        recipeDTO = recipeLogic.save(recipeDTO);
        for (StepCreateRequest stepCreateRequest : request.getSteps()) {
            StepDTO stepDTO = StepDTO.builder()
                    .recipeId(recipeDTO.getId())
                    .description(stepCreateRequest.getDescription())
                    .orderValue(stepCreateRequest.getOrderValue())
                    .build();
            stepDTO = stepLogic.save(stepDTO);
            for (String img : stepCreateRequest.getImages()) {
                StepImgDTO stepImgDTO = StepImgDTO.builder()
                        .stepId(stepDTO.getId())
                        .image(img)
                        .build();
                stepImgLogic.save(stepImgDTO);
            }
        }
        return null;
    }

    @Override
    public List<RecipeResponse> getAll() {
        List<RecipeDTO> recipeDTOS = recipeLogic.findAll();
        return recipeDTOS.stream().map(recipeDTO -> {
            List<StepDTO> stepDTOS = stepLogic.findAllByRecipeId(recipeDTO.getId());// lấy tất cả các bước của công thức
            List<StepImgDTO> stepImgDTOS = stepDTOS.stream().map(stepDTO ->
                    stepImgLogic.findAllByStepId(stepDTO.getId())).flatMap(List::stream).collect(Collectors.toList());//lấy tất cả các ảnh của các bước
            return RecipeResponse.builder()
                    .id(recipeDTO.getId())
                    .title(recipeDTO.getTitle())
                    .description(recipeDTO.getDescription())
                    .ration(recipeDTO.getRation())
                    .cookingTime(recipeDTO.getCookingTime())
                    .createdDate(recipeDTO.getCreatedDate())
                    .lastModifiedDate(recipeDTO.getLastModifiedDate())
                    .steps(stepDTOS.stream().map(stepDTO -> {
                        List<String> images = stepImgDTOS.stream().filter(stepImgDTO -> Objects.equals(stepImgDTO
                                .getStepId(), stepDTO.getId())).map(StepImgDTO::getImage).collect(Collectors.toList());
                        return StepResponse.builder()
                                .description(stepDTO.getDescription())
                                .orderValue(stepDTO.getOrderValue())
                                .stepImgs(images)
                                .build();
                    }).collect(Collectors.toList()))
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public RecipeResponse getById(Long id) {
        RecipeDTO recipeDTO = recipeLogic.getById(id);
        List<StepDTO> stepDTOS = stepLogic.findAllByRecipeId(id);
        List<StepImgDTO> stepImgDTOS = stepDTOS.stream().map(stepDTO -> stepImgLogic.findAllByStepId(stepDTO.getId()))
                .flatMap(List::stream).collect(Collectors.toList());

        return RecipeResponse.builder()
                .id(recipeDTO.getId())
                .title(recipeDTO.getTitle())
                .description(recipeDTO.getDescription())
                .ration(recipeDTO.getRation())
                .cookingTime(recipeDTO.getCookingTime())
                .createdDate(recipeDTO.getCreatedDate())
                .lastModifiedDate(recipeDTO.getLastModifiedDate())
                .steps(stepDTOS.stream().map(stepDTO -> {
                    List<String> images = stepImgDTOS.stream().filter(stepImgDTO -> Objects.equals(stepImgDTO
                            .getStepId(), stepDTO.getId())).map(StepImgDTO::getImage).collect(Collectors.toList());
                    return StepResponse.builder()
                            .description(stepDTO.getDescription())
                            .orderValue(stepDTO.getOrderValue())
                            .stepImgs(images)
                            .build();
                }).collect(Collectors.toList()))
                .build();
    }

    @Override
    public RecipeResponse update(Long id, RecipeUpdateRequest request) {
        RecipeDTO recipeDTO = recipeLogic.getById(id);
        if (Objects.isNull(recipeDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("recipe.not.found"));
        }
        recipeDTO.setTitle(request.getTitle());
        recipeDTO.setDescription(request.getDescription());
        recipeDTO.setRation(request.getRation());
        recipeDTO.setCookingTime(request.getCookingTime());
        recipeDTO = recipeLogic.save(recipeDTO);
        return RecipeResponse.builder()
                .id(recipeDTO.getId())
                .title(recipeDTO.getTitle())
                .description(recipeDTO.getDescription())
                .ration(recipeDTO.getRation())
                .cookingTime(recipeDTO.getCookingTime())
                .createdDate(recipeDTO.getCreatedDate())
                .lastModifiedDate(recipeDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public List<RecipeResponse> getAllByAccountId(String remoteUser) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        List<RecipeDTO> recipeDTOS = recipeLogic.getAllByAccountId(accountDTO.getId());
        return recipeDTOS.stream().map(recipeDTO -> {
            List<StepDTO> stepDTOS = stepLogic.findAllByRecipeId(recipeDTO.getId());// lấy tất cả các bước của công thức
            List<StepImgDTO> stepImgDTOS = stepDTOS.stream().map(stepDTO ->
                            stepImgLogic.findAllByStepId(stepDTO.getId())).flatMap(List::stream)
                    .collect(Collectors.toList());//lấy tất cả các ảnh của các bước
            return RecipeResponse.builder()
                    .id(recipeDTO.getId())
                    .title(recipeDTO.getTitle())
                    .description(recipeDTO.getDescription())
                    .ration(recipeDTO.getRation())
                    .cookingTime(recipeDTO.getCookingTime())
                    .createdDate(recipeDTO.getCreatedDate())
                    .lastModifiedDate(recipeDTO.getLastModifiedDate())
                    .steps(stepDTOS.stream().map(stepDTO -> {
                        List<String> images = stepImgDTOS.stream().filter(stepImgDTO -> Objects.equals(
                                        stepImgDTO.getStepId(), stepDTO.getId())).map(StepImgDTO::getImage)
                                .collect(Collectors.toList());
                        return StepResponse.builder()
                                .description(stepDTO.getDescription())
                                .orderValue(stepDTO.getOrderValue())
                                .stepImgs(images)
                                .build();
                    }).collect(Collectors.toList()))
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(Long recipeId) {
        RecipeDTO recipeDTO = recipeLogic.getById(recipeId);
        if (Objects.isNull(recipeDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.service.not-existed"));
        }
        recipeDTO.setDeleted(true);
        recipeLogic.save(recipeDTO);
}

}
