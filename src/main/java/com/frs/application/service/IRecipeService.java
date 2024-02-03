package com.frs.application.service;

import com.frs.application.payload.request.recipe.RecipeCreateRequest;
import com.frs.application.payload.request.recipe.RecipeUpdateRequest;
import com.frs.application.payload.response.IngredientsResponse;
import com.frs.application.payload.response.RecipeResponse;

import java.util.List;

public interface IRecipeService {
    List<RecipeResponse> create(RecipeCreateRequest request,String remoteUser);
    List<RecipeResponse> getAll();
    RecipeResponse getById(Long id);
    RecipeResponse update(Long id, RecipeUpdateRequest request);
    List<RecipeResponse> getAllByAccountId(String remoteUser);
    public void delete(Long recipeId);

    RecipeResponse getRecipeDetails(Long id);

    List<IngredientsResponse> getIngredientOfRecipe(Long id);
}
