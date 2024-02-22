package com.frs.application.service;

import com.frs.application.payload.response.RecipeImgRespose;

import java.util.List;

public interface IRecipeImgService {
    List<RecipeImgRespose> getAllByRecipeId(Long recipeId);
}
