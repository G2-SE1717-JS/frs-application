package com.frs.application.service;

import com.frs.application.payload.response.SaveRecipeResponse;

import java.util.List;

public interface ISaveRecipeService {
    List<SaveRecipeResponse> getByAccountID(Long accountId);
    SaveRecipeResponse save(Long recipeId, String remoteUser);

    void delete(Long id);
}
