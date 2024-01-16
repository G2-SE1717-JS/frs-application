package com.frs.application.service.impl;

import com.frs.application.dto.AccountDTO;
import com.frs.application.dto.RecipeDTO;
import com.frs.application.dto.StepDTO;
import com.frs.application.dto.StepImgDTO;
import com.frs.application.logic.IAccountLogic;
import com.frs.application.logic.IRecipeLogic;
import com.frs.application.logic.IStepImgLogic;
import com.frs.application.logic.IStepLogic;
import com.frs.application.payload.request.recipe.RecipeCreateRequest;
import com.frs.application.payload.request.step.StepCreateRequest;
import com.frs.application.payload.response.RecipeResponse;
import com.frs.application.service.IRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
        for(StepCreateRequest stepCreateRequest : request.getSteps()){
            StepDTO stepDTO = StepDTO.builder()
                    .recipeId(recipeDTO.getId())
                    .description(stepCreateRequest.getDescription())
                    .orderValue(stepCreateRequest.getOrderValue())
                    .build();
            stepDTO = stepLogic.save(stepDTO);
            for(String img : stepCreateRequest.getImages()){
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
        return null;
    }

    @Override
    public RecipeResponse getById(Long id) {
        return null;
    }

    @Override
    public RecipeResponse update(Long id, RecipeCreateRequest request) {
        return null;
    }
}
