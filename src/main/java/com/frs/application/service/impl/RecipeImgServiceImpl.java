package com.frs.application.service.impl;

import com.frs.application.dto.RecipeDTO;
import com.frs.application.dto.RecipeImgDTO;
import com.frs.application.logic.IRecipeImgLogic;
import com.frs.application.logic.IRecipeLogic;
import com.frs.application.payload.response.RecipeImgRespose;
import com.frs.application.service.IRecipeImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeImgServiceImpl implements IRecipeImgService {
    private final IRecipeImgLogic recipeImgLogic;
    private final IRecipeLogic recipeLogic;

    @Override
    public List<RecipeImgRespose> getAllByRecipeId(Long recipeId) {
        RecipeDTO recipeDTO = recipeLogic.getById(recipeId);
        List<RecipeImgDTO> recipeImgDTOS = recipeImgLogic.getAllByRecipeId(recipeId);
        return recipeImgDTOS.stream().map(recipeImgDTO -> RecipeImgRespose.builder()
                .id(recipeImgDTO.getId())
                .image(recipeImgDTO.getImage())
                .recipeId(recipeDTO.getId())
                .build()).collect(Collectors.toList());
    }
}